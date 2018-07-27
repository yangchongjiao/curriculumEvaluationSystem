package com.curriculumEvaluationSystem.report;

import java.util.Map;

@SuppressWarnings({"serial"}) 	
public class RequestReport extends BaseReport
{
	private AppInfo appInfo;

	private UserProperty userProperty;

	private DeviceInfo deviceInfo;

	private CommandInfo commandInfo;


	public RequestReport()
	{
		super();
	}

	public AppInfo getAppInfo()
	{
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo)
	{
		this.appInfo = appInfo;
	}

	public UserProperty getUserProperty()
	{
		return userProperty;
	}

	public void setUserProperty(UserProperty userProperty)
	{
		this.userProperty = userProperty;
	}

	public DeviceInfo getDeviceInfo()
	{
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo)
	{
		this.deviceInfo = deviceInfo;
	}

	public CommandInfo getCommandInfo()
	{
		return commandInfo;
	}

	public void setCommandInfo(CommandInfo commandInfo)
	{
		this.commandInfo = commandInfo;
	}

	public String getDataValue(String key) {
		// TODO Auto-generated method stub
		if(commandInfo==null || commandInfo.getData()==null){
			return "";
		}
		return commandInfo.getData().get(key)==null?null:commandInfo.getData().get(key).toString();
	}

	public  Object toBean(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
        Object obj = beanClass.newInstance();  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
        return obj;  
    }    
}
