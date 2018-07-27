package com.curriculumEvaluationSystem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Course;
import com.curriculumEvaluationSystem.bean.CurriculaVariable;
import com.curriculumEvaluationSystem.bean.Student;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.CourseLoadService;
import com.curriculumEvaluationSystem.service.CurriculaVariableService;
import com.curriculumEvaluationSystem.service.StudentService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/curriculaVariable ")
public class CurriculaVariableController {
	@Autowired
	private CurriculaVariableService curriculaVariableService;
	@Autowired
	private CourseLoadService courseLoadService;
	@Autowired
	private StudentService studentService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findCurriculaVariableById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findCurriculaVariableById(@RequestBody RequestReport rr) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("curriculaVariable");

		try {
			CurriculaVariable curriculaVariable = (CurriculaVariable)rr.toBean(map,CurriculaVariable.class);
			if(curriculaVariable==null
					||(curriculaVariable.getClassId()==null||curriculaVariable.getClassId()==0)
					||((curriculaVariable.getStudentId()==null||curriculaVariable.getStudentId()==0))
				){
				
				System.out.println("选课  '"+curriculaVariable.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","选课内容不能为空！");
				return result;
			}
			Map resCurriculaVariable=curriculaVariableService.selectByPrimaryKey(curriculaVariable);
			String result = "{}";
			if (resCurriculaVariable==null) {
				System.out.println("选课信息不存在！");
				result = BeanUtil.getErrResultJsonStr("100","选课信息不存在！");
				

			} else {
				System.out.println("选课信息获取成功");
				result = BeanUtil.getErrResultJsonStr("200","选课信息获取成功");
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("选课内容获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","选课内容获取失败！");
			return result;
		}
			
		
		
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="addCurriculaVariable",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addCurriculaVariable(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("curriculaVariable");

		try {
			CurriculaVariable curriculaVariable = (CurriculaVariable)rr.toBean(map,CurriculaVariable.class);
			if(curriculaVariable==null
					||curriculaVariable.getStudentId()==null||curriculaVariable.getStudentId()==0
					||curriculaVariable.getClassId()==null||curriculaVariable.getClassId()==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","学生编号和课程编号不能为空！");
				return result;
			}
			CurriculaVariable reqCurriculaVariable = new CurriculaVariable();
			reqCurriculaVariable.setStudentId(curriculaVariable.getStudentId());
			reqCurriculaVariable.setClassId(curriculaVariable.getClassId());
			List<Map>  curriculaVariables=curriculaVariableService.selectByParam(curriculaVariable);
			if(curriculaVariables!=null&&curriculaVariables.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","课程已选，不能重复选择！");
				return result;
			}
			Map resCourseLoad=courseLoadService.selectByPrimaryKey(curriculaVariable.getClassId());
			
			if (resCourseLoad==null) {
				String result = BeanUtil.getErrResultJsonStr("100","所选课程不存在！");
				return result;
				
			}
			
			Student resStudent=studentService.selectByPrimaryKey(curriculaVariable.getStudentId());
			
			if (resStudent==null) {
				System.out.println("学生信息不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","学生信息不存在！");
				return result;

			}
			
			CurriculaVariable resCurriculaVariable=curriculaVariableService.insertSelective(curriculaVariable);
			String result = "{}";
			if (resCurriculaVariable==null) {
				
				System.out.println("选课信息 添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","选课信息 添加失败！");
				

			} else {
				
				System.out.println("选课信息 添加成功");
				Map resCurriculaVariableMap=curriculaVariableService.selectByPrimaryKey(resCurriculaVariable);
				result = BeanUtil.getJsonStr(resCurriculaVariableMap);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("选课信息 添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","选课信息 添加失败！");
			return result;
		}
			
		
	}
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateCurriculaVariable",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateCurriculaVariable(@RequestBody RequestReport rr) {
		//@ModelAttribute CurriculaVariable curriculaVariable,int oldStudentId, int oldClassId
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("curriculaVariable");
		String oldStudentIdStr = rr.getDataValue("oldStudentId");
        String oldClassIdStr = rr.getDataValue("oldClassId");
		try {
			int oldStudentId = Integer.valueOf(StringUtils.isNullOrEmpty(oldStudentIdStr)?"0":oldStudentIdStr);
			int oldClassId = Integer.valueOf(StringUtils.isNullOrEmpty(oldClassIdStr)?"0":oldClassIdStr);
			CurriculaVariable curriculaVariable = (CurriculaVariable)rr.toBean(map,CurriculaVariable.class);
			if(curriculaVariable==null
					||(
						(curriculaVariable.getStudentId()==null ||curriculaVariable.getStudentId()==0)
						||(curriculaVariable.getClassId()==null ||curriculaVariable.getClassId()==0)
					  )
					||oldStudentId==0
					||oldClassId==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","需要修改的选课内容不能为空！");
				return result;
			}
			
			Map resCourseLoad=courseLoadService.selectByPrimaryKey(curriculaVariable.getClassId());
			
			if (resCourseLoad==null) {
				String result = BeanUtil.getErrResultJsonStr("100","所选课程不存在！");
				return result;
				
			}
			
			Student resStudent=studentService.selectByPrimaryKey(curriculaVariable.getStudentId());
			
			if (resStudent==null) {
				System.out.println("学生信息不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","学生信息不存在！");
				return result;

			}
			
			CurriculaVariable oldCurriculaVariable = new CurriculaVariable();
			oldCurriculaVariable.setStudentId(oldStudentId);
			oldCurriculaVariable.setClassId(oldClassId);
			Map  beforCurriculaVariable=curriculaVariableService.selectByPrimaryKey(oldCurriculaVariable);
			if (beforCurriculaVariable==null) {
				String result = BeanUtil.getErrResultJsonStr("100","要修改的选课信息不存在！");
				return result;
			}
			
			int updateRow=curriculaVariableService.updateByPrimaryKeySelective(curriculaVariable, oldCurriculaVariable);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("选课信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","选课信息修改失败！");
				

			} else {
				
				System.out.println("选课信息修改成功");
				Map reCurriculaVariable=curriculaVariableService.selectByPrimaryKey(curriculaVariable);
				result = BeanUtil.getJsonStr(reCurriculaVariable);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("选课信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","选课信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="curriculaVariablelist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String curriculaVariablelist() throws Exception{
		List<Map> curriculaVariables = curriculaVariableService.getAll();
		String result = BeanUtil.getJsonListStr(curriculaVariables);
		return result;
    }

	



}
