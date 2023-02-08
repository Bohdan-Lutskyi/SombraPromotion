package com.sombra.promotion.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("system.default")
@NoArgsConstructor
@AllArgsConstructor
public class SystemProperties {

    private Double passCourseThresholdValue;
}
