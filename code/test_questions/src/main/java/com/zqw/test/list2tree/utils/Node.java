package com.zqw.test.list2tree.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname NodeTree
 * @Description TODO
 * @Date 2019/7/15 19:52
 * @Created by zqw
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node<T> {
    private T t;
    private Integer count;
    private Boolean isLeaf;
    private List<Node<T>> childs;
}



