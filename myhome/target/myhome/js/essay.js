 var pageIndex = 0; //页面索引初始值  
 var pageSize = 5; //每页显示条数初始化，修改显示条数，修改这里即可 
 var totalData = 0; //分页总数量
 var type=0;
 $(function(){
 	//第一次预先加载全部
 	PageCallback(0);
 	//统计右边的文章个数
 	$(".widget_categories li").click(function(){
 		type=$(this).attr("name");
 		PageCallback(0);
 	})
 	//搜索
 	$("#essay_searchArticle").parent().find("a").click(function(){
 		PageCallback(0);
 	})
 	$.ajax({
 		type:"get",
 		contentType: "application/json",
 		url: 'getArticleTypeNums.action', 
 		success:function(data){
 			data=JSON.parse(data); 
 			for(var i=0;i<data.length;i++){
// 				alert(data[i].parentid);
 				$(".widget_categories li[name='"+data[i].parentid+"'] font").html(data[i].count);
 			}
 		}
 	})
 	//打开单独的yemain
 	$(".article_parent").on("click","article button",function(){
 		javascript:window.location.href='article.html?id='+$(this).attr("name");
 	})
 })
function PageCallback(page_index){
	pageIndex=page_index;
 	$.ajax({
 		type: "POST",
   		contentType: "application/json",
 		dataType: "json",
 		url: 'getArticles.action', //提交到一般处理程序请求数据   
 		data: JSON.stringify({
 			'currentPage': page_index,
 			'pageSize': pageSize,
 			'type':type,
 			'keyword':$("#essay_searchArticle").val().trim()
 		}), //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)           
 		success: function(data) {
 			totalData = data.total;
 			$("#essay_pagination").pagination(totalData, {
		 		callback: PageCallback, //PageCallback() 为翻页调用次函数。　　　　　　　
		 		prev_text: "« 上一页",
		 		next_text: "下一页 »",
		 		items_per_page: pageSize,
		 		num_edge_entries: 1,
		 		num_display_entries: 2,
		 		current_page: page_index,
		 		//			link_to: "?id=__id__" 	//分页的js中会自动把"__id__"替换为当前的数。0　
		
		 	});
 			
 			$(".article_parent").html("");
 			var htmlstr = "";
 			for(var i = 0; i < data.list.length; i++) {
 				htmlstr += '<article class="post">' +
 					'<h3>'+data.list[i].title+'</h3>' +
 					'<span><i class="glyphicon glyphicon-time"></i>'+data.list[i].createtime+'</span>'+
 					'<pre>'+data.list[i].content +'</pre>'+
 					'<button class="btn btn-default" name="'+data.list[i].articleid+'">show more...</button>'+
 					'</article>';
 			}
 			$(".article_parent").html(htmlstr);
 		}
 	});
}
