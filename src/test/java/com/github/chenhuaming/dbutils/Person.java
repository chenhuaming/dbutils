package com.github.chenhuaming.dbutils;

/**
 * Created by chenhuaming on 2017/1/16.
 */
public class Person {
    private String name;
    private Integer age;
    private Object catName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getCatName() {
        return catName;
    }

    public void setCatName(Object catName) {
        this.catName = catName;
    }
}
