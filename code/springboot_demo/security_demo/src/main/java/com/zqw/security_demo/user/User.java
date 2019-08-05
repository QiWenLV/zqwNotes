package com.zqw.security_demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/8/5 14:20
 * @Created by zqw
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
}
