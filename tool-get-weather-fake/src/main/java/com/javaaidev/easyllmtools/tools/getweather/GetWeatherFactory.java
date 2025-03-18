package com.javaaidev.easyllmtools.tools.getweather;

import com.javaaidev.easyllmtools.llmtoolspec.ToolFactory;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration;

public class GetWeatherFactory implements ToolFactory<GetWeather, GetWeatherConfiguration> {

    @Override
    public GetWeather create(final GetWeatherConfiguration config) {
        return new GetWeather(config);
    }
}
