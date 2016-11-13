package com.wenhao.netshop.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.*;
import java.util.*;

/**
 * Created by lenovo on 2016/11/13.
 */
public class DataBaseUtil {

    private static Properties ps = new Properties();
    private static Connection connection;

    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        try {
            ps.load(is);
            Class.forName(ps.getProperty("jdbcDriver"));
            connection = DriverManager.getConnection(ps.getProperty("jdbcUrl"), ps.getProperty("jdbcuser"), ps.getProperty("jdbcpassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map getTab(String tableName) throws Exception {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rest = metaData.getTables(null, null, null, new String[]{"TABLE"});
        List<String> columnNameList = new ArrayList();
        List<String> columnTypeNameList = new ArrayList();
        String tableName1 = null;
        while (rest.next()) {
            String table = rest.getString(3);
            if (tableName.equals(table)) {
                String sql = "select * from " + table;
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(sql);
                ResultSetMetaData data = resultSet.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    columnNameList.add(columnName);
                    //System.out.println(columnName);
                    String columnTypeName = data.getColumnTypeName(i);
                    columnTypeNameList.add(columnTypeName);
                    //System.out.println(columnTypeName);
                    // 获得所有列的数目及实际列数
                    //int columnCount = data.getColumnCount();
                    //System.out.println(columnCount);
                    // 获得指定列的列名
                    // 获得指定列的列值
                    //int columnType = data.getColumnType(i);
                    //System.out.println(columnType);
                    // 获得指定列的数据类型名
                    // 所在的Catalog名字
                    //                    String catalogName = data.getCatalogName(i);
                    // 对应数据类型的类
                    //                    String columnClassName = data.getColumnClassName(i);
                    // 在数据库中类型的最大字符个数
                    //                    int columnDisplaySize = data.getColumnDisplaySize(i);
                    // 默认的列的标题
                    //                    String columnLabel = data.getColumnLabel(i);
                    // 获得列的模式
                    //                    String schemaName = data.getSchemaName(i);
                    // 某列类型的精确度(类型的长度)
                    //                    int precision = data.getPrecision(i);
                    // 小数点后的位数
                    //                    int scale = data.getScale(i);
                    // 获取某列对应的表名
                    // 是否自动递增
                    //                    boolean isAutoInctement = data.isAutoIncrement(i);
                    // 在数据库中是否为货币型
                    //                    boolean isCurrency = data.isCurrency(i);
                    // 是否为空
                    //                    int isNullable = data.isNullable(i);
                    // 是否为只读
                    //                    boolean isReadOnly = data.isReadOnly(i);
                    // 能否出现在where中
                    //                    boolean isSearchable = data.isSearchable(i);
                    //                    System.out.println("获得列" + i + "的字段名称:" + columnName);
                    //                    System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
                    //                    System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
                    //                    System.out.println("获得列" + i + "对应数据类型的类:" + columnClassName);
                    //                    System.out.println("获得列" + i + "在数据库中类型的最大字符个数:" + columnDisplaySize);
                    //                    System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
                    //                    System.out.println("获得列" + i + "的模式:" + schemaName);
                    //                    System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
                    //                    System.out.println("获得列" + i + "小数点后的位数:" + scale);
                    //                    System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
                    //                    System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
                    //                    System.out.println("获得列" + i + "是否为空:" + isNullable);
                    //                    System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
                    //                    System.out.println("获得列" + i + "能否出现在where中:" + isSearchable);
                }
                tableName1 = data.getTableName(1);
                //System.out.println(tableName1);
            }
        }
        Map map = new HashMap();
        map.put("columnNameList", columnNameList);
        map.put("columnTypeNameList", columnTypeNameList);
        map.put("tableName1", tableName1);
        return map;
    }

    public static void main(String[] args) throws Exception {
        VelocityContext v = new VelocityContext();
        String tableName = "user";
        Map map = DataBaseUtil.getTab(tableName);
        List<String> list = (List<String>) map.get("columnNameList");
        v.put("list", list);
        Template template = Velocity.getTemplate("velocity.jsp", "UTF-8");
        StringWriter writer = new StringWriter();
        template.merge(v, writer);
        System.out.println(writer.toString());
        for (String columnName : list) {
            //System.out.println(columnName + "====");
//            v.put("columnName", columnName);
//            Template template = Velocity.getTemplate("velocity.jsp", "UTF-8");
//            StringWriter writer = new StringWriter();
//            template.merge(v, writer);
//            System.out.println(writer.toString());
        }
        List<String> list1 = (List<String>) map.get("columnTypeNameList");
        for (String columnTypeName : list1) {
            //System.out.println(columnTypeName + "****");
        }
    }
}
