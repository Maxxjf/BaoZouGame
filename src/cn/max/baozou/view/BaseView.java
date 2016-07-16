package cn.max.baozou.view;

import cn.max.baozou.MainActivity;
import cn.max.baozou.config.Constants;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class BaseView extends SurfaceView implements Callback,Runnable{
	protected float scaleX;//����ͼƬ���ű���
	protected float scaleY;
	protected MainActivity mainActivity;
	
	protected Canvas canvas;//����
	protected Paint paint;//����
	protected SurfaceHolder surfaceHolder;
	
	protected Thread thread;//�滭�߳�
	protected boolean threadFlag;//����߳�����
	
	
	public BaseView(Context context) {
		super(context);
		mainActivity=(MainActivity) context;
		surfaceHolder=getHolder();
		surfaceHolder.addCallback(this);
		paint=new Paint();
	}

	//�߳����еķ���
	@Override
	public void run() {}

	//��ʼ��ͼƬ��Դ
	public void initBitmap() {}
	//�ͷ�ͼƬ��Դ
		public void release() {}
		//��ͼ����
		public void drawSelf() {}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Constants.SCREEN_WIDTH=getWidth();
		Constants.SCREEN_HEIGHT=getHeight();
		threadFlag=true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		threadFlag=false;
	}

	public boolean isThreadFlag() {
		return threadFlag;
	}

	public void setThreadFlag(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}
	
}
