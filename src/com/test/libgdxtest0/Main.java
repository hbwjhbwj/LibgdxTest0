package com.test.libgdxtest0;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Main implements ApplicationListener {
	// 绘图用的SpriteBatch
	private SpriteBatch batch;

	private Texture tt;
	private Pixmap mPixmap;
	private StageTest mStage;
	private Player p;

	@Override
	public void create() {
		batch = new SpriteBatch(); // 实例化
		mStage = new StageTest(400, 800, false);
		mStage.addActor(new Player());
		// mStage.addActor(new Player2());
		
		InputAdapterTest inputProcessor = new InputAdapterTest();
//		Gdx.input.setInputProcessor(inputProcessor);
		//@1
		Gdx.input.setInputProcessor(mStage);
	
		//@2
//		InputMultiplexer imp = new InputMultiplexer();
//		imp.addProcessor(mStage);
//		Gdx.input.setInputProcessor(imp);
	}

	@Override
	public void dispose() {
		batch.dispose();
		tt.dispose();
		mStage.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("Main","here is Main method pause");
	}

	@Override
	public void render() {
		/**
		 * Test_01 SpriteBatch
		 */
		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // 清屏
		// tt = new Texture(Gdx.files.internal("root.png"));
		// batch.begin();
		// batch.draw(tt, 0, 0);
		// batch.end();

		/**
		 * Test_02 Stage
		 */
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	
	//自定义Actor,继承Actor
	public class Player extends Actor {

		private Pixmap mPixmap;
		private Texture mTexture;
		private TextureRegion mTextureRegion;
		private Sprite mSprite;

		private float timer;

		public Player() {
			//#利用Pixmap初始化图片时，手机端关屏调用pause()后，会清空掉图片，变成白色
			//#可以直接利用new Texture(Gdx.files.internal("root.png"))
			mPixmap = new Pixmap(Gdx.files.internal("root.png"));
			mTexture = new Texture(mPixmap);
			mTextureRegion = new TextureRegion(mTexture, 40, 300, 200, 300);
			Log.i("GETU", "msg" + mTextureRegion.getV() + "   "
					+ mTextureRegion.getRegionY());
			mSprite = new Sprite(mTextureRegion);
			mSprite.setPosition(0, 0);
			Gdx.app.log("form Player",
					"width: " + mTextureRegion.getRegionWidth() + " "
							+ mTextureRegion.getRegionHeight());
//			setWidth(mTextureRegion.getRegionWidth());
//			setHeight(mTextureRegion.getRegionHeight());
		}

		@Override
		public void act(float arg0) {
			super.act(arg0);
			// Log.i("Player", "here is player method act(float");
		}

		@Override
		public boolean fire(Event event) {
			Gdx.app.log("Player", "here is player method fire");
			return super.fire(event);
		}

		@Override
		public Actor hit(float x, float y, boolean touchable) {
			Gdx.app.log("Player", "here is player method hit");
			return super.hit(x, y, touchable);
		}

		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {

			// timer += Gdx.graphics.getDeltaTime();
			// // 1.移动
			// if (timer < 3) {
			// // 每秒移动50像素
			// mSprite.translateX(50 * Gdx.graphics.getDeltaTime());
			// }
			// // 2.放缩
			// else if (timer > 3 && timer < 6) {
			// // 每秒xy方向上放大1倍
			// mSprite.setPosition(0, 0);
			// mSprite.scale(1 * Gdx.graphics.getDeltaTime());
			// }
			// // 3.翻转
			// else if (timer > 6 && timer < 9) {
			// // 每秒旋转90度
			// mSprite.setScale(1, 1);
			// mSprite.rotate(90 * Gdx.graphics.getDeltaTime());
			// } else {
			// timer = 0;
			// mSprite.setPosition(480 / 2, 800 / 2);
			// mSprite.setScale(1, 1);
			// mSprite.setRotation(0);
			// }
			// Gdx.app.log("Player", "here is player method draw");
//			mSprite.draw(batch);
			if(mSprite != null && mSprite.getTexture() != null){
				batch.draw(mTextureRegion, 20, 20);
				Gdx.app.log("Player", "here is Palyer method draw()");
			}
		}

	}

	public class Player2 extends Actor {

		private Pixmap mPixmap;
		private Texture mTexture;
		private TextureRegion mTextureRegion;
		private Sprite mSprite;

		private float timer;

		public Player2() {
			mPixmap = new Pixmap(Gdx.files.internal("root.png"));
			mTexture = new Texture(mPixmap);
			mTextureRegion = new TextureRegion(mTexture, 0, 0, 200, 200);
			Log.i("GETU", "msg" + mTextureRegion.getV() + "   "
					+ mTextureRegion.getRegionY());
			mSprite = new Sprite(mTextureRegion);
			mSprite.setPosition(100, 100);
		}

		@Override
		public void act(float arg0) {
			super.act(arg0);
			// Log.i("Playe2r", "here is player method act(float");
		}

		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {

			// timer += Gdx.graphics.getDeltaTime();
			// // 1.移动
			// if (timer < 3)
			// {
			// // 每秒移动50像素
			// mSprite.translateX(50 * Gdx.graphics.getDeltaTime());
			// }
			// // 2.放缩
			// else if (timer > 3 && timer < 6)
			// {
			// // 每秒xy方向上放大1倍
			// mSprite.setPosition(480 / 2, 800 / 2);
			// mSprite.scale(1 * Gdx.graphics.getDeltaTime());
			// }
			// // 3.翻转
			// else if (timer > 6 && timer < 9)
			// {
			// // 每秒旋转90度
			// mSprite.setScale(1, 1);
			// mSprite.rotate(90 * Gdx.graphics.getDeltaTime());
			// }
			// else
			// {
			// timer = 0;
			// mSprite.setPosition(480 / 2, 800 / 2);
			// mSprite.setScale(1, 1);
			// mSprite.setRotation(0);
			// }

			mSprite.draw(batch);
		}

	}
}
