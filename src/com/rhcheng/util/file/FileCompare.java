package com.rhcheng.util.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.osgi.framework.adaptor.FilePath;

public class FileCompare {
    static String filePath1 = "D:\\project\\husor\\beibei-i\\application\\libraries\\service\\MemberService.php";
    static String filePath2 = "D:\\project\\husor\\beibei-trunk\\application\\libraries\\service\\MemberService.php";
   
    
    public static void findDifferentMethod(String filePath1, String filePath2) throws IOException{
        List<String> methods1 = findAllMethods(filePath1);
        List<String> methods2 = findAllMethods(filePath2);
        if((null == methods1 || methods1.size()<1) && (null == methods2 || methods2.size()<1)){
            System.out.println("no methods.");
        }else if(null == methods1 || methods1.size()<1){
            System.out.println("no methods.("+filePath1+")");
            System.out.println("methods list: ("+filePath2+")");
            listout(methods2);
        }else if(null == methods2 || methods2.size()<1){
            System.out.println("methods list: ("+filePath1+")");
            listout(methods1);
            System.out.println("no methods.("+filePath2+")");
        }else{
            for(int i=0;i<methods1.size();i++){
                for(int j=0;j<methods2.size();j++){
                    if(methods1.get(i).equalsIgnoreCase(methods2.get(j))){
                        methods1.remove(i);
                        methods2.remove(j);
                        i--;j--;break;
                    }
                }
            }
            System.out.println(filePath1);
            listout(methods1);
            System.out.println(filePath2);
            listout(methods2);
        }
        
    }
    
    
    public static void main(String[] args) throws IOException {
        // string->pattern->matcher
//        Matcher mac = phpmethod.matcher("  public function get_by_email ($email, $withprofile = FALSE) {  ");
//        while(mac.find()){
//            System.out.println(mac.group());
//        }
//        
//        listout(findAllMethods(filePath2));
        findDifferentMethod(filePath1, filePath2);
        
    }
    
    
    //-------------------------------------------------------commons
    public static List<String> findAllMethods(String filePath1) throws IOException{
        BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
        List<String> methods1 = new ArrayList<String>();
        String line = "";
        Matcher matcher;
        Matcher matcher1;
        Matcher matcher2;
        
        while((line = br1.readLine()) != null){
            matcher = phpmethod.matcher(line);
            matcher1 = phpRemoteMethod.matcher(line);
            matcher2 = phpInlineMethod.matcher(line);
            while(matcher.find() && !matcher1.find() && !matcher2.find()){
                methods1.add(matcher.group().trim());
            }
        }
        if(null != br1){
            br1.close();
        }
        return methods1;
    }
    
    public static void listout(List<String> args){
        if(args == null || args.size()<1){
            System.out.println("no elements.");
        }else{
            for(int i = 0;i<args.size();i++){
                System.out.println(i+"、"+args.get(i));
            }
        }
    }
    
    public static final Pattern phpmethod = Pattern.compile("(?<=function).*\\(.*\\)");
    public static final Pattern phpRemoteMethod = Pattern.compile("(?<=function)\\s*\\(\\)\\s*use\\s*\\(.*\\)");
    public static final Pattern phpInlineMethod = Pattern.compile("(?<=function)\\s*\\(.*\\)");
    
}
