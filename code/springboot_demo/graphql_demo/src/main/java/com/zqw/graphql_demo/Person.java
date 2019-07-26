package com.zqw.graphql_demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2019/7/26 10:11
 * @Created by zqw
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String name;
    private Integer age;
    private String time;
}
