﻿(function($){   
    $.fn.extend({     
         yx_rotaion: function(options) {   
		    //默认参数
            var defaults = {
			     /**轮换间隔时间，单位毫秒*/
                 during:2000,
				 /**是否显示左右按钮*/
                 btn:true,
				 /**是否显示焦点按钮*/
                 focus:true,
				 /**是否显示标题*/
                 title:true,
				 /**是否自动播放*/
                 auto:true				 
            };       
            var options = $.extend(defaults, options);   
            return this.each(function(){
			    var o = options;   
				var curr_index = 0;
                var $this = $(this);				
                var $li = $this.find("li");
                var li_count = $li.length;
                //　滚动显示范围的宽、高等属性
				//$this.css({position:'relative',overflow:'hidden',width:$li.find("img").width(),height:$li.find("img").height()});
                $this.css({position:'relative',overflow:'hidden',width:'1200px',height:'270px'});
                
                /*自适应屏幕宽度**/
                /*$this.css({position:'relative',overflow:'hidden',width:'100%',height:'100%'});
                $li.find("img").attr("width","100%");*/
                /*//**/
                
				// 隐藏所有的滚动li元素
				$this.find("li").css({position:'absolute',left:0,top:0}).hide();
			    // 默认显示第一个
				$li.first().show();
			    $this.append('<div class="yx-rotaion-btn"><span class="left_btn"><\/span><span class="right_btn"></span><\/div>');
				if(!o.btn) $(".yx-rotaion-btn").css({visibility:'hidden'});
                if(o.title) $this.append(' <div class="yx-rotation-title"><\/div><a href="" class="yx-rotation-t"><\/a>');
                if(o.focus) $this.append('<div class="yx-rotation-focus"><\/div>');
				var $btn = $(".yx-rotaion-btn span"),$title = $(".yx-rotation-t"),$title_bg = $(".yx-rotation-title"),$focus = $(".yx-rotation-focus");
				//如果自动播放，设置定时器
				if(o.auto) var t = setInterval(function(){$btn.last().click();},o.during);
				// 默认情况下title处显示信息的获取与处理
				$title.text($li.first().find("img").attr("alt"));	
				$title.attr("href",$li.first().find("a").attr("href"));				
				
               // 输出焦点按钮
               for(i=1;i<=li_count;i++){
                 $focus.append('<span>'+i+'</span>');
               }
               // 兼容IE6透明图片   
               if($.browser.msie && $.browser.version == "6.0" ){
                  $btn.add($focus.children("span")).css({backgroundImage:'url(images/ico.gif)'});
               }		
               var $f = $focus.children("span");
               $f.first().addClass("hover");
               // 鼠标覆盖左右按钮设置透明度
               $btn.hover(function(){
	              $(this).addClass("hover");
               },function(){
	              $(this).removeClass("hover");
               });
			   //鼠标覆盖元素，清除计时器
               $btn.add($li).add($f).hover(function(){
                if(t) clearInterval(t);
               },function(){
                if(o.auto) t = setInterval(function(){$btn.last().click();},o.during);
               });
			   //鼠标覆盖焦点按钮效果
               $f.bind("mouseover",function(){
	             var i = $(this).index();
	             $(this).addClass("hover");
	             $focus.children("span").not($(this)).removeClass("hover");
	             $li.eq(i).fadeIn(300);
                 $li.not($li.eq(i)).fadeOut(300);	
	             // 滚动时更改title信息
                 $title.text($li.eq(i).find("img").attr("alt"));
	             curr_index = i;
               });
			   //鼠标点击左右按钮效果
               $btn.bind("click",function(){
                 $(this).index() == 1?curr_index++:curr_index--;
	             if(curr_index >= li_count) curr_index = 0;
	             if(curr_index < 0) curr_index = li_count-1;
                 $li.eq(curr_index).fadeIn(300);
	             $li.not($li.eq(curr_index)).fadeOut(300);	
	             $f.eq(curr_index).addClass("hover");
	             $f.not($f.eq(curr_index)).removeClass("hover");
	             
	             $title.text($li.eq(curr_index).find("img").attr("alt"));
				 $title.attr("href",$li.eq(curr_index).find("a").attr("href"));	
               });
 
            });   
        }   
    });   
       
})(jQuery);

