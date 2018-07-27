package com.curriculumEvaluationSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.curriculumEvaluationSystem.exception.CustomerException;
import com.curriculumEvaluationSystem.service.FileUpLoadService;
import com.curriculumEvaluationSystem.tool.ImgUtils;

/**
 * Created by Administrator on 2017/9/13.
 */
@Component
public class FileUpLoadServiceImple implements FileUpLoadService {

    
    private String orderImgPath="d:/data/curriculumEvaluationSystem/";
    /**
     * 图片上传
     * 返回图片url
     */
    @Override
    public String imgUpload( MultipartHttpServletRequest request) {

        List<MultipartFile> list = request.getFiles("file");
        List<String> pathList = new ArrayList<String>();

        for(MultipartFile file : list ){
            if(file.getSize()<=0){
                continue;
            }

            String filePath = ImgUtils.fileUpload2(file, orderImgPath);


            if(StringUtils.isEmpty(filePath)){
                throw new CustomerException("路径为空");
            }

            //String absoluteFilePath = orderImgUrl + filePath;
            String newImagePath=ImgUtils.subImageUrl(filePath);

            pathList.add(newImagePath);
        }

        String pathes = StringUtils.join(pathList, ",");
        //orderRedis.updateImgs(orderId, pathes);

        return pathes;
    }
    
    
}
