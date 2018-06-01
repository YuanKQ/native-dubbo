package com.pku.netlab.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 322530832662222535L;  // IDEA可自动生成
    private String name;
    private int age;
    private long id;
    private List<String> infos = new ArrayList<>();

    public List<String> getInfos() {
        return infos;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
