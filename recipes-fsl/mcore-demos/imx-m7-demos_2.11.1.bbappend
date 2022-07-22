#Copyright (C) 2022-23 iWave Systems Technologies Pvt Ltd

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "${M7-BINARIES}"
SRC_URI[sha256sum] = "0e0de9e1398a60e06f4ecd016d284d582201df078bfae425a1fbb3897f7660e6"

do_install () {
    install -d ${D}${base_libdir}/firmware
    install -m 0644 ${S}/../*TCM*.bin ${D}${base_libdir}/firmware/
}

do_deploy () {
if [ "${MACHINE}" = "imx8mn-iwg37s-2gb" ] || [ "${MACHINE}" = "imx8mn-iwg37m-2gb" ] || [ "${MACHINE}" = "imx8mn-iwg37m-q7-2gb" ]; then
    install -m 0644 ${S}/../*TCM*2gb.bin ${DEPLOYDIR}/

else if [ "${MACHINE}" = "imx8mn-iwg37s" ] || [ "${MACHINE}" = "imx8mn-iwg37m" ] || [ "${MACHINE}" = "imx8mn-iwg37m-q7" ]; then
    install -m 0644 ${S}/../*TCM*1gb.bin ${DEPLOYDIR}/
fi
fi
}
