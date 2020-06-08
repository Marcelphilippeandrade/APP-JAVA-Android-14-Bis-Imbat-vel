package br.com.marcelphilippe.bis14vs100meteoros;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import br.com.marcelphilippe.bis14vs100meteoros.game.scenes.TitleScreen;

public class MainActivity extends Activity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // definindo orientação como landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // configura a tela
        CCGLSurfaceView glSurfaceView = new CCGLSurfaceView(this);
        setContentView(glSurfaceView);
        CCDirector.sharedDirector().attachInView(glSurfaceView);

        // configura CCDirector
        CCDirector.sharedDirector().setScreenSize(320, 480);

        // abre tela principal
        CCScene scene = new TitleScreen().scene();
        CCDirector.sharedDirector().runWithScene(scene);
    }
}

