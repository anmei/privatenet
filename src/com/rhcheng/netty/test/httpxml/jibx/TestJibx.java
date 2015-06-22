package com.rhcheng.netty.test.httpxml.jibx;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rhcheng.netty.test.httpxml.entity.Customer;

/**
 * 比较重要的是要先生成绑定关系
 * 编译：javac -Djava.ext.dirs=./lib Test.java  
 * java -Djava.ext.dirs=./lib Test  
 * 
 * -Djava.ext.dirs指定需要用到的相关jar包路径
 * 
 * @author RhCheng
 * 2015年6月21日
 */
public class TestJibx {
	private IBindingFactory factory = null;
    private StringWriter writer = null;
    private StringReader reader = null;
    private Customer customer;
    
	@Before
	public void setup() throws JiBXException{
		customer = new Customer();
		customer.setCid(1);
		customer.setFirstName("haha");
		customer.setLastName("nihao");
		
		factory = BindingDirectory.getFactory(Customer.class);
		
	}
	
    @After
    public void destory() {
        customer = null;
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();
    }
	
   
    @Test
    public void bean2XML() {
        try {
            writer = new StringWriter();
            // marshal pojo——>xml
            IMarshallingContext mctx = factory.createMarshallingContext();
            mctx.setIndent(2);
            mctx.marshalDocument(customer, "UTF-8", null, writer);
            System.out.println(writer);

            reader = new StringReader(writer.toString());
            //unmarshal xml——>pojo
            IUnmarshallingContext uctx = factory.createUnmarshallingContext();
            Customer acc = (Customer) uctx.unmarshalDocument(reader, null);
            System.out.println(acc);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
	
	
}
