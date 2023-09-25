package com.socketio.demo.dto;

import lombok.Data;

@Data
public class AqEventDataDto{
    private Integer pm25;
    private Integer pm10;
    private Float nh3;
    private Float h2s;
    private Float ch2o;
    private Float tvoc;
    private Float temperature;
    private Integer co2;
    private Float humidity;

    public AqEventDataDto() {
    }

    public AqEventDataDto(Integer pm25, Integer pm10, Float nh3, Float h2s, Float ch2o, Float tvoc, Float temperature, Integer co2, Float humidity) {
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.nh3 = nh3;
        this.h2s = h2s;
        this.ch2o = ch2o;
        this.tvoc = tvoc;
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
    }
}
