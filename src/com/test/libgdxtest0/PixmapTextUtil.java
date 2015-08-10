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
		//����һ��Bitmap,��ΪͼƬԴ
		mBitmap = Bitmap.createBitmap((int)getTextLength(mText), (int)getTextTopHeight(), Config.ARGB_8888);
		//��Bitmap�ŵ������ϣ�׼����������
		mCanvas = new Canvas(mBitmap);
	}
	
	/**
	 * ��������Pixmap
	 * @return
	 */
	public Pixmap GeneratePixmap(){
		bitmap_w = (int)(bitmap_w - getTextLength(mText))/2;
		bitmap_h = (int)((bitmap_h - getTextHeight())/2);
//		Gdx.app.log("tX", "tX is " + bitmap_w);
//		Gdx.app.log("tY", "tY is " + bitmap_h);
		//�����ԭ���������Ͻǣ�������x������������y������
		//�����������ʱ�������½�Ϊԭ�㡣��
		mCanvas.drawText(mText, 0, getTextHeight(), mPaint);
		byte[] bitmapBytes = Bitmap2Bytes(mBitmap);
		mPixmap = new Pixmap(bitmapBytes, 0, bitmapBytes.length);
		return mPixmap;
	}
	
	/**
	 * ���ݻ�������������ֿ��
	 * @param Text
	 * @return ����ָ���ʺ�ָ���ַ����ĳ���
	 */
	private float getTextLength(String Text){
		return mPaint.measureText(Text);
	}
	
	/**
	 * ���ݻ�������������ָ߶�
	 * @param Paint
	 * @return ����ָ���ʵ����ָ߶�
	 */
	private float getTextHeight()  {    
        FontMetrics fm = mPaint.getFontMetrics();
//        Gdx.app.log("getTextHeight", "descent : ascent" + fm.descent + ":" + fm.ascent + ":" + fm.leading);
        return fm.descent - fm.ascent;    
    }
	

	
	/**
	 * ���ݻ�������������ָ߶�
	 * @param Paint
	 * @return ����ָ���ʵ����ָ߶�
	 */
	private float getTextTopHeight()  {    
        FontMetrics fm = mPaint.getFontMetrics();
//        Gdx.app.log("getTextTopHeight", "bottom : top" + fm.bottom + ":" + fm.top + ":" + fm.leading);
        return fm.bottom - fm.top;    
    }
	
	/**
	 * 
	 * @param Paint
	 * @return ����ָ���� �����ֶ����Ļ�׼����
	 */
	private float getFontLeading()  {       
        FontMetrics fm = mPaint.getFontMetrics();
        //����ʱû��leadingֵ,���м��=0
        return fm.leading- fm.ascent;  
    }

	/**
	 * ���û��ʵ��������ָ߶�
	 * @return
	 */
	private Paint getMPaint() {
		textSize = 36;
		mColors[0] = Color.RED;
		
		mPaint = new TextPaint();
		mPaint.setColor(Color.CYAN);
		mPaint.setTextAlign(Paint.Align.LEFT);//�����
		mPaint.setTextSize(textSize);//���û��������С
		mPaint.setColor(mColors[0]);//���û���������ɫ
		mPaint.setStyle(Paint.Style.STROKE);//���û�������Ϊ����,���ú�StrokeWidth����Ч
		mPaint.setStrokeWidth(0f);//���û�������ʴ���ȣ�Style���ó�STROKE�����Ч
//		mPaint.setTypeface(font);//��������
		mPaint.setAntiAlias(true);//ȥ�����  
		mPaint.setFilterBitmap(true);//��λͼ�����˲����� 
		return mPaint;
	}

	/**
	 * ����Bitmap���ֽ�����
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
