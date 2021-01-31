package com.kindee.proxyapi.exception;

import com.kindee.proxyapi.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

/**
 * Created by Administrator on 2018/1/4.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
       log.warn(ex.getMessage(),ex);
       return new ResponseEntity<Object>(ApiResponse.getUnkownFailureResponse(), NOT_EXTENDED);
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleExceptionOther(HttpServletRequest request, Exception e) throws Exception {
        log.warn(e.getMessage(),e);
        return ApiResponse.getUnkownFailureResponse();
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(ApiResponse.getFailureResponse(), NOT_EXTENDED);
    }

}
