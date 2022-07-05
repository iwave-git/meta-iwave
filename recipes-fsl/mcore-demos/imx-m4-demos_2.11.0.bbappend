#Copyright (C) 2022-23 iWave Systems Technologies Pvt Ltd

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "${M4-BINARIES}"
SRC_URI[sha256sum] = "a0fb1988fdcd9c4815885aabbd3caceff2a2919437202fb462791c91b2e1fe02"
#SRC_URI[sha256sum] = "c14f6d2f41d2a16eb9f948c6182ef3fbb9dfccdd5399b78e2fe3fa8ae9006dde"

do_install () {
    install -d ${D}${base_libdir}/firmware
    install -m 0644 ${S}/../*TCM*.bin ${D}${base_libdir}/firmware/
}

do_deploy () {
if [ ${MACHINE} = "imx8mm-iwg34s-2gb" ] || [ ${MACHINE} = "imx8mm-iwg34s-4gb" ] || [ ${MACHINE} = "imx8mm-iwg34s-8gb" ] || [ ${MACHINE} = "imx8mm-iwg34m-2gb" ] || [ ${MACHINE} = "imx8mm-iwg34m-4gb" ] || [ ${MACHINE} = "imx8mm-iwg34m-q7-2gb" ]; then
    install -m 0644 ${S}/../*TCM*2gb.bin ${DEPLOYDIR}/

else if [ ${MACHINE} = "imx8mm-iwg34s" ] || [ ${MACHINE} = "imx8mm-iwg34m" ] || [ ${MACHINE} = "imx8mm-iwg34m-q7" ]; then
    install -m 0644 ${S}/../*TCM*1gb.bin ${DEPLOYDIR}/
fi
fi
}
