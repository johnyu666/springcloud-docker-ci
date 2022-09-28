package cn.johnyu.order.service;

import cn.johnyu.order.fallback.MyFallbackFactory;
import cn.johnyu.order.service.impl.OrderServiceFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "payment-service")
public interface OrderService {

    @RequestMapping(value = "/payments/{id}")
    String loadPayment(@PathVariable(value = "id") int id);

    default  String loadPaymentFallback(int id){
        return "错错！";
    }
}
