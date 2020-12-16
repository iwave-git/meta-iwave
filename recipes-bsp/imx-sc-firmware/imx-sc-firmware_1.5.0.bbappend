# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2019 NXP

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_imx8qm-iwg27s = " file://mx8qm-iwg27s-scfw-tcm_4gb.bin"
SRC_URI_append_imx8qm-iwg27s-8gb = " file://mx8qm-iwg27s-scfw-tcm_8gb.bin"
SRC_URI_append_imx8qm-iwg27s-2gb = " file://mx8qm-iwg27s-scfw-tcm_2gb.bin"

SC_FIRMWARE_NAME ?= "INVALID"
SC_FIRMWARE_NAME_imx8qm-iwg27s = "mx8qm-iwg27s-scfw-tcm_4gb.bin"
SC_FIRMWARE_NAME_imx8qm-iwg27s-8gb = "mx8qm-iwg27s-scfw-tcm_8gb.bin"
SC_FIRMWARE_NAME_imx8qm-iwg27s-2gb = "mx8qm-iwg27s-scfw-tcm_2gb.bin"

# iWave SCFW are in the workdir not in the git repository move them
do_deploy_prepend() {
	mv ${WORKDIR}/${SC_FIRMWARE_NAME} ${S}/
}
