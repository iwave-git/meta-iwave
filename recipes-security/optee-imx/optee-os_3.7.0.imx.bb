# Copyright (C) 2020 iWave Systems Technologies Pvt Ltd
require recipes-security/optee-imx/optee-os.imx.inc

SRCBRANCH = "iwave_5.4.24_2.1.0-iwg27m-q7-r1.0-rel0.1"
OPTEE_OS_SRC = "git://github.com/iwave-git/imx-optee-os-iWave.git;protocol=https"
SRC_URI = "${OPTEE_OS_SRC};branch=${SRCBRANCH}"
SRCREV = "bfc2b80d69f8ed2300cdb388949bbcd5b4a98da8"

PLATFORM_FLAVOR_mx8qm = "${@bb.utils.contains('MACHINE', 'imx8qm-iwg27m', 'mx8qm_iwg27m', 'mx8qm_iwg27m_8gb', d)}"

COMPATIBLE_MACHINE = "(imx8qm-iwg27m|imx8qm-iwg27m-8gb)"
