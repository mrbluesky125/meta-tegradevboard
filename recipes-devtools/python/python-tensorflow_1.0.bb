DESCRIPTION = "Adafruit BBIO"
HOMEPAGE = "https://github.com/adafruit/adafruit-beaglebone-io-python/"
SECTION = "devel/python"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI[md5sum] = "666aeb76736f51783b69fa8587b6a692"
SRC_URI[sha256sum] = "a79fcb04092afa2737a45ff81cfc74f4423d239bfe642159eb34c90233900157"


SRCNAME = "Tensorflow-gpu"	
SRC_URI = "https://raw.githubusercontent.com/PINTO0309/Tensorflow-bin/master/tensorflow-2.0.0-cp37-cp37m-linux_aarch64.whl"

DEPENDS += "python3-pip-native \
	python3-wheel-native \
	zip-native \
	"
inherit python3-dir
S = "${WORKDIR}/"

export PYTHON_BIN_PATH="${PYTHON}"
export PYTHON_LIB_PATH="${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages"


FILES_${PN} += "\                                                                                   
    /python3.7-packages \                                            
"

export STAGING_LIBDIR 
export STAGING_INCDIR 

do_install() {
    install -d ${D}/python3.7-packages/
    echo ${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages
    pip3 install ${S}tensorflow_gpu-2.0.0-cp37-cp37m-manylinux2010_x86_64.whl
    zip ${D}/python3.7-packages/tensorflow.zip -r ${STAGING_LIBDIR_NATIVE}/${PYTHON_DIR}/site-packages
    #cp -R ${STAGING_LIBDIR_NATIVE}/${PYTHON_DIR}/site-packages ${D}/python3.7-packages/
}

do_package_qa[noexec] = "1"


INSANE_SKIP_${PN}_append = "already-stripped"
