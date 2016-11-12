package com.wenhao.netshop.domain;

import java.math.BigDecimal;

public class Spendingbill {
    private Long id;

    /**
     * 支出金额
     */
    private BigDecimal
            amountofpayout;

    /**
     * 付款方式
     */
    private String
            paymentmethod;

    /**
     * 用途类型
     */
    private String purposetype;

    /**
     * 详细说明
     */
    private String
            detaileddescription;

    /**
     * 出借人
     */
    private Integer userid;

    /**
     * 借款人
     */
    private String borrower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountofpayout() {
        return amountofpayout;
    }

    public void setAmountofpayout(BigDecimal amountofpayout) {
        this.amountofpayout = amountofpayout;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getPurposetype() {
        return purposetype;
    }

    public void setPurposetype(String purposetype) {
        this.purposetype = purposetype;
    }

    public String getDetaileddescription() {
        return detaileddescription;
    }

    public void setDetaileddescription(String detaileddescription) {
        this.detaileddescription = detaileddescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
}