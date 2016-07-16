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
	protected float scaleX;//背景图片缩放比例
	protected float scaleY;
	protected MainActivity mainActivity;
	
	protected Canvas canvas;//画布
	protected Paint paint;//画笔
	protected SurfaceHolder surfaceHolder;
	
	protected Thread thread;//绘画线程
	protected boolean threadFlag;//标记线程运行
	
	
	public BaseView(Context context) {
		super(context);
		mainActivity=(MainActivity) context;
		surfaceHolder=getHolder();
		surfaceHolder.addCallback(this);
		paint=new Paint();
	}

	//线程运行的方法
	@Override
	public void run() {}

	//初始化图片资源
	public void initBitmap() {}
	//释放图片资源
		public void release() {}
		//绘图方法
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
