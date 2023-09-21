package com.tianci.system.data;

import android.util.Log;

import com.skyworth.framework.skysdk.util.SkyData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class LocaLInfoData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -998001229797861995L;

	public static class LocalInfoItem implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7666192786893388400L;
		String key = null;
		String value = null;
		
		public LocalInfoItem(){}
		public LocalInfoItem(String key, String value){
			this.key = key;
			this.value = value;
		}
		
		public LocalInfoItem(String value){
			SkyData data = new SkyData(value);
			this.key = data.getString("0");
			this.value = data.getString("1");
		}
		
		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
		/**
		 * @param key the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			SkyData data = new SkyData();
			data.add("0", key);
			data.add("1", value);
			return data.toString();
		}
		
	}
	
	List<LocalInfoItem> mInfoList = null;
	public LocaLInfoData(){
		mInfoList = new ArrayList<LocalInfoItem>();
	}
	
	public LocaLInfoData(String value){
		mInfoList = new ArrayList<LocaLInfoData.LocalInfoItem>();
		SkyData data = new SkyData(value);
		int count = data.getInt("count");
		for(int i = 0;i < count;i++){
			LocalInfoItem item = new LocalInfoItem(data.getString("" + i));
			mInfoList.add(item);
		}
	}
	
	public LocaLInfoData(HashMap<Integer, List<String>> map){
		mInfoList = new ArrayList<LocaLInfoData.LocalInfoItem>();
		deserializeMapInfo(map);
	}
	
	private void LocaLInfoData(LocaLInfoData data){
		Log.d("testlw", "data.getItemList()=" + data.getItemList());
		this.mInfoList = data.getItemList();
	}
	
    public LocaLInfoData(byte[] byteData)
    {
        try
        {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteData);
            ObjectInputStream objInputStream = new ObjectInputStream(byteInputStream);
            LocaLInfoData ackData = (LocaLInfoData) objInputStream.readObject();
            this.LocaLInfoData(ackData);
            byteInputStream.close();
            objInputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
    public byte[] getBytes()
    {
    	byte[] bytes = null;
        try
        {
            ByteArrayOutputStream byteArrayIn = new ByteArrayOutputStream();
            ObjectOutputStream objOutputStream = new ObjectOutputStream(byteArrayIn);
            objOutputStream.writeObject(this);
            bytes = byteArrayIn.toByteArray();
            byteArrayIn.close();
            objOutputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bytes;
    }
	
	private void deserializeMapInfo(HashMap<Integer, List<String>> map){
		if(map == null){
			return;
		}
		try{
//			Collection<List<String>> cols = map.values();
			for (int i=0; i<map.size(); i++) {
				mInfoList.add(new LocalInfoItem(map.get(i).get(0), map.get(i).get(1)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SkyData data = new SkyData();
		if(mInfoList != null){
			for(int i = 0;i < mInfoList.size();i++){
				data.add("" + i, mInfoList.get(i).toString());
			}
			data.add("count", mInfoList.size());
		}

		return data.toString();
	}
	
	public List<LocalInfoItem> getItemList(){
		return mInfoList;
	}

	public void addItem(LocalInfoItem item){
		try{
			mInfoList.add(item);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args){
        HashMap<Integer,List<String>> systemInfo = new HashMap<Integer, List<String>>();
        for(int i=0; i<50; i++)
        {
        	List<String> list = new ArrayList<String>();
        	list.add("name - " + i);
        	list.add("value - " + i);
        	systemInfo.put(i, list);
        }
        for(int i=0; i<systemInfo.size(); i++)
        {
        	System.out.println(systemInfo.get(i).get(0) + " -> " + systemInfo.get(i).get(1));
        }
		Collection<List<String>> cols = systemInfo.values();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("size === " + systemInfo.size());
		
		for (int i=0; i<systemInfo.size(); i++)
		{
			List<String> aList = systemInfo.get(i);
			System.out.println(aList.get(0) + " -> " + aList.get(1));
		}
        
		System.out.println("ddddddddddddddddddddddddddd");
        String str = new LocaLInfoData(systemInfo).toString();
        
        LocaLInfoData data = new LocaLInfoData(str);
        List<LocalInfoItem> itemList = data.getItemList();
        for(int i=0; i<itemList.size(); i++)
        {
        	LocalInfoItem item = itemList.get(i);
        	System.out.println(item.key + " --> " + item.value);
        }
    }
}
