package com.rhcheng.news.webmagic;

import java.util.Map;

import com.rhcheng.news.webmagic.BaseSpider;

/**
 * 抓取网络信息时需要的基本信息
 * @author mei
 *
 */
public class CrawlModel {
    private String url;
    private String charset;
    private String method; // GET POST PUT DELETE ……
    private Class<? extends BaseSpider> pageProcessor;
    private Map<String,String> headers; // 请求头信息
    private Map<String,String> parameters; // POST请求参数
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Class<? extends BaseSpider> getPageProcessor() {
        return pageProcessor;
    }
    public void setPageProcessor(Class<? extends BaseSpider> pageProcessor) {
        this.pageProcessor = pageProcessor;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    
    
}
