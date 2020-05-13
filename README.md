# springboot-mybatis
本项目展示了 Spring Boot 项目如何整合Mybatis开发。
一.创建新的springboot项目，引入pom文件。
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.dyz</groupId>
    <artifactId>mybatis</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>mybatis</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.3.3</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

二、编写yml文件，数据库连接池使用hikari
spring:
  datasource:
    url: jdbc:mysql://192.168.25.6:3306/demo_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource
    initialization-mode: always
    continue-on-error: true
    hikari:  #连接池配置
      minimum-idle: 5
      connection-test-query: SELECT 1 FROM DUAL
      maximum-pool-size: 20
      auto-commit: true
      idle-timeout: 30000
      pool-name: SpringBootDemoHikariCP
      max-lifetime: 60000
      connection-timeout: 30000
logging:
  level:
    com./**
     *@param 
     *@return 
     */: debug
    com.dyz.mybatis.mapper: trace
mybatis:
  configuration:
    # 下划线转驼峰
    map-underscore-to-camel-case: true
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.dyz.mybatis.entity

三、添加实体类
/**
 * @author dyz
 * @version 1.0
 * @date 2020/5/12 10:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
private  Integer  id;
private  String  name;
private  String password;
private  String  phone;
private Date  createTime;
}

四、编写Mapper接口
/**
 * @author dyz
 * @version 1.0
 * @date 2020/5/12 11:20
 */


@Mapper
@Component
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return 用户列表
     * 注解形式
     */
    @Select("SELECT * FROM user")
    List<User> selectAllUser();



    /**
     * 根据id查询用户
     *
     * @param id 主键id
     * @return user
     * 注解形式
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(@Param("id") int id);



    /**
     * 保存用户
     *
     * @param user 用户
     * @return
     */
    int saveUser(@Param("user") User user);



    /**
     * 删除用户
     *
     * @param id 主键id
     * @return
     */
    int deleteById(@Param("id") int id);


}

五、添加编写mapper接口的xml文件
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.dyz.mybatis.mapper.UserMapper">

    <insert id="saveUser">
        INSERT INTO `user` (name, password,phone)
        VALUES (#{user.name},
                #{user.password},
                #{user.phone})
    </insert>

    <delete id="deleteById">
        DELETE
        FROM `user`
        WHERE `id` = #{id}
    </delete>
</mapper>

五、启动项目调用接口测试。


六、源码地址：https://github.com/MichaelDYZ/springboot-mybatis
