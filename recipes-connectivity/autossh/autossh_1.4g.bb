LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
RDEPENDS_autossh ?= "bash"

SRC_URI = " \
    https://www.harding.motd.ca/autossh/autossh-${PV}.tgz \
    file://sshtunnel \
    file://init \
" 
SRC_URI[md5sum] = "2b804bc1bf6d2f2afaa526d02df7c0a2"
SRC_URI[sha256sum] = "5fc3cee3361ca1615af862364c480593171d0c54ec156de79fc421e31ae21277"


S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools-brokensep update-rc.d

INITSCRIPT_NAME = "autossh"
INITSCRIPT_PARAMS = "start 60 S ."

CONFIGUREOPTS += " --with-ssh=/usr/bin/ssh "

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/autossh
    install -m 0755 ${S}/autossh ${D}${bindir}
    install -m 0755 ${WORKDIR}/sshtunnel ${D}${sysconfdir}/autossh/sshtunnel
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/autossh
}
