package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.Permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Permission record);
}