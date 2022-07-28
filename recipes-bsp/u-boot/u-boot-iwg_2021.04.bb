#Copyright (C) 2022-23 iWave Systems Technologies Pvt Ltd.

require ../meta-bsp/recipes-bsp/u-boot/u-boot-imx_2021.04.bb

UBOOT_SRC = "git://github.com/iwave-git/uboot-imx-iWave.git;protocol=https"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH-UBOOT} \
"
SRCREV = "${SRCREV-UBOOT}"
