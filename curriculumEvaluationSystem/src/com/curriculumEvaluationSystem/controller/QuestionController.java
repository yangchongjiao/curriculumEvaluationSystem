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
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.QuestionService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	

	@RequestMapping(value="/findQuestionById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findUserById(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("question");

		try {
			Question question = (Question)rr.toBean(map,Question.class);
			if(question==null||question.getId()==0){
				System.out.println("问题  '"+question.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","问题id不能为空！");
				return result;
			}
			Question resQuestion=questionService.selectByPrimaryKey(question.getId());
			String result = "{}";
			if (resQuestion==null) {
				System.out.println("问题信息不存在！");
				result = BeanUtil.getErrJsonStr();
				

			} else {
				
				System.out.println("问题获取成功");
				result = BeanUtil.getJsonStr(resQuestion);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问题获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问题获取失败！");
			return result;
		}
			
		
		
		
	}
	@RequestMapping(value="addQuestion",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addQuestion(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("question");

		try {
			Question question = (Question)rr.toBean(map,Question.class);
			if(question==null
					||StringUtils.isNullOrEmpty(question.getContent())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","问题内容不能为空！");
				return result;
			}
			Question reqQuestion = new Question();
			reqQuestion.setContent(question.getContent());
			List<Question>  questions=questionService.selectByParam(question);
			if(questions!=null&&questions.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","该问题内容已存在！");
				return result;
			}
			Question resQuestion=questionService.insertSelective(question);
			String result = "{}";
			if (resQuestion==null) {
				
				System.out.println("问题 添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","问题添加失败！");
				

			} else {
				
				System.out.println("问题添加成功");
				result = BeanUtil.getJsonStr(resQuestion);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问题 添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问题 添加失败！");
			return result;
		}
			
		
	}
	
	

	@RequestMapping(value="updateQuestion",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateQuestion(@RequestBody RequestReport rr) {
		@SuppressWarnings("unchecked")
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("question");

		try {
			Question question = (Question)rr.toBean(map,Question.class);
			if(question==null
					||question.getId()==null
					||question.getId()==0
					||StringUtils.isNullOrEmpty(question.getContent())
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","问题信息不能为空！");
				return result;
			}
			
			int updateRow=questionService.updateByPrimaryKeySelective(question);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("问题信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","问题 信息修改失败！");
				

			} else {
				
				System.out.println("问题信息修改成功");
				Question reQuestion=questionService.selectByPrimaryKey(question.getId());
				result = BeanUtil.getJsonStr(reQuestion);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问题信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问题信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@RequestMapping(value="questionlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String questionlist() throws Exception{
		List<Question> questions = questionService.getAll();
		String result = BeanUtil.getJsonListStr(questions);
		return result;
    }

	



}
