#Copyright (C) 2022-23 iWave System Technologies Pvt Ltd.

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

IMX_MKIMAGE_SRC = "git://github.com/iwave-git/imx-mkimage-iWave.git;protocol=https"
SRC_URI = "${IMX_MKIMAGE_SRC};branch=${SRCBRANCH-MKIMAGE} \
file://PATCH000-PRFYZ-iMX8M-soc.mak-use-native-mkimage-from-sysroot.patch \
"
SRCREV = "${SRCREV-MKIMAGE}"

do_compile() {
    # mkimage for i.MX8
    # Copy TEE binary to SoC target folder to mkimage
    if ${DEPLOY_OPTEE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin ${BOOT_STAGING}
    fi
    for target in ${IMXBOOT_TARGETS}; do
        compile_${SOC_FAMILY}
        if [ "$target" = "flash_linux_m4_no_v2x" ]; then
           # Special target build for i.MX 8DXL with V2X off
           bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} V2X=NO ${target}"
           make SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} V2X=NO dtbs=${UBOOT_DTB_NAME} flash_linux_m4
        else
           bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} ${target}"
           make SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} TEE_LOAD_ADDR=${TEE_LOAD_ADDR} dtbs=${UBOOT_DTB_NAME} ${target}
        fi
        if [ -e "${BOOT_STAGING}/flash.bin" ]; then
            cp ${BOOT_STAGING}/flash.bin ${S}/${BOOT_CONFIG_MACHINE}-${target}
        fi
    done
}
