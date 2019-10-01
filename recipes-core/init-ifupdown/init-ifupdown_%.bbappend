
# Files directory
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Sources
SRC_URI_append = " \
    file://interfaces \
"

do_install_append () {
    # Network permissions
    chmod 777 ${D}/etc/network/interfaces
}
