package com.curriculumEvaluationSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curriculumEvaluationSystem.bean.Evaluate;
import com.curriculumEvaluationSystem.bean.Wenjuan;
import com.curriculumEvaluationSystem.report.RequestReport;
import com.curriculumEvaluationSystem.service.EvaluateService;
import com.curriculumEvaluationSystem.service.WenjuanService;
import com.curriculumEvaluationSystem.util.BeanUtil;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/wenjuan ")
public class WenjuanController {
	@Autowired
	private WenjuanService wenjuanService;
	@Autowired
	private EvaluateService evaluateService;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/findWenjuanById",produces = "application/json; charset=utf-8")  
	@ResponseBody
	private String findWenjuanById(@RequestBody RequestReport rr) {
		
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("wenjuan");

		try {
			Wenjuan wenjuan = (Wenjuan)rr.toBean(map,Wenjuan.class);
			if(wenjuan==null||wenjuan.getId()==0){
				
				System.out.println("问卷  '"+wenjuan.toString()+"'不存在！");
				String result = BeanUtil.getErrResultJsonStr("100","问卷id不能为空！");
				return result;
			}
			Map resWenjuan=wenjuanService.selectByPrimaryKey(wenjuan.getId());
			String result = "{}";
			if (resWenjuan==null) {
				System.out.println("问卷信息不存在！");
				result = BeanUtil.getErrResultJsonStr("100","问卷信息不存在！");
				

			} else {
				
				System.out.println("问卷信息获取成功");
				Evaluate reqevaluate = new Evaluate();
				int studentId = (int)resWenjuan.get("studentId");
				int classId = (int)resWenjuan.get("classId");
				reqevaluate.setStudentId(studentId);
				reqevaluate.setClassId(classId);
				List<Map>  evaluates=evaluateService.selectByParam(reqevaluate);
				resWenjuan.put("evaluates",evaluates);
				result = BeanUtil.getJsonStr(resWenjuan);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问卷信息获取失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问卷信息获取失败！");
			return result;
		}
			
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="addWenjuan",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String addWenjuan(@RequestBody RequestReport rr) {
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("wenjuan");

		try {
			List<Map>  evaluates=(List<Map>)map.get("evaluates");
			map.remove("evaluates");
			Wenjuan wenjuan = (Wenjuan)rr.toBean(map,Wenjuan.class);
			if(wenjuan==null
					||wenjuan.getClassId()==null||wenjuan.getClassId()==0
					||wenjuan.getStudentId()==null||wenjuan.getStudentId()==0
					||StringUtils.isNullOrEmpty(wenjuan.getWenjuanTitle()) 
					||StringUtils.isNullOrEmpty(wenjuan.getEvaluateContnet()) 
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","问题编号、课程编号、问卷标题和评价内容不能为空！");
				return result;
			}
			Wenjuan reqWenjuan = new Wenjuan();
			reqWenjuan.setStudentId(wenjuan.getStudentId());
			reqWenjuan.setClassId(wenjuan.getClassId());
			List<Map>  Wenjuans=wenjuanService.selectByParam(wenjuan);
			if(Wenjuans!=null&&Wenjuans.size()>0){
				String result = BeanUtil.getErrResultJsonStr("100","问卷内容已存在！");
				return result;
			}
			Wenjuan resWenjuan=wenjuanService.insertSelective(wenjuan);
			String result = "{}";
			if (resWenjuan==null) {
				
				System.out.println("问卷信息 添加失败！");
				result = BeanUtil.getErrResultJsonStr("100","问卷信息添加失败！");
				

			} else {
				for (int i = 0; i < evaluates.size(); i++) {
					Map evaluateMap = (Map)evaluates.get(i);
					
					Evaluate evaluate = (Evaluate)rr.toBean(evaluateMap,Evaluate.class);
					Evaluate reqevaluate = new Evaluate();
					
					reqevaluate.setWentiId(evaluate.getWentiId());
					reqevaluate.setStudentId(evaluate.getStudentId());
					reqevaluate.setClassId(evaluate.getClassId());
					List<Map>  beforevaluates=evaluateService.selectByParam(evaluate);
					if(beforevaluates!=null&&beforevaluates.size()>0){
						System.out.println("问卷评价内容已存在！---"+beforevaluates.get(0).toString());
						continue;
					}
					evaluate=evaluateService.insertSelective(evaluate);
					if (evaluate!=null) {
						System.out.println("评价id："+evaluate.getId()+" 的信息添加成功");
					} else {
						System.out.println("评价课程 评价失败");
					}
				}
				System.out.println("问卷信息添加成功");
				Map resWenjuanMap=wenjuanService.selectByPrimaryKey(resWenjuan.getId());
				Evaluate reqevaluate = new Evaluate();
				int studentId = (int)resWenjuanMap.get("studentId");
				int classId = (int)resWenjuanMap.get("classId");
				reqevaluate.setStudentId(studentId);
				reqevaluate.setClassId(classId);
				List<Map>  evaluatelist=evaluateService.selectByParam(reqevaluate);
				resWenjuanMap.put("evaluates",evaluatelist);
				result = BeanUtil.getJsonStr(resWenjuanMap);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问卷信息添加失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问卷信息添加失败！");
			return result;
		}
			
		
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="updateWenjuan",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	private String updateWenjuan(@RequestBody RequestReport rr) {
		Map<String, Object>  map   = (Map<String, Object>)rr.getCommandInfo().getData().get("wenjuan");

		try {
			List<Map>  evaluates=(List<Map>)map.get("evaluates");
			map.remove("evaluates");
			Wenjuan wenjuan = (Wenjuan)rr.toBean(map,Wenjuan.class);
			if(wenjuan==null
					||evaluates==null
					||wenjuan.getId()==null
					||wenjuan.getId()==0
					||(
						(StringUtils.isNullOrEmpty(wenjuan.getWenjuanTitle()) )
						&&(StringUtils.isNullOrEmpty(wenjuan.getEvaluateContnet()) )
					  )
					){
				
				String result = BeanUtil.getErrResultJsonStr("100","需要修改的问卷id不能为空且问卷标题和评价内容不能同时为空，且问题列表不能为空！");
				return result;
			}
			
			Wenjuan updateWenjuan = new Wenjuan();
			updateWenjuan.setId(wenjuan.getId());
			if(!StringUtils.isNullOrEmpty(wenjuan.getWenjuanTitle())){
				updateWenjuan.setWenjuanTitle(wenjuan.getWenjuanTitle());
			}
			if(!StringUtils.isNullOrEmpty(wenjuan.getEvaluateContnet())){
				updateWenjuan.setEvaluateContnet(wenjuan.getEvaluateContnet());
			}
			int updateRow=wenjuanService.updateByPrimaryKeySelective(updateWenjuan);
			String result = "{}";
			if (updateRow==0) {
				
				System.out.println("问卷信息修改失败！");
				result = BeanUtil.getErrResultJsonStr("100","问卷 信息修改失败！");
				

			} else {
				for (int i = 0; i < evaluates.size(); i++) {
					Map evaluateMap = (Map)evaluates.get(i);
					int id = (int)evaluateMap.get("id");
					int grade = (int)evaluateMap.get("grade");
					Evaluate updateEvaluate = new Evaluate();
					updateEvaluate.setId(id);
					updateEvaluate.setGrade(grade);;
					int upRow=evaluateService.updateByPrimaryKeySelective(updateEvaluate);
					if (upRow>0) {
						System.out.println("评价id："+id+"的信息修改成功");
					} else {
						System.out.println("评价id："+id+"信息修改失败");
					}
				}
				
				System.out.println("问卷信息修改成功");
				Map reWenjuan=wenjuanService.selectByPrimaryKey(wenjuan.getId());
				Evaluate reqevaluate = new Evaluate();
				int studentId = (int)reWenjuan.get("studentId");
				int classId = (int)reWenjuan.get("classId");
				reqevaluate.setStudentId(studentId);
				reqevaluate.setClassId(classId);
				List<Map>  evaluatelist=evaluateService.selectByParam(reqevaluate);
				reWenjuan.put("evaluates",evaluatelist);
				result = BeanUtil.getJsonStr(reWenjuan);
				
			}
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("问卷信息修改失败！");
			String result = BeanUtil.getErrResultJsonStr("100","问卷信息修改失败！");
			return result;
		}
			
		
	}
	//列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="wenjuanlist",method=RequestMethod.GET,produces = "application/json; charset=utf-8")  
	@ResponseBody
    public String wenjuanlist() throws Exception{
		List<Map> wenjuans = wenjuanService.getAll();
		List<Map> wenjuanList = new ArrayList();
		for (int i = 0; i < wenjuans.size(); i++) {
			Map reWenjuan=wenjuans.get(i);
			
			Evaluate reqevaluate = new Evaluate();
			int studentId = (int)reWenjuan.get("studentId");
			int classId = (int)reWenjuan.get("classId");
			reqevaluate.setStudentId(studentId);
			reqevaluate.setClassId(classId);
			List<Map>  evaluates=evaluateService.selectByParam(reqevaluate);
			reWenjuan.put("evaluates",evaluates);
			wenjuanList.add(reWenjuan);
		}
		String result = BeanUtil.getJsonListStr(wenjuanList);
		return result;
    }

	



}
