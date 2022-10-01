package cn.johnyu.order.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = SocketTimeoutException.class)

    public ResponseEntity<String> timeoutHandle(HttpServletRequest request,Exception e){
        return new ResponseEntity("因调用Feign超时，导致controller的异常", HttpStatus.GATEWAY_TIMEOUT);
    }
}
