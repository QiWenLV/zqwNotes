package com.zqw.springdata_demo.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname Article
 * @Description TODO
 * @Date 2019/8/5 19:54
 * @Created by zqw
 * @Version 1.0
 */
@Data
@Document(collection = "article_info")
public class Article {

    @Id
    private String id;
    @Field("title")
    private String title;
    @Field("url")
    private String url;
    @Field("author")
    private String author;
    @Field("tags")
    private List<String> tags;
    @Field("visit_count")
    private Long visitCount;
    @Field("add_time")
    private LocalDateTime addTime;

}
