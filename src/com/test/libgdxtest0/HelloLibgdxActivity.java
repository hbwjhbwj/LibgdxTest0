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
	final static boolean whichOne = true;// false�ߵ�һ�֣�true�ߵڶ���

	/**
	 * ����onCreate() ��һ���ǽ�libgdx������viewǶ��main.xml��RelativeLayout�У�����addView(view)
	 * �ڶ����ǽ�libgdx������viewǶ��WindowManager�У�����addView(view,param)
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (!whichOne) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.main);

			// ��ȡRealtiveLayout�����ñ���
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.test_rl);
			rl.setBackgroundResource(R.drawable.smile);

			// ����config���ԣ�������Ϊ������͸��
			AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
			// ���ñ�����͸��
			cfg.a = cfg.b = cfg.g = cfg.r = 8;

			// ���²����뱳��͸���޹�
			// cfg.numSamples = 16;// number of samples for CSAA/MSAA, 2 is
			// cfg.useAccelerometer = false;
			// cfg.useCompass = false;
			// cfg.useGL20 = true;

			// libgdx ��View,��SurfaceViewҲ���ԣ��Ͼ�GLSurfaceView��������
			GLsfv = (GLSurfaceView) initializeForView(new Main(), cfg);
			GLsfv.getHolder().setFormat(PixelFormat.RGBA_8888);
			// ���Թ�����setZOrderOnTop(boolean onTop)��Control whether the surface
			// view's
			// surface is placed on top of its window.
			// �ڶ��ַ����ǽ�view����WindowManager�У��������������û��Ч�ڵڶ��ַ���
			GLsfv.setZOrderOnTop(true);

			// Realtivelayout�����sfv
			rparam = new RelativeLayout.LayoutParams(720 - 16 * 2, 300);// 16��Relativelayout���ڱ߿�
			rparam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			rparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			rl.addView(GLsfv, rparam);// ����param��view�ڵ�textureҲ����֮ѹ��

		} else {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			// ���þ��е�ͼƬ
			imageView = new ImageView(HelloLibgdxActivity.this);
			// �������ò����Чѽ����
			RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
					720, 1200);
			imageView.setScaleType(ScaleType.CENTER);
			imageView.setLayoutParams(layout);
			Drawable d = this.getResources().getDrawable(R.drawable.ic_launcher);
			imageView.setImageDrawable(d);// ����ͼƬ

			// RelativeLayout�����imageView
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.test_rl);
			rl.setBackgroundResource(R.drawable.smile);
			rl.addView(imageView);

			AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
			cfg.a = cfg.b = cfg.g = cfg.r = 8;
			cfg.useGL20 = true;

			wparam = new WindowManager.LayoutParams();
			wparam.type = WindowManager.LayoutParams.TYPE_PHONE; // ϵͳ��ʾ����,��Ҫ
			wparam.format = PixelFormat.RGBA_8888;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_FULLSCREEN;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // �Ű治������
			wparam.flags = wparam.flags | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON; // ������
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
			//��addView()����onResume()�Ǹ������о�
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