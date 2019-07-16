package com.zqw.test.list2tree;

import com.alibaba.fastjson.JSON;
import com.zqw.test.list2tree.utils.List2TreeInterfaceUtils;
import com.zqw.test.list2tree.utils.List2TreeUtils;
import com.zqw.test.list2tree.utils.Node;
import com.zqw.test.list2tree.utils.NodeInterface;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname TestList2Tree
 * @Description TODO
 * @Date 2019/7/15 16:57
 * @Created by zqw
 * @Version 1.0
 */
public class TestList2Tree {

    public static void main(String[] args) throws Exception {
        //初始化
        List<NodeClass> init = init();
        NodeClass nodeClass = NodeClass.builder().id("1").name("节点a").parentId("0").build();

        //普通版本
        NodeTree tree = findChild(NodeClass.builder().id("1").name("节点a").parentId("0").build(), init);
        System.out.println(tree);
        System.out.println("-------------------");

        //工具类版本
        List2TreeUtils<NodeClass> list2TreeUtils = new List2TreeUtils<NodeClass>("id", "parentId", NodeClass.class);
        Node node = list2TreeUtils.findChild(nodeClass, init);
        System.out.println(JSON.toJSON(node));
        System.out.println("-------------------");

        //接口工具类版
        List2TreeInterfaceUtils<NodeClass> list2TreeInterfaceUtils = new List2TreeInterfaceUtils<>();
        Node<NodeClass> child = list2TreeInterfaceUtils.findChild(nodeClass, init);
        System.out.println(JSON.toJSON(child));
        System.out.println("-------------------");
    }


    public static NodeTree findChild(final NodeClass node, List<NodeClass> nodeList){

        NodeTree nodeTree = NodeTree.builder()
                .id(node.getId())
                .parentId(node.getParentId())
                .name(node.getName())
                .childs(new ArrayList<>())
                .build();

        List<NodeClass> collect = nodeList
                .stream()
                .filter(x -> x.getParentId().equals(node.getId()))
                .sorted(Comparator.comparing(NodeClass::getId))
                .collect(Collectors.toList());

        boolean flag = collect.size() > 0;
        nodeTree.setCount(collect.size());
        if(flag){
            nodeTree.setIsAble(true);
            for (NodeClass group : collect) {
                NodeTree child = findChild(group, nodeList);
                nodeTree.getChilds().add(child);
            }
        } else {
            nodeTree.setIsAble(false);
            return nodeTree;
        }
        return nodeTree;
    }

    public static List<NodeClass> init(){
        List<NodeClass> list = new ArrayList<NodeClass>();
        list.add(NodeClass.builder().id("1").name("节点a").parentId("0").build());
        list.add(NodeClass.builder().id("2").name("节点a1").parentId("1").build());
        list.add(NodeClass.builder().id("3").name("节点a2").parentId("1").build());
        list.add(NodeClass.builder().id("4").name("节点a11").parentId("2").build());
        list.add(NodeClass.builder().id("5").name("节点b").parentId("0").build());
        return list;
    }
}
