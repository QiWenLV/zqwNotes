package com.zqw.graphql_demo.demo;

import graphql.schema.DataFetchingEnvironment;

/**
 * @Classname Datafetcher
 * @Description TODO
 * @Date 2019/7/26 11:25
 * @Created by zqw
 * @Version 1.0
 */
public interface Datafetcher<T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;

}
