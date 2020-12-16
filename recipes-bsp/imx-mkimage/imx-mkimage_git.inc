# Copyright 2017-2019 NXP
 
DEPENDS = "zlib-native openssl-native"

SRCBRANCH = "imx_5.4.24_2.1.0"
IMX_MKIMAGE_SRC ?= "git://source.codeaurora.org/external/imx/imx-mkimage.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH} \
	${@bb.utils.contains('MACHINE_FEATURES', 'optee', bb.utils.contains('MACHINE', 'imx8qm-iwg27s', 'file://imx_mkimage_4gb_lpddr4_optee_support_iwg27s.patch', '', d), '', d)}"
SRCREV = "6745ccdcf15384891639b7ced3aa6ce938682365"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"
SYSROOT_DIRS += "/boot"
