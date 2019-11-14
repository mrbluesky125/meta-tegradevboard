SUMMARY = "Darknet: Open Source Neural Networks in C"
DESCRIPTION = "Darknet is an open source neural network framework written in C."
HOMEPAGE="http://pjreddie.com/darknet/"
SECTION = "libs"
LICENSE = "PD"
PR = "r0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=4714f70f7f315d04508e3fd63d9b9232"

SRC_URI = "git://github.com/AlexeyAB/darknet.git; \
           "

SRCREV = "d628e8eab7371f136cc05ae090b904eede7f9c55"



COMPATIBLE_MACHINE = "(tegra)"

# do_fetch[noexec] = "1"
# do_unpack[depends] += "${MLPREFIX}cuda-binaries:do_preconfigure dpkg-native:do_populate_sysroot"

S = "${WORKDIR}/git"

DEPENDS += "cudnn cuda-cudart opencv"

inherit pkgconfig cuda

CUDA_NVCC_ARCH_FLAGS ??= ""

def extract_sm(d):
    archflags = d.getVar('CUDA_NVCC_ARCH_FLAGS').split()
    for flag in archflags:
        parts = flag.split('=')
        if len(parts) == 2 and parts[0] == '--gpu-code':
            return parts[1].split('_')[1]
    return ''

B = "${S}"

CUDA_PATH = "/usr/local/cuda-${CUDA_VERSION}"
CC_FIRST = "${@d.getVar('CC').split()[0]}"
CC_REST = "${@' '.join(d.getVar('CC').split()[1:])}"
CFLAGS += "-I=${CUDA_PATH}/include"
EXTRA_NVCCFLAGS = "-I${STAGING_DIR_HOST}${CUDA_PATH}/include"

def filtered_ldflags(d):
    newflags = []
    for flag in d.getVar('LDFLAGS').split():
        if flag.startswith('-f'):
            continue
        if flag.startswith('-Wl,'):
            newflags.append(flag[4:])
        else:
            newflags.append(flag)
    return ' '.join(newflags)


#LINKFLAGS = "-L${STAGING_DIR_HOST}${CUDA_PATH}/lib ${TOOLCHAIN_OPTIONS} ${@filtered_ldflags(d)} -lstdc++"
#CUDA_PATH="${STAGING_DIR_NATIVE}${CUDA_PATH}" CCFLAGS="${CC_REST} ${CFLAGS}" LDFLAGS="${LINKFLAGS}"
EXTRA_OEMAKE = " \
   'CC=${CC}' 'CXX=${CXX}' \
   'CFLAGS= -I${STAGING_DIR_HOST}${CUDA_PATH}/include -I${S}/include -I${STAGING_DIR_HOST}/usr/include/' \
   'LDFLAGS=${LDFLAGS}' \
"
do_install() {
    install -d ${D}${bindir}
    install -d ${D}${libdir}
    install -d ${D}${datadir}/${PN}/data
    install -d ${D}${datadir}/${PN}/cfg
    install ${S}/darknet ${D}${bindir}
    install ${S}/libdarknet.so.* ${D}${libdir}
    ln -sr ${D}${libdir}/libdarknet.so.0.0.1 ${D}${libdir}/libdarknet.so.0
    ln -sr ${D}${libdir}/libdarknet.so.0 ${D}${libdir}/libdarknet.so

    # include subdirectories
    cp -r ${S}/data/* ${D}${datadir}/${PN}/data
    cp -r ${S}/cfg/* ${D}${datadir}/${PN}/cfg
}


#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#FILES_${PN} = "${bindir}"
#FILES_${PN}-dev = "${CUDA_PATH}"
#INSANE_SKIP_${PN}-dev = "staticdev"
#PACKAGE_ARCH = "${SOC_FAMILY_PKGARCH}"

