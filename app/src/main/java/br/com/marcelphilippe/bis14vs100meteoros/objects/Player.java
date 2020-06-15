package br.com.marcelphilippe.bis14vs100meteoros.objects;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import br.com.marcelphilippe.bis14vs100meteoros.R;
import br.com.marcelphilippe.bis14vs100meteoros.calibrate.Accelerometer;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.AccelerometerDelegate;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ShootEngineDelegate;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class Player extends CCSprite implements AccelerometerDelegate {

    float positionX = screenWidth()/2;
    // Era 50; mas coloquie 100 para posicionar melhor o player na tela
    float positionY = 100;
    private ShootEngineDelegate delegate;
    private Accelerometer accelerometer;
    private float currentAccelX;
    private float currentAccelY;

    private static final double NOISE = 1;

    public Player(){
        super(Assets.NAVE);
        setPosition(positionX, positionY);
        this.schedule("update");
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

        SoundEngine.sharedEngine().pauseSound();

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

    public void catchAccelerometer() {
        Accelerometer.sharedAccelerometer().catchAccelerometer();
        this.accelerometer = Accelerometer.sharedAccelerometer();
        this.accelerometer.setDelegate(this);
    }

    public void update(float dt) {
        if(this.currentAccelX < -NOISE){
            this.positionX++;
        }

        if(this.currentAccelX > NOISE){
            this.positionX--;
        }

        if(this.currentAccelY < -NOISE){
            this.positionY++;
        }

        if(this.currentAccelY > NOISE){
            this.positionY--;
        }

        // Configura posicao do aviao
        this.setPosition(CGPoint.ccp(this.positionX, this.positionY));
    }

    @Override
    public void accelerometerDidAccelerate(float x, float y) {
        System.out.println("X: " + x);
        System.out.println("Y: " + y);

        this.currentAccelX = x;
        this.currentAccelY = y;
    }
}
