package org.cmms.modules.pad.nhxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NhJtcyxx {

    /**系统评定说明*/
    @Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
    private String xtpdsm;
    /**修改时间*/
    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date xgsj;
    /**修改人*/
    @Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private String xgr;
    /**是否吸毒人员*/
    @Excel(name = "是否吸毒人员", width = 15)
    @ApiModelProperty(value = "是否吸毒人员")
    private String sfxdry;
    /**是否贫困户*/
    @Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
    private String sfpkh;
    /**是否低保*/
    @Excel(name = "是否低保", width = 15)
    @ApiModelProperty(value = "是否低保")
    private String sfdb;
    /**从事职业*/
    @Excel(name = "从事职业", width = 15)
    @ApiModelProperty(value = "从事职业")
    private String cszy;
    /**是否公职人员*/
    @Excel(name = "是否公职人员", width = 15)
    @ApiModelProperty(value = "是否公职人员")
    private String sfgzry;
    /**是否非法集资*/
    @Excel(name = "是否非法集资", width = 15)
    @ApiModelProperty(value = "是否非法集资")
    private String sfffjz;
    /**患病记录*/
    @Excel(name = "患病记录", width = 15)
    @ApiModelProperty(value = "患病记录")
    private String hbjl;
    /**建档完整度*/
    @Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
    private BigDecimal infoRate;
    /**出生年月*/
    @Excel(name = "出生年月", width = 15)
    @ApiModelProperty(value = "出生年月")
    private String csny;
    /**是否领取社保卡（1：是 2：否）*/
    @Excel(name = "是否领取社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否领取社保卡（1：是 2：否）")
    private String sflqsbk;
    /**是否开通社保卡（1：是 2：否）*/
    @Excel(name = "是否开通社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否开通社保卡（1：是 2：否）")
    private String sfktsbk;
    /**原所属乡镇*/
    @Excel(name = "原所属乡镇", width = 15)
    @ApiModelProperty(value = "原所属乡镇")
    private String yssxz;
    /**原行政村*/
    @Excel(name = "原行政村", width = 15)
    @ApiModelProperty(value = "原行政村")
    private String yxzc;
    /**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
    @Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15)
    @ApiModelProperty(value = "系统评定结果（1：灰名单 2：白名单 3：黑名单）")
    private String xtpdjg;

    /**机构代码*/
    @Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
    private String jgdm;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id2")
    private String id2;
    /**所属支行*/
    @Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    private String sszh;
    /**所属营销单元*/
    @Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
    private String ssyxdy;
    /**户号编码*/
    @Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
    private String hhbm;
    /**与户主关系*/
    @Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
    private String yhzgx;
    /**常住地址*/
    @Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
    private String zz;
    /**是否户主*/
    @Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
    private String sfhz;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
    private String khlx;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
    /**手机号码*/
    @Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    private String sjhm;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;
    /**性别*/
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private String xb;
    /**年龄*/
    @Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String nl;
    /**民族*/
    @Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String mz;
    /**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
    private String hyzk;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
    /**录入标识*/
    @Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
    private String lrbz;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;

    /**是否开通存款业务*/
    @Excel(name = "是否开通存款业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通存款业务")
    private String sfktckyw;
    /**存款余额*/
    @Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
    private BigDecimal ckye;
    /**存款日平余额*/
    @Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
    private BigDecimal ckrpye;
    /**存款年日平余额*/
    @Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
    private BigDecimal cknrpye;
    /**活期存款余额*/
    @Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
    private BigDecimal hqckye;
    /**定期存款余额*/
    @Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
    private BigDecimal dqckye;
    /**活期存款日平余额*/
    @Excel(name = "活期存款日平余额", width = 15)
    @ApiModelProperty(value = "活期存款日平余额")
    private BigDecimal hqckrpye;
    /**定期存款日平余额*/
    @Excel(name = "定期存款日平余额", width = 15)
    @ApiModelProperty(value = "定期存款日平余额")
    private BigDecimal dqckrpye;
    /**活期存款年日平余额*/
    @Excel(name = "活期存款年日平余额", width = 15)
    @ApiModelProperty(value = "活期存款年日平余额")
    private BigDecimal hqcknrpye;
    /**定期存款年日平余额*/
    @Excel(name = "定期存款年日平余额", width = 15)
    @ApiModelProperty(value = "定期存款年日平余额")
    private BigDecimal dqcknrpye;
    /**是否开通贷款业务*/
    @Excel(name = "是否开通贷款业务", width = 15)
    @ApiModelProperty(value = "是否开通贷款业务")
    private String sfktdkyw;
    /**贷款金额*/
    @Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
    private BigDecimal dkje;
    /**贷款余额*/
    @Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
    private BigDecimal dkye;
    /**最近贷款到期日期*/
    @Excel(name = "最近贷款到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近贷款到期日期")
    private Date zjdkdqrq;
    /**不良贷款余额*/
    @Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
    private BigDecimal bldkye;

    /**不良贷款余额*/
    @Excel(name = "不良余额", width = 15)
    @ApiModelProperty(value = "不良余额")
    private BigDecimal blye;

    public void setBlye() {
        this.blye = new BigDecimal(0);
        if(this.bldkye != null) {
            this.blye = this.blye.add(this.bldkye);
        }
        if (this.bwbldkye != null) {
            this.blye = this.blye.add(this.bwbldkye);
        }
    }

    @Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
    private BigDecimal bwbldkye;
    /**是否开通手机银行业务*/
    @Excel(name = "是否开通手机银行业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通手机银行业务")
    @Dict(dicCode = "sfbz")
    private String sfktsjyhyw;
    /**是否开通网上银行业务*/
    @Excel(name = "是否开通网上银行业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通网上银行业务")
    @Dict(dicCode = "sfbz")
    private String sfktwsyhyw;
    /**是否办理etc业务*/
    @Excel(name = "是否办理etc业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理etc业务")
    @Dict(dicCode = "sfbz")
    private String sfbletcyw;

    /**是否代发工资*/
    @Excel(name = "是否代发工资", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否代发工资")
    @Dict(dicCode = "sfbz")
    private String sfdfgz;
    /**是否开通POS机*/
    @Excel(name = "是否开通POS机",  width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通POS机")
    @Dict(dicCode = "sfbz")
    private String sfktpos;
    /**是否开通聚合支付*/
    @Excel(name = "是否开通聚合支付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通聚合支付")
    @Dict(dicCode = "sfbz")
    private String sfktjhzf;
    /**是否办理E支付*/
    @Excel(name = "是否办理E支付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理E支付")
    @Dict(dicCode = "sfbz")
    private String sfblezf;
    /**是否办理E缴费*/
    @Excel(name = "是否办理E缴费", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理E缴费")
    @Dict(dicCode = "sfbz")
    private String sfblejf;
    /**是否办理助农终端*/
    @Excel(name = "是否办理助农终端", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理助农终端")
    @Dict(dicCode = "sfbz")
    private String sfblznzd;
    /**是否办理理财业务*/
    @Excel(name = "是否办理理财业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理理财业务")
    @Dict(dicCode = "sfbz")
    private String sfbllcyw;
    /**是否办理代理保险业务*/
    @Excel(name = "是否办理代理保险业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理代理保险业务")
    @Dict(dicCode = "sfbz")
    private String sfbldlbx;
    /**是否关注我行公众号*/
    @Excel(name = "是否关注我行公众号", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否关注我行公众号")
    @Dict(dicCode = "sfbz")
    private String sfgzgzh;
    /**是否开通信用卡*/
    @Excel(name = "是否开通信用卡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通信用卡")
    @Dict(dicCode = "sfbz")
    private String sfktxyk;
    /**是否开通福民卡*/
    @Excel(name = "是否开通福民卡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通福民卡")
    @Dict(dicCode = "sfbz")
    private String sfktfmk;
    /**是否开扫码付*/
    @Excel(name = "是否开扫码付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开扫码付")
    @Dict(dicCode = "sfbz")
    private String sfktsmf;
    /**是否开通他行社保卡*/
    @Excel(name = "是否开通他行社保卡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通他行社保卡")
    @Dict(dicCode = "sfbz")
    private String sfktthsbk;
    /**是否授信*/
    @Excel(name = "是否授信", width = 15)
    @ApiModelProperty(value = "是否授信")
    @Dict(dicCode = "sxyxqk")
    private String sfsx;
    /**授信额度*/
    @Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
    private java.math.BigDecimal sxed;
    /**授信开始日期*/
    @Excel(name = "授信开始日期", width = 15)
    @ApiModelProperty(value = "授信开始日期")
    private String sxksrq;
    /**授信到期日期*/
    @Excel(name = "授信到期日期", width = 15)
    @ApiModelProperty(value = "授信到期日期")
    private String sxdqrq;
    /**是否死亡*/
    @Excel(name = "是否死亡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否死亡")
    @Dict(dicCode = "sfbz")
    private String sfsw;
    //cbs_borm_bas 的endDate 和  app_maturity_date 取最大值
    @TableField(exist = false)
    private String endDate;

    @TableField(exist = false)
    private Date sjrq;

    @TableField(exist = false)
    private Boolean sfsxdx = false;
    @TableField(exist = false)
    private String zfyxfs;

    /** 是否导入惠农快贷 */
    @TableField(exist = false)
    @Dict(dicCode = "sfbz")
    private String sfdrhnkd;

    /** 贷款支行 */
    @TableField(exist = false)
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    private String dkzh;

    @TableField(exist = false)
    private java.math.BigDecimal onedkje;
    @TableField(exist = false)
    private java.math.BigDecimal onedkye;
    /** 是否完成精准营销 */
    @TableField(exist = false)
    @Dict(dicCode = "sfbz")
    private String sfwcjzyx;
}
