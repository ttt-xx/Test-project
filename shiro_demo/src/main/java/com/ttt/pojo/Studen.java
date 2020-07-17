package com.ttt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Studen)实体类
 *
 * @author ttt
 * @since 2020-07-17 15:55:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studen implements Serializable {
    /**
    * 学号
    */
    private Integer sid;
    /**
    * 学员姓名
    */
    private String sname;
    /**
    * 密码
    */
    private String spas;
    /**
    * 学员性别
    */
    private String sgender;
    /**
    * 学员年龄
    */
    private Integer sage;


}