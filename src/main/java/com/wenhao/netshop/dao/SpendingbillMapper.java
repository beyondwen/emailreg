package com.wenhao.netshop.dao;

import com.wenhao.netshop.domain.Spendingbill;

public interface SpendingbillMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Spendingbill record);

    Spendingbill selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Spendingbill record);
}