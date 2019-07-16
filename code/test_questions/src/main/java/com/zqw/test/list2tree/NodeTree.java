package com.zqw.test.list2tree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Classname NodeTree
 * @Description TODO
 * @Date 2019/7/15 17:14
 * @Created by zqw
 * @Version 1.0
 */
@Data
@Builder
public class NodeTree {

    private String id;
    private String name;
    private Integer count;
    private Boolean isAble;
    private String parentId;
    private List<NodeTree> childs;
}
