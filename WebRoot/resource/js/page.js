    function checkPage(e) {
		var e = e ? e : window.event;// 前者FF,后者IE
		var src = e.target || e.srcElement;
		var c = e.charCode || e.keyCode;
		if (!(c >= 48 && c <= 57 || c == 8 || c == 37 || c == 39)) {
			c = 0;
			return false;
		} else {
			return true;
		}
	}

    function page(page) {
    	var totalpages = $("#totalPages").val()*1;
    	if(page*1 < 1){
    		$("#pageInput").val(1);
    	}else if(page > totalpages){
    		$("#pageInput").val(totalpages);
    	}else{
    		$("#pageInput").val(page);
    	}
		$("#pageForm").submit();
	}
	
	function pageBnt() {
		var page = $("#pageInput").val()*1;
		var totalpages = $("#totalPages").val()*1;
		if(page < 1){
    		alert("页码从第1页开始");
    	}else if(page > totalpages){
    		alert("页码超出范围");
    	}else{
    		$("#pageForm").submit();
    	}
	}