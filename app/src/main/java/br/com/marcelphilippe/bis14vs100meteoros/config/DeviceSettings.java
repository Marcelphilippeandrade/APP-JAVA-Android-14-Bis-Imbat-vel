package br.com.marcelphilippe.bis14vs100meteoros.config;

import android.hardware.SensorManager;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class DeviceSettings {

    private static SensorManager sensormanager;

    public static CGPoint screenResolution(CGPoint gcPoint) {
        return gcPoint;
    }

    public static float screenWidth() {
        return winSize().width;
    }

    public static float screenHeight() {
        return winSize().height;
    }

    public static CGSize winSize() {
        return CCDirector.sharedDirector().winSize();
    }

    public static void setSensorManager(SensorManager sensormanager) {
        DeviceSettings.sensormanager = sensormanager;
    }

    public static SensorManager getSensormanager() {
        return sensormanager;
    }
}
