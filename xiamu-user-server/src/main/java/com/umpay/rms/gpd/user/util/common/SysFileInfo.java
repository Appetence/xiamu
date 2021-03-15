package com.umpay.rms.gpd.user.util.common;

import java.util.Date;

/** 
* @ClassName: SysFileInfo 
* @Description: TODO(文件上传实体) 
* @author zhuyuru
* @date 2014年3月20日 下午3:34:19 
*/ 
public class SysFileInfo {

	//spring mvc上传文件的两种方式
	
	private Date upfiletime;
	
	private String savefilename;//2015-05-29 09 49 58
	
	private String savepath;
	private String relName;
	
	
	


	public String getRelName( ) {
		return relName;
	}

	public void setRelName( String relName ) {
		this.relName = relName;
	}

	public Date getUpfiletime() {
		return upfiletime;
	}

	public void setUpfiletime(Date upfiletime) {
		this.upfiletime = upfiletime;
	}


	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	
}
