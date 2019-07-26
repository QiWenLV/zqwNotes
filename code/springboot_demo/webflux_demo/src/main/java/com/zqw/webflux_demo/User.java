package com.zqw.webflux_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/7/26 14:12
 * @Created by zqw
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
}
