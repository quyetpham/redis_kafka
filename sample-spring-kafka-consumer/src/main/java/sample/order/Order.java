package sample.order;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RedisHash("Order")
public class Order implements Serializable {
	private String name;
	private String code;
	private String price;
	private String count;
}
