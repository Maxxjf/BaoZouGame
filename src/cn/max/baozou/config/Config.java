package cn.max.baozou.config;

public  class Config {

	public final static int TO_GAME_VIEW = 1;//命令切换到主视图
	public final static int END_GAME = 0;//命令游戏结束
	
	public final static int LOADING_GAME_INTERVAL = 2000;//加载界面时间
	
	public final static int SPEED = (int) (Constants.SCREEN_WIDTH * 10 / 480);//column和ground的运动速度
	
	public final static float COLUMN_Y_GAP = Constants.SCREEN_HEIGHT * 173 / 854;//column图片管子间空隙高度（不得修改，由图片决定）
	public final static float COLUMN_X_GAP = Constants.SCREEN_WIDTH / 2 + 50;//两个管子间的水平间距
	
	
}
