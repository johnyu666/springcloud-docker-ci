package cn.johnyu.config.client;

import com.google.errorprone.annotations.RequiredModifiers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RefreshScope //保证Config的客户端可以"手动或自动"的接收到配置信息的变化
public class ConfigClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class,args);
    }

    @Value("${username:JohnYu}")
    private String username="abc";

    @RequestMapping("/users")
    public String findUser(){
        return username;
    }
}
