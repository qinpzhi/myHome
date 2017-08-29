package com.home.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.CommentDao;

/**
 * @author qpz:
 * @version 创建时间：2017年8月24日 下午8:12:30
 * 类说明
 */
@Service
public class CommentService {
	@Autowired 
	private CommentDao commentDao;
	//分页查找comment
	public Map<String,Object> getComment(int currentPage,int pageSize){
		return commentDao.getComment(currentPage, pageSize);
	}
	//点赞
	public void clickUp(int id){
		commentDao.clickUp(id);
	}
	//踩
	public void stepDown(int id){
		commentDao.stepDown(id);
	}
	//增加评论
	public void addcomment(String comment){
		commentDao.addcomment(comment);
	}
}