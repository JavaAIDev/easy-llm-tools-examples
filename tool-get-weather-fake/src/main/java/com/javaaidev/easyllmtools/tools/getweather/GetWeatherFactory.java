package com.javaaidev.easyllmtools.tools.getweather;

import com.javaaidev.easyllmtools.llmtoolspec.ConfigurableToolFactory;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherParameters;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherReturnType;

public class GetWeatherFactory implements ConfigurableToolFactory<GetWeatherParameters, GetWeatherReturnType, GetWeather, GetWeatherConfiguration> {

    @Override
    public GetWeather create(final GetWeatherConfiguration config) {
        return new GetWeather(config);
    }

    @Override
    public String toolId() {
        return "GetWeather";
    }
}
