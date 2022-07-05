require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc 

#MACHINE_FEATURES_append_use-nxp-bsp = " optee bcm4359 "

IMX_DEFAULT_BSP = "nxp"
UBOOT_DTB_NAME = "${KERNEL_DEVICETREE_BASENAME}.dtb"

IMX_DEFAULT_BOOTLOADER_use-nxp-bsp = "u-boot-imx"
IMX_DEFAULT_BOOTLOADER_use-mainline-bsp = "u-boot-fslc"

UBOOT_SUFFIX = "bin"

UBOOT_CONFIG ??= "sd"
UBOOT_CONFIG[sd]      = "${UBOOT_CONFIG_BASENAME}_defconfig,sdcard"
UBOOT_CONFIG[mfgtool] = "${UBOOT_CONFIG_BASENAME}_defconfig"

SPL_BINARY = "spl/u-boot-spl.bin"

DDR_FIRMWARE_NAME = "\
	lpddr4_pmu_train_1d_imem.bin \
	lpddr4_pmu_train_1d_dmem.bin \
	lpddr4_pmu_train_2d_imem.bin \
	lpddr4_pmu_train_2d_dmem.bin \
"

IMAGE_INSTALL:append = " iwtest dnsmasq glibc-gconv-utf-16 "

# Extra firmware package name, that is required to build boot container for fslc bsp
IMX_EXTRA_FIRMWARE = "firmware-imx-8m"

IMXBOOT_TARGETS_BASENAME = "flash_iwg"
IMXBOOT_TARGETS = "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', '${IMXBOOT_TARGETS_BASENAME}_flexspi', '${IMXBOOT_TARGETS_BASENAME}', d)}"

IMX_BOOT_UART_BASE = "0x30a60000"
SERIAL_CONSOLES = "115200;ttymxc3"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = "all"

PREFERRED_PROVIDER_virtual/kernel:${MACHINE} = "linux-iwg"
PREFERRED_PROVIDER_virtual/bootloader:${MACHINE} = "u-boot-iwg"
PREFERRED_PROVIDER_u-boot:${MACHINE} = "u-boot-iwg"
