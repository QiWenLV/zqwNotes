package com.zqw.springdata_demo.mongodb;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname MongoUitls
 * @Description TODO
 * @Date 2019/8/5 19:48
 * @Created by zqw
 * @Version 1.0
 */
public class MongoDemo {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void bacthInsert(){
        //批量添加
        List<Article> articles = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("MongoTemplate的基本使用");
            article.setAuthor("zqw");
            article.setUrl("http://csdn.com/blog/detail/" + i);
            article.setTags(Arrays.asList("java", "mongodb", "spring"));
            article.setVisitCount(0L);
            article.setAddTime(LocalDateTime.now());
            articles.add(article);
        }
        mongoTemplate.insert(articles, Article.class);
    }


    public void delete(){
        //删除author为yinjihuan的数据
        Query query = Query.query(Criteria.where("author").is("yinjihuan"));
        mongoTemplate.remove(query, Article.class);

        //如果实体类中没配集合名词，可在删除的时候单独指定article_info
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        mongoTemplate.remove(query, "article_info");

        //删除集合，可传实体类，也可以传名称
        mongoTemplate.dropCollection(Article.class);
        mongoTemplate.dropCollection("article_info");

        //删除数据库
        mongoTemplate.getDb().drop();

        //查询出符合条件的第一个结果，并将符合条件的数据删除,只会删除第一条
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        Article article = mongoTemplate.findAndRemove(query, Article.class);

        //查询出符合条件的所有结果，并将符合条件的所有数据删除
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        List<Article> articles = mongoTemplate.findAllAndRemove(query, Article.class);

    }

    public void update(){
        //修改第一条author为yinjihuan的数据中的title和visitCount
        Query query = Query.query(Criteria.where("author").is("yinjihuan"));
        Update update = Update.update("title", "MongoTemplate").set("visitCount", 10);
        mongoTemplate.updateFirst(query, update, Article.class);

        //修改全部符合条件的
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        update = Update.update("title", "MongoTemplate").set("visitCount", 10);
        mongoTemplate.updateMulti(query, update, Article.class);

        //特殊更新，更新author为jason的数据，如果没有author为jason的数据则以此条件创建一条新的数据
        //当没有符合条件的文档，就以这个条件和更新文档为基础创建一个新的文档，如果找到匹配的文档就正常的更新。
        query = Query.query(Criteria.where("author").is("jason"));
        update = Update.update("title", "MongoTemplate").set("visitCount", 10);
        mongoTemplate.upsert(query, update, Article.class);

        //更新条件不变，更新字段改成了一个我们集合中不存在的，用set方法如果更新的key不存在则创建一个新的key
        query = Query.query(Criteria.where("author").is("jason"));
        update = Update.update("title", "MongoTemplate").set("money", 100);
        mongoTemplate.updateMulti(query, update, Article.class);

        //update的inc方法用于做累加操作，将money在之前的基础上加上100
        query = Query.query(Criteria.where("author").is("jason"));
        update = Update.update("title", "MongoTemplate").inc("money", 100);
        mongoTemplate.updateMulti(query, update, Article.class);

        //update的rename方法用于修改key的名称
        query = Query.query(Criteria.where("author").is("jason"));
        update = Update.update("title", "MongoTemplate").rename("visitCount", "vc");
        mongoTemplate.updateMulti(query, update, Article.class);

        //update的unset方法用于删除key
        query = Query.query(Criteria.where("author").is("jason"));
        update = Update.update("title", "MongoTemplate").unset("vc");
        mongoTemplate.updateMulti(query, update, Article.class);

        //update的pull方法用于删除tags数组中的java
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        update = Update.update("title", "MongoTemplate").pull("tags", "java");
        mongoTemplate.updateMulti(query, update, Article.class);

    }

    public void find(){
        //根据作者查询所有符合条件的数据，返回List
        Query query = Query.query(Criteria.where("author").is("yinjihuan"));
        List<Article> articles = mongoTemplate.find(query, Article.class);

        //只查询符合条件的第一条数据，返回Article对象
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        Article article = mongoTemplate.findOne(query, Article.class);

        //查询集合中所有数据，不加条件
        articles = mongoTemplate.findAll(Article.class);

        //查询符合条件的数量
        query = Query.query(Criteria.where("author").is("yinjihuan"));
        long count = mongoTemplate.count(query, Article.class);

        //根据主键ID查询
        article = mongoTemplate.findById(new ObjectId("57c6e1601e4735b2c306cdb7"), Article.class);


        //in查询
        List<String> authors = Arrays.asList("yinjihuan", "jason");
        query = Query.query(Criteria.where("author").in(authors));
        articles = mongoTemplate.find(query, Article.class);

        //ne（!=）查询
        query = Query.query(Criteria.where("author").ne("yinjihuan"));
        articles = mongoTemplate.find(query, Article.class);

        //It(<)查询访问量小于10的文章
        query = Query.query(Criteria.where("visitCount").lt(10));
        articles = mongoTemplate.find(query, Article.class);


        //范围查询，大于5小于10
        query = Query.query(Criteria.where("visitCount").gt(5).lt(10));
        articles = mongoTemplate.find(query, Article.class);

        //模糊查询，author中包含a的数据
        query = Query.query(Criteria.where("author").regex("a"));
        articles = mongoTemplate.find(query, Article.class);

        //数组查询，查询tags里数量为3的数据
        query = Query.query(Criteria.where("tags").size(3));
        articles = mongoTemplate.find(query, Article.class);

        //or查询，查询author=jason的或者visitCount=0的数据
        query = Query.query(Criteria.where("").orOperator(
                Criteria.where("author").is("jason"),
                Criteria.where("visitCount").is(0)));
        articles = mongoTemplate.find(query, Article.class);
    }



}
