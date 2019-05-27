## Allatori混淆技术介绍



Allatori是一个Java 混淆器，它属于第二代混淆器，因此它能够全方位的保护你的知识产权。 Allatori具有以下几种保护方式：命名混淆，流混淆，调试信息混淆，字符串混淆，以及水印技术。对于教育和非商业项目来说这个混淆器是免费的。支持war和jar文件格式，并且允许对需要混淆代码的应用程序添加有效日期。 有项目需要对代码进行保护，比较初级的方案就是对代码进行混淆，打包之后的文件进行反编译后，就可以看到效果。此外，使用Allatori打的包大小也会小一点。

### 目录结构截图

![](https://wendy-image.oss-cn-shanghai.aliyuncs.com/19-05-20/1558314872758.png)



在根目录的lib文件夹中加入Allatori的jar包。

编辑pom.xml文件，加入Allatori的插件

```xml
<!-- Allatori plugin start -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>2.6</version>
    <executions>
        <execution>
            <id>copy-and-filter-allatori-config</id>
            <phase>package</phase>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <configuration>
                <outputDirectory>${basedir}/target</outputDirectory>
                <resources>
                    <resource>
                        <directory>${basedir}/allatori</directory>
                        <includes>
                            <include>allatori.xml</include>
                        </includes>
                        <filtering>true</filtering>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>1.2.1</version>
    <executions>
        <execution>
            <id>run-allatori</id>
            <phase>package</phase>
            <goals>
                <goal>exec</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <executable>java</executable>
        <arguments>
            <argument>-Xms128m</argument>
            <argument>-Xmx512m</argument>
            <argument>-jar</argument>
            <argument>${basedir}/lib/allatori.jar</argument>
            <argument>${basedir}/target/allatori.xml</argument>
        </arguments>
    </configuration>
</plugin>
<!-- Allatori plugin end -->
```

使用maven打包插件，Spring Boot构建的工程，Allatori的配置在上面也有说明，Allatori配置里面比较重要的是：

```xml
		<argument>${basedir}/lib/allatori.jar</argument>
		<argument>${basedir}/target/allatori.xml</argument>
```

指定Allatori的allatori.jar文件路径，如果你的工程是一个pom工程，可以在父工程中放lib目录，然后子工程只需要：

```xml
		<argument>../lib/allatori.jar</argument>
```

即可。

##### **allatori.xml这个文件也很重要，看看其中的内容：**

```xml
<config>
    <input>
        <jar in="confusion-0.0.1-SNAPSHOT.jar" out="confusion-0.0.1-SNAPSHOT-obfuscated.jar"/>
    </input>

    <keep-names>
        <class access="protected+">
            <field access="protected+"/>
            <method access="protected+"/>
        </class>
    </keep-names>

    <property name="log-file" value="log.xml"/>
</config>
```

即是对Allatori混淆器的具体配置，这里可以配置很多信息，很多种策略，也可以指定哪些类不被混淆，具体的各种方式可以在在文末附件里面的文档得到。 这里需要说明的是：

```xml
 <input>
        <jar in="confusion-0.0.1-SNAPSHOT.jar" out="confusion-0.0.1-SNAPSHOT-obfuscated.jar"/>
 </input>
```

`confusion-0.0.1-SNAPSHOT.jar`这个是打包后的未被混淆的包，而`confusion-0.0.1-SNAPSHOT-obfuscated.jar`是混淆后的包，这个是我们需要的。

### **打包步骤**

**1、clean maven工程。**

**2、将resources下面的allatori.xml文件复制到target目录下面。**

**3、install maven工程，看到如下信息后表示成功：**

