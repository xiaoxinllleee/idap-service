package org.cmms.modules.tjfx.birthdayreminder.dto;


import lombok.Data;


@Data
public class statusDto {

    private String id;

    private String operate;

    private String operatePeople;

/*
    *//**
     * @Description: dw
     * @Author: jeecg-boot
     * @Date: 2022-07-19
     * @Version: V1.0
     * <p>
     * /**姓名
     *//*
    private String name;
    *//**
     * 日期
     *//*
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date days;
    *//**
     * 性别
     *//*

    private String sex;
    *//**
     * 户号编码
     *//*

    private String doornumbercode;
    *//**
     * 年龄
     *//*


    private String age;
    *//**
     * 身份证
     *//*

    private String idnumber;
    *//**
     * 家庭地址
     *//*

    private String homeaddress;
    *//**
     * 是否达标
     *//*


    private String gzrwSfdb;
    *//**
     * 达标原因
     *//*

    private String standardcause;
    *//**
     * 主键
     *//*

    private String qsId;

    *//**
     * 备注
     *//*

    private String bz;

    *//**
     * 所属支行 id**
     *//*
    private String sszh;

    *//**
     * 原所属乡镇**
     *//*
    private String yssxz;
    *//**
     * 是否重要
     *//*

    private String ifMaster;
    private String id;
    private String operate;
    private String operatePeople;*/
}
