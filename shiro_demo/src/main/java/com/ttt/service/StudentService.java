package com.ttt.service;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttt.mapper.StudentMapper;
import com.ttt.pojo.Studen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Studen> StudenList() {
        List<Studen> studens = studentMapper.selectList(null);
        System.out.println(studens);
        System.out.println("");
        System.out.println(studens.size());
        return studens;

    }

    public List<Object> listObjs() {
        List<Object> objects = studentMapper.selectObjs(null);
        return objects;
    }

    public Studen getStudentByName(String name){
        Studen studen = null;
        try {
            studen = studentMapper.selectList(new QueryWrapper<Studen>().eq("sname", name)).get(0);
        } catch (Exception e) {
        }
        return studen;
    }
}
