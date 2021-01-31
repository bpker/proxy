package com.kindee.proxyapi.model;

import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by lixuezhao on 2018/4/27.
 */
@Data
public class ApiResponse {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1001;
    public static final int UNKONW_FAILURE = 500;
    public static String SUCCESS_DESCRIPTION = "查询成功";
    public static String FAILURE_DESCRIPTION = "查询失败";
    public static String UNKONW_FAILURE_DESCRIPTION = "未知错误";


    private int errorCode;
    private String description;
    private JSONArray data;

    public ApiResponse(int errorCode, String description, JSONArray data) {
        this.errorCode = errorCode;
        this.description = description;
        this.data = data;
    }


    public static ApiResponse getSuccessResonse(JSONArray data){
        return new ApiResponse(ApiResponse.SUCCESS, ApiResponse.SUCCESS_DESCRIPTION, data);
    }

    public static ApiResponse getFailureResponse(){
        return new ApiResponse(ApiResponse.FAILURE, ApiResponse.FAILURE_DESCRIPTION, null);
    }

    public static ApiResponse getUnkownFailureResponse(){
        return new ApiResponse(ApiResponse.UNKONW_FAILURE, ApiResponse.UNKONW_FAILURE_DESCRIPTION, null);
    }



}
