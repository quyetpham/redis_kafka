package sample.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import sample.queue.MessagePublisher;
import sample.queue.RedisMessagePublisher;

@Configuration
@ConfigurationProperties(prefix = "spring1.redis")
public class RedisConfig {
	@Autowired
	private RedisProperties redisProperties;

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		System.out.println("add config");
		System.out.println(redisProperties.getHost());
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		System.out.println("add config");
		System.out.println(redisProperties.getHost());
		jedisConFactory.setHostName(redisProperties.getHost());
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	MessagePublisher redisPublisher() {
		return new RedisMessagePublisher(redisTemplate(), topic());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("pubsub:queue");
	}
}
