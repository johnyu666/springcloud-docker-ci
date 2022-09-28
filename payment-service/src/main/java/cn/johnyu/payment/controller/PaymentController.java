package cn.johnyu.payment.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @RequestMapping(value = "/payments/{id}")
    @HystrixCommand(fallbackMethod = "loadPaymentFallback",
        commandProperties = {
                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
                @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "40"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "8000"),
                @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "2")
            }
//            ,
//        threadPoolProperties = {
//                @HystrixProperty(name = "coreSize",value = "2")
//        }
    )
    public String loadPayment(@PathVariable int id){
        //id==0时，直接出异常
        if(id==0) throw new RuntimeException("id==0,错误了");
        //id==1时，延时2秒
        if(id==1){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
        }
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String processInfo=runtimeMXBean.getName();
        return Thread.currentThread().getName()+ "@"+processInfo+"-"+Math.random();
    }
    public String loadPaymentFallback(int id){
        return "payment 错误，"+id;
    }
}
