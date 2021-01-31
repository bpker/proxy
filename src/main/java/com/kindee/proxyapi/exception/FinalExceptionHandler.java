package com.kindee.proxyapi.exception;

import com.kindee.proxyapi.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2018/1/4.
 */
@RestController
@Slf4j
public class FinalExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath(){
        log.info("error.");
        return "/error";
    }
    @RequestMapping("/error")
    public Object error(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        log.info("error.");
        return ApiResponse.getUnkownFailureResponse();
    }


}
