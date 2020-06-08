package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.nodes.CCSprite;

import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;

import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class Player extends CCSprite {

    float positionX = screenWidth()/2;
    float positionY = 50;

    public Player(){
        super(Assets.NAVE);
        setPosition(positionX, positionY);
    }

    /*public void setDelegate(ShootEngineDelegate delegate) {
        this.delegate = delegate;
    }*/
}
