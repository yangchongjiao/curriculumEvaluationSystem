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

import com.curriculumEvaluationSystem.bean.Evaluate;
import com.curriculumEvaluationSystem.bean.Question;
import com.curriculumEvaluationSystem.bean.Student;
import com.curriculumEvaluationSystem.bean.Wenjuan;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.CourseLoadService;
import com.curriculumEvaluationSystem.service.EvaluateService;
import com.curriculumEvaluationSystem.service.QuestionService;
import com.curriculumEvaluationSystem.service.StudentService;
import com.curriculumEvaluationSystem.service.WenjuanService;
import com.curriculumEvaluationSystem.util.BeanUtil;
@Controller
@RequestMapping("/evaluate ")
public class EvaluateController {
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private WenjuanService wenjuanService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CourseLoadService courseLoadService;
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findEvaluateById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findEvaluateById(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("evaluate");

		try {
			Evaluate evaluate = (Evaluate)rr.toBean(map,Evaluate.class);
			if(evaluate==null||evaluate.getId()==0){
					
					System.out.println("问卷  '"+evaluate.toString()+"'不存在！");
					String result = BeanUtil.getErrJsonStr();
					return result;
				}
				Map resevaluate=evaluateService.selectByPrimaryKey(evaluate.getId());
				String result = "{}";
				if (resevaluate==null) {
					System.out.println("问卷评价信息不存在！");
					result = BeanUtil.getErrJsonStr();
					
	
				} else {
					//req.setAttribute("evaluate", evaluate);
					System.out.println("问卷评价信息获取成功");
					result = BeanUtil.getJsonStr(resevaluate);
					
				}
				return result;
			}catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("问卷评价信息添加失败！");
				String result = BeanUtil.getErrResultJsonStr("100","问卷评价信息添加失败！");
				return result;
			}
		}
		@SuppressWarnings("rawtypes")
		@RequestMapping(value="addEvaluate",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
		@ResponseBody
		private String addEvaluate(@RequestBody RequestReport rr) {
			@SuppressWarnings("unchecked")
			Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("evaluate");

			try {
				Evaluate evaluate = (Evaluate)rr.toBean(map,Evaluate.class);
				if(evaluate==null
						||evaluate.getWentiId()==null||evaluate.getWentiId()==0
						||evaluate.getStudentId()==null||evaluate.getStudentId()==0
						||evaluate.getClassId()==null||evaluate.getClassId()==0
						||evaluate.getGrade()==null||evaluate.getGrade()==0
						){
					
					String result = BeanUtil.getErrResultJsonStr("100","课程号、问题编号、学生编号和评价分数不能为空！");
					return result;
				}
				Evaluate reqevaluate = new Evaluate();
				
				reqevaluate.setWentiId(evaluate.getWentiId());
				reqevaluate.setStudentId(evaluate.getStudentId());
				reqevaluate.setClassId(evaluate.getClassId());
				List<Map>  evaluates=evaluateService.selectByParam(evaluate);
				if(evaluates!=null&&evaluates.size()>0){
					String result = BeanUtil.getErrResultJsonStr("100","问卷评价内容已存在！");
					return result;
				}
				
				Wenjuan reqWenjuan = new Wenjuan();
				reqWenjuan.setStudentId(evaluate.getStudentId());
				reqWenjuan.setClassId(evaluate.getClassId());
				List<Map>  wenjuans=wenjuanService.selectByParam(reqWenjuan);
				
				if (wenjuans==null||wenjuans.size()==0) {
					String result = BeanUtil.getErrResultJsonStr("100","学生id--'"+evaluate.getStudentId()+"和课程id--'"+evaluate.getClassId()+"' 对应的问卷内容不存在！");
					return result;
				
				}
				Student resStudent=studentService.selectByPrimaryKey(evaluate.getStudentId());
				
				if (resStudent==null) {
					System.out.println("学生信息不存在！");
					String result = BeanUtil.getErrResultJsonStr("100","学生信息不存在！");
					return result;
				}
				Question resQuestion=questionService.selectByPrimaryKey(evaluate.getWentiId());
				
				if (resQuestion==null) {
					System.out.println("问题信息不存在！");
					String result = BeanUtil.getErrResultJsonStr("100","问题信息不存在！");
					return result;

				}
				Map resCourseLoad=courseLoadService.selectByPrimaryKey(evaluate.getClassId());
				
				if (resCourseLoad==null) {
					String result = BeanUtil.getErrResultJsonStr("100","被评价课程不存在！");
					return result;
					
				}
				Evaluate resevaluate=evaluateService.insertSelective(evaluate);
				String result = "{}";
				if (resevaluate==null) {
					
					System.out.println("问卷评价信息 添加失败！");
					result = BeanUtil.getErrResultJsonStr("100","问卷评价信息添加失败！");
					

				} else {
					
					System.out.println("问卷评价信息添加成功");
					Map resevaluateMap=evaluateService.selectByPrimaryKey(resevaluate.getId());
					result = BeanUtil.getJsonStr(resevaluateMap);
					
				}
				return result;
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问卷评价信息添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问卷评价信息添加失败！");
			return result;
		}
		
		
		
	}
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="updateEvaluate",method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String updateEvaluate(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("evaluate");

		try {
			Evaluate evaluate = (Evaluate)rr.toBean(map,Evaluate.class);
			
			if(evaluate==null
					||evaluate.getId()==null
					||evaluate.getId()==0
					||evaluate.getGrade()==null
					||evaluate.getGrade()==0
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","需要修改的问卷评价内容不能为空！");
				return result;
			}
			Map beforEvaluate=evaluateService.selectByPrimaryKey(evaluate.getId());
			if (beforEvaluate==null) {
				System.out.println("评价信息不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","评价id--'"+evaluate.getId()+"' 对应的信息不存在！");
				return result;
			}
			Evaluate updateEvaluate = new Evaluate();
			updateEvaluate.setId(evaluate.getId());
			updateEvaluate.setGrade(evaluate.getGrade());;
			int updateRow=evaluateService.updateByPrimaryKeySelective(updateEvaluate);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("问卷评价信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","问卷评价信息修改失败！");
				

			} else {
				
				System.out.println("问卷评价信息修改成功");
				Map reevaluate=evaluateService.selectByPrimaryKey(evaluate.getId());
				result = BeanUtil.getJsonStr(reevaluate);
				
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问卷评价信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问卷评价信息修改失败！");
			return result;
		}
		
	}
	//列表
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="evaluatelist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String evaluatelist() throws Exception{
		List<Map> evaluates = evaluateService.getAll();
		String result = BeanUtil.getJsonListStr(evaluates);
		return result;
    }

	



}
