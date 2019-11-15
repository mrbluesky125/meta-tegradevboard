DESCRIPTION = "TensorFlow’s lightweight solution for mobile and embedded devices"
AUTHOR = "Google Inc. and Yuan Tang"
HOMEPAGE = "https://www.tensorflow.org/lite"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=64a34301f8e355f57ec992c2af3e5157"

MD5SUM_EIGEN = "c7dffca8a2dca6ed464c700ba84dc3c1"
SHA256SUM_EIGEN = "091d1a3124ea41ac2e70e30028365d78d43a1c617a26445aef15e140e4fab1dd"
MD5SUM_FARMHASH = "f039a65a7f62bdb6c4b4c8a732638d80"
SHA256SUM_FARMHASH = "6560547c63e4af82b0f202cb710ceabb3f21347a4b996db565a411da5b17aba0"
MD5SUM_GEMMLOWP = "09cf63d1d388a2753de3323684da70ed"
SHA256SUM_GEMMLOWP = "6678b484d929f2d0d3229d8ac4e3b815a950c86bb9f17851471d143f6d4f7834"
MD5SUM_GTEST = "16877098823401d1bf2ed7891d7dce36"
SHA256SUM_GTEST = "58a6f4277ca2bc8565222b3bbd58a177609e9c488e8a72649359ba51450db7d8"
MD5SUM_ABSLCPP = "7c9945f256d3733a8eade255afcbd072"
SHA256SUM_ABSLCPP = "7dd09690ae7ca4551de3111d4a86b75b23ec17445f273d3c42bdcdc1c7b02e4e"
MD5SUM_NEON2SSE = "11fb94c08a43f88a68a9edff317d82d0"
SHA256SUM_NEON2SSE = "70526166a054f64b254213e9dc227b5877a21b621161e9907931093cc30341dd"
MD5SUM_FLATBUFFER = "02c64880acb89dbd57eebacfd67200d8"
SHA256SUM_FLATBUFFER = "3f4a286642094f45b1b77228656fbd7ea123964f19502f9ecfd29933fd23a50b"
MD5SUM_FFT = "72dff55737b580d98008517269471b23"
SHA256SUM_FFT = "ada7e99087c4ed477bfdf11413f2ba8db8a840ba9bbf8ac94f4f3972e2a7cec9"
PR = "r5"
SRC_URI = "git://github.com/tensorflow/tensorflow.git;branch=r2.1;protocol=https \
    https://bitbucket.org/eigen/eigen/get/afc120bc03bd.tar.gz;md5sum=${MD5SUM_EIGEN};sha256sum=${SHA256SUM_EIGEN} \
    https://github.com/google/googletest/archive/release-1.8.0.tar.gz;md5sum=${MD5SUM_GTEST};sha256sum=${SHA256SUM_GTEST} \
    https://github.com/abseil/abseil-cpp/archive/48cd2c3f351ff188bc85684b84a91b6e6d17d896.tar.gz;md5sum=${MD5SUM_ABSLCPP};sha256sum=${SHA256SUM_ABSLCPP} \
    https://github.com/intel/ARM_NEON_2_x86_SSE/archive/master.zip;md5sum=${MD5SUM_NEON2SSE};sha256sum=${SHA256SUM_NEON2SSE} \
    https://storage.googleapis.com/mirror.tensorflow.org/github.com/google/farmhash/archive/816a4ae622e964763ca0862d9dbd19324a1eaf45.tar.gz;md5sum=${MD5SUM_FARMHASH};sha256sum=${SHA256SUM_FARMHASH} \
    https://storage.googleapis.com/mirror.tensorflow.org/github.com/google/flatbuffers/archive/v1.11.0.tar.gz;md5sum=${MD5SUM_FLATBUFFER};sha256sum=${SHA256SUM_FLATBUFFER} \
    https://storage.googleapis.com/mirror.tensorflow.org/github.com/google/gemmlowp/archive/12fed0cd7cfcd9e169bf1925bc3a7a58725fdcc3.zip;md5sum=${MD5SUM_GEMMLOWP};sha256sum=${SHA256SUM_GEMMLOWP} \
    https://storage.googleapis.com/mirror.tensorflow.org/www.kurims.kyoto-u.ac.jp/~ooura/fft2d.tgz;md5sum=${MD5SUM_FFT};sha256sum=${SHA256SUM_FFT}\
    file://tensorflow-lite.pc.in \
    "

SRCREV = "8959ed1596ab02b7c358cbd9ea5d882b66c4af7d"

S = "${WORKDIR}/git"

DEPENDS = "zlib"
TARGET_CFLAGS_remove = "-O2"
TARGET_CPPLAGS_remove = "-O2"
TARGET_CXXLAGS_remove = "-O2"
CCFLAGS_append = " -O3 -DNDEBUG -fPIC -DGEMMLOWP_ALLOW_SLOW_SCALAR_FALLBACK \
    -I${STAGING_INCDIR}"
CXXFLAGS_append = " -O3 -DNDEBUG -fPIC -DGEMMLOWP_ALLOW_SLOW_SCALAR_FALLBACK \
    -I${STAGING_INCDIR}"
LDFLAGS_remove = "-Wl,-O1"
CXXFLAGS_append = " -O3 -DNDEBUG -fPIC -DGEMMLOWP_ALLOW_SLOW_SCALAR_FALLBACK \
    -I${STAGING_INCDIR}"
LIBS = "-lstdc++ -lpthread -lm -lz -ldl -lrt"
BUILD_DEPS_DOWNLOAD_DIR_PREFIX = "${S}/tensorflow/lite/tools/make/downloads/"
BUILD_WITH_NNAPI = "false"
do_cp_downloaded_build_deps() {
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}eigen
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}gemmlowp
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}googletest
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}absl
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}farmhash
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}neon_2_sse
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}flatbuffers
    mkdir -p ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}fft2d

    cp -rf ${WORKDIR}/eigen-eigen-afc120bc03bd/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}eigen
    cp -rf ${WORKDIR}/gemmlowp-12fed0cd7cfcd9e169bf1925bc3a7a58725fdcc3/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}gemmlowp
    cp -rf ${WORKDIR}/googletest-release-1.8.0/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}googletest
    cp -rf ${WORKDIR}/abseil-cpp-48cd2c3f351ff188bc85684b84a91b6e6d17d896/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}absl
    cp -rf ${WORKDIR}/farmhash-816a4ae622e964763ca0862d9dbd19324a1eaf45/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}farmhash
    cp -rf ${WORKDIR}/ARM_NEON_2_x86_SSE-master/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}neon_2_sse
    cp -rf ${WORKDIR}/flatbuffers-1.11.0/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}flatbuffers
    cp -rf ${WORKDIR}/fft2d/* ${BUILD_DEPS_DOWNLOAD_DIR_PREFIX}fft2d
}
addtask do_cp_downloaded_build_deps after do_unpack before do_patch

EXTRA_OEMAKE = "\
    'CC=${CC}' \
    'CXX=${CXX}' \
    'CPP=${CPP}' \
    'CFLAGS=${CFLAGS}' \
    'CPPFLAGS=${CFLAGS}' \
    'CXXFLAGS=${CXXFLAGS}' \
    'AR=${AR}' \
    'LD=${LD}' \
    'LDFLAGS=${LDFLAGS}' \
    'LIBS=${LIBS}' \
    'TARGET=${TARGET_OS}' \
    'TARGET_ARCH=${TUNE_ARCH}' \
    "

do_configure() {
    oe_runmake -f tensorflow/lite/tools/make/Makefile clean
}

do_compile() {
    oe_runmake -f tensorflow/lite/tools/make/Makefile
}

do_install() {
    install -d ${D}${libdir}
    install -m 0644 ${S}/tensorflow/lite/tools/make/gen/${TARGET_OS}_${TUNE_ARCH}/lib/libtensorflow-lite.a ${D}${libdir}/
    install -d ${D}${includedir}/tensorflow/lite
    (find ${S}/tensorflow/lite -name '*.h' -print | tar --create --files-from -) | (cd ${D}${includedir}/tensorflow/lite && tar xvfp -)
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/tensorflow-lite.pc.in ${D}${libdir}/pkgconfig/tensorflow-lite.pc
    sed -i 's:@version@:${PV}:g
        s:@libdir@:${libdir}:g
        s:@includedir@:${includedir}:g' ${D}${libdir}/pkgconfig/tensorflow-lite.pc
    # flatbuffers
    install -d  ${D}${includedir}/flatbuffers
    install -m 0644 ${S}/tensorflow/lite/tools/make/downloads/flatbuffers/include/flatbuffers/*.h ${D}${includedir}/flatbuffers/
}

ALLOW_EMPTY_${PN} = "1"
