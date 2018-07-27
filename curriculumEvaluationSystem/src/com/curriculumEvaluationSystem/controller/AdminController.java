package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Admin;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.AdminService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	

	@RequestMapping(value="/login",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String login(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("admin");

		try {
			Admin admin = (Admin)rr.toBean(map,Admin.class);
			
			
			if(admin==null||
					StringUtils.isNullOrEmpty(admin.getUsername())
					||StringUtils.isNullOrEmpty(admin.getPassword())){
				
				System.out.println("用户  '"+admin.getUsername()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","账号或密码不正确，请重新输入！");
				return result;
			}
			Admin resAdmin=adminService.selectByParam(admin);
			String result = "{}";
			if (resAdmin==null) {
				
				System.out.println("用户  '"+admin.getUsername()+"'不存在！");
				result = BeanUtil.getErrJsonStr();
				

			} else {
				
				System.out.println("用户已登录");
				result = BeanUtil.getJsonStr(resAdmin);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("登录失败！");
			String result = BeanUtil.getErrResultJsonStr("100","登录失败！");
			return result;
		}
			
		
		
	}
	@RequestMapping(value="saveSignup",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String saveSignup(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("admin");

		try {
			Admin admin = (Admin)rr.toBean(map,Admin.class);
			if(admin==null
					||StringUtils.isNullOrEmpty(admin.getUsername())
					||StringUtils.isNullOrEmpty(admin.getPassword())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","用户信息不能为空！");
				return result;
			}
			Admin reqadmin = new Admin();
			reqadmin.setUsername(admin.getUsername());
			Admin oldAdmin=adminService.selectByParam(admin);
			if(oldAdmin!=null){
				String result = BeanUtil.getErrResultJsonStr("100","用户名称已存在！");
				return result;
			}
			Admin resAdmin=adminService.insertSelective(admin);
			String result = "{}";
			if (resAdmin==null) {
				
				System.out.println("用户 注册失败！");
				result = BeanUtil.getErrResultJsonStr("100","用户 注册失败！");
				

			} else {
				
				System.out.println("用户注册成功");
				result = BeanUtil.getJsonStr(resAdmin);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户 注册失败！");
			String result = BeanUtil.getErrResultJsonStr("100","用户 注册失败！");
			return result;
		}
			
			
		
		
	}
	
	

	@RequestMapping(value="updateAmin",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateAmin(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("admin");

		try {
			Admin admin = (Admin)rr.toBean(map,Admin.class);
			if(admin==null
					||admin.getId()==0
					||StringUtils.isNullOrEmpty(admin.getUsername())
					||StringUtils.isNullOrEmpty(admin.getPassword())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","用户信息不能为空！");
				return result;
			}
			Admin reqadmin = new Admin();
			reqadmin.setUsername(admin.getUsername());
			Admin oldAdmin=adminService.selectByParam(admin);
			if(oldAdmin!=null){
				String result = BeanUtil.getErrResultJsonStr("100","用户名称已存在！");
				return result;
			}
			int updateRow=adminService.updateByPrimaryKeySelective(admin);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("用户信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","用户 信息修改失败！");
				

			} else {
				
				System.out.println("用户修改成功");
				oldAdmin=adminService.selectByParam(admin);
				result = BeanUtil.getJsonStr(oldAdmin);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("用户信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","用户信息修改失败！");
			return result;
		}
			
		
		
	}
	//列表
	@RequestMapping(value="userlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String userlist() throws Exception{
		List<Admin> users = adminService.getUsers();
		String result = BeanUtil.getJsonListStr(users);
		return result;
    }

	



}
