package cn.johnyu.order.controller;

import cn.johnyu.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/orders/{id}")
    public String buy(@PathVariable int id){
        String info = restTemplate.getForObject("http://payment-service/payments/1", String.class);
        return "下订单： "+info;
    }

    @RequestMapping(value = "/orders2/{id}")
    @HystrixCommand(fallbackMethod = "loadPaymentFallback")
    public String buy2(@PathVariable int id){

        String info=orderService.loadPayment(id);
        return  "使用feign下订单："+info;
    }

    public String loadPaymentFallback(int id){
        return "errror,error!!!";
    }
}
