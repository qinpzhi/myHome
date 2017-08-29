package com.home.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author qpz:
 * @version 创建时间：2017年8月24日 下午7:54:35
 * 有关评论的相关底层操作
 */
@Repository
public class CommentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//分页查找comment
	public Map<String,Object> getComment(int currentPage,int pageSize){
		Map<String,Object> map=new HashMap<String, Object>();
		String sql="select *from comment order by createtime desc limit "+currentPage*pageSize+","+pageSize;
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		map.put("list", list);
		sql="select count(*) from comment";
		int count=jdbcTemplate.queryForInt(sql);
		map.put("total", count);
		return map;
	}
	//点赞
	public void clickUp(int id){
		String sql="update comment set clicktimes=clicktimes+1 where id="+id;
		jdbcTemplate.update(sql);
	}
	//踩
	public void stepDown(int id){
		String sql="update comment set steptimes=steptimes+1 where id="+id;
		jdbcTemplate.update(sql);
	}
	//增加评论
	public void addcomment(String comment){
		String sql="insert into comment(content) values('"+comment+"')";
		jdbcTemplate.update(sql);
	}
}
