package com.curriculumEvaluationSystem.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.curriculumEvaluationSystem.bean.Admin;

public class BeanUtil {

	
	public static String getJsonStr(Object obj){
		JSONObject  dataJson= JSON.parseObject(JSON.toJSONString(obj));
		JSONObject json = new JSONObject();
		json.put("code", "200");
		json.put("errMsg", "ok");
		json.put("data", dataJson);
		return json.toJSONString();
	}
	
	public static String getErrJsonStr(){
		
		JSONObject json = new JSONObject();
		json.put("code", "100");
		json.put("errMsg", "账号或密码不正确，请重新输入");
		json.put("data", new JSONObject());
		return json.toJSONString();
	}

	public static String getErrResultJsonStr(String code,String errMsg){
		
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("errMsg", errMsg);
		json.put("data", new JSONObject());
		return json.toJSONString();
	}
	public static String getJsonListStr(List users){
		JSONArray jsonarr=JSONArray.parseArray(JSONArray.toJSONString(users));
		JSONObject dataJson = new JSONObject();
		dataJson.put("list", jsonarr);
		JSONObject resutJson = new JSONObject();
		resutJson.put("code", "200");
		resutJson.put("errMsg", "ok");
		resutJson.put("data", dataJson);
		return resutJson.toJSONString();
	}
}
