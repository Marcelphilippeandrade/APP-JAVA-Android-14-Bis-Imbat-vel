package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;

import br.com.marcelphilippe.bis14vs100meteoros.R;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ShootEngineDelegate;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class Player extends CCSprite {

    float positionX = screenWidth()/2;
    // Era 50; mas coloquie 100 para posicionar melhor o player na tela
    float positionY = 100;
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

    public void explode() {
        SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.over);

        // Para o agendamento
        this.unschedule("update");

        // Cria efeitos
        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 2f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1, a2);

        // Roda os efeitos
        this.runAction(CCSequence.actions(s1));
    }
}
