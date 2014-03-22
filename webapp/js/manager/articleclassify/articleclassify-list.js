define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('page'); 
	$(document).ready(function(){  
		$(".editBtn").click(function(e) {
            var idLength = $("input[name='id']:checked").length;
			if(idLength <= 0) {alert("请选择一个进行编辑!");return}
			if(idLength >= 2) {alert("只能选择一个进行编辑!");return}
			location.href = 'edit?id=' + $("input[name='id']:checked").val();
        });
		
		$(".delBtn").click(function(e) {
			var idLength = $("input[name='id']:checked").length;
			if(idLength <= 0) {alert("请选择一个进行操作!");return} 
			
			 
			$("#controlForm").attr("action","del.json");
			$("#controlForm").ajaxSubmit({
				dataType:'json', 
				success:function(data) {
					alert(data.message); 
					$("input[name='id']:checked").each(function(index, element) {
                        $(this).parent("td").parent("tr").remove();  
                    });
					
				}
			});
        });
	}); 
});