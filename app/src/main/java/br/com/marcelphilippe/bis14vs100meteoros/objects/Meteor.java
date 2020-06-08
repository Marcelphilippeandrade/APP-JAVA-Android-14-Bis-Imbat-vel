package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import java.util.Random;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenHeight;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class Meteor extends CCSprite {

    private float x, y;

    public Meteor(String image) {
        super(image);
        x = new Random().nextInt(Math.round(screenWidth()));
        y = screenHeight();
    }

    public void start() {
        this.schedule("update");
    }

    public void update(float dt) {
        y -= 1;
        this.setPosition(screenResolution(CGPoint.ccp(x, y )));
    }
}
