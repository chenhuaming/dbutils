package com.github.chenhuaming.dbutils.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by chenhuaming on 2017/1/16.
 */
public class ReflectionUtils {

    public static List<Column> reflectJavaBeanToColumns(Object bean) {
        Objects.requireNonNull(bean);
        Class<?> clz = bean.getClass();
        Method[] methods = clz.getDeclaredMethods();
        List<Column> list = new ArrayList<>();
        Map<String, Method> getterMap = new HashMap<>();
        Map<String, Method> setterMap = new HashMap<>();
        Map<String, Object> nameValueMap = new HashMap<>();
        Map<String, Type> nameTypeMap = new HashMap<>();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && methodName.length() > 3) {
                String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                Type returnType = method.getReturnType();
                if (method.getParameterCount() == 0) {
                    try {
                        Object value = method.invoke(bean);
                        nameValueMap.put(fieldName,value);
                        nameTypeMap.put(fieldName,returnType);
                        getterMap.put(fieldName,method);
                        continue;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(methodName.startsWith("set") && methodName.length() > 3){
                String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                if(method.getParameterCount() == 1){
                    setterMap.put(fieldName,method);
                }
            }
        }
        for(Map.Entry<String,Object> kv : nameValueMap.entrySet()){
            String key = kv.getKey();
            Object value = kv.getValue();
            if(setterMap.containsKey(key) && getterMap.containsKey(key)){
                Column column = new Column();
                column.setName(key);
                column.setValue(value);
                column.setType(nameTypeMap.get(key));
                column.setGetter(getterMap.get(key));
                column.setSetter(setterMap.get(key));
                list.add(column);
            }
        }
        return list;
    }

    public static class Column {
        private String name;
        private Object value;
        private Type type;
        private Method getter;
        private Method setter;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Method getGetter() {
            return getter;
        }

        public void setGetter(Method getter) {
            this.getter = getter;
        }

        public Method getSetter() {
            return setter;
        }

        public void setSetter(Method setter) {
            this.setter = setter;
        }
    }
}
