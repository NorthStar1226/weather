package com.example.demo4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class ShowWeather extends AppCompatActivity {
    public LocationClient mLocationClient;
    public String ss;
    public ImageView title_img;
    public TextView current_city,current_temp,one_day,one_now,one__min,one_max,
            two_day,two_now,two_min,two_max,three_day,three_now,three_min,three_max,
            suggestion_one,suggestion_two,suggestion_three,sugestion_four,suggestion_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        HeConfig.init("HE1905211450541502", "f16ebcbf627c41d78bb62f0711ada530");
        HeConfig.switchToFreeServerNode();
        setContentView(R.layout.show_weather);
        title_img = findViewById(R.id.title_img);
        current_city = findViewById(R.id.current_city);
        current_temp = findViewById(R.id.current_temp);
        one_day = findViewById(R.id.one_day);
        one_now = findViewById(R.id.one_now);
        one__min = findViewById(R.id.one_min);
        one_max = findViewById(R.id.one_max);
        two_day = findViewById(R.id.two_day);
        two_now = findViewById(R.id.two_now);
        two_min = findViewById(R.id.two_min);
        two_max = findViewById(R.id.two_max);
        three_day = findViewById(R.id.three_day);
        three_now = findViewById(R.id.three_now);
        three_min = findViewById(R.id.three_min);
        three_max = findViewById(R.id.three_max);
        suggestion_one = findViewById(R.id.suggestion_one);
        suggestion_two = findViewById(R.id.suggestion_two);
        suggestion_three = findViewById(R.id.suggestion_three);
        sugestion_four = findViewById(R.id.suggestion_four);
        suggestion_five = findViewById(R.id.suggestion_five);
        /*自动定位*/
        Intent in = getIntent();
        ss=in.getStringExtra("city");
//        current_city.setText(ss);
        HeWeather.getWeather(ShowWeather.this, ss, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherDataListBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(ShowWeather.this,throwable.toString(),Toast.LENGTH_SHORT).show();
                finish();
            }

            String todayWeather = null;
            @Override
            public void onSuccess(interfaces.heweather.com.interfacesmodule.bean.weather.Weather weather) {
                if ( Code.OK.getCode().equalsIgnoreCase(weather.getStatus()) ){
                    //此时返回数据
                    todayWeather = weather.getNow().getCond_txt();
                    current_city.setText(weather.getBasic().getParent_city());
                    current_temp.setText(weather.getNow().getTmp()+"℃");
                    one_day.setText(weather.getDaily_forecast().get(1).getDate());
                    one_now.setText(weather.getDaily_forecast().get(1).getCond_txt_d());
                    one__min.setText(weather.getDaily_forecast().get(1).getTmp_min()+"℃");
                    one_max.setText(weather.getDaily_forecast().get(1).getTmp_max()+"℃");
                    two_day.setText(weather.getDaily_forecast().get(2).getDate());
                    two_now.setText(weather.getDaily_forecast().get(2).getCond_txt_d());
                    two_min.setText(weather.getDaily_forecast().get(2).getTmp_min()+"℃");
                    two_max.setText(weather.getDaily_forecast().get(2).getTmp_max()+"℃");
                    three_day.setText(weather.getDaily_forecast().get(3).getDate());
                    three_now.setText(weather.getDaily_forecast().get(3).getCond_txt_d());
                    three_min.setText(weather.getDaily_forecast().get(3).getTmp_min()+"℃");
                    three_max.setText(weather.getDaily_forecast().get(3).getTmp_max()+"℃");
                    suggestion_one.setText("生活指数: \n"+weather.getLifestyle().get(0).getTxt()+" "+weather.getLifestyle().get(2).getTxt());
                    suggestion_two.setText(weather.getNow().getFl()+"℃");
                    suggestion_three.setText(weather.getDaily_forecast().get(0).getHum()+"%");
                    sugestion_four.setText(weather.getDaily_forecast().get(0).getWind_dir());
                    suggestion_five.setText(weather.getDaily_forecast().get(0).getPres()+"hPa");

                    if(!todayWeather.equals("")) {
                        Log.e("type", todayWeather);
                        switch (todayWeather) {
                            case "晴":
                                title_img.setImageResource(R.drawable.title_qing);
                                break;
                            case "阴":
                                 title_img.setImageResource(R.drawable.title_yin);
                                break;
                            case "雾":
                                title_img.setImageResource(R.drawable.title_wu);
                                break;
                            case "多云":
                                title_img.setImageResource(R.drawable.title_duoyun);
                                break;
                            case "小雨":
                                title_img.setImageResource(R.drawable.title_xiaoyu);
                                break;
                            case "中雨":
                                title_img.setImageResource(R.drawable.title_zhongyu);
                                break;
                            case "大雨":
                                title_img.setImageResource(R.drawable.title_dayu);
                                break;
                            case "阵雨":
                                title_img.setImageResource(R.drawable.title_zhenyu);
                                break;
                            case "雷阵雨":
                                title_img.setImageResource(R.drawable.title_leizhenyu);
                                break;
                            case "雷阵雨加暴":
                                title_img.setImageResource(R.drawable.title_leizhenyujiabingbao);
                                break;
                            case "暴雨":
                                title_img.setImageResource(R.drawable.title_baoyu);
                                break;
                            case "大暴雨":
                                title_img.setImageResource(R.drawable.title_dabaoyu);
                                break;
                            case "特大暴雨":
                                title_img.setImageResource(R.drawable.title_tedabaoyu);
                                break;
                            case "阵雪":
                                title_img.setImageResource(R.drawable.title_zhenxue);
                                break;
                            case "暴雪":
                                title_img.setImageResource(R.drawable.title_baoxue);
                                break;
                            case "大雪":
                                title_img.setImageResource(R.drawable.title_daxue);
                                break;
                            case "小雪":
                                title_img.setImageResource(R.drawable.title_xiaoxue);
                                break;
                            case "雨夹雪":
                                title_img.setImageResource(R.drawable.title_yujiaxue);
                                break;
                            case "中雪":
                                title_img.setImageResource(R.drawable.title_zhongyu);
                                break;
                            case "沙尘暴":
                                title_img.setImageResource(R.drawable.title_shachenbao);
                                break;
                            default:
                                break;
                        }
                    }

                } else {
                    //在此查看返回数据失败的原因
                    String status = weather.getStatus();
                    Code code = Code.toEnum(status);
                    Toast.makeText(ShowWeather.this,status+":"+code,Toast.LENGTH_SHORT).show();
                }

            }
        });

        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(ShowWeather.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(ShowWeather.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(ShowWeather.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String [] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(ShowWeather.this,permission,1);
        }
        else {
            requstLocation();
        }

    }

    private void requstLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
    }
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
    }
    public void onRequestPermissionsResult(int requestCode, String [] permissions,int [] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"同意",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requstLocation();
                }else {
                    Toast.makeText(this,"未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
}
