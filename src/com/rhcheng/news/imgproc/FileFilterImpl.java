package com.rhcheng.news.imgproc;

import java.io.File;
import java.io.FileFilter;


public class FileFilterImpl implements FileFilter{
	private String imguuid;
	public FileFilterImpl(String iuuid){
		this.imguuid = iuuid;
	}
	public boolean accept(File pathname) {
		String name = pathname.getName();
        return (name.indexOf(this.imguuid) != -1) ;
	}
	
}

