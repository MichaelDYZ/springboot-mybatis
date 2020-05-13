package com.dyz.mybatis.mapper;

import com.dyz.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

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