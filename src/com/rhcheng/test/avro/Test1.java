package com.rhcheng.test.avro;

import org.apache.avro.Schema;
import org.codehaus.jackson.node.TextNode;

public class Test1 {
	public static void main(String[] args) {
		System.out.println(Schema.parseJson("{\"ffs\":\"1\",\"fsad\":\"2\"}").isInt());
		System.out.println(TextNode.valueOf("fdsa"));
	}
}
