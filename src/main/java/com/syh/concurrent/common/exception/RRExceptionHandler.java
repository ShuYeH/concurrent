package com.syh.concurrent.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.syh.concurrent.common.Response.ResponseInfo;
import com.syh.concurrent.common.Response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 *
 */
@RestControllerAdvice //全局异常处理类
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseEntity<JSONObject> handleRRException(RRException e){
		ResponseEntity responseEntity = new ResponseEntity(e.getErr(),e.getStatus());
		return responseEntity;
	}

	/**
	 * 请求方式不对
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleRequestMethodException(HttpRequestMethodNotSupportedException e){
		ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	/**
	 * 实体类验证异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		Map errors=new HashMap();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseInfo.error(400,errors.toString());
	}

	/**
	 * 方法单独参数验证异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseInfo handleConstraintViolationException(ConstraintViolationException e){
		return ResponseInfo.error(400,e.getLocalizedMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
