package com.syh.concurrent.common.Response;

import com.alibaba.fastjson.JSONObject;


import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseInfo {

	public static void write(HttpServletResponse response, Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}

	/**
	 * 状态码
	 */
	//状态码,当等于200的时候,都是成功!其它都是失败")
	private int code;

	/**
	 * 返回的数据
	 */
	//返回的数据,根据不同接口可能有JSONObject 或者 JSONArray")
	private Object data;

	/**
	 * 返回结果描述
	 */
	//当状态码返回不是200的时候的错误信息")
	private String msg;


	@Override
	public String toString(){
		return  "code:"+code+"  msg:"+msg+"  data:"+ JSONObject.toJSONString(data);
	}

	public ResponseInfo(ResultCode status,Object data){
		this.code = status.getCode();
		this.msg = status.getMessage();
		this.data = data;
	}

	public ResponseInfo(ResultCode status) {
		this.code = status.getCode();
		this.msg = status.getMessage();
		this.data = "";
	}

	public ResponseInfo(int code,String message) {
		this.code = code;
		this.msg = message;
		this.data = "";
	}

	public static ResponseInfo ok(Object data){
		return new ResponseInfo(ResultCode.SUCCESS,data);
	}

	public static ResponseInfo ok() {
		return new ResponseInfo(ResultCode.SUCCESS);
	}

	public static ResponseInfo error(ResultCode error) {
		return new ResponseInfo(error);
	}

	public static ResponseInfo error(int code,String message) {
		return new ResponseInfo(code, message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
