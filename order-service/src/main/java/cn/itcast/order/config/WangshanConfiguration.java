package cn.itcast.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wangshan")
public class WangshanConfiguration {

    private Integer test;
}
