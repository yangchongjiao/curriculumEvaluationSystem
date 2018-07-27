package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Teacher;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.TeacherService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	

	@RequestMapping(value="/findTeacherById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findTeacherById(@RequestBody RequestReport rr) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("teacher");

		try {
			Teacher teacher = (Teacher)rr.toBean(map,Teacher.class);
			if(teacher==null||teacher.getId()==0){
				
				System.out.println("教师  '"+teacher.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","教师id不能为空");
				return result;
			}
			Teacher resTeacher=teacherService.selectByPrimaryKey(teacher.getId());
			String result = "{}";
			if (resTeacher==null) {
				System.out.println("教师信息不存在！");
				result = BeanUtil.getErrJsonStr();
				

			} else {
				
				System.out.println("教师信息获取成功");
				result = BeanUtil.getJsonStr(resTeacher);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("学生信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","学生信息修改失败！");
			return result;
		}
			
		
	}
	@RequestMapping(value="addTeacher",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addTeacher(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("teacher");

		try {
			Teacher teacher = (Teacher)rr.toBean(map,Teacher.class);
			if(teacher==null
					||StringUtils.isNullOrEmpty(teacher.getName())
					||StringUtils.isNullOrEmpty(teacher.getPhone())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","教师姓名和手机号不能为空！");
				return result;
			}
			Teacher reqTeacher = new Teacher();
			reqTeacher.setPhone(teacher.getPhone());
			List<Teacher>  teachers=teacherService.selectByParam(teacher);
			if(teachers!=null&&teachers.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","手机号已存在！");
				return result;
			}
			Teacher resTeacher=teacherService.insertSelective(teacher);
			String result = "{}";
			if (resTeacher==null) {
				
				System.out.println("教师 添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","教师添加失败！");
				

			} else {
				
				System.out.println("教师添加成功");
				result = BeanUtil.getJsonStr(resTeacher);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("教师添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","教师 添加失败！");
			return result;
		}
			
		
	}
	
	

	@RequestMapping(value="updateTeacher",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateTeacher(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("teacher");

		try {
			Teacher teacher = (Teacher)rr.toBean(map,Teacher.class);
			if(teacher==null
					||teacher.getId()==null
					||teacher.getId()==0
					||StringUtils.isNullOrEmpty(teacher.getName())
					||StringUtils.isNullOrEmpty(teacher.getPhone())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","教师信息不能为空！");
				return result;
			}
			
			int updateRow=teacherService.updateByPrimaryKeySelective(teacher);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("教师信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","教师 信息修改失败！");
				

			} else {
				
				System.out.println("教师信息修改成功");
				Teacher reTeacher=teacherService.selectByPrimaryKey(teacher.getId());
				result = BeanUtil.getJsonStr(reTeacher);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("教师信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","教师信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@RequestMapping(value="teacherlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String teacherlist() throws Exception{
		List<Teacher> teachers = teacherService.getAll();
		String result = BeanUtil.getJsonListStr(teachers);
		return result;
    }

	



}
