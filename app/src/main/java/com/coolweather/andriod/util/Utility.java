package com.coolweather.andriod.util;

import android.text.TextUtils;


import com.coolweather.andriod.db.City;
import com.coolweather.andriod.db.County;
import com.coolweather.andriod.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static boolean handleProvinceResponse(String response){

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray =   new JSONArray(response);
                for (int i= 0;i<jsonArray.length();i++){
                   JSONObject object =  jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(object.getString("name"));
                    province.setProvinceCode(object.getInt("id"));
                    province.save();
                }
                return  true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCityResponse(String response,int provinceId){

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0 ;i < jsonArray.length();i ++) {
                 JSONObject object =   jsonArray.getJSONObject(i);
                City city = new City();
                city.setCityName(object.getString("name"));
                city.setCityCode(object.getInt("id"));
                city.setProvinceId(provinceId);
                city.save();
                }
                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int  i = 0 ; i < jsonArray.length() ; i++){
                   JSONObject object =  jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.save();
                }
                return true;
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
