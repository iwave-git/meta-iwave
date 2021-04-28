# Copyright (C) 2021 iWave Systems Technologies Pvt Ltd
require recipes-security/optee-imx/optee-os.imx.inc

DEPENDS_append = " python3-pycryptodomex-native"

SRCBRANCH = "iwave_5.4.70_2.3.0-iwg40m-r1.0-rel0.1"
SRCREV = "92a8d2226c58f242cf0bd0c5b4096512ebedb3ed"
OPTEE_OS_SRC = "git://github.com/iwave-git/imx-optee-os-iWave.git;protocol=https"

PLATFORM_FLAVOR_mx8mp = "${@bb.utils.contains('MACHINE', 'imx8mp-iwg40m', 'mx8mp_iwg40m', 'mx8mp_iwg40m_2gb', d)}"

COMPATIBLE_MACHINE = "(imx8mp-iwg40m|imx8mp-iwg40m-2gb)"
# tee-init_load_addr.txt has been remove in lates optee-os version.
# to keep backward compatibility with existing optee-os recipe.
do_compile_append () {
    if [ "${OPTEE_ARCH}" != "arm64" ]; then
        IMX_LOAD_ADDR=`${TARGET_PREFIX}readelf -h ${B}/core/tee.elf | grep "Entry point address" | awk '{print $4}'` && \
        echo ${IMX_LOAD_ADDR} > ${B}/core/tee-init_load_addr.txt
    fi
}
