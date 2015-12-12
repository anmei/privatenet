package com.rhcheng.util.commonlogging;

import org.apache.commons.logging.impl.LogFactoryImpl;

import com.rhcheng.util.UtilClient;

/**
 * 编译——加载——连接（准备-验证-解析）——初始化——使用——销毁
 * 
 * LogFactory加载过程：寻找LogFactory实现类(系统变量、META-INF、配置文件)——ClassLoader.load（）加载相应的LogFactory实现类——Class.getInstance()
 * 获取具体的Logger系统
 * FileName——ClassLoader——URL——Properties
 * 加载资源：资源名——InputStream——Properties——Map
 * 
 * @author mei
 *
 */
public class LogFacoryd extends LogFactoryImpl{
    
    
    
    public static void main(String[] args) {
        LogFacoryd d = new LogFacoryd();
        System.out.println(d.getClassLoader(LogFacoryd.class));
//        
        
    }
    
}
