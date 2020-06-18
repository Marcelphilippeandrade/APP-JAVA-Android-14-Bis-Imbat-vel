package br.com.marcelphilippe.bis14vs100meteoros.Button;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;
import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.game.scenes.GameScene;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ButtonDelegate;

import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenHeight;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class GameButtons extends CCLayer implements ButtonDelegate {

    private Button leftControl;
    private Button rightControl;
    private Button shootButton;
    private Button pauseButton;
    private GameScene delegate;

    public GameButtons() {
        // Habilita o toque na tela
        this.setIsTouchEnabled(true);

        // Cria os botões
        this.leftControl = new Button(Assets.LEFTCONTROL);
        this.rightControl = new Button(Assets.RIGHTCONTROL);
        this.shootButton = new Button(Assets.SHOOTBUTTON);
        this.pauseButton = new Button(Assets.PAUSE);

        // Configura as delegações
        this.leftControl.setDelegate(this);
        this.rightControl.setDelegate(this);
        this.shootButton.setDelegate(this);
        this.pauseButton.setDelegate(this);

        // Configura posições
        setButtonspPosition();

        // Adiciona os botões na tela
        //addChild(leftControl);
        //addChild(rightControl);
        addChild(shootButton);
        addChild(pauseButton);
    }

    public static GameButtons gameButtons() {
        return new GameButtons();
    }

    private void setButtonspPosition() {
        // Posição dos botões
        leftControl.setPosition(screenResolution(CGPoint.ccp( 40 , 40 ))) ;
        rightControl.setPosition(screenResolution(CGPoint.ccp( 100 , 40 ))) ;
        shootButton.setPosition(screenResolution(CGPoint.ccp( screenWidth() -40 , 40 )));
        pauseButton.setPosition(screenResolution(CGPoint.ccp(40, screenHeight() - 30 )));
    }

    public void setDelegate(GameScene gameScene) {
        this.delegate = gameScene;
    }

    @Override
    public void buttonClicked(Button sender) {
        if (sender.equals(this.leftControl)) {
            this.delegate.moveLeft();
        }
        if (sender.equals(this.rightControl)) {
            this.delegate.moveRight();
        }
        if (sender.equals(this.shootButton)) {
            this.delegate.shoot();
        }
        if (sender.equals(this.pauseButton)) {
            this.delegate.pauseGameAndShowLayer();
        }
    }
}
