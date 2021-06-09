# Copyright 2017-2020 NXP
 
DEPENDS = "zlib-native openssl-native"

SRCBRANCH = "iwave_5.4.70_2.3.0-iwg34s-iwg37s-r1.0-rel0.1"
IMX_MKIMAGE_SRC ?= "git://github.com/iwave-git/imx-mkimage-iWave.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH} \
	   "
SRCREV = "c8b3b11714b0426f636296c862f7931d600c4c52"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"
SYSROOT_DIRS += "/boot"
