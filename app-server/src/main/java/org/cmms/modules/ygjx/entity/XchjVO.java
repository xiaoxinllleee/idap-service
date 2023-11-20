package org.cmms.modules.ygjx.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/2/28
 * @Created by eran
 * APP 薪酬明细数据
 */
@Data
public class XchjVO {
    //薪酬合计元
    private BigDecimal xchj = new BigDecimal("0.00");
    //绩效薪酬   == 张家界穿透绩效
    private BigDecimal jxxc = new BigDecimal("0.00");
    //基本薪酬   == 基本工资
    private BigDecimal jbxc = new BigDecimal("0.00");
    //专项考核
    private BigDecimal zxkh = new BigDecimal("0.00");
    //其他费用  == 张家界 其他薪酬
    private BigDecimal qtfy = new BigDecimal("0.00");
    //张家界团队绩效
    private BigDecimal tdjx = new BigDecimal("0.00");

    private String gzrq;

    public void jsXchj(){
        this.xchj = this.jxxc.add(this.jbxc).add(this.zxkh).add(this.qtfy).add(tdjx);
    }
}
