package cn.max.baozou;

import cn.max.baozou.config.Config;
import cn.max.baozou.view.GameView;
import cn.max.baozou.view.LoadingView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
private LoadingView loadingView;
private GameView gameView;
private Handler handler=new Handler(){
	public void handleMessage(android.os.Message msg) {
		if (msg.what==Config.TO_GAME_VIEW) {
			toGameView();
		}
		if(msg.what == Config.END_GAME) {
			 endGame();
		 }
	}
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		loadingView=new LoadingView(this);
		setContentView(loadingView);
		
	}

	public void toGameView() {
		if(gameView == null) {
			gameView = new GameView(this);
		} else {
			gameView = null;
			gameView = new GameView(this);
		}
		setContentView(gameView);
		loadingView = null;
	}
	
	public void endGame() {
		if(gameView== null) {
			gameView.setThreadFlag(false);
		}
		if(this.loadingView != null) {
			this.loadingView.setThreadFlag(false);
		}
		this.finish();
	}
	public Handler getHandler() {
		return this.handler;
	}

}
