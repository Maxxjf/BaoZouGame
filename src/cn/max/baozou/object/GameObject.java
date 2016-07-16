package cn.max.baozou.object;

import android.R.integer;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author max
 * �����ʱ��3������̳еģ��������ǹ�ͬ������
 */
public abstract class GameObject {
	protected int currentFrame;//��ǰ����֡
	
	protected float obj_x;//�������Ͻ�����
	protected float obj_y;
	
	protected float obj_mid_x;//�������ĺ�����
	protected float obj_mid_y;//��������������
	
	protected float obj_width;//������
	protected float obj_height;//����߶�
	
	protected Resources resources;
	protected Paint paint;
	public GameObject(Resources resources) {
		this.resources = resources;
		paint=new Paint();
	}
	//�����˶�����
	public abstract void step();
	//��ͼ����
	public abstract void drawSelf(Canvas canvas);
	//��ʼ��ͼƬ��Դ
		public abstract void initBitmap();
		//�ͷ�ͼƬ��Դ
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
