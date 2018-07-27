package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Course;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.CourseLoadService;
import com.curriculumEvaluationSystem.service.CourseService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/course ")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseLoadService courseLoadService;
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findCourseById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findCourseById(@RequestBody RequestReport rr) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("course");

		try {
			Course course = (Course)rr.toBean(map,Course.class);
			if(course==null||course.getClassId()==0){
				
				System.out.println("课程  '"+course.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","课程id不能为空");
				return result;
			}
			Map resCourse=courseService.selectByPrimaryKey(course.getClassId());
			String result = "{}";
			if (resCourse==null) {
				System.out.println("课程信息不存在！");
				result = BeanUtil.getErrJsonStr();
				

			} else {
				
				System.out.println("课程信息获取成功");
				result = BeanUtil.getJsonStr(resCourse);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("课程信息获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","课程信息获取失败！");
			return result;
		}
			
		
		
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="addCourse",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addCourse(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("course");

		try {
			Course course = (Course)rr.toBean(map,Course.class);
			if(course==null
					||StringUtils.isNullOrEmpty(course.getAddress())
					||StringUtils.isNullOrEmpty(course.getSchoolTime())
					||course.getClassId()==null
					||course.getClassId()==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","课程id、上课地址和上课时间不能为空！");
				return result;
			}
			Map resCourseLoadMap=courseLoadService.selectByPrimaryKey(course.getClassId());
			if(resCourseLoadMap==null){
				String result = BeanUtil.getErrResultJsonStr("100","课程id--'"+course.getClassId()+"' 对应的课程不存在，请先添加开课信息");
				return result;
			}
			Map resCourseMap=courseService.selectByPrimaryKey(course.getClassId());
			if(resCourseMap!=null){
				String result = BeanUtil.getErrResultJsonStr("100","该课程已存在");
				return result;
			}
			Course resCourse=courseService.insertSelective(course);
			String result = "{}";
			if (resCourse==null) {
				
				System.out.println("课程信息 添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","课程信息添加失败！");
				

			} else {
				
				System.out.println("课程信息添加成功");
				resCourseMap=courseService.selectByPrimaryKey(resCourse.getClassId());
				result = BeanUtil.getJsonStr(resCourseMap);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("课程信息 添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","课程信息 添加失败！");
			return result;
		}
			
		
	}
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateCourse",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateCourse(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("course");

		try {
			Course course = (Course)rr.toBean(map,Course.class);
			if(course==null
					||StringUtils.isNullOrEmpty(course.getAddress())
					||StringUtils.isNullOrEmpty(course.getSchoolTime())
					||course.getClassId()==null
					||course.getClassId()==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","课程id、上课地址和上课时间不能为空！");
				return result;
			}
			
			int updateRow=courseService.updateByPrimaryKeySelective(course);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("课程信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","课程 信息修改失败！");
				

			} else {
				
				System.out.println("课程信息修改成功");
				Map reCourse=courseService.selectByPrimaryKey(course.getClassId());
				result = BeanUtil.getJsonStr(reCourse);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("课程信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","课程信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="courselist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String courselist() throws Exception{
		List<Map> courses = courseService.getAll();
		String result = BeanUtil.getJsonListStr(courses);
		return result;
    }

	



}
