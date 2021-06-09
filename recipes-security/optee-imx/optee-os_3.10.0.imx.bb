# Copyright (C) 2021 iWave Systems Technologies Pvt Ltd
require recipes-security/optee-imx/optee-os.imx.inc

DEPENDS_append = " python3-pycryptodomex-native"

SRCBRANCH = "iwave_5.4.70_2.3.0-iwg34s-iwg37s-r1.0-rel0.1"
SRCREV = "ff4ef4f7c5c738d0cb7211e3974c4179e6c89c18"
OPTEE_OS_SRC = "git://github.com/iwave-git/imx-optee-os-iWave.git;protocol=https"

PLATFORM_FLAVOR_mx8mm = "${@bb.utils.contains('MACHINE', 'imx8mm-iwg34m', 'mx8mm_iwg34s', 'mx8mm_iwg34s_2gb', d)}"

PLATFORM_FLAVOR_mx8mn = "${@bb.utils.contains('MACHINE', 'imx8mn-iwg37m', 'mx8mn_iwg37s', d)}"

COMPATIBLE_MACHINE = "(imx8mm-iwg34s|imx8mm-iwg34s-2gb|imx8mn-iwg37s)"

# tee-init_load_addr.txt has been remove in lates optee-os version.
# to keep backward compatibility with existing optee-os recipe.
do_compile_append () {
    if [ "${OPTEE_ARCH}" != "arm64" ]; then
        IMX_LOAD_ADDR=`${TARGET_PREFIX}readelf -h ${B}/core/tee.elf | grep "Entry point address" | awk '{print $4}'` && \
        echo ${IMX_LOAD_ADDR} > ${B}/core/tee-init_load_addr.txt
    fi
}
