# Copyright (C) 2022-23 iWave System Technologies Pvt Ltd.

IWG_BSP_VERSION = "\r Rootfs Version : ${ROOTFS_VERSION} \n"
IWG_HOST = "${HOSTNAME}"

do_install:append () {
	# deal with hostname
	if [ -n "${IWG_HOST}" ]; then
		echo ${IWG_HOST} > ${D}${sysconfdir}/hostname
		echo "127.0.1.1 ${IWG_HOST}" >> ${D}${sysconfdir}/hosts
	fi
}

do_install_basefilesissue () {
	install -m 644 ${WORKDIR}/issue*  ${D}${sysconfdir}
	if [ -n "${IWG_BSP_VERSION}" ]; then
               printf "${IWG_BSP_VERSION} " >> ${D}${sysconfdir}/issue
               printf "${IWG_BSP_VERSION} " >> ${D}${sysconfdir}/issue.net
        fi
        if [ -n "${IWG_HOST}" ]; then
               echo ${IWG_HOST} > ${D}${sysconfdir}/hostname
        fi

        if [ -n "${DISTRO_NAME}" ]; then
		printf "${DISTRO_NAME} " >> ${D}${sysconfdir}/issue
		printf "${DISTRO_NAME} " >> ${D}${sysconfdir}/issue.net
		if [ -n "${DISTRO_VERSION}" ]; then
			distro_version_nodate="${@d.getVar('DISTRO_VERSION').replace('snapshot-${DATE}','snapshot').replace('${DATE}','')}"
			printf "%s " $distro_version_nodate >> ${D}${sysconfdir}/issue
			printf "%s " $distro_version_nodate >> ${D}${sysconfdir}/issue.net
		fi
		printf "\\\n \\\l\n" >> ${D}${sysconfdir}/issue
		echo >> ${D}${sysconfdir}/issue
		echo "%h"    >> ${D}${sysconfdir}/issue.net
		echo >> ${D}${sysconfdir}/issue.net
 	fi
}
