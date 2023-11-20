package org.cmms.modules.khgl.ckkh.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;

import java.util.Date;

/**
 * @Date 2022/3/7
 * @Created by eran
 */
@Data
public class CkkhCardVO {

    private String clcp;
    private String qtcp;
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhmjm;
    @Desensitize(rule = DesensitizeRuleEnums.RSAENCRYPT)
    private String zjhm;
    private String khbh;
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String sjhm;
    @Desensitize(rule = DesensitizeRuleEnums.RSAENCRYPT)
    private String sjhmrsa;
    private String dzyx;
    private String zz;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String khmc;
    private String accGrp;
    //是否被关注 1是 2否
    private String isGz = "2";

    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String jgdm;
    private Integer nl;
    private Integer xb;
    private String csrq;
    private String custId;


    public void setGrkh(KhgxglKhzlglGrkh khgxglKhzlglGrkh){
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz())){
            this.zz = khgxglKhzlglGrkh.getZz();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZjhm())){
            this.zjhmjm = khgxglKhzlglGrkh.getZjhm();
            this.zjhmjm = khgxglKhzlglGrkh.getZjhm();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm())){
            this.sjhm=khgxglKhzlglGrkh.getSjhm();
            this.sjhmrsa=khgxglKhzlglGrkh.getSjhm();
        }
        if (khgxglKhzlglGrkh.getCsrq() != null){
            this.csrq = DateUtil.format(khgxglKhzlglGrkh.getCsrq(), DatePattern.NORM_DATE_PATTERN);
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getKhbh())){
            this.khbh=khgxglKhzlglGrkh.getKhbh();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getCustId())){
            this.custId = khgxglKhzlglGrkh.getCustId();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getDzyx())){
            this.dzyx = khgxglKhzlglGrkh.getDzyx();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getKhmc())){
            this.khmc = khgxglKhzlglGrkh.getKhmc();
        }
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getJgdm())){
            this.jgdm = khgxglKhzlglGrkh.getJgdm();
        }
    }

//    public void setMx(TbCkYgghcksjmx tbCkYgghcksjmx){
//        if (StringUtils.isNotBlank(tbCkYgghcksjmx.getCustName())){
//            this.khmc = tbCkYgghcksjmx.getCustName();
//        }
//        if (StringUtils.isNotBlank(tbCkYgghcksjmx.getJgdm())){
//            this.jgdm = tbCkYgghcksjmx.getJgdm();
//        }
//    }
}
