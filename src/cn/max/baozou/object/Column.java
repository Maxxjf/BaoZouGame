package cn.max.baozou.object;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import cn.max.baozou.R;
import cn.max.baozou.config.Config;
import cn.max.baozou.config.Constants;

public class Column extends GameObject{
private Bitmap columnImg;
	
	private Random random;
	
	private float groundHeight;
	
	
	private Rect rectTop;
	private Rect rectBottom;
	public Column(Resources resources,float x,float groundHeight) {
		super(resources);
		rectTop = new Rect();
		rectBottom = new Rect();
		this.groundHeight = groundHeight;
		random = new Random();
		this.obj_mid_x = x;
		this.obj_mid_y = random.nextInt((int)(cn.max.baozou.config.Constants.SCREEN_HEIGHT - this.groundHeight - cn.max.baozou.config.Config.COLUMN_Y_GAP * 2)) + cn.max.baozou.config.Config.COLUMN_Y_GAP;
		initBitmap();
	}

	@Override
	public void step() {
		this.obj_mid_x -=cn.max.baozou.config.Config.SPEED;
		if(this.obj_mid_x <= -(cn.max.baozou.config.Config.COLUMN_X_GAP - this.obj_width / 2)) {
			this.obj_mid_x = cn.max.baozou.config.Config.COLUMN_X_GAP * 2 + this.obj_width / 2;
			this.obj_mid_y = random.nextInt((int)(cn.max.baozou.config.Constants.SCREEN_HEIGHT - this.groundHeight - cn.max.baozou.config.Config.COLUMN_Y_GAP * 2)) + cn.max.baozou.config.Config.COLUMN_Y_GAP;
		}
		
	}

	@Override
	public void drawSelf(Canvas canvas) {
		canvas.drawBitmap(columnImg, obj_mid_x - this.obj_width / 2, obj_mid_y - this.obj_height / 2, paint);
		
	}

	@Override
	public void initBitmap() {
		columnImg = BitmapFactory.decodeResource(resources, R.drawable.column);
		this.obj_width = columnImg.getWidth();
		this.obj_height = columnImg.getHeight();
		
	}

	@Override
	public void release() {
		if(!columnImg.isRecycled()) {
			columnImg.recycle();
		}
	
	}
	public Rect getObjRectTop() {
		this.rectTop.set((int)(obj_mid_x - obj_width / 2), 0, (int)(obj_mid_x + obj_width / 2), (int)(obj_mid_y - Config.COLUMN_Y_GAP / 2));
		return this.rectTop;
	}
	
	public Rect getObjRectBottom() {
		this.rectBottom.set((int)(obj_mid_x - obj_width / 2), (int)(obj_mid_y + Config.COLUMN_Y_GAP / 2), (int)(obj_mid_x + obj_width / 2), (int)Constants.SCREEN_HEIGHT);
		return this.rectBottom;
	}
}
