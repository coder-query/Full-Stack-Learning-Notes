package com.it.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public Object handlerJsonDateException(ArithmeticException e) {
        return "算数异常,请检查代码...";
    }

    /**
     * 当发生空指针异常会触发此方法!
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public Object handlerNullException(NullPointerException e) {
        return "出现空指针,请检查数据...";
    }

    /**
     * 所有异常都会触发此方法!但是如果有具体的异常处理Handler!
     * 具体异常处理Handler优先级更高!
     * 例如: 发生NullPointerException异常!
     * 会触发handlerNullException方法,不会触发handlerException方法!
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e) {
        return null;
    }

}
