MACHINEOVERRIDES =. "imx-boot-container:mx8:mx8m:mx8mn:"

require imx8m-iwg-common.inc

MACHINE_FEATURES += "wifi bluetooth bcm43455 bcm4356"

IMAGE_INSTALL:append = " gstreamer1.0-libav ffmpeg x264 "
CORE_IMAGE_EXTRA_INSTALL += "gstreamer1.0-plugins-ugly-meta \
			packagegroup-fsl-gstreamer1.0-commercial"
	CORE_IMAGE_EXTRA_INSTALL += "gstreamer1.0-plugins-ugly"
LICENSE_FLAGS_WHITELIST = "commercial"

TEE_LOAD_ADDR = "0x56000000"

ATF_PLATFORM = "imx8mn"
ATF_LOAD_ADDR = "0x960000"

IMX_BOOT_SOC_TARGET = "iMX8MN"

BOOT_SPACE = "65536"

# Image boot offset as defined in section 6.1.6.1 "Primary image offset and IVT offset" of
# i.MX 8M Nano Applications Processor Reference Manual, Rev. 0, 12/2019
# Doc ID: IMX8MNRM
IMX_BOOT_SEEK = "32"
