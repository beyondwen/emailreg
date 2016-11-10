package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    int insert(User record);
    User selectByPrimaryKey(Long id);
    int updateByPrimaryKey(User record);
    User selectByEmail(String email);
}