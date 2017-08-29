package com.home.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dw.utils.DateUtil;


/**
 * @author qpz:
 * @version 创建时间：2017年8月26日 上午9:48:14
 * 关于文章操作的底层操作
 */
@Repository
public class ArticleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//有关闲情杂记的分类统计
	public  List<Map<String, Object>> getArticleTypeNums(){
		String sql="SELECT parentid,count(*)AS count FROM article WHERE folderid=2 GROUP BY parentid";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}

	//有关代码的分类统计
	public  List<Map<String, Object>> getCodeArticleTypeNums(){
		String sql="SELECT parentid,count(*)AS count FROM article WHERE folderid=3 GROUP BY parentid";
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}
	//分类查找文章(日记)
	public Map<String,Object> getdariys(int currentPage,int pageSize,String starttime,String endtime,String keyword){
		Map<String,Object> map=new HashMap<String, Object>();
		String sql="SELECT * FROM article WHERE folderid=1 AND  createtime>='"+starttime+"' and createtime<='"+endtime+"' and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%') "
				+ "order by createtime  LIMIT "+currentPage*pageSize+","+pageSize;
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		for(int i=0;i<list.size();i++){
			list.get(i).put("createtime", DateUtil.formatFromDate((Date) list.get(i).get("createtime")));
		}
		map.put("list", list);
		sql="select count(*) from article WHERE folderid=1 AND  createtime>='"+starttime+"' and createtime<='"+endtime+"' and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%')";
		int count=jdbcTemplate.queryForInt(sql);
		map.put("total", count);
		return map;
	}
	//分类查找文章(闲情杂记)
	public Map<String,Object> getArticles(int currentPage,int pageSize,int type,String keyword){
		//如果是0查找全部代码
		if(type==0){
			Map<String,Object> map=new HashMap<String, Object>();
			String sql="SELECT * FROM article WHERE folderid=2 and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%') "
					+ "order by createtime desc LIMIT "+currentPage*pageSize+","+pageSize;
			List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
			for(int i=0;i<list.size();i++){
				list.get(i).put("createtime", DateUtil.formatFromDate((Date) list.get(i).get("createtime")));
			}
			map.put("list", list);
			sql="select count(*) from article WHERE folderid=2 and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%')";
			int count=jdbcTemplate.queryForInt(sql);
			map.put("total", count);
			return map;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		String sql="SELECT * FROM article WHERE folderid=2 AND parentid="+type+" and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%') "
				+ "order by createtime desc LIMIT "+currentPage*pageSize+","+pageSize;
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		for(int i=0;i<list.size();i++){
			list.get(i).put("createtime", DateUtil.formatFromDate((Date) list.get(i).get("createtime")));
		}
		map.put("list", list);
		sql="select count(*) from article WHERE folderid=2 AND parentid="+type+" and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%')";
		int count=jdbcTemplate.queryForInt(sql);
		map.put("total", count);
		return map;
	}
	//分类查找文章(代码类)
	public Map<String,Object> getCodeArticles(int currentPage,int pageSize,int type,String keyword){
		//如果是0查找全部代码
		if(type==0){
			Map<String,Object> map=new HashMap<String, Object>();
			String sql="SELECT * FROM article WHERE folderid=3 and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%') "
					+ "order by createtime desc LIMIT "+currentPage*pageSize+","+pageSize;
			List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
			for(int i=0;i<list.size();i++){
				list.get(i).put("createtime", DateUtil.formatFromDate((Date) list.get(i).get("createtime")));
			}
			map.put("list", list);
			sql="select count(*) from article WHERE folderid=3 and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%')";
			int count=jdbcTemplate.queryForInt(sql);
			map.put("total", count);
			return map;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		String sql="SELECT * FROM article WHERE folderid=3 AND parentid="+type+" and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%') "
				+ "order by createtime desc LIMIT "+currentPage*pageSize+","+pageSize;
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		for(int i=0;i<list.size();i++){
			list.get(i).put("createtime", DateUtil.formatFromDate((Date) list.get(i).get("createtime")));
		}
		map.put("list", list);
		sql="select count(*) from article WHERE folderid=3 AND parentid="+type+" and (title LIKE '%"+keyword+"%' OR content LIKE '%"+keyword+"%')";
		int count=jdbcTemplate.queryForInt(sql);
		map.put("total", count);
		return map;
	}
	//根据文章id来找文章
	public List<Map<String,Object>> getArticlesbyId(int id){
		String sql="select * from article where articleid="+id;
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		list.get(0).put("createtime", DateUtil.formatFromDate((Date) list.get(0).get("createtime")));
		return list;
	}
}
