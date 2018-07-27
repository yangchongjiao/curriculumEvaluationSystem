package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Student;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.StudentService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	

	@RequestMapping(value="/findStudentById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findStudentById(@RequestBody RequestReport rr) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("student");

		try {
			Student student = (Student)rr.toBean(map,Student.class);
			if(student==null||student.getId()==0){
				
				System.out.println("学生  '"+student.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","学生id不能为空！");
				return result;
			}
			Student resStudent=studentService.selectByPrimaryKey(student.getId());
			String result = "{}";
			if (resStudent==null) {
				System.out.println("学生信息不存在！");
				result = BeanUtil.getErrResultJsonStr("100","学生信息不存在！");
				

			} else {
				
				System.out.println("学生信息获取成功");
				result = BeanUtil.getJsonStr(resStudent);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("学生信息获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","学生信息获取失败！");
			return result;
		}
			
		
	}
	@RequestMapping(value="addStudent",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addStudent(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("student");

		try {
			Student student = (Student)rr.toBean(map,Student.class);
			if(student==null
					||StringUtils.isNullOrEmpty(student.getName())
					||StringUtils.isNullOrEmpty(student.getPhone())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","学生姓名和手机号不能为空！");
				return result;
			}
			Student reqStudent = new Student();
			reqStudent.setStudentNumber(student.getStudentNumber());;
			List<Student>  students=studentService.selectByParam(reqStudent);
			if(students!=null&&students.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","学号 - '"+student.getStudentNumber()+"' 已存在！");
				return result;
			}
			Student resStudent=studentService.insertSelective(student);
			String result = "{}";
			if (resStudent==null) {
				
				System.out.println("学生添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","学生添加失败！");
				

			} else {
				
				System.out.println("学生添加成功");
				result = BeanUtil.getJsonStr(resStudent);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("学生添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","学生添加失败！");
			return result;
		}
			
		
	}
	
	

	@RequestMapping(value="updateAmin",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateAmin(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("student");

		try {
			Student student = (Student)rr.toBean(map,Student.class);
			if(student==null
					||student.getId()==null
					||student.getId()==0
					||StringUtils.isNullOrEmpty(student.getName())
					||StringUtils.isNullOrEmpty(student.getPhone())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","学生信息不能为空！");
				return result;
			}
			
			int updateRow=studentService.updateByPrimaryKeySelective(student);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("学生信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","学生 信息修改失败！");
				

			} else {
				
				System.out.println("学生信息修改成功");
				Student restudent=studentService.selectByPrimaryKey(student.getId());
				result = BeanUtil.getJsonStr(restudent);
				
			}
			return result;
			
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("学生信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","学生信息修改失败！");
			return result;
		}
	}
	//列表
	@RequestMapping(value="studentlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String studentlist() throws Exception{
		List<Student> students = studentService.getAll();
		String result = BeanUtil.getJsonListStr(students);
		return result;	
    }

	



}
