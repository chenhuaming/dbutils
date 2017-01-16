package com.github.chenhuaming.dbutils.sql;

import com.github.chenhuaming.dbutils.reflection.ReflectionUtils;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenhuaming on 2017/1/17.
 */
public class SqlHelper {
    public static PreparedStatement generateInsertPrepareStatement(Connection conn, String tableName, Object bean) throws SQLException {
        List<ReflectionUtils.Column> columns = ReflectionUtils.reflectJavaBeanToColumns(bean);
        String insertTemplate = "insert into %s ( %s ) values ( %s )";
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();
        for (int i=0; i<columns.size();i++ ) {
            ReflectionUtils.Column column = columns.get(i);
            if (i == 0) {
                s2.append(column.getName());
                s3.append("?");
            } else {
                s2.append(", " + column.getName());
                s3.append(", ?");
            }
        }
        String sql = String.format(insertTemplate, tableName, s2, s3);
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0; i<columns.size();i++ ) {
            ReflectionUtils.Column column = columns.get(i);
            Type type = column.getType();
            if(type == String.class){

            }else if(type == String.class){

            }else if(type == Integer.class){

            }else if(type == Double.class){

            }else if(type == Float.class){

            }
        }
        return null;
    }
}
