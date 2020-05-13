package com.dyz.mybatis;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.dyz.mybatis.entity.User;
import com.dyz.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@Slf4j
class MybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试查询所有
     */
    @Test
    public void selectAllUser() {
        List<User> userList = userMapper.selectAllUser();
        System.out.println(userList);
    }


    /**
     * 根据ID查询
     */
    @Test
    public void selectUserById() {
        User user = userMapper.selectUserById(1);
        System.out.println(user);
    }



    /**
     * 保存数据
     */
    @Test
    public void saveUser() {
        String salt = IdUtil.fastSimpleUUID();
        User user = User.builder().name("dyz6").password("dyz666").phone("17300000003").build();
        int i = userMapper.saveUser(user);
        System.out.println(i);
    }

    /**
     * 根据ID删除
     */
    @Test
    public void deleteById() {
        int i = userMapper.deleteById(4);
        System.out.println(i);
    }





}
