package com.wenhao.netshop.dao;

import com.wenhao.netshop.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lw on 2016/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test11 {

    @Test
    public void teseName() {
        String name = "aaaaa";
        String na = StringUtil.toUpperCaseFirstOne(name);
        System.out.println(na);
    }

}
