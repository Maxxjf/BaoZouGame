package cn.max.baozou.object;

import cn.max.baozou.R;
import cn.max.baozou.config.Constants;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BaoZou extends GameObject {
	private Bitmap[] baoZouBitmaps;
	private Bitmap baoZouBitmap;
	private float groundHeight;
	private Rect obj_rect;
	
	public void setCurretBitmap(int i){
		this.baoZouBitmap=baoZouBitmaps[i];
	}
	public BaoZou(Resources resources, float groundHeight) {
		super(resources);
		obj_rect = new Rect();
		this.groundHeight = groundHeight;
		initBitmap();
	}

	@Override
	public void step() {
		obj_rect.left = (int) (obj_x + (obj_width - obj_height) / 2);
		obj_rect.top = (int) (obj_y + (obj_width - obj_height) / 2);
		obj_rect.right = (int) (obj_rect.left + obj_height);	
		obj_rect.bottom = (int) (obj_rect.top + obj_height - (obj_width - obj_height) / 2);
	}

	@Override
	public void drawSelf(Canvas canvas) {
		canvas.save();
		canvas.drawBitmap(baoZouBitmap, obj_x, obj_y, paint);
		canvas.restore();

	}
//通过柱子
	public boolean pass(Column column) {
		if (this.obj_mid_x <= column.getObj_mid_x()
				&& column.getObj_mid_x() - this.obj_mid_x < 5) {
			return true;
		}
		return false;
	}
//撞柱子
	public boolean hitColumn(Column column) {
		if (this.obj_rect.intersect(column.getObjRectTop())
				|| this.obj_rect.intersect(column.getObjRectBottom())) {
			return true;
		}
		return false;
	}
//撞地面
	public boolean hitGround1(BackGround ground) {

		if ((this.obj_rect.bottom + 1) >= ground.getObjRect().top) {
			return true;
		}
		return false;
	}

	@Override
	public void initBitmap() {
		baoZouBitmaps=new Bitmap[2];
		baoZouBitmaps[0]=BitmapFactory.decodeResource(resources, R.drawable.bao0);
		baoZouBitmaps[1]=BitmapFactory.decodeResource(resources, R.drawable.bao1);
		
		baoZouBitmap=baoZouBitmaps[0];
		
		obj_width=baoZouBitmap.getWidth();
		obj_height=baoZouBitmap.getHeight();
		
		obj_x=0;
		obj_mid_y=Constants.SCREEN_HEIGHT/2;
		
		obj_mid_x=obj_x+obj_width/2;
		obj_mid_y=obj_y+obj_height/2;
	}

	@Override
	public void release() {
		for(int i=0; i<2; i++) {
			if(!baoZouBitmaps[i].isRecycled()) {
				baoZouBitmaps[i].recycle();
			}
		}

	}
	public Rect getObjRect() {
		return this.obj_rect;
	}

}
