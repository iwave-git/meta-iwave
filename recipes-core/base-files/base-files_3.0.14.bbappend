DISTRO_NAME = "NXP i.MX Release "
DISTRO_VERSION = "L5.4.70_2.3.0"

IWG_BSP_VERSION = "\r Rootfs Version : iW-PRGII-SC-01-R1.0-REL0.1-YoctoZeus\n"

IWG_HOST = "${@bb.utils.contains_any('MACHINE', 'imx8mm-iwg34s imx8mm-iwg34s-2gb', 'iWave-G34S', bb.utils.contains_any('MACHINE', 'imx8mn-iwg37s', 'iWave-G37S', '', d), d)}"

do_install () {
	for d in ${dirs555}; do
		install -m 0555 -d ${D}$d
	done
	for d in ${dirs755}; do
		install -m 0755 -d ${D}$d
	done
	for d in ${dirs1777}; do
		install -m 1777 -d ${D}$d
	done
	for d in ${dirs2775}; do
		install -m 2775 -d ${D}$d
	done
	for d in ${volatiles}; do
		ln -sf volatile/$d ${D}${localstatedir}/$d
	done

	ln -snf ../run ${D}${localstatedir}/run
	ln -snf ../run/lock ${D}${localstatedir}/lock

	install -m 0644 ${WORKDIR}/hosts ${D}${sysconfdir}/hosts
	${BASEFILESISSUEINSTALL}

	rotation=`cat ${WORKDIR}/rotation`
	if [ "$rotation" != "0" ]; then
 		install -m 0644 ${WORKDIR}/rotation ${D}${sysconfdir}/rotation
	fi

	install -m 0644 ${WORKDIR}/fstab ${D}${sysconfdir}/fstab
	install -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/profile
	sed -i 's#ROOTHOME#${ROOT_HOME}#' ${D}${sysconfdir}/profile
        sed -i 's#@BINDIR@#${bindir}#g' ${D}${sysconfdir}/profile
	install -m 0644 ${WORKDIR}/shells ${D}${sysconfdir}/shells
	install -m 0755 ${WORKDIR}/share/dot.profile ${D}${sysconfdir}/skel/.profile
	install -m 0755 ${WORKDIR}/share/dot.bashrc ${D}${sysconfdir}/skel/.bashrc
	install -m 0644 ${WORKDIR}/host.conf ${D}${sysconfdir}/host.conf
	install -m 0644 ${WORKDIR}/motd ${D}${sysconfdir}/motd

	ln -sf /proc/mounts ${D}${sysconfdir}/mtab

	# deal with hostname
	if [ -n "${IWG_HOST}" ]; then
               echo ${IWG_HOST} > ${D}${sysconfdir}/hostname
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
