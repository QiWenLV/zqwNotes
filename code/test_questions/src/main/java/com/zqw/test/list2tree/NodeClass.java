package com.zqw.test.list2tree;

import com.zqw.test.list2tree.utils.NodeInterface;
import lombok.Builder;
import lombok.Data;

/**
 * @Classname NodeClass
 * @Description TODO
 * @Date 2019/7/15 16:54
 * @Created by zqw
 * @Version 1.0
 */
@Data
@Builder
public class NodeClass implements NodeInterface {
    private String id;
    private String name;
    private String parentId;


}
