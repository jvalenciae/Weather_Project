package com.example.myapplicationrw.data;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherGson {

    public static Gson g = new Gson();

    public List<Weather> weather;
    public Main main;
    public long dt;

    public static ArrayList<ForecastWeatherGson> getClimateForecast(JSONObject response){
        ArrayList<ForecastWeatherGson> list = new ArrayList<>();
        try {
            JSONArray info = response.getJSONArray("list");
            for(int i = 0; i < info.length(); i++){
                String forecast = info.getJSONObject(i).toString();
                ForecastWeatherGson temp = g.fromJson(forecast, ForecastWeatherGson.class);
                list.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;

        public Weather(){}

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }

    public static class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;

        public Main(){}

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
}
