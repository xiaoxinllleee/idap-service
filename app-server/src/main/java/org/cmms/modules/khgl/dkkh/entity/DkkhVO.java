package org.cmms.modules.khgl.dkkh.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglQykh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Date 2022/3/9
 * @Created by eran
 */
@Data
public class DkkhVO {
    private KhgxglKhzlglGrkh khgxglKhzlglGrkh = new KhgxglKhzlglGrkh();
    private KhgxglKhzlglQykh khgxglKhzlglQykh = new KhgxglKhzlglQykh();
    //贷款状态 贷款五级分类
    private String fiveClassType;
    private String fiveClassTypeVal;

    private BigDecimal dkje = new BigDecimal(0);
    private BigDecimal dkye = new BigDecimal(0);
    //发放日期
    private Date ffrq;
    private String ffrqVal;
    //到期日期
    private Date dqrq;
    private String dqrqVal;
    //贷款类型 01个人 02是对公
    private String dklx;
    //不良余额
    private BigDecimal blye;
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhmjm;
    @Desensitize(rule = DesensitizeRuleEnums.RSAENCRYPT)
    private String zjhm;
    private String custId;
    private Integer gender;
    private Integer nl;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String khmc;
    //手机号码
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String sjhm;
    @Desensitize(rule = DesensitizeRuleEnums.RSAENCRYPT)
    private String sjhmrsa;
    //地址
    private String zz;
    private String jgdm;
    private String jgdmVal;
    private String cpxx;
    //是否关注
    private String isGz = "2";

    public void maxFiveClassType(String fiveClassType,BigDecimal dkye){
        Integer integer = Integer.valueOf(fiveClassType);
        if (integer > 2){
            if (this.blye == null){
                this.blye = dkye;
            }else {
                this.blye = this.blye.add(dkye);
            }
        }

        if (this.fiveClassType == null){
            this.fiveClassType = fiveClassType;
        }else {
            //对比大小
            Integer integer1 = Integer.valueOf(this.fiveClassType);
            if (integer > integer1){
                this.fiveClassType = fiveClassType;
            }
        }
    }

    public void hjdkje(BigDecimal dkje){
        this.dkje = this.dkje.add(dkje);
    }

    public void hjdkye(BigDecimal dkye){
        this.dkye = this.dkye.add(dkye);
    }

    public void maxFfrq(Date ffrq){
        if (this.ffrq == null){
            this.ffrq = ffrq;
            this.ffrqVal = DateUtil.format(ffrq, DatePattern.NORM_DATE_PATTERN);
        }else {
            //取大值
            if (this.ffrq.compareTo(ffrq) < 0){
                this.ffrq = ffrq;
                this.ffrqVal = DateUtil.format(ffrq, DatePattern.NORM_DATE_PATTERN);

            }
        }
    }

    public void maxDqrq(Date dqrq){
        if (this.dqrq == null){
            this.dqrq = dqrq;
            this.dqrqVal = DateUtil.format(dqrq, DatePattern.NORM_DATE_PATTERN);

        }else {
            //取大值
            if (this.dqrq.compareTo(dqrq) < 0){
                this.dqrq = dqrq;
                this.dqrqVal = DateUtil.format(dqrq, DatePattern.NORM_DATE_PATTERN);
            }
        }
    }

    public void appendDklx(String custType){
        if ("01".equals(custType)){
            custType = "个人";
        }else {
            custType = "对公";
        }

        if (StringUtils.isBlank(this.dklx)){
            this.dklx = custType;
        }else {
            if (!this.dklx.contains(custType)){
                this.dklx = this.dklx +"丶" + custType;
            }
        }
    }

    public void jssex(){
        if (StringUtils.isNotBlank(this.zjhm)){
            if (IdcardUtil.isValidCard(this.zjhm)){
               this.gender = IdcardUtil.getGenderByIdCard(this.zjhm);
               this.nl = IdcardUtil.getAgeByIdCard(this.zjhm);
            }else {
                this.gender = 3;
            }
        }
    }
}
