package com.test.libgdxtest0;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyActorListener extends InputListener {

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		boolean temp = super.touchDown(event, x, y, pointer, button);
		Gdx.app.log("MyActorListener", "here is Event boolean is " + temp);
		return temp;
	}

}
