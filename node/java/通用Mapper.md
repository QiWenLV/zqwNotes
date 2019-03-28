## 与Spring Boot 集成

依赖

```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
```





```xml
<!-- 
    方式一: 嵌套结果: 使用嵌套结果映射来处理重复的联合结果的子集
    SELECT * FROM class c, teacher t,student s WHERE c.teacher_id=t.t_id AND c.C_id=s.class_id AND  c.c_id=1
-->
<select id="getClass3" parameterType="int" resultMap="ClassResultMap3">
    select * from class c, teacher t,student s where c.teacher_id=t.t_id and c.C_id=s.class_id and  c.c_id=#{id}
</select>
<resultMap type="me.gacl.domain.Classes" id="ClassResultMap3">
    <id property="id" column="c_id"/>
    <result property="name" column="c_name"/>
    <association property="teacher" column="teacher_id" javaType="me.gacl.domain.Teacher">
        <id property="id" column="t_id"/>
        <result property="name" column="t_name"/>
    </association>
    <!-- ofType指定students集合中的对象类型 -->
    <collection property="students" ofType="me.gacl.domain.Student">
        <id property="id" column="s_id"/>
        <result property="name" column="s_name"/>
    </collection>
</resultMap>
     
<!-- 
    方式二：嵌套查询：通过执行另外一个SQL映射语句来返回预期的复杂类型
        SELECT * FROM class WHERE c_id=1;
        SELECT * FROM teacher WHERE t_id=1   //1 是上一个查询得到的teacher_id的值
        SELECT * FROM student WHERE class_id=1  //1是第一个查询得到的c_id字段的值
-->
<select id="getClass4" parameterType="int" resultMap="ClassResultMap4">
    select * from class where c_id=#{id}
</select>
<resultMap type="me.gacl.domain.Classes" id="ClassResultMap4">
    <id property="id" column="c_id"/>
    <result property="name" column="c_name"/>
    <association property="teacher" column="teacher_id" javaType="me.gacl.domain.Teacher" select="getTeacher2"></association>
    <collection property="students" ofType="me.gacl.domain.Student" column="c_id" select="getStudent"></collection>
</resultMap>

<select id="getTeacher2" parameterType="int" resultType="me.gacl.domain.Teacher">
    SELECT t_id id, t_name name FROM teacher WHERE t_id=#{id}
</select>

<select id="getStudent" parameterType="int" resultType="me.gacl.domain.Student">
    SELECT s_id id, s_name name FROM student WHERE class_id=#{id}
</select>
```

