SUMMARY = "A qt5 development image"
LICENSE = "MIT"
require minimal-nano-image.bb
inherit populate_sdk_qt5
QT_TOOLS = " \
    qtbase \
    qtbase-plugins \
    qtserialport \
    qtwebglplugin \
"

IMAGE_INSTALL += " \
    ${QT_TOOLS} \
"

export IMAGE_BASENAME = "qt5-nano-image"