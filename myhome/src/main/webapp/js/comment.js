 var pageIndex = 0; //页面索引初始值  
 var pageSize = 10; //每页显示条数初始化，修改显示条数，修改这里即可 
 var totalData = 0; //分页总数量
 $(function() {
 	//第一次预先加载
 	PageCallback(0);
 	//点赞
 	$(".commentItem_parent").on('click','.comment_item i.icon-dianzan',function(){
 		$.ajax({
	 		type: "POST",
	 		contentType: "application/json",
	 		dataType: "json",
	 		async:false,
	 		url: 'clickUp.action', //提交到一般处理程序请求数据   
			data: JSON.stringify({'id':$(this).parent().attr("name")}),     
	 		success: function(data) {
	 		}
 		});
 	 PageCallback(pageIndex);
 	})
 	//踩
 	//点赞
 	$(".commentItem_parent").on('click','.comment_item i.icon-cai',function(){
 		$.ajax({
	 		type: "POST",
	 		contentType: "application/json",
	 		dataType: "json",
	 		async:false,
	 		url: 'stepDown.action', //提交到一般处理程序请求数据   
			data: JSON.stringify({'id':$(this).parent().attr("name")}),     
	 		success: function(data) {
	 		}
 		});
 	 PageCallback(pageIndex);
 	})
 });

 function PageCallback(page_index) {
 	pageIndex=page_index;
 	$.ajax({
 		type: "POST",
// 		contentType: "application/json",
 		dataType: "json",
 		url: 'getCommentList.action', //提交到一般处理程序请求数据   
 		data: JSON.stringify({
 			'currentPage': page_index,
 			'pageSize': pageSize
 		}), //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)           
 		success: function(data) {
 			totalData = data.total;
 			$(".comments_list .totalCount").html("共"+totalData+"条评论")
 			$("#comments_pagination").pagination(totalData, {
		 		callback: PageCallback, //PageCallback() 为翻页调用次函数。　　　　　　　
		 		prev_text: "« 上一页",
		 		next_text: "下一页 »",
		 		items_per_page: pageSize,
		 		num_edge_entries: 1,
		 		num_display_entries: 2,
		 		current_page: page_index,
		 		//			link_to: "?id=__id__" 	//分页的js中会自动把"__id__"替换为当前的数。0　
		
		 	});
 			
 			$(".commentItem_parent").html("");
 			var htmlstr = "";
 			for(var i = 0; i < data.list.length; i++) {
 				htmlstr += '<div class="comment_item">' +
 					'<p>' + data.list[i].content + '<br>' +
 					'<font name="'+data.list[i].id+'"><i class="iconfont icon-dianzan">'+data.list[i].clicktimes+'</i><i class="iconfont icon-cai">'+data.list[i].steptimes+'</i></font>' +
 					'</p>' +
 					'</div>';
 			}
 			$(".commentItem_parent").html(htmlstr);
 		}
 	});
 }