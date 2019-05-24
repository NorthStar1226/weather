package com.example.demo4;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class MyLocationListener implements BDLocationListener {
    private String city;
    private  String method;
    public void onReceiveLocation(BDLocation location){
        city = location.getCity();
        if (location.getLocType() == BDLocation.TypeGpsLocation){
            method = "GPS";
        }else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
            method = "网络";
        }
        StringBuilder currentPosition = new StringBuilder();
        currentPosition.append(location.getCity()).append("/n");
    }
    public String getCity(){
        return city;
    }
    public String getMethod(){return method;}
}