package com.test.libgdxtest0;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class HelloLibgdxActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize(new Main(), false);
		Gdx.app.log("HelloLibgdxActivity", "here is Oncreate");
	}

}
