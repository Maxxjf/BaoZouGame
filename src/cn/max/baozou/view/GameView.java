package cn.max.baozou.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import cn.max.baozou.R;
import cn.max.baozou.config.Config;
import cn.max.baozou.config.Constants;
import cn.max.baozou.object.BackGround;
import cn.max.baozou.object.BaoZou;
import cn.max.baozou.object.Column;

public class GameView extends BaseView {
	// 地面
	private BackGround ground;
	// 3个柱子
	private Column column1;
	private Column column2;
	private Column column3;
	// 一个暴走
	private BaoZou baozou;
 //图片资源
	 //图片资源
	private Bitmap bgImg;
	private Bitmap startImg;
	private Bitmap endImg;
	private Bitmap restartButtonImg;
	private Bitmap exitButtonImg;
//	private Bitmap noticeImg;
	private Bitmap pauseButtonImg;
	private Bitmap bigNumbersImg;
	private Bitmap smallNumbersImg;
//	private Bitmap medalImg;
	// "开始按钮"坐标
	private float startImgX;
	private float startImgY;
	private float endImgX;
	private float endImgY;
//	private float noticeImgX;
//	private float noticeImgY;
	private float restartButtonImgX;
	private float restartButtonImgY;
	private float exitButtonImgX;
	private float exitButtonImgY;
	private float pauseButtonImgX;
	private float pauseButtonImgY;
	private float bigNumbersImgX;
	private float bigNumbersImgY;
	private float smallNumbersImgX;// bestScore位置
	private float smallNumbersImgY;
	private float smallScoreX;
	private float smallScoreY;
	private float medalImgX;
	private float medalImgY;

	private boolean isStart;
	private boolean isHit;
	private boolean isOver;
	private boolean isPause;
	private boolean isWrite;

	private int score;
	private int bestScore;

	// private Rect rect;
	public GameView(Context context) {
		super(context);
		isStart = false;
		isHit = false;
		isOver = false;
		isPause = false;
		isWrite = false;
		ground = new BackGround(getResources());
		column1 = new Column(getResources(), Config.COLUMN_X_GAP * 2,
				ground.getObj_height());
		column2 = new Column(getResources(), Config.COLUMN_X_GAP
				+ column1.getObj_mid_x(), ground.getObj_height());
		column3 = new Column(getResources(), Config.COLUMN_X_GAP
				+ column2.getObj_mid_x(), ground.getObj_height());
		baozou = new BaoZou(getResources(), ground.getObj_height());
		thread = new Thread(this);
	}

	// 线程开始
	@Override
	public void run() {
		Log.i("tedu", "running");
		while (this.threadFlag) {
			if (!isHit && !isOver) {
				ground.step();
			}
			if (isStart && !isHit && !isOver) {
				column1.step();
				column2.step();
				column3.step();
			}
			 if(isStart) {
			 baozou.step();
			 }
			drawSelf();
			if (isOver) {
				threadFlag = false;
			}
			if (isPause) {
				synchronized (thread) {
					try {
						thread.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//			try {
//				Thread.sleep(1000 / 60);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		drawSelf();

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		drawNotice();

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i <= score; i++) {
//			drawResult(i);
//			try {
//				Thread.sleep(1000 / 60);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		synchronized (thread) {
//			drawMedal();
//		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		drawButton();
		isWrite = true;
	}

//	public void drawResult(int i) {
//		try {
//			canvas = surfaceHolder.lockCanvas();
//
//			drawObject();
//
//			canvas.drawBitmap(endImg, endImgX, endImgY, paint);
//
//			canvas.drawBitmap(noticeImg, noticeImgX, noticeImgY, paint);
//
//			drawScore(smallNumbersImg, smallScoreX, smallScoreY, i);
//		} catch (Exception err) {
//			err.printStackTrace();
//		} finally {
//			if (canvas != null) {
//				surfaceHolder.unlockCanvasAndPost(canvas);
//			}
//		}
//	}

	@Override
	public void drawSelf() {
		try {
			canvas = surfaceHolder.lockCanvas();
			drawObject();
			if (!isHit) {
				if (baozou.pass(column1) || baozou.pass(column2)
						|| baozou.pass(column3)) {
					score++;
				}
				if (baozou.hitColumn(column1) || baozou.hitColumn(column2)
						|| baozou.hitColumn(column3)) {
					baozou.setCurretBitmap(1);
					paint.setAlpha(50);
					paint.setColor(Color.WHITE);
					canvas.drawRect(0, 0, Constants.SCREEN_WIDTH,
							Constants.SCREEN_HEIGHT, paint);
					isHit = true;
					isOver = true;
				}
			}
			if (!isOver) {
				drawScore(bigNumbersImg, bigNumbersImgX, bigNumbersImgY, score);
			}

			if (isOver) {
				canvas.drawBitmap(endImg, endImgX, endImgY, paint);
			}
			// 暴走撞到地面
			if (!isOver) {
				if (baozou.hitGround1(ground)) {
					isOver = true;
				}
			}
			if (!isStart) {
				if (!isStart) {
					canvas.drawBitmap(startImg, startImgX, startImgY, paint);
				}
			}
			if (isStart && !isHit && !isOver) {
				if (!isPause) {
					canvas.save();
					canvas.clipRect(pauseButtonImgX, pauseButtonImgY,
							pauseButtonImgX + pauseButtonImg.getWidth(),
							pauseButtonImgY + pauseButtonImg.getHeight() / 2);
					canvas.drawBitmap(pauseButtonImg, pauseButtonImgX,
							pauseButtonImgY, paint);
					canvas.restore();
				} else {
					canvas.save();
					canvas.clipRect(pauseButtonImgX, pauseButtonImgY,
							pauseButtonImgX + pauseButtonImg.getWidth(),
							pauseButtonImgY + pauseButtonImg.getHeight() / 2);
					canvas.drawBitmap(pauseButtonImg, pauseButtonImgX,
							pauseButtonImgY - pauseButtonImg.getHeight() / 2,
							paint);
					canvas.restore();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void drawNotice() {
		try {
			canvas = surfaceHolder.lockCanvas();
			drawObject();
			canvas.drawBitmap(endImg, endImgX, endImgY, paint);
//			canvas.drawBitmap(noticeImg, noticeImgX, noticeImgY, paint);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	public void drawResulf(int i) {
		try {
			canvas = surfaceHolder.lockCanvas();

			drawObject();

			canvas.drawBitmap(endImg, endImgX, endImgY, paint);

//			canvas.drawBitmap(noticeImg, noticeImgX, noticeImgY, paint);

//			drawScore(smallNumbersImg, smallScoreX, smallScoreY, i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void drawMedal() {
		try {
			canvas = surfaceHolder.lockCanvas();

			drawObject();
			canvas.drawBitmap(endImg, endImgX, endImgY, paint);

//			canvas.drawBitmap(noticeImg, noticeImgX, noticeImgY, paint);

//			drawScore(smallNumbersImg, smallScoreX, smallScoreY, score);
//
//			drawScore(smallNumbersImg, smallNumbersImgX, smallNumbersImgY,
//					bestScore);
//
//			drawMedalImg();
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void drawButton() {
		try {
			canvas = surfaceHolder.lockCanvas();

			drawObject();

//			canvas.drawBitmap(endImg, endImgX, endImgY, paint);

//			canvas.drawBitmap(noticeImg, noticeImgX, noticeImgY, paint);

			drawScore(bigNumbersImg, bigNumbersImgX, bigNumbersImgY, score);
//
//			drawScore(smallNumbersImg, smallNumbersImgX, smallNumbersImgY,
//					bestScore);
//
//			drawMedalImg();

			canvas.drawBitmap(restartButtonImg, restartButtonImgX,
					restartButtonImgY, paint);
			canvas.drawBitmap(exitButtonImg, exitButtonImgX, exitButtonImgY,
					paint);
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void drawObject() {
		canvas.save();
		canvas.scale(scaleX, scaleY);
		canvas.drawBitmap(bgImg, 0, 0, paint);
		canvas.restore();
		// TODO 画对象
		column1.drawSelf(canvas);
		column2.drawSelf(canvas);
		column3.drawSelf(canvas);
		baozou.drawSelf(canvas);
		ground.drawSelf(canvas);
	}

//	public void drawMedalImg() {
//		canvas.save();
//		canvas.clipRect(medalImgX, medalImgY, medalImgX + medalImg.getWidth(),
//				medalImgY + medalImg.getHeight() / 2);
//		if (score >= 60) {
//			canvas.drawBitmap(medalImg, medalImgX,
//					medalImgY - medalImg.getHeight() / 2, paint);
//		} else {
//			canvas.drawBitmap(medalImg, medalImgX, medalImgY, paint);
//		}
//		canvas.restore();
//	}

	public void drawScore(Bitmap numbersImg, float x, float y, int num) {
		List<Integer> list = new ArrayList<Integer>();
		int scoreCopy = num;
		int quotient = 0;

		while ((quotient = scoreCopy / 10) != 0) {
			list.add(scoreCopy % 10);
			scoreCopy = quotient;
		}
		list.add(scoreCopy % 10);

		float posX = x;
		float posY = y;

		int len = list.size();

		float oddNumW = numbersImg.getWidth() / 10;
		float oddNumH = numbersImg.getHeight();

		posX -= len * oddNumW / 2;

		canvas.save();
		for (int i = len - 1; i >= 0; i--) {
			switch (list.get(i)) {
			case 0:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 0 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 1:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 1 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 2:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 2 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 3:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 3 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 4:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 4 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 5:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 5 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 6:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 6 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 7:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 7 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 8:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 8 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			case 9:
				canvas.clipRect(posX, posY, posX + oddNumW, posY + oddNumH);
				canvas.drawBitmap(numbersImg, posX - 9 * oddNumW, posY, paint);
				posX += oddNumW;
				canvas.restore();
				canvas.save();
				break;

			}
		}
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isHit) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (!isStart) {
					isStart = true;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				baozou.setObj_x(event.getX()-baozou.getObj_width()/2);
				baozou.setObj_y(event.getY()-baozou.getObj_height()/2);
				break;
			case MotionEvent.ACTION_UP:

				break;

			default:
				break;
			}
			if (!baozou.hitColumn(column1) && !baozou.hitColumn(column2)
					&& !baozou.hitColumn(column3)) {
				return true;
			} else {
				isHit=true;
				threadFlag=false;
				isOver=true;
				Log.i("tedu", isHit+"");
				return false;
			}
		}else {
			float x = event.getX();
			float y = event.getY();
			if(isWrite) {				
				if(x >= restartButtonImgX && x <= restartButtonImgX + restartButtonImg.getWidth() && y >= restartButtonImgY && y <= restartButtonImgY + restartButtonImg.getHeight()) {
					mainActivity.getHandler().sendEmptyMessage(Config.TO_GAME_VIEW);
					return true;
				}
				if(x >= exitButtonImgX && x <= exitButtonImgX + exitButtonImg.getWidth() && y >= exitButtonImgY && y <= exitButtonImgY + exitButtonImg.getHeight()) {
					mainActivity.getHandler().sendEmptyMessage(Config.END_GAME);
					return true;
				}
			}
			
		}
		
		return false;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.surfaceCreated(holder);
		initBitmap();
		if (thread.isAlive()) {
			thread.start();
		} else {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void initBitmap() {
		bgImg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
//		startImg = BitmapFactory.decodeResource(getResources(),
//				R.drawable.start);
		endImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.text_gameover);
		restartButtonImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.restartbutton);
		exitButtonImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.exitbutton);
//		noticeImg = BitmapFactory.decodeResource(getResources(),
//				R.drawable.notice);
		pauseButtonImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.pausebutton);
		bigNumbersImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.bignumbers);
		smallNumbersImg = BitmapFactory.decodeResource(getResources(),
				R.drawable.smallnumbers);
//		medalImg = BitmapFactory.decodeResource(getResources(),
//				R.drawable.medal);

		this.scaleX = cn.max.baozou.config.Constants.SCREEN_WIDTH
				/ bgImg.getWidth();
		this.scaleY = cn.max.baozou.config.Constants.SCREEN_HEIGHT
				/ bgImg.getHeight();

//		startImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2
//				- startImg.getWidth() / 2;
//		startImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT / 2
//				- startImg.getHeight() / 2;
		endImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2
				- endImg.getWidth() / 2;
		endImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT / 2
				- endImg.getHeight() * 3;
//		noticeImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2
//				- noticeImg.getWidth() / 2;
//		noticeImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT / 2
//				- endImg.getHeight();
		restartButtonImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2
				- restartButtonImg.getWidth() * 5 / 4;
		restartButtonImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT / 2;
		exitButtonImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2
				+ exitButtonImg.getWidth() / 4;
		exitButtonImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT / 2;
		pauseButtonImgX = 0;
		pauseButtonImgY = cn.max.baozou.config.Constants.SCREEN_HEIGHT
				- pauseButtonImg.getHeight() / 2;
		bigNumbersImgX = cn.max.baozou.config.Constants.SCREEN_WIDTH / 2;
		bigNumbersImgY = 10;
//		smallNumbersImgX = noticeImgX + noticeImg.getWidth() * 5 / 6;
//		smallNumbersImgY = noticeImgY + noticeImg.getHeight()
//				- smallNumbersImg.getHeight() * 2;
		smallScoreX = smallNumbersImgX;
		smallScoreY = smallNumbersImgY - smallNumbersImg.getHeight() * 23 / 10;
//		medalImgX = noticeImgX + noticeImg.getWidth() / 8;
//		medalImgY = noticeImgY + noticeImg.getHeight() * 7 / 20;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		super.surfaceDestroyed(holder);
		release();
	}

	@Override
	public void release() {
		if (!bgImg.isRecycled()) {
			bgImg.recycle();
		}
//		if (!startImg.isRecycled()) {
//			startImg.recycle();
//		}
		if (!endImg.isRecycled()) {
			endImg.recycle();
		}
		if (!restartButtonImg.isRecycled()) {
			restartButtonImg.recycle();
		}
		if (!exitButtonImg.isRecycled()) {
			exitButtonImg.recycle();
		}
//		if (!noticeImg.isRecycled()) {
//			noticeImg.recycle();
//		}
		if (!pauseButtonImg.isRecycled()) {
			pauseButtonImg.recycle();
		}
		if (!bigNumbersImg.isRecycled()) {
			bigNumbersImg.recycle();
		}
		if (!smallNumbersImg.isRecycled()) {
			smallNumbersImg.recycle();
		}
//		if (!medalImg.isRecycled()) {
//			medalImg.recycle();
//		}
		ground.release();
		column1.release();
		column2.release();
		column3.release();
		baozou.release();
	}

}
