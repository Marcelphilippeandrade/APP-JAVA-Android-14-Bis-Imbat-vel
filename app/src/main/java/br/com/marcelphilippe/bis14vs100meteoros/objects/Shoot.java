package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import br.com.marcelphilippe.bis14vs100meteoros.R;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ShootEngineDelegate;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;

public class Shoot extends CCSprite {

    private ShootEngineDelegate delegate;
    private float positionX, positionY;

    public Shoot(float positionX, float positionY){
        super(Assets.SHOOT);
        this.positionX = positionX;
        this.positionY = positionY;
        setPosition(this.positionX, this.positionY);
        this.schedule("update");
    }

    public void update(float dt) {
        positionY += 2;
        this.setPosition(screenResolution(CGPoint.ccp(positionX, positionY )));
    }

    public void setDelegate(ShootEngineDelegate delegate) {
        this.delegate = delegate;
    }

    public void start() {
        SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.shoot);
        System.out.println("shoot moving!");
    }

    public void explode() {

        // Remove do array
        this.delegate.removeShoot(this);

        // Para o agendamento
        this.unschedule("update");

        // Cria efeitos
        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 2f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1, a2);

        // Função a ser executada após efeito
        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");

        // Roda efeito
        this.runAction(CCSequence.actions(s1, c1));
    }

    public void removeMe() {
        this.removeFromParentAndCleanup(true);
    }
}
