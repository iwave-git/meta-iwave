# Copyright (c) 2020 iWave Systems Technologies Pvt. Ltd.

DESCRIPTION = "iwtest files"
PROVIDES += "${PN}"
LICENSE = "GPLv2+"

COMPATIBLE_MACHINE = "(imx8mm-iwg34s|imx8mm-iwg34s-2gb|imx8mn-iwg37s)"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

LIC_FILES_CHKSUM = "file://GPL-2;md5=94d55d512a9ba36caa9b7df079bae19f"

FILES_${PN} = "iwtest"
S = "${WORKDIR}"

inherit deploy
addtask deploy before do_build after do_install

do_install() {
    mkdir -p ${D}/iwtest
    install -m 0644 ${S}/tfr.mp4 ${D}/iwtest
    chmod   +x ${D}/iwtest/*
}
