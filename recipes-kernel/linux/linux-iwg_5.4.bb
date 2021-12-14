# Copyright (C) 2021 iWave Systems Technologies Pvt Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "iwave_5.4.70_2.3.0-iwg34s-iwg37s-r1.0-rel0.1"
LOCALVERSION = "-2.3.0"
KERNEL_SRC ?= "git://github.com/iwave-git/linux-imx-iWave.git;protocol=https"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH} \
	"

#SRCREV = "55b4e8a94fd0fecfa8bd580c8648a14245fc2b25"  /* PCIe external clock configuration */
#Below change of SRCREV for PCIe internal clock configuartion.
#SRCREV = "4ca51a89f5868c9ce5b255699bd4bf3d975d0eef"
#Below change of SRCREV for 8GB configuration
SRCREV = "60122bf00bcabc03ac89208a66bf7c0859674ae3"

FILES_${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEFAULT_PREFERENCE = "1"

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY_mx6 = "yes"
DO_CONFIG_V7_COPY_mx7 = "yes"
DO_CONFIG_V7_COPY_mx8 = "no"

addtask copy_defconfig after do_patch before do_preconfigure

do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest imx_v7_defconfig to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/.config
        cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/../defconfig
    else
        # copy latest iw_rainbowgxxx_defconfig to use for mx8
        mkdir -p ${B}
	if [ "${@bb.utils.contains_any('MACHINE', 'imx8mm-iwg34s imx8mm-iwg34s-2gb imx8mm-iwg34s-8gb', 'yes', 'no', d)}" = "yes" ]; then
		cp ${S}/arch/arm64/configs/iw_rainbowg34s_defconfig ${B}/.config
		cp ${S}/arch/arm64/configs/iw_rainbowg34s_defconfig ${B}/../defconfig
	fi
	if [ "${@bb.utils.contains_any('MACHINE', 'imx8mn-iwg37s', 'yes', 'no', d)}" = "yes" ]; then
		cp ${S}/arch/arm64/configs/iw_rainbowg37s_defconfig ${B}/.config
		cp ${S}/arch/arm64/configs/iw_rainbowg37s_defconfig ${B}/../defconfig
	fi
    fi
}

DELTA_KERNEL_DEFCONFIG ?= ""
#DELTA_KERNEL_DEFCONFIG_prepend_mx8 = "sdk_imx.config "

do_merge_delta_config[dirs] = "${B}"
do_merge_delta_config() {
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        if [ -f ${S}/arch/${ARCH}/configs/${deltacfg} ]; then
            ${KERNEL_CONFIG_COMMAND}
            oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} ${deltacfg}
        elif [ -f "${WORKDIR}/${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${WORKDIR}/${deltacfg}
        elif [ -f "${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${deltacfg}
        fi
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_preconfigure after do_copy_defconfig

COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
