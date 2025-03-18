package com.javaaidev.easyllmtools.tools.getweather;

import com.javaaidev.easyllmtools.llmtoolspec.Tool;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherParameters;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherReturnType;

public abstract class AbstractGetWeather implements Tool<GetWeatherParameters, GetWeatherReturnType> {

    protected GetWeatherConfiguration config;

    protected AbstractGetWeather(final GetWeatherConfiguration config) {
        this.config = config;
    }

    @Override
    public String getId() {
        return "GetWeather";
    }

    @Override
    public String getName() {
        return "GetWeather";
    }

    @Override
    public String getDescription() {
        return "Get weather by location";
    }

    @Override
    public String getParametersSchema() {
        return "{\"type\":\"object\",\"properties\":{\"location\":{\"type\":\"string\",\"description\":\"Location\"}}}";
    }

    @Override
    public String getReturnTypeSchema() {
        return "{\"type\":\"object\",\"properties\":{\"condition\":{\"type\":\"string\",\"description\":\"Weather condition\"},\"temperature\":{\"type\":\"number\",\"description\":\"Temperature\"},\"temperatureUnit\":{\"type\":\"string\",\"description\":\"Unit of temperature\",\"enum\":[\"C\",\"F\"]}}}";
    }

    @Override
    public String getExamples() {
        return "[{\"description\":\"Get weather of New York\",\"parameters\":{\"location\":\"New York\"},\"returnValue\":{\"condition\":\"Sunny\",\"temperature\":28,\"temperatureUnit\":\"C\"}}]";
    }
}
