# Copyright (C) 2022-23 iWave System Technologies Pvt Ltd. 

ATF_SRC = "git://git-codecommit.ap-south-1.amazonaws.com/v1/repos/imx-atf-iWave;protocol=https"
SRC_URI = "${ATF_SRC};branch=${SRCBRANCH-ATF} \
"
SRCREV = "${SRCREV-ATF}"	

EXTRA_OEMAKE += " \
    CROSS_COMPILE="${TARGET_PREFIX}" \
    PLAT=${PLATFORM} \
    BL32_BASE=${TEE_LOAD_ADDR} \
    IMX_BOOT_UART_BASE=${IMX_BOOT_UART_BASE} \
"
