
# Files directory
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Sources
SRC_URI_append = " \
    file://dnsmasq.conf \
    file://dnsmasq-usb0.conf \
    file://dnsmasq-wlan0.conf \
    file://dnsmasq \
"

do_install_append() {
    install -d ${D}/${sysconfdir}/default/
    install -m 0777 ${WORKDIR}/dnsmasq ${D}/${sysconfdir}/default/
    install -m 0777 ${WORKDIR}/dnsmasq-usb0.conf ${D}/${sysconfdir}/dnsmasq.d/
    install -m 0777 ${WORKDIR}/dnsmasq-wlan0.conf ${D}/${sysconfdir}/dnsmasq.d/
}
