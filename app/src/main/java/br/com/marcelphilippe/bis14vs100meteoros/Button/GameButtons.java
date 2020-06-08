package br.com.marcelphilippe.bis14vs100meteoros.Button;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import br.com.marcelphilippe.bis14vs100meteoros.config.Assets;
import br.com.marcelphilippe.bis14vs100meteoros.game.scenes.GameScene;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.ButtonDelegate;

import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenResolution;
import static br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings.screenWidth;

public class GameButtons extends CCLayer implements ButtonDelegate {

    private Button leftControl;
    private Button rightControl;
    private Button shootButton;

    private GameScene delegate;

    public static GameButtons gameButtons() {
        return new GameButtons();
    }

    public GameButtons() {
        // Habilita o toque na tela
        this.setIsTouchEnabled(true);

        // Cria os botões
        this.leftControl = new Button(Assets.LEFTCONTROL);
        this.rightControl = new Button(Assets.RIGHTCONTROL);
        this.shootButton = new Button(Assets.SHOOTBUTTON);

        // Configura as delegações
        this.leftControl.setDelegate(this);
        this.rightControl.setDelegate(this);
        this.shootButton.setDelegate(this);

        // Configura posições
        setButtonspPosition();

        // Adiciona os botões na tela
        addChild(leftControl);
        addChild(rightControl);
        addChild(shootButton);
    }

    private void setButtonspPosition() {
        // Posição dos botões
        leftControl.setPosition(screenResolution(CGPoint.ccp( 40 , 40 ))) ;
        rightControl.setPosition(screenResolution(CGPoint.ccp( 100 , 40 ))) ;
        shootButton.setPosition(screenResolution(CGPoint.ccp( screenWidth() -40 , 40 )));
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
    }
}
