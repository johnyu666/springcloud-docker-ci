package cn.johnyu.order.service.impl;

import cn.johnyu.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceFallback implements OrderService {
    @Override
    public String loadPayment(int id) {
        return "调用loadPayment失败了";
    }
}
