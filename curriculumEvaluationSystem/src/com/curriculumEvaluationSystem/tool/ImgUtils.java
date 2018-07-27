package com.curriculumEvaluationSystem.tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.curriculumEvaluationSystem.exception.CustomerException;


/**
 * 上传图片
 * @author lisong
 *上传路径 ：项目跟路径 + /img/orderImg/201603/1.jpg
 */
public class ImgUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImgUtils.class);
	
	public static String fileUpload(MultipartFile file,String orderImgPath){
		
		String filePath  = "";
		
		CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();


		
		/**
		 * 判断是否是图片
		 */
//		boolean isImage = isImage(f);
//		if(!isImage){
//			logger.error("不是图片");
//			throw new CustomerException("不是图片");
//		}
		
		/**
		 * 判断是否超过2M
		 */
		try {
			if(file.getBytes().length>2097152){
				logger.error("大小超过2M");
				throw new CustomerException("大小超过2M");
			}
		} catch (IOException e1) {
			logger.error("file.getBytes()失败 "+e1);
			throw new CustomerException("file.getBytes()失败");
		}
		
		
		//数据库存储路径
		String dataPath = "";
		String fileName = getUUIDStr();
		try {
			
			String originalFilename = file.getOriginalFilename();
			String[] strs = originalFilename.split("\\.");
			String extension = strs[strs.length-1].toLowerCase();
			//只支持jpg,jpeg,png格式
			logger.info("图片格式 ：{}",extension);
			if(!("jpg".equals(extension) || "png".equals(extension) || "jpeg".equals(extension))){
				throw new CustomerException("只支持jpg,jpeg,png格式");
			} 
			
			fileName+="."+extension;
			
			Calendar cal = Calendar.getInstance();
			String yearMonth = cal.get(Calendar.YEAR)+"-"+dayFormat((cal.get(Calendar.MONTH)+1),2)+"-"+dayFormat(cal.get(Calendar.DAY_OF_MONTH),2);
			//项目根路径
			String pathBase = System.getProperty("user.dir");
			logger.info("项目根路径 ：{}",pathBase);
			
			File fileTmp = new File(orderImgPath + "/img/orderImg/"+yearMonth+"/");
			
			//拼接返回结果
			filePath =  "/orderImg/"+yearMonth+"/"+fileName;
			
			if(!fileTmp.exists()){
				boolean hasMade = fileTmp.mkdirs();
				
				if(!hasMade){
					throw new FileNotFoundException();
				}
			}
			
			
			logger.info("图片物理存储路径 :"+ fileTmp+"/"+fileName);
			dataPath =  fileTmp+"/"+fileName;
			FileOutputStream fo = new FileOutputStream(dataPath);
			fo.write(file.getBytes());
			fo.flush();
			fo.close();
		} catch (FileNotFoundException e) {
			logger.error("图片存储路径创建失败 "+e);
			throw new CustomerException("图片存储路径创建失败 ");
		} catch (Exception e) {
			logger.error("图片写入失败 "+e);
			throw new CustomerException("您的图片有损坏或格式异常，请您予以检查。");
		}
		
		return filePath;
	}
	
	public static String fileUpload2(MultipartFile file,String orderImgPath){
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传前的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		//String filePath = "E://test//";
		// 解决中文问题，liunx下中文路径，图片显示问题
		 fileName = getUUIDStr() + suffixName;

		logger.info("上传后的文件名为：" + fileName);
		Calendar cal = Calendar.getInstance();
		String yearMonth = cal.get(Calendar.YEAR)+"-"+dayFormat((cal.get(Calendar.MONTH)+1),2)+"-"+dayFormat(cal.get(Calendar.DAY_OF_MONTH),2);
		String filePath = orderImgPath + "/img/"+yearMonth+"/"+ fileName;
		File dest = new File(filePath);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			return filePath;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	public static String dayFormat(int day,int length){
		String formatDay = day + "";
		if((day+"").length()<length){
			int len = length-(day+"").length();
			for (int i = 0; i < len; i++) {
				formatDay = "0" + formatDay;
			}
		}
		return formatDay;
		
	}
	
	/**
	 * 判断是否是图片
	 * @param file
	 * @return boolean
	 */
	public static boolean isImage(File file){
	
		try {
			BufferedImage image =ImageIO.read(file);
			if(null == image){
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("图片读取失败 "+e);
			return false;
		}
		
		return true;
	}
	
	/**
	 *  生成唯一标示符，可用作文件名，防止文件重复
	 * @return uuid 字符串
	 */
	public static String getUUIDStr(){
		String[] strs = UUID.randomUUID().toString().split("-");
		StringBuilder stringBuilder = new StringBuilder();
		for(String str : strs){
			stringBuilder.append(str);
		}
		
		return stringBuilder.toString();
	}
	/**
	 * 截取图片相对路径（只保留时间文件夹+图片）
	 * @return imagePath 字符串
	 */
	public static String subImageUrl(String imagePath){
		String subimgstr="img/";
		if (imagePath!=null&&imagePath.indexOf(subimgstr)!=-1) {
			imagePath=imagePath.substring(imagePath.indexOf(subimgstr)+subimgstr.length());
		}
		return imagePath;
	}
}
