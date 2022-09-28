package cn.johnyu.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class PaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class,args);
    }
}
