package com.lwansbrough.RCTCamera;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.*;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.List;
import java.util.ArrayList;
import com.facebook.react.common.MapBuilder;
import com.facebook.infer.annotation.Assertions;

import java.util.Map;
public class RCTCameraViewManager extends ViewGroupManager<RCTCameraView> {
    private static final String REACT_CLASS = "RCTCamera";
    public static final int Disable_Camera = 2;
    public static final int Enable_Camera = 1;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RCTCameraView createViewInstance(ThemedReactContext context) {
        RCTCameraView view=new RCTCameraView(context);
        view.setBackgroundColor(0xFF000000);
        return view;
    }

    @ReactProp(name = "aspect")
    public void setAspect(RCTCameraView view, int aspect) {
        view.setAspect(aspect);
    }

    @ReactProp(name = "captureMode")
    public void setCaptureMode(RCTCameraView view, int captureMode) {
        // TODO - implement video mode
    }

    @ReactProp(name = "captureTarget")
    public void setCaptureTarget(RCTCameraView view, int captureTarget) {
        // No reason to handle this props value here since it's passed again to the RCTCameraModule capture method
    }

    @ReactProp(name = "type")
    public void setType(RCTCameraView view, int type) {
        //view.setCameraType(type);
    }

    @ReactProp(name = "captureQuality")
    public void setCaptureQuality(RCTCameraView view, String captureQuality) {
        view.setCaptureQuality(captureQuality);
    }

    @ReactProp(name = "torchMode")
    public void setTorchMode(RCTCameraView view, int torchMode) {
        view.setTorchMode(torchMode);
    }

    @ReactProp(name = "flashMode")
    public void setFlashMode(RCTCameraView view, int flashMode) {
        view.setFlashMode(flashMode);
    }

    @ReactProp(name = "orientation")
    public void setOrientation(RCTCameraView view, int orientation) {
        view.setOrientation(orientation);
    }

    @ReactProp(name = "captureAudio")
    public void setCaptureAudio(RCTCameraView view, boolean captureAudio) {
        // TODO - implement video mode
    }

    @ReactProp(name = "barcodeScannerEnabled")
    public void setBarcodeScannerEnabled(RCTCameraView view, boolean barcodeScannerEnabled) {
        view.setBarcodeScannerEnabled(barcodeScannerEnabled);
    }

    @ReactProp(name = "barCodeTypes")
    public void setBarCodeTypes(RCTCameraView view, ReadableArray barCodeTypes) {
        if (barCodeTypes == null) {
            return;
        }
        List<String> result = new ArrayList<String>(barCodeTypes.size());
        for (int i = 0; i < barCodeTypes.size(); i++) {
            result.add(barCodeTypes.getString(i));
        }
        view.setBarCodeTypes(result);
    }


    @Override
    public Map<String,Integer> getCommandsMap() {
    return MapBuilder.of("enableCamera",Enable_Camera,"disableCamera",Disable_Camera);
    }

    @Override
    public void receiveCommand(
        RCTCameraView view,
        int commandType,
        @Nullable ReadableArray args) {
        switch (commandType) {
        case Enable_Camera: {
          Assertions.assertNotNull(view);
          Assertions.assertNotNull(args);
          view.enableCamera();
          return;
        }
        case Disable_Camera: {
          Assertions.assertNotNull(view);
          Assertions.assertNotNull(args);
          view.disableCamera();
          return;
        }
        default:
            throw new IllegalArgumentException(String.format(
                    "Unsupported command %d received by %s.",
                    commandType,
                    getClass().getSimpleName()));
    }
}
}
