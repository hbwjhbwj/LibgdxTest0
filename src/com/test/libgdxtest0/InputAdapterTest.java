package com.test.libgdxtest0;

import android.util.Log;

import com.badlogic.gdx.InputAdapter;

public class InputAdapterTest extends InputAdapter {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Log.i("InputAdapterTest", "touchDown" + pointer + "," + button);
//		return super.touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		Log.i("InputAdapterTest", "keyDown Event" + keycode);
		return super.keyDown(keycode);
	}

}
