package sample.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.consumer.config.RedisConfig;

@RestController
public class ConnectController {
	@Autowired
	RedisConfig redisConfig;
    
	@GetMapping("/connectdb")
    public void connectdb() {
		redisConfig.redisTemplate().convertAndSend("chat", "hello");		
    	System.out.println("connect db thanh cong");
    }

}
