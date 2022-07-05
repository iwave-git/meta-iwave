MACHINEOVERRIDES =. "imx-boot-container:mx8:mx8m:mx8mm:"

require imx8m-iwg-common.inc

MACHINE_FEATURES += "pci wifi bluetooth bcm43455 bcm4356 optee bcm4359"

ATF_PLATFORM = "imx8mm"
ATF_LOAD_ADDR = "0x920000"

IMX_BOOT_SOC_TARGET = "iMX8MM"

IMX_BOOT_SEEK = "33"

OPTEE_BIN_EXT = "8mm"
