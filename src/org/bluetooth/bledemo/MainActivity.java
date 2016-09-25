package org.bluetooth.bledemo;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	private Handler mHandler;
	private DrawChart view;
	public static Context mContext;
    public static Context getContext(){
    	return mContext;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {  
        LinearLayout layout=(LinearLayout) findViewById(R.id.root);  
        view = new DrawChart(this);  
      
        view.invalidate();  
        layout.addView(view);
        
    	mHandler = new Handler();
		mHandler.post(new TimerProcess());
          
    }  
    
	public class TimerProcess implements Runnable {
		public void run() {
			view.invalidate();
			mHandler.postDelayed(this, 0);//1000是10秒的延迟
		}
	}
	
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
