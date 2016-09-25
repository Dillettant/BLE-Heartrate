package org.bluetooth.bledemo;


import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class DrawChart extends View{
	
	private int CHARTH = 150;//表格纵向的高
	private int CHARTW = 590;//图表横向的宽
	private int OFFSET_LEFT = 30;//与左边框的距离
	private int OFFSET_TOP = 5;//与上边框的距离
	private int TEXT_OFFSET = 20;//左侧数字的间距
	private int X_INTERVAL = 10;//速度
	float X=CharacteristicDetailsAdapter.voltage;
//	private BluetoothGattCharacteristic mCharacteristic = null;
//	int VOLTAGE=CharacteristicDetailsAdapter.mValue_voltage;
	
	private List<Point> plist;

	public DrawChart(Context context) {
		super(context);
		plist = new ArrayList<Point>();
		//initPlist();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawTable(canvas);
		
		prepareLine();
		drawCurve(canvas);
	}
	
	private void drawTable(Canvas canvas){
		
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		
		//画外框
		paint.setStyle(Paint.Style.STROKE);
		//paint.setStrokeWidth(Pain);		
		Rect chartRec = new Rect(OFFSET_LEFT, OFFSET_TOP,CHARTW+OFFSET_LEFT, CHARTH+OFFSET_TOP);
		canvas.drawRect(chartRec, paint);
		
/**		//画左边的文字
		Path textPath = new Path();
		paint.setStyle(Paint.Style.FILL);
		textPath.moveTo(30, 420);
		textPath.lineTo(30, 300);
		paint.setTextSize(15);
		paint.setAntiAlias(true);
		canvas.drawTextOnPath("电压 [mV]", textPath, 0, 0, paint);
**/
	
		//画左侧数字
        canvas.drawText("0", OFFSET_LEFT - TEXT_OFFSET +5, OFFSET_TOP+5, paint);
        for(int i = 1 ; i<10 ; i++){
        	canvas.drawText("-"+10*i, OFFSET_LEFT - TEXT_OFFSET-5, OFFSET_TOP + CHARTH/10*i, paint);
        }
        canvas.drawText("-100", OFFSET_LEFT - TEXT_OFFSET -10, OFFSET_TOP + CHARTH, paint);
        
        //画表格中的虚线
        Path path = new Path();     
        PathEffect effects = new DashPathEffect(new float[]{2,2,2,2},1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(false);
        paint.setPathEffect(effects); 		
		for(int i = 1 ; i<10 ; i++){
			path.moveTo(OFFSET_LEFT, OFFSET_TOP + CHARTH/10*i);  
	        path.lineTo(OFFSET_LEFT+CHARTW,OFFSET_TOP + CHARTH/10*i); 
	        canvas.drawPath(path, paint);
			//canvas.drawLine(OFFSET_LEFT, OFFSET_TOP + CHARTH/10*i, OFFSET_LEFT+CHARTW, OFFSET_TOP + CHARTH/10*i, paint);			
			
		}
	}
	
	private void drawCurve(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true);
		//canvas.drawLines(line, paint);
		
		if(plist.size() >= 2){
			for(int i = 0; i<plist.size()-1; i++){
				canvas.drawLine(plist.get(i).x, plist.get(i).y, plist.get(i+1).x, plist.get(i+1).y, paint);
			}
		}
	}
/**	public void newValueForCharacteristic(final BluetoothGattCharacteristic ch, final String strVal) {
		if(!ch.equals(this.mCharacteristic)) return;
		String string_temp=strVal;
		char[] char_temp=string_temp.toCharArray();
		if(char_temp[0]=='P')
		{
			char[] charArr=null;
			charArr[0]=char_temp[4];
			charArr[1]=char_temp[5];
			charArr[2]=char_temp[6];
			charArr[3]=char_temp[7];
			String final_string=new	String(charArr);
			VOLTAGE=Integer.parseInt(final_string);
		}
	}
	**/
	//Math.radom()随机生成一组数用作测试
	private void prepareLine(){
		int py = OFFSET_TOP +  (int)(((4096-CharacteristicDetailsAdapter.voltage)*2)* (CHARTH - OFFSET_TOP)/(4096))-40;
//这是纵坐标int py = OFFSET_TOP + (int)(Math.random()*(CHARTH - OFFSET_TOP));
		Point p = new Point(OFFSET_LEFT + CHARTW , py );
		if(plist.size() > 60){
			plist.remove(0);
			for(int i = 0; i<59;i++){
				if(i == 0) plist.get(i).x -= (X_INTERVAL - 2);
				else plist.get(i).x -= X_INTERVAL;
				System.out.println(py);
			}
			plist.add(p);
		}
		else{
			for(int i = 0; i<plist.size()-1; i++){
				plist.get(i).x -= X_INTERVAL;
			}
			plist.add(p);
		}

	}
	
	private void initPlist(){
		int py = OFFSET_TOP + (int)(Math.random()*(CHARTH - OFFSET_TOP));
		Point p = new Point(OFFSET_LEFT + CHARTW , py );
		plist.add(p);
		
	}
	

}
