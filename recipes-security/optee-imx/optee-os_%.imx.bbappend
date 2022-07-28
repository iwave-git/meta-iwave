#Copyright (C) 2022-23 iWave Systems Technologies Pvt Ltd

OPTEE_OS_SRC = "git://github.com/iwave-git/imx-optee-os-iWave.git;protocol=https"
SRC_URI = "${OPTEE_OS_SRC};branch=${SRCBRANCH-OPTEE} \
"
SRCREV = "${SRCREV-OPTEE}"

# Add different platform flavor for 4GB and 8GB Boards 
PLATFORM_FLAVOR:imx8mm-iwg34s-4gb = "mx8mm_iwg34s_4gb"
PLATFORM_FLAVOR:imx8mm-iwg34m-4gb = "mx8mm_iwg34s_4gb"
PLATFORM_FLAVOR:imx8mm-iwg34s-8gb = "mx8mm_iwg34s_8gb"

PLATFORM_FLAVOR:mx8mm = "mx8mm_iwg34s"
PLATFORM_FLAVOR:mx8mn = "mx8mn_iwg37s"

EXTRA_OEMAKE = " \
    PLATFORM=imx \
    PLATFORM_FLAVOR=${PLATFORM_FLAVOR} \
    CFG_DDR_SIZE=${CFG_DDR_SIZE} \
    CROSS_COMPILE=${HOST_PREFIX} \
    CROSS_COMPILE64=${HOST_PREFIX} \
    CFG_TEE_TA_LOG_LEVEL=0 \
    CFG_TEE_CORE_LOG_LEVEL=0 \
    -C ${S} O=${B}\
"
