package com.rhcheng.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class FileDocument {
	
	/**
	 * 将文件转换为一个document对象
	 * @param file 文件
	 * @return
	 */
	public Document fileToDocument(File file){
		Document document=new Document();
		document.add(new Field("name", file.getName(), Store.YES, Index.ANALYZED));
		document.add(new Field("content", this.readFileRetStr(file), Store.YES, Index.ANALYZED));
		return document;
	}
    
	/**
	 * 将名字、内容字段转为document
	 * @param content  内容
	 * @param name 文件名字
	 * @return
	 */
	public Document stringToDocumet(String name,String content){
		Document document=new Document();
		document.add(new Field("name",name, Store.YES, Index.ANALYZED));
		document.add(new Field("content", content, Store.YES, Index.ANALYZED));
		return document;
	}
	
	/**
	 * 将文件内容转为string类型
	 * @param file 文件
	 * @return
	 */
	public String readFileRetStr(File file){
		FileInputStream fStream = null;
		String tempStr = "";
		StringBuffer sBuffer = new StringBuffer();
		try {
			fStream = new FileInputStream(file);
			BufferedReader bReader=new BufferedReader(new InputStreamReader(fStream,"UTF-8"));
			while((tempStr=bReader.readLine())!=null){
				sBuffer.append(tempStr);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sBuffer.toString();
	}
}
