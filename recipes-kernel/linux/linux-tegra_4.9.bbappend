FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${@bb.parse.BBHandler.vars_from_file(d.getVar('FILE', False),d)[1]}:"



#SRC_URI += " \
#	file://defconfig \
#"
#L4T_VERSION = "l4t-r32.2-8gb"

#SRCBRANCH = "patches-${L4T_VERSION}"
#SRCREV = "7467bde43b5d678b39e2bd2ed0968bd21cd6eeb1"

#do_apply_realtime() {
#    bbwarn ${THISDIR}
#    cd ${STAGING_KERNEL_DIR}/scripts
#    ./rt-patch.sh apply-patches
#}
#addtask do_apply_realtime after do_unpack before do_patch
