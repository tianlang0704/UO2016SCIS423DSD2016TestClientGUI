To use BridJ in your Android project:
- Copy libs/ to your project's root directory.
- If you're building a native library with the NDK:
    - Copy native-libs/ to your project's root directory.
    - Add the following to your jni/Android.mk file:
    
        include $(CLEAR_VARS)
        LOCAL_MODULE := bridj
        LOCAL_SRC_FILES := ../native-libs/$(TARGET_ARCH_ABI)/libbridj.so
        include $(PREBUILT_SHARED_LIBRARY)
        
- If you're not building any native library with the NDK and just want to use BridJ to bind system libraries:
    - Copy the contents of native-libs/ to libs/.

In any case, if your project is already opened in the ADT / Eclipse, close it and reopen it to make sure it picks up the correct BridJ sources and javadoc locations.

BridJ is opensource software. Please refer to LICENSE.BridJ.txt to know under which conditions you may use it.