FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${@bb.parse.BBHandler.vars_from_file(d.getVar('FILE', False),d)[1]}:"



SRC_URI += " \
	file://defconfig \
"


do_apply_realtime() {
    bbwarn ${THISDIR}
    cd ${STAGING_KERNEL_DIR}/scripts
    ./rt-patch.sh apply-patches
}
addtask do_apply_realtime after do_patch before do_compile
