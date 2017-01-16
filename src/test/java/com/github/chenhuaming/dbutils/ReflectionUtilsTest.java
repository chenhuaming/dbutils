package com.github.chenhuaming.dbutils;

import com.github.chenhuaming.dbutils.reflection.ReflectionUtils;

import java.util.List;

/**
 * Created by chenhuaming on 2017/1/16.
 */
public class ReflectionUtilsTest {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("chenhuaming");
        p.setAge(1000);
        p.setCatName("acat");
        List<ReflectionUtils.Column> columns = ReflectionUtils.reflectJavaBeanToColumns(p);
        System.out.println(columns.size());
        for(ReflectionUtils.Column cloumn : columns){
            System.out.println(cloumn.getType() == String.class);
        }
    }
}
