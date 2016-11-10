package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2016/11/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void selectByEmail() throws Exception {
        User user = mapper.selectByEmail("921790476@qq.com");
        System.out.println(user);
    }

}