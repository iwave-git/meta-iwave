# Copyright (C) 2021 iWave Systems Technologies Pvt Ltd
require recipes-security/optee-imx/optee-os.imx.inc

DEPENDS_append = " python3-pycryptodomex-native"

SRCBRANCH = "iwg40m_osm_5.4.70_2.3.0_0.1"
SRCREV = "68491936d8b4f210d46c5ccf5ba3b7a40c096d1d"
OPTEE_OS_SRC = "git://github.com/iwave-git/imx-optee-os-iWave.git;protocol=https"

PLATFORM_FLAVOR_mx8mp = "${@bb.utils.contains('MACHINE', 'imx8mp-iwg40m-osm', 'mx8mp_iwg40m', 'mx8mp_iwg40m_osm_2gb', d)}"
#PLATFORM_FLAVOR_mx8mp = "${@bb.utils.contains('MACHINE', 'imx8mp-iwg40m-osm', 'mx8mp_iwg40m_osm', 'mx8mp_iwg40m_2gb', d)}"

COMPATIBLE_MACHINE = "(imx8mp-iwg40m-osm|imx8mp-iwg40m-osm-2gb)"
# tee-init_load_addr.txt has been remove in lates optee-os version.
# to keep backward compatibility with existing optee-os recipe.
do_compile_append () {
    if [ "${OPTEE_ARCH}" != "arm64" ]; then
        IMX_LOAD_ADDR=`${TARGET_PREFIX}readelf -h ${B}/core/tee.elf | grep "Entry point address" | awk '{print $4}'` && \
        echo ${IMX_LOAD_ADDR} > ${B}/core/tee-init_load_addr.txt
    fi
}
