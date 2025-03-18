package com.javaaidev.easyllmtools.tools.getweather;

import java.lang.reflect.Type;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherParameters;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherReturnType;

public class GetWeather extends AbstractGetWeather {

    public GetWeather(final GetWeatherConfiguration config) {
        super(config);
    }

    @Override
    public Type getRequestType() {
        return GetWeatherParameters.class;
    }

    @Override
    public GetWeatherReturnType call(final GetWeatherParameters parameters) {
        return new GetWeatherReturnType();
    }
}
