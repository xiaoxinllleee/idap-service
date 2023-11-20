package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 个人客户信息表
 */

@Data
@ToString
public class EcifPersonVO {
    /**客户编号*/
    private String custId;
    /**性别*/
    private String gender;
    /**出生日期*/
    private String birthday;
    /**婚姻状况*/
    private String marriage;
    /**手机号码*/
    private String mobilePhone;
    /**住宅电话*/
    private String homeTel;
    /**最高学历*/
    private String highestSchooling;
    /**通讯地址(默认为住宅地址,可不同)*/
    private String postAddr;
}
