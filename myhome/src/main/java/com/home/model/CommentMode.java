package com.home.model;
/**
 * @author qpz:
 * @version 创建时间：2017年8月24日 下午7:51:40
 * comment实体类
 */
public class CommentMode {
	String content;
	int clicktimes;
	int steptimes;
	String creattime;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getClicktimes() {
		return clicktimes;
	}
	public void setClicktimes(int clicktimes) {
		this.clicktimes = clicktimes;
	}
	public int getSteptimes() {
		return steptimes;
	}
	public void setSteptimes(int steptimes) {
		this.steptimes = steptimes;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
}
