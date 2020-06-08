package br.com.marcelphilippe.bis14vs100meteoros.game.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.List;

import br.com.marcelphilippe.bis14vs100meteoros.Button.Button;
import br.com.marcelphilippe.bis14vs100meteoros.Button.GameButtons;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.engines.MeteorsEngine;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ButtonDelegate;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.MeteorsEngineDelegate;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ShootEngineDelegate;
import br.com.marcelphilippe.bis14vs100meteoros.objects.Meteor;
import br.com.marcelphilippe.bis14vs100meteoros.objects.Player;
import br.com.marcelphilippe.bis14vs100meteoros.objects.Shoot;

import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenHeight;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class GameScene extends CCLayer implements MeteorsEngineDelegate, ShootEngineDelegate {

    private ScreenBackground background;
    private MeteorsEngine meteorsEngine;
    private CCLayer meteorsLayer;
    private List meteorsArray;
    private CCLayer playerLayer;
    private Player player;
    private CCLayer shootsLayer;
    private ArrayList shootsArray;

    private GameScene() {
        this.background = new ScreenBackground(Assets.BACKGROUND);
        this.background.setPosition(screenResolution(CGPoint.ccp(screenWidth() / 2.0f, screenHeight() / 2.0f)));
        this.addChild(this.background);

        this.meteorsLayer = CCLayer.node();
        this.addChild(this.meteorsLayer);

        this.playerLayer = CCLayer.node();
        this.addChild(this.playerLayer);

        this.shootsLayer = CCLayer.node();
        this.addChild(this.shootsLayer);
        this.setIsTouchEnabled(true);

        GameButtons gameButtonsLayer = GameButtons.gameButtons();
        gameButtonsLayer.setDelegate(this);
        this.addChild(gameButtonsLayer);

        this.addGameObjects();

    }

    public static CCScene createGame() {
        CCScene scene = CCScene.node();
        GameScene layer = new GameScene();
        scene.addChild(layer);
        return scene;
    }

    @Override
    public void createMeteor(Meteor meteor, float x, float y, float vel, double ang, int vl) {
        this.meteorsLayer.addChild(meteor);
        meteor.start();
        this.meteorsArray.add(meteor);
    }

    public void createMeteor(Meteor meteor){
        this.meteorsLayer.addChild(meteor);
        meteor.start();
        this.meteorsArray.add(meteor);
    }

    private void addGameObjects() {
        this.meteorsArray = new ArrayList();
        this.meteorsEngine = new MeteorsEngine();

        this.player = new Player();
        this.playerLayer.addChild(this.player);

        this.shootsArray = new ArrayList();
        this.player.setDelegate(this);
    }

    public void onEnter() {
        super.onEnter();
        this.startEngines();
    }

    private void startEngines() {
        this.addChild(this.meteorsEngine);
        this.meteorsEngine.setDelegate(this);
    }

    public boolean shoot(){
        player.shoot();
        return true;
    }

    public void moveLeft() {
        player.moveLeft();
    }

    public void moveRight() {
        player.moveRight();
    }

    @Override
    public void createShoot(Shoot shoot) {
        this.shootsLayer.addChild(shoot);
        shoot.setDelegate(this);
        shoot.start();
        this.shootsArray.add(shoot);
    }
}
