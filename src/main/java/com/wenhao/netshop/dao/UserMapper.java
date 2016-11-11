package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.Permission;
import com.wenhao.netshop.domain.Role;
import com.wenhao.netshop.domain.User;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    User selectByUsername(String username);

    Set<String> findRole(String username);

    Set<String> findPermission(String username);
}