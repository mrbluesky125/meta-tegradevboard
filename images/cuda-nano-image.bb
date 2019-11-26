SUMMARY = "A console development image with some C/C++ dev tools"
LICENSE = "MIT"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

require console-nano-image.bb


DEEP_LEARING_TOOLS = " \
    tensorrt \
    tensorflow-lite \
    cudnn \
    cudnn-dev \
    opencv \
    opencv-dev \
    cuda-cudart \
    cuda-cudart-dev \
    cuda-core \
    cuda-core-dev \
    cuda-cublas-dev \
    cuda-curand \
    cuda-curand-dev \
    cuda-cusolver \
    cuda-cusparse \
    cuda-npp \
    cuda-nvrtc \
    cuda-toolkit \
    cuda-misc-headers \
"

X11_PACKAGES = " \
    xauth \
    xcursor-transparent-theme \
    xf86-input-evdev \
    xf86-input-keyboard \
    xf86-input-mouse \
    xf86-video-fbdev \
    xhost \
    xinit \
    xinput \
    xinput-calibrator \
    xkbcomp \
    xkeyboard-config \
    xkeyboard-config-locale-en-gb \
    xmodmap \
    xrandr \
    xserver-nodm-init \
    xserver-xf86-config \
    xserver-xorg \
    xset \
 "
X11_EXTRAS = " \
    formfactor \
    pcmanfm \
    pcmanfm-locale-en-gb \
    shutdown-desktop \
    x11vnc \
 "
PYTHON_EXTRAS = " \
    python-pip \
    python-tensorflow \
    "

IMAGE_INSTALL += " \
    ${DEEP_LEARING_TOOLS} \
    ${PYTHON_EXTRAS} \
    ${X11_EXTRAS} \
    ${X11_PACKAGES} \
"

export IMAGE_BASENAME = "cuda-nano-image"


