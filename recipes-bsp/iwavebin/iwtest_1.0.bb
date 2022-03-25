# Copyright (c) 2020 iWave Systems Technologies Pvt. Ltd.

DESCRIPTION = "iwtest files"
PROVIDES += "${PN}"
LICENSE = "GPLv2+"

COMPATIBLE_MACHINE = "(imx8mp-iwg40m-osm|imx8mp-iwg40m-osm-2gb)"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

LIC_FILES_CHKSUM = "file://GPL-2;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "file://GPL-2 file://tfr.mp4 file://iwg40m_osm_hello_world.bin file://iwg40m_osm_rpmsg_lite_pingpong_rtos_linux_remote.bin file://iwg40m_osm_rpmsg_lite_str_echo_rtos.bin file://pyeiq_object_detection.py file://spidev_fdx"
FILES_${PN} = "iwtest "
#FILES_${PN} = "${bindir}"
S = "${WORKDIR}"

inherit deploy
addtask deploy before do_build after do_install
do_deploy () {
	install -m 0644 ${S}/iwg40m_osm_hello_world.bin ${DEPLOYDIR}
	install -m 0644 ${S}/iwg40m_osm_rpmsg_lite_pingpong_rtos_linux_remote.bin ${DEPLOYDIR}
	install -m 0644 ${S}/iwg40m_osm_rpmsg_lite_str_echo_rtos.bin ${DEPLOYDIR}
}

do_install() {
    mkdir -p ${D}/iwtest
    #mkdir -p ${D}/${bindir}
    install -m 0644 ${S}/tfr.mp4 ${D}/iwtest
    install -m 0644 ${S}/pyeiq_object_detection.py ${D}/iwtest
    install -m 0644 ${S}/spidev_fdx ${D}/iwtest
    chmod   +x ${D}/iwtest/*
    #chmod   +x ${D}/${bindir}/spidev_fdx
}
