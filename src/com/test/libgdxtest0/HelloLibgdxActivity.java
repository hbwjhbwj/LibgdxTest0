package com.test.libgdxtest0;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.libgdxtest.R;

public class HelloLibgdxActivity extends AndroidApplication {
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams wparam;
	private RelativeLayout.LayoutParams rparam;
	private GLSurfaceView GLsfv;
	private Handler mHandler;
	private ImageView imageView;
	final static boolean whichOne = true;// false走第一种，true走第二种

	/**
	 * 两种onCreate() 第一种是将libgdx创建的view嵌入main.xml的RelativeLayout中，利用addView(view)
	 * 第二种是将libgdx创建的view嵌入WindowManager中，利用addView(view,param)
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (!whichOne) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.main);

			// 获取RealtiveLayout并设置背景
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.test_rl);
			rl.setBackgroundResource(R.drawable.smile);

			// 定义config属性，并设置为背景是透明
			AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
			// 设置背景是透明
			cfg.a = cfg.b = cfg.g = cfg.r = 8;

			// 以下参数与背景透明无关
			// cfg.numSamples = 16;// number of samples for CSAA/MSAA, 2 is
			// cfg.useAccelerometer = false;
			// cfg.useCompass = false;
			// cfg.useGL20 = true;

			// libgdx 的View,是SurfaceView也可以，毕竟GLSurfaceView是其子类
			GLsfv = (GLSurfaceView) initializeForView(new Main(), cfg);
			GLsfv.getHolder().setFormat(PixelFormat.RGBA_8888);
			// 来自官网：setZOrderOnTop(boolean onTop)：Control whether the surface
			// view's
			// surface is placed on top of its window.
			// 第二种方法是将view放在WindowManager中，这里加上这个设置会等效于第二种方法
			GLsfv.setZOrderOnTop(true);

			// Realtivelayout里添加sfv
			rparam = new RelativeLayout.LayoutParams(720 - 16 * 2, 300);// 16是Relativelayout的内边框
			rparam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			rparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			rl.addView(GLsfv, rparam);// 设置param后，view内的texture也会随之压缩

		} else {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			// 设置居中的图片
			imageView = new ImageView(HelloLibgdxActivity.this);
			// 这个设置貌似无效呀？？
			RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
					720, 1200);
			imageView.setScaleType(ScaleType.CENTER);
			imageView.setLayoutParams(layout);
			Drawable d = this.getResources().getDrawable(R.drawable.ic_launcher);
			imageView.setImageDrawable(d);// 设置图片

			// RelativeLayout里添加imageView
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.test_rl);
			rl.setBackgroundResource(R.drawable.smile);
			rl.addView(imageView);

			AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
			cfg.a = cfg.b = cfg.g = cfg.r = 8;
			cfg.useGL20 = true;

			wparam = new WindowManager.LayoutParams();
			wparam.type = WindowManager.LayoutParams.TYPE_PHONE; // 系统提示类型,重要
			wparam.format = PixelFormat.RGBA_8888;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_FULLSCREEN;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON; // 唤醒者
			wparam.alpha = 0.0f;
			wparam.gravity = Gravity.LEFT | Gravity.TOP;
			wparam.x = 0;
			wparam.y = 0;
			wparam.width = 720 - 16 * 2;
//			wparam.height = 300;
			Point p = new Point();
			getWindowManager().getDefaultDisplay().getSize(p);
			wparam.height = p.y/2;
			GLsfv = (GLSurfaceView) initializeForView(new Main(), cfg);
			GLsfv.getHolder().setFormat(PixelFormat.RGBA_8888);
			mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
			//将addView()放入onResume()是更合理，感觉
			//			mWindowManager.addView(GLsfv, wparam);

		}

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					// TODO STH IN MAIN UI THREAD
					break;
				}
			}
		};
		new Thread(new mThread()).start();

	}

	@Override
	protected void onPause() {
		super.onPause();
		if(mWindowManager != null){
			mWindowManager.removeView(GLsfv);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(mWindowManager != null && GLsfv != null){
			mWindowManager.addView(GLsfv, wparam);
		}
	}

	class mThread implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			Message message = new Message();
			message.what = 1;

			mHandler.sendMessage(message);
		}
	}
}