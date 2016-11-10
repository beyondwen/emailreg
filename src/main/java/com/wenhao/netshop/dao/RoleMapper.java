package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Role record);
}