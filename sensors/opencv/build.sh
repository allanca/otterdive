#!/bin/sh

# version configuration
OPENCV_VERSION=2.0.0
GCC_VERSION=4.2
SDK_VERSION=4.2

# directory paths
ROOTDIR=$(pwd)
SRCDIR="${ROOTDIR}/OpenCV-${OPENCV_VERSION}"

I686DIR="$ROOTDIR/i686"
ARMDIR="$ROOTDIR/armv6"

# apply patch
cd ${SRCDIR}
patch -p0 -N < ../cvcalibration.cpp.patch
rm src/cv/cvcalibration.cpp.rej
cd  ${ROOTDIR}

# configure path
if [ -z "${CONFIGURE}" ]; then
	CONFIGURE=${SRCDIR}/configure
fi
if [ ! -e "${CONFIGURE}" ]; then
	echo "Missing '${CONFIGURE}'."
	exit 1
fi
if [ "$1" = "--configure-help" -o "$1" = "-c" ]; then
	${CONFIGURE} --help
	exit
fi

# build for simulator
build_simulator() {
   rm -rf $I686DIR
   mkdir -p $I686DIR
   pushd $I686DIR

   SDKNAME=Simulator
   ARCH=i686
   HOST=i686
   PLATFORM=/Developer/Platforms/iPhone${SDKNAME}.platform
   BIN=${PLATFORM}/Developer/usr/bin
   SDK=${PLATFORM}/Developer/SDKs/iPhone${SDKNAME}${SDK_VERSION}.sdk

   PREFIX=`pwd`/`dirname $0`/${ARCH}
   PATH=/bin:/sbin:/usr/bin:/usr/sbin:${BIN}

   ${CONFIGURE} \
	--prefix=${PREFIX} \
	--build=i686-apple-darwin9 \
	--host=${HOST}-apple-darwin9 \
	--target=${HOST}-apple-darwin9 \
	--enable-static \
    --disable-openmp \
	--disable-shared \
	--disable-sse \
	--disable-apps \
	--without-python \
	--without-ffmpeg  \
	--without-1394libs \
	--without-v4l \
	--without-imageio \
	--without-quicktime \
	--without-carbon \
	--without-gtk \
	--without-gthread \
	--without-swig \
	--disable-dependency-tracking \
	$* \
	CC=${BIN}/gcc-${GCC_VERSION} \
	CXX=${BIN}/g++-${GCC_VERSION} \
	CFLAGS="-arch ${ARCH} -isysroot ${SDK}" \
	CXXFLAGS="-arch ${ARCH} -isysroot ${SDK}" \
	CPP=${BIN}/cpp \
	CXXCPP=${BIN}/cpp \
	AR=${BIN}/ar

    make || exit 1
    popd

}

build_device() {
   rm -rf $ARMDIR
   mkdir -p $ARMDIR
   pushd $ARMDIR
   
   SDKNAME=OS
   ARCH=armv6
   HOST=arm
   PLATFORM=/Developer/Platforms/iPhone${SDKNAME}.platform
   BIN=${PLATFORM}/Developer/usr/bin
   SDK=${PLATFORM}/Developer/SDKs/iPhone${SDKNAME}${SDK_VERSION}.sdk

   PREFIX=`pwd`/`dirname $0`/${ARCH}
   PATH=/bin:/sbin:/usr/bin:/usr/sbin:${BIN}

   ${CONFIGURE} \
	--prefix=${PREFIX} \
	--build=i686-apple-darwin9 \
	--host=${HOST}-apple-darwin9 \
	--target=${HOST}-apple-darwin9 \
	--enable-static \
    --disable-openmp \
	--disable-shared \
	--disable-sse \
	--disable-apps \
	--without-python \
	--without-ffmpeg  \
	--without-1394libs \
	--without-v4l \
	--without-imageio \
	--without-quicktime \
	--without-carbon \
	--without-gtk \
	--without-gthread \
	--without-swig \
	--disable-dependency-tracking \
	$* \
	CC=${BIN}/gcc-${GCC_VERSION} \
	CXX=${BIN}/g++-${GCC_VERSION} \
	CFLAGS="-arch ${ARCH} -isysroot ${SDK}" \
	CXXFLAGS="-arch ${ARCH} -isysroot ${SDK}" \
	CPP=${BIN}/cpp \
	CXXCPP=${BIN}/cpp \
	AR=${BIN}/ar

    make || exit 1
    popd

}

# build the multi-architecture static libraries
build_static_libs() {

   lipo -create ${I686DIR}/src/.libs/libcv.a \
                ${ARMDIR}/src/.libs/libcv.a \
        -output libcv.a || exit 1

   lipo -create ${I686DIR}/src/.libs/libcxcore.a \
                ${ARMDIR}/src/.libs/libcxcore.a \
        -output libcxcore.a || exit 1

   lipo -create ${I686DIR}/src/.libs/libcvaux.a \
                ${ARMDIR}/src/.libs/libcvaux.a \
        -output libcvaux.a || exit 1

    lipo -create ${I686DIR}/src/.libs/libml.a \
                 ${ARMDIR}/src/.libs/libml.a \
         -output libml${SUFFIX}.a || exit 1

    lipo -create ${I686DIR}/src/.libs/libhighgui.a \
                 ${ARMDIR}/src/.libs/libhighgui.a \
         -output libhighgui.a || exit 1
  
}

# main loop
cd ${ROOTDIR}
rm -f *.a

build_device
build_simulator
build_static_libs


