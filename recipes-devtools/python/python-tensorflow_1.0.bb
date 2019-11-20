DESCRIPTION = "Adafruit BBIO"
HOMEPAGE = "https://github.com/adafruit/adafruit-beaglebone-io-python/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.rst;md5=df95471969e3e40ce701b0ff86fe4568"

SRC_URI[md5sum] = "8babec0c13828c251ea6907e113d5c26"
SRC_URI[sha256sum] = "4b9aef88de437aa08185172a7d09ee7ef62e448acb2d71f77c9c5e0f0f4b9b97"

#SRC_URI[md5sum] = "75c676577216244b74958d02900480b5"
#SRC_URI[sha256sum] = "9e8255aefb3470706ca2bb63432e4ceb697de2ab1b0be69904456840da0dafd8"

inherit setuptools

#PYPI_PACKAGE = "Adafruit_BBIO"
SRC_URI = "https://files.pythonhosted.org/packages/bd/ee/dfbf0521287fc4cd8870427618b5d41c6a292f6d640af7b4c407329d7031/Adafruit_BBIO-${PV}.tar.gz"

S = "${WORKDIR}/Adafruit_BBIO-${PV}"


RDEPENDS_${PN} = "python"

BBCLASSEXTEND = "native"

CLEANBROKEN="1"

