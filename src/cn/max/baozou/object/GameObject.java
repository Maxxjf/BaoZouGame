package cn.max.baozou.object;

import android.R.integer;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author max
 * 这个类时被3个对象继承的，有着他们共同的特性
 */
public abstract class GameObject {
	protected int currentFrame;//当前动画帧
	
	protected float obj_x;//对象左上角坐标
	protected float obj_y;
	
	protected float obj_mid_x;//对象中心横坐标
	protected float obj_mid_y;//对象中心纵坐标
	
	protected float obj_width;//对象宽度
	protected float obj_height;//对象高度
	
	protected Resources resources;
	protected Paint paint;
	public GameObject(Resources resources) {
		this.resources = resources;
		paint=new Paint();
	}
	//对象运动规则
	public abstract void step();
	//绘图方法
	public abstract void drawSelf(Canvas canvas);
	//初始化图片资源
		public abstract void initBitmap();
		//释放图片资源
		public abstract void release();
		public int getCurrentFrame() {
			return currentFrame;
		}
		public void setCurrentFrame(int currentFrame) {
			this.currentFrame = currentFrame;
		}
		public float getObj_x() {
			return obj_x;
		}
		public void setObj_x(float obj_x) {
			this.obj_x = obj_x;
		}
		public float getObj_y() {
			return obj_y;
		}
		public void setObj_y(float obj_y) {
			this.obj_y = obj_y;
		}
		public float getObj_mid_x() {
			return obj_mid_x;
		}
		public void setObj_mid_x(float obj_mid_x) {
			this.obj_mid_x = obj_mid_x;
		}
		public float getObj_mid_y() {
			return obj_mid_y;
		}
		public void setObj_mid_y(float obj_mid_y) {
			this.obj_mid_y = obj_mid_y;
		}
		public float getObj_width() {
			return obj_width;
		}
		public void setObj_width(float obj_width) {
			this.obj_width = obj_width;
		}
		public float getObj_height() {
			return obj_height;
		}
		public void setObj_height(float obj_height) {
			this.obj_height = obj_height;
		}
	
	
}
