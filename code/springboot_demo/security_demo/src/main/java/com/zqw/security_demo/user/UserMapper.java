package com.zqw.security_demo.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/8/5 14:30
 * @Created by zqw
 * @Version 1.0
 */
@Component
public class UserMapper {

    private List<User> users;

    public UserMapper() {
        users = new ArrayList<>();
        users.add(User.builder().id("1").username("aa").password("aa").roles(Arrays.asList("admin", "user")).build());
        users.add(User.builder().id("2").username("bb").password("bb").roles(Arrays.asList("admin", "user")).build());
        users.add(User.builder().id("3").username("cc").password("cc").roles(Arrays.asList("user")).build());
        users.add(User.builder().id("4").username("dd").password("dd").roles(Arrays.asList("user")).build());
    }

    public User getUserByUsername(String username){
        return users.stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    }

    public List<User> getUsers(){
        return users;
    }

}
