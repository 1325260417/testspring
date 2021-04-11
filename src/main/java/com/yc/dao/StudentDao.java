package com.yc.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {

    public int add(String name);

    public void update(String name);
}
