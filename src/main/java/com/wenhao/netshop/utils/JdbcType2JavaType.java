package com.wenhao.netshop.utils;

/**
 * Created by lw on 2016/11/14.
 */
public class JdbcType2JavaType {
    public static String convertType(String type) {
        if ("VARCHAR".equals(type)){
            return "String";
        }else if ("CHAR".equals(type)){
            return "String";
        }else if ("BLOB".equals(type)){
            return "Byte[]";
        }else if ("TEXT".equals(type)){
            return "String";
        }else if ("INTEGER".equals(type)){
            return "Long";
        }else if ("TINYINT".equals(type)){
            return "Integer";
        }else if ("SMALLINT".equals(type)){
            return "Integer";
        }else if ("MEDIUMINT".equals(type)){
            return "Integer";
        }else if ("BIT".equals(type)){
            return "Boolean";
        }else if ("BIGINT".equals(type)){
            return "Long";
        }else if ("FLOAT".equals(type)){
            return "Float";
        }else if ("DOUBLE".equals(type)){
            return "Double";
        }else if ("DECIMAL".equals(type)){
            return "BigDecimal";
        }else if ("BOOLEAN".equals(type)){
            return "Integer";
        }else if ("DATE".equals(type)){
            return "Date";
        }else if ("TIME".equals(type)){
            return "Time";
        }else if ("DATETIME".equals(type)){
            return "Timestamp";
        }else if ("TIMESTAMP".equals(type)){
            return "Timestamp";
        }else if ("YEAR".equals(type)){
            return "Date";
        }
        return "";
    }
}
