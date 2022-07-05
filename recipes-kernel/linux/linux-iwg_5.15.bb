#Copyright (C) 2022-23 iWave System Technologies Pvt Ltd.

require ../meta-bsp/recipes-kernel/linux/linux-imx_5.15.bb

KERNEL_SRC = "git://git-codecommit.ap-south-1.amazonaws.com/v1/repos/linux-imx-iWave;protocol=https"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH} \
"

SRCREV = "${SRCREV-KERNEL}"

do_copy_defconfig () {
    install -d ${B}
        # copy latest defconfig to use to iwg34s or iwg37s
        mkdir -p ${B}
	cp ${S}/arch/arm64/configs/${KERNEL_DEFCONFIG_NAME} ${B}/.config
	cp ${S}/arch/arm64/configs/${KERNEL_DEFCONFIG_NAME} ${B}/../defconfig
}
