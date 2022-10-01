package cn.johnyu.payment.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @RequestMapping(value = "/payments/{id}")
    @HystrixCommand(fallbackMethod = "loadPaymentFallback",
        commandProperties = {
//            timeout的配置，只有在strategy=THREAD和SEMAPHORE时都会生效
                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
//                最近滑窗错误率达到 40% 的阈时，请求被熔断
                @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "40"),
//                滑窗的样本为10个请
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
//                断路器，在没有新的请求到达，冷却恢复的时间为8秒
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "8000"),
//              采用信号量机制的隔离策略
                @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
//                采用线程池机制的隔离策略
//                @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD"),
//                同一时间段处理的最大信号量的个数为2
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
        return "Thread:"+Thread.currentThread().getName()+ "@"+processInfo+"-"+Math.random();
    }
    public String loadPaymentFallback(int id){
        return "payment 错误，"+id;
    }
}
