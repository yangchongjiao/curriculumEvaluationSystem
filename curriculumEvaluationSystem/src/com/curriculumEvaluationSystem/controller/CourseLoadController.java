package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.CourseLoad;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.CourseLoadService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/courseLoad ")
public class CourseLoadController {
	@Autowired
	private CourseLoadService courseLoadService;
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findCourseLoadById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findCourseLoadById(@RequestBody RequestReport rr) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("courseLoad");

		try {
			CourseLoad courseLoad = (CourseLoad)rr.toBean(map,CourseLoad.class);
			if(courseLoad==null||courseLoad.getId()==0){
				
				System.out.println("开课  '"+courseLoad.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","开课id不能为空！");
				return result;
			}
			Map resCourseLoad=courseLoadService.selectByPrimaryKey(courseLoad.getId());
			String result = "{}";
			if (resCourseLoad==null) {
				System.out.println("开课信息不存在！");
				result = BeanUtil.getErrJsonStr();
				

			} else {
				
				System.out.println("开课已登录");
				result = BeanUtil.getJsonStr(resCourseLoad);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("开课信息获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","开课信息获取失败！");
			return result;
		}
			
		
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="addCourseLoad",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addCourseLoad(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("courseLoad");

		try {
			CourseLoad courseLoad = (CourseLoad)rr.toBean(map,CourseLoad.class);
			if(courseLoad==null
					||StringUtils.isNullOrEmpty(courseLoad.getClassname())
					||courseLoad.getTeacherId()==null
					||courseLoad.getTeacherId()==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","课程名称和教师编号不能为空！");
				return result;
			}
			CourseLoad reqCourseLoad = new CourseLoad();
			reqCourseLoad.setClassname(courseLoad.getClassname());
			List<Map>  courseLoads=courseLoadService.selectByParam(courseLoad);
			if(courseLoads!=null&&courseLoads.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","课程名称已存在！");
				return result;
			}
			CourseLoad resCourseLoad=courseLoadService.insertSelective(courseLoad);
			String result = "{}";
			if (resCourseLoad==null) {
				
				System.out.println("开课信息添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","开课信息添加失败！");
				

			} else {
				
				System.out.println("开课信息添加成功");
				Map resCourseLoadMap=courseLoadService.selectByPrimaryKey(resCourseLoad.getId());
				result = BeanUtil.getJsonStr(resCourseLoadMap);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("开课信息添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","开课信息添加失败！");
			return result;
		}
			
		
	}
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateCourseLoad",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateCourseLoad(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("courseLoad");

		try {
			
			CourseLoad courseLoad = (CourseLoad)rr.toBean(map,CourseLoad.class);
			if(courseLoad==null
					||courseLoad.getId()==null
					||courseLoad.getId()==0
					||StringUtils.isNullOrEmpty(courseLoad.getClassname())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","开课信息不能为空！");
				return result;
			}
			
			int updateRow=courseLoadService.updateByPrimaryKeySelective(courseLoad);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("开课信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","开课 信息修改失败！");
				

			} else {
				
				System.out.println("开课信息修改成功");
				Map reCourseLoad=courseLoadService.selectByPrimaryKey(courseLoad.getId());
				result = BeanUtil.getJsonStr(reCourseLoad);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("开课信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","开课信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="courseLoadlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String courseLoadlist() throws Exception{
		List<Map> courseLoads = courseLoadService.getAll();
		String result = BeanUtil.getJsonListStr(courseLoads);
		return result;
    }

	



}
