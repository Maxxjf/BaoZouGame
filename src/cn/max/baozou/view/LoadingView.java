package cn.max.baozou.view;

import cn.max.baozou.R;
import cn.max.baozou.config.Constants;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.SurfaceHolder;

public class LoadingView extends BaseView {
	
	private Bitmap bgImg;
	private Bitmap logoImg;
	private Bitmap textImg;
	
	private float logoImgX;
	private float logoImgY;
	
	private float textImgX;
	private float textImgY;
	
	private String author = "谢晋锋";//绘制文字区域
	private Rect rect;
	
	private float strWidth;
	private float strHeight;
	
	private float textX;
	private float textY;
	public LoadingView(Context context) {
		super(context);
		rect=new Rect();
		thread=new Thread(this);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.surfaceCreated(holder);
		initBitmap();
		if (thread.isAlive()) {
			thread.start();
		}else {
			thread=new Thread(this);
			thread.start();
		}
	}
	//初始化图片资源
	@Override
	public void initBitmap() {
	     bgImg=BitmapFactory.decodeResource(getResources(),R.drawable.bg);
	     logoImg=BitmapFactory.decodeResource(getResources(), R.drawable.bao0);
//	     textImg=BitmapFactory.decodeResource(getResources(), R.drawable.text_logo);
	     
	     scaleX=Constants.SCREEN_WIDTH/bgImg.getWidth();
	     scaleY=Constants.SCREEN_HEIGHT/bgImg.getHeight();
	     this.logoImgX = (Constants.SCREEN_WIDTH - this.logoImg.getWidth()) / 2;
	     this.logoImgY = Constants.SCREEN_HEIGHT / 2 - this.logoImg.getWidth() * 0;
	    
//	     this.textImgX = (Constants.SCREEN_WIDTH - this.textImg.getWidth()) / 2;
//			this.textImgY = Constants.SCREEN_HEIGHT / 2 - this.textImg.getHeight() * 2;
			
//			this.paint.setTextSize(40);
//			this.paint.getTextBounds(author, 0, author.length(), rect);
			
//			this.strWidth = rect.width();
//			this.strHeight = rect.height();
			
//			this.textX = Constants.SCREEN_WIDTH / 2 - this.strWidth / 2;
//			this.textY = Constants.SCREEN_HEIGHT / 2 + logoImg.getHeight() + this.strHeight * 2;
	}
	@Override
	public void run() {
		while (threadFlag) {
		drawSelf();
		try {
			Thread.sleep(cn.max.baozou.config.Config.LOADING_GAME_INTERVAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadFlag=false;
		}
	  mainActivity.getHandler().sendEmptyMessage(cn.max.baozou.config.Config.TO_GAME_VIEW);
	}
	@Override
	public void drawSelf() {
		try {
			canvas=surfaceHolder.lockCanvas();
			canvas.save();
			canvas.scale(scaleX, scaleY);
			canvas.drawBitmap(bgImg, 0, 0,paint);
			canvas.restore();
//			canvas.drawBitmap(textImg, textImgX, textImgY, paint);
			canvas.drawBitmap(logoImg, logoImgX, logoImgY, paint);
//			canvas.drawText(author, textX, textY, paint);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		super.surfaceDestroyed(holder);
		release();
	}
	@Override
	public void release() {
		if(!this.bgImg.isRecycled()){
			this.bgImg.recycle();
		}
		if(!this.logoImg.isRecycled()){
			this.logoImg.recycle();
		}
//		if(!this.textImg.isRecycled()){
//			this.textImg.recycle();
//		}
	}
}
