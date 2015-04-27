package com.test.libgdxtest0;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class StageTest extends Stage {

	
	private Image imageButton;
	// 实际上loop的是调用act的render()方法
	@Override
	public void act(float arg0) {
//		Log.i("StageTest", "here is StageTest method act(float)" + arg0);
		super.act(arg0);
	}

	public StageTest(float arg0, float arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		
		
		
		imageButton = new Image(
				new TextureRegion(
						new Texture(Gdx.files.internal("root.png")),0,0,50,50));
		Gdx.app.log("Gdx.graphics", "Width is " + Gdx.graphics.getWidth());
		imageButton.setPosition(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/2);
		this.addActor(imageButton);
		boolean isAdd = imageButton.addListener(new MyActorListener());
		Gdx.app.log("StageTest","here is isAdd" + isAdd);
	}

	@Override
	public void act() {
		Log.i("StageTest", "here is StageTest method act");
		super.act();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean temp = super.touchDown(screenX, screenY, pointer, button); 
		Gdx.app.log("StageTest", "here is StageTest method touchDown boolean is" + temp);
		return temp;
//		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("StageTest", "here is StageTest method touchUP");
//		return super.touchUp(screenX, screenY, pointer, button);
		return false;
	}

}
