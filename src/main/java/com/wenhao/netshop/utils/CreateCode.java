package com.wenhao.netshop.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by lw on 2016/11/14.
 */
public class CreateCode {
    //1.那些domain需要生成代码
    private static String[] domains = {"Test"};
    private static String[] domainName = {"测试"};
    //2.定义固定的目录路径:都是使用相对路径,规范：路径前面都不加/,路径的后面都加/
    private static final String SRC = "src/main/java/";
    private static final String PACKAGE = "com/wenhao/netshop/";
    private static final String RESOURCES = "src/main/resources/";
    private static final String WEBAPP = "src/main/webapp/";

    //3.有那些模板需要生成
    private static String[] templates = {"Domain.java"};
    //4.模板文件对应的生成文件路径
    private static String[] filePath = {SRC + PACKAGE + "domain/"};

    public static void main(String[] args) throws Exception {
        VelocityContext context = new VelocityContext();
        String packageName = "com.wenhao.netshop.domain";
        Map map = DataBaseUtil.getTab(StringUtils.uncapitalize(domains[0]));
        List<String> list = (List<String>) map.get("columnNameList");
        List<String> list0 = (List<String>) map.get("columnTypeNameList");
        List<String> list1 = (List<String>) map.get("firstUpperCaseColumnNameList");
        List<String> list2 = (List<String>) map.get("typeAndName");
        for (int i = 0; i < domains.length; i++) {
            context.put("list", list);
            context.put("list0", list0);
            context.put("list1", list1);
            context.put("list2", list2);
            context.put("packageName", packageName);
            context.put("domain", domains[i]);
            context.put("domainName", domainName[i]);
            //6.处理domain首字母小写
            String lowerDomain = StringUtils.uncapitalize(domains[i]);
            context.put("lowerDomain", lowerDomain);
            //7.内循环：templates和files
            for (int j = 0; j < templates.length; j++) {
                //8.实例化文件存放的路径
                File file = new File(filePath[j] + domains[i] + templates[j]);
                //9.处理特殊的文件名称
                if ("Domain.java".equals(templates[j])) {
                    file = new File(filePath[j] + domains[i] + ".java");
                }
                System.out.println(file.getAbsolutePath());
                Template template = Velocity.getTemplate("temp/" + templates[j], "UTF-8");
                //10.判断父目录是否存在
                File paretnFile = file.getParentFile();
                if (!paretnFile.exists()) {
                    paretnFile.mkdirs();
                }
                //11.必须关闭流，写入内容
                FileWriter fileWriter = new FileWriter(file);
                template.merge(context, fileWriter);
                fileWriter.close();
            }
        }
    }
}
