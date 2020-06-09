package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.nodes.CCSprite;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ShootEngineDelegate;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class Player extends CCSprite {

    float positionX = screenWidth()/2;
    float positionY = 50;
    private ShootEngineDelegate delegate;

    public Player(){
        super(Assets.NAVE);
        setPosition(positionX, positionY);
    }

    public void setDelegate(ShootEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public void shoot() {
        delegate.createShoot(new Shoot(positionX, positionY));
    }

    public void moveLeft() {
        if(positionX > 30){
            positionX -= 10;
        }
        setPosition(positionX, positionY);
    }

    public void moveRight() {
        if(positionX < screenWidth() - 30){
            positionX += 10;
        }
        setPosition(positionX, positionY);
    }
}
