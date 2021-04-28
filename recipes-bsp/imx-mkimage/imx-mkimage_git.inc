# Copyright 2017-2020 NXP
 
DEPENDS = "zlib-native openssl-native"

SRCBRANCH = "iwave_5.4.70_2.3.0-iwg40m-r1.0-rel0.1"
IMX_MKIMAGE_SRC ?= "git://github.com/iwave-git/imx-mkimage-iWave.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH} \
	   "
SRCREV = "39cbe7cbe374981ea1da5448a5c24b064127b919"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"
SYSROOT_DIRS += "/boot"
