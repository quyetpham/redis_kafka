package sample.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "spring1.redis")
@Getter
@Setter
public class RedisProperties {
	private String host;	
}
