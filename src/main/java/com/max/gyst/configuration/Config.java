package com.max.gyst.configuration;

import com.max.gyst.swagger.SpringfoxConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringfoxConfig.class)
public class Config {
}
