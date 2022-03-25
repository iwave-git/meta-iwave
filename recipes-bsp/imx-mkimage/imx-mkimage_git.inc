# Copyright 2017-2020 NXP
 
DEPENDS = "zlib-native openssl-native"

SRCBRANCH = "iwg40m_osm_5.4.70_2.3.0_0.1"
IMX_MKIMAGE_SRC ?= "git://github.com/iwave-git/imx-mkimage-iWave.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH} \
	   "
SRCREV = "28bdcccd38b534aafdc3b9ed768a35a8366527ec"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"
SYSROOT_DIRS += "/boot"
