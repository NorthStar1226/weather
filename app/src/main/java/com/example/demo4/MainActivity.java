package com.example.demo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class MainActivity extends AppCompatActivity {
    public TextView Guilin,Liuzhou,Nanning,dataTime,first_currentTemp,first_rain,first_max_min,
    second_currentTemp,second_rain,second_max_min,third_currentTemp,third_rain,third_max_min;
    public ImageView mainSearch,mainMap;
    public LocationClient mLocationClient;
    private LinearLayout layout_guilin,layout_liuzhou,layout_nanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        HeConfig.init("HE1905211450541502", "f16ebcbf627c41d78bb62f0711ada530");
        HeConfig.switchToFreeServerNode();
        setContentView(R.layout.main_layout);
        iview();
        /*Guilin = findViewById(R.id.first_city);
        Liuzhou = findViewById(R.id.third_city);
        Nanning = findViewById(R.id.second_city);
        mainSearch = findViewById(R.id.main_search);
        mainMap = findViewById(R.id.main_map);
        dataTime = findViewById(R.id.dateTime);
        layout_guilin = findViewById(R.id.layout_guilin);
        layout_liuzhou = findViewById(R.id.layout_liuzhou);
        layout_nanning = findViewById(R.id.layout_nanning);
        first_currentTemp = findViewById(R.id.first_currentTemp);
        first_rain = findViewById(R.id.first_rain);
        first_max_min = findViewById(R.id.first_max_min);
        second_currentTemp = findViewById(R.id.second_currentTemp);
        second_rain = findViewById(R.id.second_rain);
        second_max_min = findViewById(R.id.second_max_min);
        third_currentTemp = findViewById(R.id.third_currentTemp);
        third_rain = findViewById(R.id.third_rain);
        third_max_min = findViewById(R.id.third_max_min);
        layout_guilin.setOnClickListener(new MyListener());
        layout_liuzhou.setOnClickListener(new MyListener());
        layout_nanning.setOnClickListener(new MyListener());
        Guilin.setOnClickListener(new MyListener());
        Liuzhou.setOnClickListener(new MyListener());
        Nanning.setOnClickListener(new MyListener());
        mainSearch.setOnClickListener(new MyListener());
        mainMap.setOnClickListener(new MyListener());*/
        //卡片桂林
        HeWeather.getWeather(MainActivity.this, "桂林", Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherDataListBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this,throwable.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.weather.Weather weather) {
                if ( Code.OK.getCode().equalsIgnoreCase(weather.getStatus()) ){
                    //此时返回数据
                    dataTime.setText(weather.getUpdate().getLoc());
                    first_currentTemp.setText(weather.getNow().getTmp()+"℃");
                    first_rain.setText(weather.getNow().getPcpn()+"mm");
                    first_max_min.setText(weather.getDaily_forecast().get(0).getTmp_max()+"℃/"+weather.getDaily_forecast().get(0).getTmp_min()+"℃");
                } else {
                    //在此查看返回数据失败的原因
                    String status = weather.getStatus();
                    Code code = Code.toEnum(status);
                    Toast.makeText(MainActivity.this,status+":"+code,Toast.LENGTH_SHORT).show();
                }

            }
        });
        //卡片南宁
        HeWeather.getWeather(MainActivity.this, "南宁", Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherDataListBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this,throwable.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.weather.Weather weather) {
                if ( Code.OK.getCode().equalsIgnoreCase(weather.getStatus()) ){
                    //此时返回数据
                    second_currentTemp.setText(weather.getNow().getTmp()+"℃");
                    second_rain.setText(weather.getNow().getPcpn()+"mm");
                    second_max_min.setText(weather.getDaily_forecast().get(0).getTmp_max()+"℃/"+weather.getDaily_forecast().get(0).getTmp_min()+"℃");
                } else {
                    //在此查看返回数据失败的原因
                    String status = weather.getStatus();
                    Code code = Code.toEnum(status);
                    Toast.makeText(MainActivity.this,status+":"+code,Toast.LENGTH_SHORT).show();
                }

            }
        });
        //卡片柳州
        HeWeather.getWeather(MainActivity.this, "柳州", Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherDataListBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this,throwable.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.weather.Weather weather) {
                if ( Code.OK.getCode().equalsIgnoreCase(weather.getStatus()) ){
                    //此时返回数据
                    third_currentTemp.setText(weather.getNow().getTmp()+"℃");
                    third_rain.setText(weather.getNow().getPcpn()+"mm");
                    third_max_min.setText(weather.getDaily_forecast().get(0).getTmp_max()+"℃/"+weather.getDaily_forecast().get(0).getTmp_min()+"℃");
                } else {
                    //在此查看返回数据失败的原因
                    String status = weather.getStatus();
                    Code code = Code.toEnum(status);
                    Toast.makeText(MainActivity.this,status+":"+code,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void  iview (){
        Guilin = findViewById(R.id.first_city);
        Liuzhou = findViewById(R.id.third_city);
        Nanning = findViewById(R.id.second_city);
        mainSearch = findViewById(R.id.main_search);
        mainMap = findViewById(R.id.main_map);
        dataTime = findViewById(R.id.dateTime);
        layout_guilin = findViewById(R.id.layout_guilin);
        layout_liuzhou = findViewById(R.id.layout_liuzhou);
        layout_nanning = findViewById(R.id.layout_nanning);
        first_currentTemp = findViewById(R.id.first_currentTemp);
        first_rain = findViewById(R.id.first_rain);
        first_max_min = findViewById(R.id.first_max_min);
        second_currentTemp = findViewById(R.id.second_currentTemp);
        second_rain = findViewById(R.id.second_rain);
        second_max_min = findViewById(R.id.second_max_min);
        third_currentTemp = findViewById(R.id.third_currentTemp);
        third_rain = findViewById(R.id.third_rain);
        third_max_min = findViewById(R.id.third_max_min);
        layout_guilin.setOnClickListener(new MyListener());
        layout_liuzhou.setOnClickListener(new MyListener());
        layout_nanning.setOnClickListener(new MyListener());
        Guilin.setOnClickListener(new MyListener());
        Liuzhou.setOnClickListener(new MyListener());
        Nanning.setOnClickListener(new MyListener());
        mainSearch.setOnClickListener(new MyListener());
        mainMap.setOnClickListener(new MyListener());
    }

    private class MyListener implements View.OnClickListener{
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.layout_guilin:{
                    Intent intent = new Intent(MainActivity.this, ShowWeather.class);
                    String ss =Guilin.getText().toString();
                    intent.putExtra("city",ss);
                    startActivity(intent);
                    break;}
                case R.id.layout_liuzhou:{
                    Intent intent = new Intent(MainActivity.this, ShowWeather.class);
                    String ss =Liuzhou.getText().toString();
                    intent.putExtra("city",ss);
                    startActivity(intent);
                    break;}
                case R.id.layout_nanning:{
                    Intent intent= new Intent(MainActivity.this, ShowWeather.class);
                    String ss =Nanning.getText().toString();
                    intent.putExtra("city",ss);
                    startActivity(intent);
                    break;}
                case R.id.main_search:{
                    Intent intent = new Intent(MainActivity.this, FindWeather.class);
                    startActivity(intent);
                    break;}
                case R.id.main_map:{
                    Intent intent1 = new Intent(MainActivity.this, BaiduMap.class);
                    startActivity(intent1);
                    break;}
            }
        }
    }

}