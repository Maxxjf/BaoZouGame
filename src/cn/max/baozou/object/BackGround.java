package cn.max.baozou.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import cn.max.baozou.R;
import cn.max.baozou.config.Constants;

public class BackGround extends GameObject{

	private Bitmap groundBitmap;
	private Rect obj_rect;
	public BackGround(Resources resources) {
		super(resources);
		obj_rect=new Rect();
		obj_x=0;
		initBitmap();
	}

	@Override
	public void step() {
	obj_x-=cn.max.baozou.config.Config.SPEED;
	if(obj_x<=-(obj_width-Constants.SCREEN_WIDTH)){
		obj_x=-15;
	}
	}

	@Override
	public void drawSelf(Canvas canvas) {
		canvas.drawBitmap(groundBitmap, obj_x, obj_y, paint);
		
	}

	@Override
	public void initBitmap() {
		groundBitmap=BitmapFactory.decodeResource(resources, R.drawable.ground);
		 obj_width=groundBitmap.getWidth();
		 obj_height=groundBitmap.getHeight();
		 obj_y=Constants.SCREEN_HEIGHT-obj_height;
				 
	}
	//得到方形
		public Rect getObjRect() {
			obj_rect.set(0, (int)obj_y, (int)Constants.SCREEN_WIDTH, (int)Constants.SCREEN_HEIGHT);
			return this.obj_rect;
		}
	@Override
	public void release() {
		if(!groundBitmap.isRecycled()){
			groundBitmap.recycle();
		}
		
	}

}
