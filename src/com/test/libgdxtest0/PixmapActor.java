package com.test.libgdxtest0;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.text.TextPaint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PixmapActor extends Actor {

	Texture text = null;
	Paint fontPaint = new TextPaint();
	TextureRegion textR = null;
	Sprite mSprite = null;

	public PixmapActor(){
		fontPaint.setStrokeWidth(3f);
		fontPaint.setTextSize(40f);
		fontPaint.setTextAlign(Paint.Align.LEFT);
		fontPaint.setAntiAlias(true);
//		this.setPosition(0, 200);
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		PixmapTextUtil ptu = new PixmapTextUtil();
		text = new Texture(ptu.GeneratePixmap());
//		textR = new TextureRegion(text, 300, 300, 300, 300);
//		mSprite = new Sprite(textR);
		//为什么是长方形???
		batch.draw(text, 0, 200, 100, 100);
		super.draw(batch, parentAlpha);
	}
}
