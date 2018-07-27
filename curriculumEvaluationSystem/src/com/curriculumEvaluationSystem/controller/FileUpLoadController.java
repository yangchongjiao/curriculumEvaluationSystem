package com.curriculumEvaluationSystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.curriculumEvaluationSystem.service.FileUpLoadService;
import com.curriculumEvaluationSystem.util.BeanUtil;
@Controller
@RequestMapping("/fileUpLoad ")
public class FileUpLoadController {
    @Autowired
    private FileUpLoadService upLoadService;

    @Autowired
    private ResourceLoader resourceLoader;


    private String ImgPath="d:/data/curriculumEvaluationSystem/img/";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	/**
     * 图片上传
     * @return String pathes  图片路径
     */

    @RequestMapping(value="imgUpload",produces = "application/json; charset=utf-8")  
    @ResponseBody
    public String imgUpload(final MultipartHttpServletRequest  request){
        String test= request.getParameter("test");
        logger.info("imgUpload method ,test : {}",test);
        if(null == request.getFiles("file") ||  request.getFiles("file").size()<=0){
            
            String result = BeanUtil.getErrResultJsonStr("100","上传文件数量为0！");
			return result;
        }
        if(request.getFiles("file").size()>3){
            String result = BeanUtil.getErrResultJsonStr("100","上传文件数量不能超过3张！");
			return result;
        }

        logger.info("imgUpload method ,imgSize : {}",request.getFiles("file").size());


        return upLoadService.imgUpload(request);


    }

    /**
     * 处理图片显示请求
     * @param fileName
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{filepath}/{filename:.+}")
    public void showPicture(@PathVariable String filepath,@PathVariable String filename,
                            HttpServletResponse response){
        File imgFile = new File(ImgPath + filepath + "/" + filename);
        responseFile(response, imgFile);
    }

    
    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }




}
