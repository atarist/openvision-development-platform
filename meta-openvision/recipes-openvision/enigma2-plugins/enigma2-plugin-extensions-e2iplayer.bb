SUMMARY = "E2i Player for E2"
DESCRIPTION = "E2i Player for E2"
SECTION = "multimedia"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/persianpros/e2iplayer.git;protocol=http"

S = "${WORKDIR}/git"

inherit gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

inherit allarch distutils-openplugins gettext

DEPENDS = "gettext-native python"

RDEPENDS_${PN} = " \
	cmdwrapper \
	duktape \
	f4mdump \
	ffmpeg \
	hlsdl \
	iptvsubparser \
	lsdir \
	python-compression \
	python-core \
	python-e2icjson \
	python-html \
	python-json \
	python-pycurl \
	python-shell \
	python-subprocess \
	python-textutils \
	rtmpdump \
	uchardet \
	wget \
	${@bb.utils.contains("MACHINE_FEATURES", "libeplayer", "", "exteplayer3 gst-ifdsrc gstplayer", d)} \
	"

RDEPENDS_{PN}-src = "${PN}"

FILES_${PN}-src = " \
	${libdir}/enigma2/python/Plugins/*/*.py \
	${libdir}/enigma2/python/Plugins/*/*/*.py \
	${libdir}/enigma2/python/Plugins/*/*/*/*.py \
	${libdir}/enigma2/python/Plugins/*/*/*/*/*.py \
	${libdir}/enigma2/python/Plugins/*/*/*/*/*/*.py \
	${libdir}/enigma2/python/Plugins/*-py2.7.egg-info/* \
	${libdir}/enigma2/python/Plugins/*/locale/*/LC_MESSAGES/*.po \
	"

deltask package_qa

FILES_${PN} += "${sysconfdir}"

do_install_append() {
    install -d ${D}${sysconfdir}
    cp -r  --preserve=mode,links ${S}/vk ${D}${sysconfdir}/vk
}
