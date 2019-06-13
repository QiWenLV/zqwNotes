package com.zqw.logic;

import com.zqw.utils.RunTimeTools;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 对几种List集合去重方法的效率对比
 */
public class DistinctContrast {

    public static void main(String[] args) {

        DistinctContrast dc = new DistinctContrast();
        //获取随机的集合
        List<String> list = dc.createList();

        RunTimeTools<List> instance = new RunTimeTools<>();

        List list2 = instance.time(() -> dc.setFunction(list), "hashSet去重");
        List list3 = instance.time(() -> dc.streamFunction(list), "stream去重");
        List list4 = instance.time(() -> dc.setFunction2(list), "hashSet去重，保证元素顺序不变");
        List list1 = instance.time(() -> dc.foreachFunction(list), "手动循环去重");

        System.out.println(list1.size() + "  " + list2.size() + "  " + list3.size() + "  " + list4.size());
        System.out.println(list.size());
    }

    /**
     * 手动循环去重
     * @param list
     */
    public List<String> foreachFunction(List<String> list){
        List<String> newList = new ArrayList<>();

        for (String s : list) {
            if(!newList.contains(s)){
                newList.add(s);
            }
        }
        return newList;
    }

    /**
     * hashSet去重
     * @param list
     * @return
     */
    public List<String> setFunction(List<String> list){
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * hashSet去重，保证元素顺序不变
     * @param list
     * @return
     */
    public List<String> setFunction2(List<String> list){
        List<String> newList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String s : list) {
            if(set.add(s)){
                newList.add(s);
            }
        }
        return newList;
    }

    /**
     * stream去重
     * @param list
     * @return
     */
    public List<String> streamFunction(List<String> list){
        return list.stream().distinct().collect(Collectors.toList());
    }


    public List<String> createList(){
        return new Random().ints(1, 5000)
                .limit(10000)
                .boxed()
                .map(x -> x + "")
                .collect(Collectors.toList());
    }
}
