package com.test.libgdxtest0;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;

public class PixmapTextUtil {
	private Canvas mCanvas;
	private Paint mPaint;
	private Bitmap mBitmap;
	private Pixmap mPixmap;
	private int[] mColors = new int[2];
	private String mText = "";
	
	private int bitmap_w = 0;
	private int bitmap_h = 0;
	private int textSize = 0;
	public PixmapTextUtil(){
		bitmap_w = 42;
		bitmap_h = 36;
		mText = "Test";

		mPaint = getMPaint();
		//创建一个Bitmap,作为图片源
		mBitmap = Bitmap.createBitmap((int)getTextLength(mText), (int)getTextTopHeight(), Config.ARGB_8888);
		//将Bitmap放到画布上，准备做画。。
		mCanvas = new Canvas(mBitmap);
	}
	
	/**
	 * 生成文字Pixmap
	 * @return
	 */
	public Pixmap GeneratePixmap(){
		bitmap_w = (int)(bitmap_w - getTextLength(mText))/2;
		bitmap_h = (int)((bitmap_h - getTextHeight())/2);
//		Gdx.app.log("tX", "tX is " + bitmap_w);
//		Gdx.app.log("tY", "tY is " + bitmap_h);
		//坐标的原点是在左上角，向下是x正方向，向右是y正方向
		//但是字体绘制时是以左下角为原点。。
		mCanvas.drawText(mText, 0, getTextHeight(), mPaint);
		byte[] bitmapBytes = Bitmap2Bytes(mBitmap);
		mPixmap = new Pixmap(bitmapBytes, 0, bitmapBytes.length);
		return mPixmap;
	}
	
	/**
	 * 根据画笔设置算出文字宽度
	 * @param Text
	 * @return 返回指定笔和指定字符串的长度
	 */
	private float getTextLength(String Text){
		return mPaint.measureText(Text);
	}
	
	/**
	 * 根据画笔设置算出文字高度
	 * @param Paint
	 * @return 返回指定笔的文字高度
	 */
	private float getTextHeight()  {    
        FontMetrics fm = mPaint.getFontMetrics();
//        Gdx.app.log("getTextHeight", "descent : ascent" + fm.descent + ":" + fm.ascent + ":" + fm.leading);
        return fm.descent - fm.ascent;    
    }
	

	
	/**
	 * 根据画笔设置算出文字高度
	 * @param Paint
	 * @return 返回指定笔的文字高度
	 */
	private float getTextTopHeight()  {    
        FontMetrics fm = mPaint.getFontMetrics();
//        Gdx.app.log("getTextTopHeight", "bottom : top" + fm.bottom + ":" + fm.top + ":" + fm.leading);
        return fm.bottom - fm.top;    
    }
	
	/**
	 * 
	 * @param Paint
	 * @return 返回指定笔 离文字顶部的基准距离
	 */
	private float getFontLeading()  {       
        FontMetrics fm = mPaint.getFontMetrics();
        //单行时没有leading值,即行间距=0
        return fm.leading- fm.ascent;  
    }

	/**
	 * 设置画笔的属性文字高度
	 * @return
	 */
	private Paint getMPaint() {
		textSize = 36;
		mColors[0] = Color.RED;
		
		mPaint = new TextPaint();
		mPaint.setColor(Color.CYAN);
		mPaint.setTextAlign(Paint.Align.LEFT);//左对齐
		mPaint.setTextSize(textSize);//设置画笔字体大小
		mPaint.setColor(mColors[0]);//设置画笔字体颜色
		mPaint.setStyle(Paint.Style.STROKE);//设置画笔字体为空心,设置后StrokeWidth才有效
		mPaint.setStrokeWidth(0f);//设置画笔字体笔触宽度，Style设置成STROKE后才有效
//		mPaint.setTypeface(font);//设置字体
		mPaint.setAntiAlias(true);//去除锯齿  
		mPaint.setFilterBitmap(true);//对位图进行滤波处理 
		return mPaint;
	}

	/**
	 * 生成Bitmap的字节数组
	 * @param bitmap
	 * @return
	 */
	private byte[] Bitmap2Bytes(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] tmp = baos.toByteArray();
		try {
			baos.close();
			baos = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
