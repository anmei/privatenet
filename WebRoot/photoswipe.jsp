<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'photoswipe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
	<![endif]-->
	
	<!-- Core CSS file -->
	<link rel="stylesheet" href="<%=path %>/resource/photoswipe/photoswipe.css"> 
	
	<!-- Skin CSS file (styling of UI - buttons, caption, etc.)
	     In the folder of skin CSS file there are also:
	     - .png and .svg icons sprite, 
	     - preloader.gif (for browsers that do not support CSS animations) -->
	<link rel="stylesheet" href="<%=path %>/resource/photoswipe/default-skin/default-skin.css"> 
	
	<!-- Core JS file -->
	<script src="<%=path %>/resource/photoswipe/photoswipe.min.js"></script> 
	
	<!-- UI JS file -->
	<script src="<%=path %>/resource/photoswipe/photoswipe-ui-default.min.js"></script>
	
	<style type="text/css">
		.my-gallery {
		  width: 100%;
		  float: left;
		}
		.my-gallery img {
		  width: 100%;
		  width: auto;
		}
		.my-gallery figure {
		  display: block;
		  float: left;
		  margin: 0 5px 5px 0;
		  height: 150px;
		}
		.my-gallery figcaption {
		  display: none;
		}
	</style> 
  </head>
  
  <body>
    <!-- Root element of PhotoSwipe. Must have class pswp. -->
	<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
	    <!-- Background of PhotoSwipe. 
	         It's a separate element as animating opacity is faster than rgba(). -->
	    <div class="pswp__bg"></div>
	    <!-- Slides wrapper with overflow:hidden. -->
	    <div class="pswp__scroll-wrap">
	        <!-- Container that holds slides. 
	            PhotoSwipe keeps only 3 of them in the DOM to save memory.
	            Don't modify these 3 pswp__item elements, data is added later on. -->
	        <div class="pswp__container">
	            <div class="pswp__item"></div>
	            <div class="pswp__item"></div>
	            <div class="pswp__item"></div>
	        </div>
	
	        <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
	        <div class="pswp__ui pswp__ui--hidden">
	
	            <div class="pswp__top-bar">
	
	                <!--  Controls are self-explanatory. Order can be changed. -->
	
	                <div class="pswp__counter"></div>
	
	                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
	
	                <button class="pswp__button pswp__button--share" title="Share"></button>
	
	                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
	
	                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
	
	                <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
	                <!-- element will get class pswp__preloader--active when preloader is running -->
	                <div class="pswp__preloader">
	                    <div class="pswp__preloader__icn">
	                      <div class="pswp__preloader__cut">
	                        <div class="pswp__preloader__donut"></div>
	                      </div>
	                    </div>
	                </div>
	            </div>
	
	            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
	                <div class="pswp__share-tooltip"></div> 
	            </div>
	
	            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
	            </button>
	
	            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
	            </button>
	
	            <div class="pswp__caption">
	                <div class="pswp__caption__center"></div>
	            </div>
	        </div>
	    </div>
	</div>
	<H3>&nbsp;</H3>
	<h1>PHOTO GALLERY</h1>
	<div class="my-gallery" itemscope itemtype="http://schema.org/ImageGallery">

	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/c.jpg" itemprop="contentUrl" data-size="640x480">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/c.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/b.jpg" itemprop="contentUrl" data-size="640x480">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/b.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/d.jpg" itemprop="contentUrl" data-size="670x502">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/d.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/e.jpg" itemprop="contentUrl" data-size="900x904">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/e.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/f.jpg" itemprop="contentUrl" data-size="2048x1536">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/f.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/a.jpg" itemprop="contentUrl" data-size="480x640">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/a.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/g.jpg" itemprop="contentUrl" data-size="480x640">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/g.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	    <figure itemprop="associatedMedia" itemscope itemtype="http://schema.org/ImageObject">
	        <a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/h.jpg" itemprop="contentUrl" data-size="624x840">
	            <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/h.jpg" height="130" itemprop="thumbnail" alt="Image description" />
	        </a>
	        <figcaption itemprop="caption description">Image caption</figcaption>
	    </figure>
	
	
	</div>
	
	
	
	
	
	
	
	
	<script type="text/javascript">
	var initPhotoSwipeFromDOM = function(gallerySelector) {

	    // parse slide data (url, title, size ...) from DOM elements 
	    // (children of gallerySelector)
	    var parseThumbnailElements = function(el) {
	        var thumbElements = el.childNodes,
	            numNodes = thumbElements.length,
	            items = [],
	            figureEl,
	            linkEl,
	            size,
	            item;
	
	        for(var i = 0; i < numNodes; i++) {
	
	            figureEl = thumbElements[i]; // <figure> element
	
	            // include only element nodes 
	            if(figureEl.nodeType !== 1) {
	                continue;
	            }
	
	            linkEl = figureEl.children[0]; // <a> element
	
	            size = linkEl.getAttribute('data-size').split('x');
	
	            // create slide object
	            item = {
	                src: linkEl.getAttribute('href'),
	                w: parseInt(size[0], 10),
	                h: parseInt(size[1], 10)
	            };
	
	
	
	            if(figureEl.children.length > 1) {
	                // <figcaption> content
	                item.title = figureEl.children[1].innerHTML; 
	            }
	
	            if(linkEl.children.length > 0) {
	                // <img> thumbnail element, retrieving thumbnail url
	                item.msrc = linkEl.children[0].getAttribute('src');
	            } 
	
	            item.el = figureEl; // save link to element for getThumbBoundsFn
	            items.push(item);
	        }
	
	        return items;
	    };
	
	    // find nearest parent element
	    var closest = function closest(el, fn) {
	        return el && ( fn(el) ? el : closest(el.parentNode, fn) );
	    };
	
	    // triggers when user clicks on thumbnail
	    var onThumbnailsClick = function(e) {
	        e = e || window.event;
	        e.preventDefault ? e.preventDefault() : e.returnValue = false;
	
	        var eTarget = e.target || e.srcElement;
	
	        // find root element of slide
	        var clickedListItem = closest(eTarget, function(el) {
	            return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
	        });
	
	        if(!clickedListItem) {
	            return;
	        }
	
	        // find index of clicked item by looping through all child nodes
	        // alternatively, you may define index via data- attribute
	        var clickedGallery = clickedListItem.parentNode,
	            childNodes = clickedListItem.parentNode.childNodes,
	            numChildNodes = childNodes.length,
	            nodeIndex = 0,
	            index;
	
	        for (var i = 0; i < numChildNodes; i++) {
	            if(childNodes[i].nodeType !== 1) { 
	                continue; 
	            }
	
	            if(childNodes[i] === clickedListItem) {
	                index = nodeIndex;
	                break;
	            }
	            nodeIndex++;
	        }
	
	
	
	        if(index >= 0) {
	            // open PhotoSwipe if valid index found
	            openPhotoSwipe( index, clickedGallery );
	        }
	        return false;
	    };
	
	    // parse picture index and gallery index from URL (#&pid=1&gid=2)
	    var photoswipeParseHash = function() {
	        var hash = window.location.hash.substring(1),
	        params = {};
	
	        if(hash.length < 5) {
	            return params;
	        }
	
	        var vars = hash.split('&');
	        for (var i = 0; i < vars.length; i++) {
	            if(!vars[i]) {
	                continue;
	            }
	            var pair = vars[i].split('=');  
	            if(pair.length < 2) {
	                continue;
	            }           
	            params[pair[0]] = pair[1];
	        }
	
	        if(params.gid) {
	            params.gid = parseInt(params.gid, 10);
	        }
	
	        if(!params.hasOwnProperty('pid')) {
	            return params;
	        }
	        params.pid = parseInt(params.pid, 10);
	        return params;
	    };
	
	    var openPhotoSwipe = function(index, galleryElement, disableAnimation) {
	        var pswpElement = document.querySelectorAll('.pswp')[0],
	            gallery,
	            options,
	            items;
	
	        items = parseThumbnailElements(galleryElement);
	
	        // define options (if needed)
	        options = {
	            index: index,
	
	            // define gallery index (for URL)
	            galleryUID: galleryElement.getAttribute('data-pswp-uid'),
	
	            getThumbBoundsFn: function(index) {
	                // See Options -> getThumbBoundsFn section of documentation for more info
	                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
	                    pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
	                    rect = thumbnail.getBoundingClientRect(); 
	
	                return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
	            }
	
	        };
	
	        if(disableAnimation) {
	            options.showAnimationDuration = 0;
	        }
	
	        // Pass data to PhotoSwipe and initialize it
	        gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
	        gallery.init();
	    };
	
	    // loop through all gallery elements and bind events
	    var galleryElements = document.querySelectorAll( gallerySelector );
	
	    for(var i = 0, l = galleryElements.length; i < l; i++) {
	        galleryElements[i].setAttribute('data-pswp-uid', i+1);
	        galleryElements[i].onclick = onThumbnailsClick;
	    }
	
	    // Parse URL and open gallery if it contains #&pid=3&gid=1
	    var hashData = photoswipeParseHash();
	    if(hashData.pid > 0 && hashData.gid > 0) {
	        openPhotoSwipe( hashData.pid - 1 ,  galleryElements[ hashData.gid - 1 ], true );
	    }
	};
	
	// execute above function
	initPhotoSwipeFromDOM('.my-gallery');
	
	//------------------------------------------------------------------------------------------------	
	
	/* var pswpElement = document.querySelectorAll('.pswp')[0]; */
	// build items array
	<%-- var items = [
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/c.jpg',
	        w: 640,
	        h: 480
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/b.jpg',
	        w: 640,
	        h: 480
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/d.jpg',
	        w: 670,
	        h: 502
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/e.jpg',
	        w: 900,
	        h: 904
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/f.jpg',
	        w: 2048,
	        h: 1536
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/a.jpg',
	        w: 480,
	        h: 640
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/g.jpg',
	        w: 480,
	        h: 640
	    },
	    {
	        src: '<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/h.jpg',
	        w: 3120,
	        h: 4208
	    }
	]; --%>
	
	// define options (if needed)
/* 	var options = {
	    // optionName: 'option value'
	    // for example:
	    index: 0 // start at first slide
	}; */
	
	// Initializes and opens PhotoSwipe
	/* var gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
	gallery.init(); */
	
	</script>
	
  </body>
</html>
