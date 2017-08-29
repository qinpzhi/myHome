package com.home.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.ArticleDao;

/**
 * @author qpz:
 * @version 创建时间：2017年8月26日 上午9:47:32
 * 有关文章的service层
 */
@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	//有关闲情杂记的分类统计
	public  List<Map<String, Object>> getArticleTypeNums(){
		return articleDao.getArticleTypeNums();
	}
	//有关代码的分类统计
	public  List<Map<String, Object>> getCodeArticleTypeNums(){
		return articleDao.getCodeArticleTypeNums();
	}
	//分类查找文章(日记)
	public Map<String,Object> getdariys(int currentPage,int pageSize,String starttime,String endtime,String keyword){
		return articleDao.getdariys(currentPage, pageSize, starttime,endtime,keyword);
	}
	//分类查找文章(闲情杂记)
	public Map<String,Object> getArticles(int currentPage,int pageSize,int type,String keyword){
		return articleDao.getArticles(currentPage, pageSize, type,keyword);
	}
	//分类查找文章(代码类)
	public Map<String,Object> getCodeArticles(int currentPage,int pageSize,int type,String keyword){
		return articleDao.getCodeArticles(currentPage, pageSize, type,keyword);
	}
	//根据文章id来找文章
	public List<Map<String,Object>> getArticlebyId(int id){
		return articleDao.getArticlesbyId(id);
	}
}
