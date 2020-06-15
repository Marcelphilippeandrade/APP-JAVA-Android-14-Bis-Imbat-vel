package br.com.marcelphilippe.bis14vs100meteoros.screens;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import br.com.marcelphilippe.bis14vs100meteoros.Button.Button;
import br.com.marcelphilippe.bis14vs100meteoros.R;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.game.scenes.TitleScreen;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ButtonDelegate;

import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenHeight;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class FinalScreen extends CCLayer implements ButtonDelegate {
    private BackgroundScreen background;
    private Button beginButton;

    public CCScene scene() {
        CCScene scene = CCScene.node();
        scene.addChild(this);
        return scene;
    }

    public FinalScreen() {
        // background
        this.background = new BackgroundScreen(Assets.BACKGROUND);
        this.background.setPosition(screenResolution(CGPoint.ccp(screenWidth() / 2.0f, screenHeight() / 2.0f)));

        this.addChild(this.background);

        // som
        SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), R.raw.finalend, true);

        // imagem
        CCSprite title = CCSprite.sprite(Assets.FINALEND);
        title.setPosition(screenResolution(CGPoint.ccp( screenWidth() /2 , screenHeight() - 130 )));

        this.addChild(title);

        this.setIsTouchEnabled(true);
        this.beginButton = new Button(Assets.PLAY);
        this.beginButton.setPosition(screenResolution(CGPoint.ccp(screenWidth() /2 ,screenHeight() - 300 ))) ;
        this.beginButton.setDelegate(this);
        addChild(this.beginButton);
    }

    @Override
    public void buttonClicked(Button sender) {
        if (sender.equals(this.beginButton)) {
            SoundEngine.sharedEngine().pauseSound();
            CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
        }
    }
}
