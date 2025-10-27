package com.neoapps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
        "com.neoapps.usecase",
        "com.neoapps.driven_adapters",
        "com.neoapps.entry_points"},

        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        }, useDefaultFilters = false)
public class GeneralConfiguration {

}
