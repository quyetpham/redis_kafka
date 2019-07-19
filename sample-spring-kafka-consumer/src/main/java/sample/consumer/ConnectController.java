package sample.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.consumer.config.RedisConfig;
import sample.order.OrderCoin;
import sample.queue.RedisMessagePublisher;
import sample.queue.RedisMessageSubscriber;


@RestController
public class ConnectController {
	@Autowired
	RedisConfig redisConfig;
    
	@GetMapping("/connectdb")
    public void connectdb() {
		redisConfig.redisTemplate().convertAndSend("chat", "hello");		
    	System.out.println("connect db thanh cong");
    	//RedisMessageSubscriber redisSubcribe = new RedisMessageSubscriber();
    	redisConfig.redisTemplate().opsForValue().set("quyet", "Pham Van Quyet");
    	System.out.println(redisConfig.redisTemplate().opsForValue().get("quyet"));
    	
    	//System.out.println(RedisMessageSubscriber.messageList.get(0).contains("hello"));
//    	OrderCoin orderCoin = new OrderCoin();
//    	orderCoin.setCode("1");
//    	orderCoin.setCount("2");
//    	orderCoin.setName("name");
//    	orderCoin.setPrice("10000");
//    	//redisConfig.redisTemplate().opsForHash().put(key, hashKey, value);set("order1",orderCoin);
//    	System.out.println(redisConfig.redisTemplate().opsForValue().get("order1"));
    }

}
