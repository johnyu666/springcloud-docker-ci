package cn.johnyu.order.fallback;

import cn.johnyu.order.service.impl.OrderServiceFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFallbackFactory implements FallbackFactory<OrderServiceFallback> {
    @Override
    public OrderServiceFallback create(Throwable throwable) {
        System.out.println("ok.........................");
        return new OrderServiceFallback();
    }
}
