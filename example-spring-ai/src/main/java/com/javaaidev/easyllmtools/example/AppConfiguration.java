package com.javaaidev.easyllmtools.example;

import com.javaaidev.easyllmtools.integration.springai.ToolsFunctionCallbackResolver;
import com.javaaidev.easyllmtools.tools.canadaholidays.api.HolidaysApiAgentToolkit.AgentToolFactory_holidays;
import com.javaaidev.easyllmtools.tools.canadaholidays.api.HolidaysApiAgentToolkit.AgentTool_holidays;
import com.javaaidev.easyllmtools.tools.canadaholidays.api.HolidaysApiToolConfiguration;
import com.javaaidev.easyllmtools.tools.getweather.GetWeather;
import com.javaaidev.easyllmtools.tools.getweather.GetWeatherFactory;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration;
import com.javaaidev.easyllmtools.tools.getweather.model.GetWeatherConfiguration.TemperatureUnit;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiAgentToolkit.AgentToolFactory_sectionFormatGet;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiAgentToolkit.AgentTool_sectionFormatGet;
import com.javaaidev.easyllmtools.tools.nytimes.api.StoriesApiToolConfiguration;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.model.function.DefaultFunctionCallbackResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public SimpleLoggerAdvisor simpleLoggerAdvisor() {
    return new SimpleLoggerAdvisor();
  }

  @Bean
  public GetWeather getWeather() {
    var config = new GetWeatherConfiguration();
    config.setTemperatureUnit(TemperatureUnit.C);
    return new GetWeatherFactory().create(config);
  }

  @Bean
  public AgentTool_holidays getCanadaHolidays() {
    return new AgentToolFactory_holidays().create(new HolidaysApiToolConfiguration());
  }

  @Bean
  public AgentTool_sectionFormatGet getTopStories() {
    var config = new StoriesApiToolConfiguration();
    var apiKey = System.getenv("NYTIMES_API_KEY");
    config.setApiKey(apiKey);
    return new AgentToolFactory_sectionFormatGet().create(config);
  }

  @Bean
  public ToolsFunctionCallbackResolver toolsFunctionCallbackResolver(
      ApplicationContext applicationContext) {
    DefaultFunctionCallbackResolver fallbackResolver = new DefaultFunctionCallbackResolver();
    fallbackResolver.setApplicationContext(applicationContext);
    return new ToolsFunctionCallbackResolver(fallbackResolver);
  }
}
