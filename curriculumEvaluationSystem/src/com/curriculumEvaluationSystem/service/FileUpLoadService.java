package com.curriculumEvaluationSystem.service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * Created by Administrator on 2017/9/13.
 */
@Component
public interface FileUpLoadService {
    /**
     * 图片上传
     * @param request
     * @return
     */
    public String imgUpload(MultipartHttpServletRequest  request);
}
