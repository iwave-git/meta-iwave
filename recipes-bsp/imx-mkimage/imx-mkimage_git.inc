# Copyright 2017-2019 NXP
 
DEPENDS = "zlib-native openssl-native"

SRCBRANCH = "iwave_5.4.24_2.1.0-iwg27m-q7-r1.0-rel0.1"
IMX_MKIMAGE_SRC ?= "git://github.com/iwave-git/imx-mkimage-iWave.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH}"
SRCREV = "cc5e7ed3fc572eeec48684bea9275463c8eeaff7"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"
SYSROOT_DIRS += "/boot"
