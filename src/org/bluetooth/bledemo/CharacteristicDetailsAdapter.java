package org.bluetooth.bledemo;

import java.util.Locale;

import org.bluetooth.bledemo.MainActivity.TimerProcess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CharacteristicDetailsAdapter extends BaseAdapter {
//	public static PeripheralActivity instance = null;  
   	
	private BluetoothGattCharacteristic mCharacteristic = null;
	private LayoutInflater mInflater;
	private BleWrapper mBleWrapper = null;
	private byte[] mRawValue = null;
	private int mIntValue = 0;
	private String mAsciiValue = "";
	private String mStrValue = "";
	private String mStrValue15 = "";
	private String mStrValue30 = "";
	private String mStrValue60 = "";
//	static String mStrValue_time = "";
	static String mStrValue_voltage = "";
//	static int mValue_voltage = Integer.parseInt(mStrValue_voltage);
	private String mLastUpdateTime = "";
	private boolean mNotificationEnabled = false;
	private Context context;
	private Handler mHandler;
//	private DrawChartTest VIEW;
	private DrawChart view=null;
	static int voltage=500;
	
	private static byte prePreByte = 0;//静态byte变量
    private static byte preByte =0;//初始化值为0
    private static byte thisByte = 0;//由面向对象的程序架构决定
    public static byte getPrePreByte(){
    	return prePreByte;//byte函数 返回值为prePreByte
    }
    public static byte getPreByte(){
    	return preByte;//byte函数 返回值为preByte
    }
    public static byte getThisByte(){
    	return thisByte;//byte函数 返回值为thisByte
    }
    public static void setPrePreByte(byte B){
    	prePreByte = B;//void函数 将byte B的赋值给prePreByte
    }
    public static void setPreByte(byte B){
    	preByte = B;//void函数 将byte B的赋值给preByte
    }
    public static void setThisByte(byte B){
    	thisByte = B;//void函数 将byte B的赋值给thisByte
    }
    static int value=0;
	
	public CharacteristicDetailsAdapter(PeripheralActivity parent, BleWrapper ble) {
		super();
		mBleWrapper = ble;
		mInflater = parent.getLayoutInflater();
	}
	
	public void setCharacteristic(BluetoothGattCharacteristic ch) {
		this.mCharacteristic = ch;
		mRawValue = null;
		mIntValue = 0;
		mAsciiValue = "";
		mStrValue = "";
		mStrValue15 = "";
		mStrValue30 = "";
		mStrValue60 = "";
//		mStrValue_time = "";
//		mStrValue_voltage = "";
		mLastUpdateTime = "-";
		mNotificationEnabled = false;
	}
	
	public BluetoothGattCharacteristic getCharacteristic(int index) {
		return mCharacteristic;
	}

	public void clearCharacteristic() {
		mCharacteristic = null;
	}
/**	public void init() {  
        LinearLayout layout=(LinearLayout) findViewById(R.id.root_test);  
        view = new DrawChartTest(context);  
      
        view.invalidate();  
        layout.addView(view);
        
    	mHandler = new Handler();
		mHandler.post(new TimerProcess());
          
    }
    private LinearLayout findViewById(int rootTest) {
		// TODO Auto-generated method stub
		return null;
	}
	private class TimerProcess implements Runnable {
		public void run() {
			view.invalidate();
			mHandler.postDelayed(this, 0);//1000是10秒的延迟
		}
	}**/
	
	@Override
	public int getCount() {
		context=PeripheralActivity.getContext();
		view = new DrawChart(context);
		return (mCharacteristic != null) ? 1 : 0;
	}

	@Override
	public Object getItem(int position) {
		return mCharacteristic;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("null")
	public void newValueForCharacteristic(final BluetoothGattCharacteristic ch, final String strVal, final int intVal, final byte[] rawValue, final String timestamp) {
		if(!ch.equals(this.mCharacteristic)) return;
		
		mIntValue = intVal;
		String temp_string=strVal;
//		String[] temp_voltage=strVal.split("P");
		char[] temp_char=temp_string.toCharArray();
		switch(temp_char[0])
		{
		case 'B':{
			temp_char[0]=temp_char[1];
			temp_char[1]=temp_char[2];
			temp_char[2]=temp_char[3];
			temp_char[3]=' ';
			String string_final=new	String(temp_char);
			mStrValue = string_final;
			System.out.print("###########"+string_final);
			break;
		          }
		case 'C':{
			temp_char[0]=temp_char[1];
			temp_char[1]=temp_char[2];
			temp_char[2]=temp_char[3];
			temp_char[3]=' ';
			String string_final=new	String(temp_char);
			mStrValue15 =string_final+"---"+timestamp;
			break;
		          }
		case 'D':{
			temp_char[0]=temp_char[1];
			temp_char[1]=temp_char[2];
			temp_char[2]=temp_char[3];
			temp_char[3]=' ';
			String string_final=new	String(temp_char);
			mStrValue30 = string_final+"---"+timestamp;
			break;
		          }
		case 'E':{
			temp_char[0]=temp_char[1];
			temp_char[1]=temp_char[2];
			temp_char[2]=temp_char[3];
			temp_char[3]=' ';
			String string_final=new	String(temp_char);
			mStrValue60 = string_final+"---"+timestamp;
			break;
		          }
		case 'P':{
//			char[] charArr_time;
//			char[] charArr_voltage = null;
/**			charArr_time[0]=temp_char[4];
			charArr_time[1]=temp_char[5];
			charArr_time[2]=temp_char[6];
			charArr_time[3]=temp_char[7];
			
**/			
//    	    try{
//			temp_char=temp_voltage[0].toCharArray();
			temp_char[0]=temp_char[4];
			temp_char[1]=temp_char[5];
			temp_char[2]=temp_char[6];
			temp_char[3]=temp_char[7];
			temp_char[4]=' ';
			temp_char[5]=' ';
			temp_char[6]=' ';
			temp_char[7]=' ';

//			String string_final=new	String(temp_char);
//			String string_final_time=new	String(charArr_time);
//			mStrValue_time=string_final_time;
			String string_final_voltage=new	String(temp_char);
			String s=string_final_voltage.trim();
			mStrValue_voltage = s;
			voltage=Integer.parseInt(mStrValue_voltage);
			System.out.println("$$$$$$$$$$$$$$$$$$$$"+voltage);
//			}catch(Exception e){
//				Log.d("ADDVIEW","*********************"+e+voltage);
//			}
			mStrValue = "Unknow";
			break;
		          }
/**		case 'P':{
			char[] charArr_time=null;
			char[] charArr_voltage=null;
			charArr_time[0]=temp_char[4];
			charArr_time[1]=temp_char[5];
			charArr_time[2]=temp_char[6];
			charArr_time[3]=temp_char[7];
			String string_final_time=new	String(charArr_time);
			mStrValue_time=string_final_time;
			charArr_voltage[0]=temp_char[1];
			charArr_voltage[1]=temp_char[2];
			charArr_voltage[2]=temp_char[3];
			String string_final_voltage=new	String(charArr_time);
			mStrValue_voltage = string_final_voltage;
			break;
	    }**/	
		default:{
			mStrValue="Unknow";
			break;
		}
		}

		mRawValue = rawValue;
        if (mRawValue != null && mRawValue.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(mRawValue.length);
            for(byte byteChar : mRawValue)
                stringBuilder.append(String.format("%02X", byteChar));
            String BArr = stringBuilder.toString();
            byte[] BArrByte=BArr.getBytes();
          //这是旧的通信规则
            mAsciiValue = "HeartRate";
        }
        else mAsciiValue = "";
      draw();
        
        mLastUpdateTime = timestamp;
        if(mLastUpdateTime == null) mLastUpdateTime = "";
	}
	private void draw(){
		context=PeripheralActivity.getContext();
		view = new DrawChart(context);
		
		view.invalidate();  
 //     fields.layout.addView(view);
        
		mHandler = new Handler();
		mHandler.post(new TimerProcess());
		
	}
	public class TimerProcess implements Runnable {
		public void run() {
			view.invalidate();
			mHandler.postDelayed(this, 0);//1000是10秒的延迟
		}
	}
	
	public void setNotificationEnabledForService(final BluetoothGattCharacteristic ch) {
		if((!ch.equals(this.mCharacteristic)) || (mNotificationEnabled == true)) return;
		mNotificationEnabled = true;
		notifyDataSetChanged();
	}
	
	public byte[] parseHexStringToBytes(final String hex) {
		String tmp = hex.substring(2).replaceAll("[^[0-9][a-f]]", "");
		byte[] bytes = new byte[tmp.length() / 2]; // every two letters in the string are one byte finally
		
		String part = "";
		
		for(int i = 0; i < bytes.length; ++i) {
			part = "0x" + tmp.substring(i*2, i*2+2);
			bytes[i] = Long.decode(part).byteValue();
		}
		
		return bytes;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup p) {
		// get already available view or create new if necessary
		FieldReferences fields;
        if (convertView == null) {
        	convertView = mInflater.inflate(R.layout.peripheral_details_characteristic_item, null);
        	fields = new FieldReferences();
        	fields.charPeripheralName = (TextView)convertView.findViewById(R.id.char_details_peripheral_name);
        	fields.charPeripheralAddress = (TextView)convertView.findViewById(R.id.char_details_peripheral_address);
//        	fields.charServiceName = (TextView)convertView.findViewById(R.id.char_details_service);
//        	fields.charServiceUuid = (TextView)convertView.findViewById(R.id.char_details_service_uuid);
//        	fields.charName = (TextView)convertView.findViewById(R.id.char_details_name);
//        	fields.charUuid = (TextView)convertView.findViewById(R.id.char_details_uuid);
        	
//        	fields.charDataType = (TextView)convertView.findViewById(R.id.char_details_type);
//        	fields.charProperties = (TextView) convertView.findViewById(R.id.char_details_properties);
        	
        	fields.charStrValue = (TextView) convertView.findViewById(R.id.char_details_ascii_value);
        	fields.charStrValue15 = (TextView) convertView.findViewById(R.id.char_details_ascii_value15);
        	fields.charStrValue30 = (TextView) convertView.findViewById(R.id.char_details_ascii_value30);
        	fields.charStrValue60 = (TextView) convertView.findViewById(R.id.char_details_ascii_value60);
//        	fields.charStrValue_time = (TextView) convertView.findViewById(R.id.char_details_ascii_value_time);
//        	fields.charStrValue_voltage = (TextView) convertView.findViewById(R.id.char_details_ascii_value_voltage);
//        	fields.charDecValue = (TextView) convertView.findViewById(R.id.char_details_decimal_value);
        	fields.charHexValue = (EditText) convertView.findViewById(R.id.char_details_hex_value);
        	fields.charDateValue = (TextView) convertView.findViewById(R.id.char_details_timestamp);
        	fields.layout=(LinearLayout) convertView.findViewById(R.id.root);
        	
        	fields.notificationBtn = (ToggleButton) convertView.findViewById(R.id.char_details_notification_switcher);
        	fields.readBtn = (Button) convertView.findViewById(R.id.char_details_read_btn);
        	fields.writeBtn = (Button) convertView.findViewById(R.id.char_details_write_btn);
        	fields.startBtn = (Button) convertView.findViewById(R.id.char_details_wave_btn);
        	fields.writeBtn.setTag(fields.charHexValue);

        	fields.startBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					context=PeripheralActivity.getContext();
					Intent intent=new Intent();
//					intent.setClass(context,com.taweechai.humansignal.HumanSignalActivity.class);
//					intent.setClass(context,MainActivity.class);
					intent.setClass(context,com.taweechai.humansignal.HumanSignalActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
					context.startActivity(intent);

				}
			});
        	fields.readBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mBleWrapper.requestCharacteristicValue(mCharacteristic);
				}
			});

        	fields.writeBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					EditText hex = (EditText) v.getTag();
					String newValue =  hex.getText().toString().toLowerCase(Locale.getDefault());
					byte[] dataToWrite = parseHexStringToBytes(newValue);

					mBleWrapper.writeDataToCharacteristic(mCharacteristic, dataToWrite);
				}
			});          	
        	
        	fields.notificationBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked == mNotificationEnabled) return; // no need to update anything

					mBleWrapper.setNotificationForCharacteristic(mCharacteristic, isChecked);
					mNotificationEnabled = isChecked;
					
				}
			} );
        	
            convertView.setTag(fields);
        } else {
            fields = (FieldReferences) convertView.getTag();
        }			
		
        // set proper values into the view
        fields.charPeripheralName.setText(mBleWrapper.getDevice().getName());
        fields.charPeripheralAddress.setText(mBleWrapper.getDevice().getAddress());
        
        String tmp = mCharacteristic.getService().getUuid().toString().toLowerCase(Locale.getDefault());
//        fields.charServiceUuid.setText(tmp);
//      fields.charServiceName.setText(BleNamesResolver.resolveServiceName(tmp));
        
        String uuid = mCharacteristic.getUuid().toString().toLowerCase(Locale.getDefault());
        String name = BleNamesResolver.resolveCharacteristicName(uuid);
        
//      fields.charName.setText(name);
//      fields.charUuid.setText(uuid);
        
        int format = mBleWrapper.getValueFormat(mCharacteristic);
//      fields.charDataType.setText(BleNamesResolver.resolveValueTypeDescription(format));
        int props = mCharacteristic.getProperties();
        String propertiesString = String.format("0x%04X [", props);
        if((props & BluetoothGattCharacteristic.PROPERTY_READ) != 0) propertiesString += "read ";
        if((props & BluetoothGattCharacteristic.PROPERTY_WRITE) != 0) propertiesString += "write ";
        if((props & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0) propertiesString += "notify ";
        if((props & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0) propertiesString += "indicate ";
        if((props & BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0) propertiesString += "write_no_response ";
//      fields.charProperties.setText(propertiesString + "]");
        
        fields.notificationBtn.setEnabled((props & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0);
        fields.notificationBtn.setChecked(mNotificationEnabled);
        fields.readBtn.setEnabled((props & BluetoothGattCharacteristic.PROPERTY_READ) != 0);
        fields.writeBtn.setEnabled((props & (BluetoothGattCharacteristic.PROPERTY_WRITE | BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) != 0);
        fields.charHexValue.setEnabled(fields.writeBtn.isEnabled());
        
        fields.charHexValue.setText(mAsciiValue);
        fields.charStrValue.setText(mStrValue);
        fields.charStrValue15.setText(mStrValue15);
        fields.charStrValue30.setText(mStrValue30);
        fields.charStrValue60.setText(mStrValue60);
//      fields.charStrValue_time.setText(mStrValue_time);
//      fields.charStrValue_voltage.setText(mStrValue_voltage);
//      fields.charDecValue.setText(String.format("%d", mIntValue));
        fields.charDateValue.setText(mLastUpdateTime);
        try{
			fields.layout.addView(view);
		}catch(Exception e){
			Log.d("ADDVIEW","+++++++++++++++"+e);
		}
        
        return convertView;
	}
    	
	private class FieldReferences {
		TextView charPeripheralName;
		TextView charPeripheralAddress;
//		TextView charServiceName;
//		TextView charServiceUuid;
//		TextView charUuid;
//		TextView charName;
//		TextView charDataType;
		TextView charStrValue;
		TextView charStrValue15;
		TextView charStrValue30;
		TextView charStrValue60;
//		TextView charStrValue_time;
//		TextView charStrValue_voltage;
		EditText charHexValue;
//		TextView charDecValue;
		TextView charDateValue;
		LinearLayout layout;
//		TextView charProperties;
		
		ToggleButton notificationBtn;
		Button readBtn;
		Button writeBtn;
		Button startBtn;
	}
}
