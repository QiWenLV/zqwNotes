package com.zqw.test.list2tree.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname List2TreeUtils
 * @Description TODO
 * @Date 2019/7/15 19:49
 * @Created by zqw
 * @Version 1.0
 */
public class List2TreeInterfaceUtils<T extends NodeInterface> {

    public Node<T> findChild(final T current, List<T> nodeList) throws Exception {

        Node<T> node = new Node<>();
        node.setT(current);
        node.setChilds(new ArrayList<>());

        ArrayList<T> collect = new ArrayList<T>();
        for (T t : nodeList) {
            if(t.getId().equals(t.getParentId())){
                collect.add(t);
            }
        }

        boolean flag = collect.size() > 0;
        node.setCount(collect.size());

        if(flag){
            node.setIsLeaf(false);
            for (T group : collect) {
                Node<T> child = findChild(group, nodeList);
                node.getChilds().add(child);
            }
        } else {
            node.setIsLeaf(false);
            return node;
        }
        return node;
    }

    public String upperCase(String str) {
        if ((str == null) || (str.length() == 0)) return str;
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

}
