package com.talelin.linboot.autoconfigure.core;

import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author 桔子
 * @since 2021/1/25 18:41
 */
@ComponentScan(value = "com.talelin.linboot", excludeFilters = @ComponentScan.Filter(
        type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class))
public @interface EnableLinBoot {

}
