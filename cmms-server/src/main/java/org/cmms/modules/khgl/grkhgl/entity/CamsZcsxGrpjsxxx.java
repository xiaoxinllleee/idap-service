package org.cmms.modules.khgl.grkhgl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人客户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRPJSXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRPJSXXX对象", description="个人客户评级授信信息")
public class CamsZcsxGrpjsxxx {

	/**其他支出(家庭支出)*/
	@Excel(name = "其他支出(家庭支出)", width = 15)
    @ApiModelProperty(value = "其他支出(家庭支出)")
	private java.math.BigDecimal zcQtzc;
	/**合计（支出）*/
	@Excel(name = "合计（支出）", width = 15)
    @ApiModelProperty(value = "合计（支出）")
	private java.math.BigDecimal zcHj;
	/**家庭净收入*/
	@Excel(name = "家庭净收入", width = 15)
    @ApiModelProperty(value = "家庭净收入")
	private java.math.BigDecimal jtjsr;
	/**家庭成员是否有不良嗜好*/
	@Excel(name = "家庭成员是否有不良嗜好", width = 15)
    @ApiModelProperty(value = "家庭成员是否有不良嗜好")
	private String shsyBlsh;
	/**家庭成员是否勤快*/
	@Excel(name = "家庭成员是否勤快", width = 15)
    @ApiModelProperty(value = "家庭成员是否勤快")
	private String shsySfqk;
	/**家庭成员是否有民间高息贷款*/
	@Excel(name = "家庭成员是否有民间高息贷款", width = 15)
    @ApiModelProperty(value = "家庭成员是否有民间高息贷款")
	private String shsySfygld;
	/**家庭成员是否有打架、闹事等不良行为*/
	@Excel(name = "家庭成员是否有打架、闹事等不良行为", width = 15)
    @ApiModelProperty(value = "家庭成员是否有打架、闹事等不良行为")
	private String shsySfdjns;
	/**家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）*/
	@Excel(name = "家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）", width = 15)
    @ApiModelProperty(value = "家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）")
	private String shsySflqbz;
	/**家庭成员是否有刑事犯罪记录*/
	@Excel(name = "家庭成员是否有刑事犯罪记录", width = 15)
    @ApiModelProperty(value = "家庭成员是否有刑事犯罪记录")
	private String shsySfxsfz;
	/**家庭成员是否涉诉*/
	@Excel(name = "家庭成员是否涉诉", width = 15)
    @ApiModelProperty(value = "家庭成员是否涉诉")
	private String shsySfss;
	/**对户主评价-品行评价*/
	@Excel(name = "对户主评价-品行评价", width = 15)
    @ApiModelProperty(value = "对户主评价-品行评价")
	private String dhzpjPxpj;
	/**对户主评价-信用评价*/
	@Excel(name = "对户主评价-信用评价", width = 15)
    @ApiModelProperty(value = "对户主评价-信用评价")
	private String dhzpjXypj;
	/**初评等级*/
	@Excel(name = "初评等级", width = 15)
    @ApiModelProperty(value = "初评等级")
	private String cpdj;
	/**系统评定等级*/
	@Excel(name = "系统评定等级", width = 15)
    @ApiModelProperty(value = "系统评定等级")
	private String xtpddj;
	/**系统评定得分*/
	@Excel(name = "系统评定得分", width = 15)
    @ApiModelProperty(value = "系统评定得分")
	private java.math.BigDecimal xtpddf;
	/**系统授信额度*/
	@Excel(name = "系统授信额度", width = 15)
    @ApiModelProperty(value = "系统授信额度")
	private java.math.BigDecimal xtsxed;
	/**最终评定等级*/
	@Excel(name = "最终评定等级", width = 15)
    @ApiModelProperty(value = "最终评定等级")
	private String zzpddj;
	/**最终评定得分*/
	@Excel(name = "最终评定得分", width = 15)
    @ApiModelProperty(value = "最终评定得分")
	private java.math.BigDecimal zzpddf;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
    @ApiModelProperty(value = "最终授信额度")
	private java.math.BigDecimal zzsxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**其他补助情况*/
	@Excel(name = "其他补助情况", width = 15)
    @ApiModelProperty(value = "其他补助情况")
	private String qtbzqk;
	/**犯罪类型*/
	@Excel(name = "犯罪类型", width = 15)
    @ApiModelProperty(value = "犯罪类型")
	private String fzlx;
	/**是否建档立卡贫困户*/
	@Excel(name = "是否建档立卡贫困户", width = 15)
    @ApiModelProperty(value = "是否建档立卡贫困户")
	private String sfjdlkpkh;
	/**授信日期*/
	@Excel(name = "授信日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "授信日期")
	private Date sxrq;
	/**spid*/
	@Excel(name = "spid", width = 15)
    @ApiModelProperty(value = "spid")
	private String spid;
	/**意见*/
	@Excel(name = "意见", width = 15)
    @ApiModelProperty(value = "意见")
	private String yj;
	/**花名册id*/
	@Excel(name = "花名册id", width = 15)
    @ApiModelProperty(value = "花名册id")
	@TableId(type = IdType.ASSIGN_ID)
	private String hmcId;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**住房套数（家庭资产情况-固定资产）*/
	@Excel(name = "住房套数（家庭资产情况-固定资产）", width = 15)
    @ApiModelProperty(value = "住房套数（家庭资产情况-固定资产）")
	private Integer gdzcZfts;
	/**面积（家庭资产情况-固定资产）*/
	@Excel(name = "面积（家庭资产情况-固定资产）", width = 15)
    @ApiModelProperty(value = "面积（家庭资产情况-固定资产）")
	private java.math.BigDecimal gdzcZfmj;
	/**价值（家庭资产情况-固定资产）*/
	@Excel(name = "价值（家庭资产情况-固定资产）", width = 15)
    @ApiModelProperty(value = "价值（家庭资产情况-固定资产）")
	private java.math.BigDecimal gdzcZfjz;
	/**其他（家庭资产情况-固定资产）*/
	@Excel(name = "其他（家庭资产情况-固定资产）", width = 15)
    @ApiModelProperty(value = "其他（家庭资产情况-固定资产）")
	private java.math.BigDecimal gdzcQt;
	/**合计（家庭资产情况-固定资产）*/
	@Excel(name = "合计（家庭资产情况-固定资产）", width = 15)
    @ApiModelProperty(value = "合计（家庭资产情况-固定资产）")
	private java.math.BigDecimal gdzcHj;
	/**现金及我行存款（家庭资产情况-流程资产）*/
	@Excel(name = "现金及我行存款（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "现金及我行存款（家庭资产情况-流程资产）")
	private java.math.BigDecimal ldzcXjjwhck;
	/**其他（家庭资产情况-流程资产）*/
	@Excel(name = "其他（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "其他（家庭资产情况-流程资产）")
	private java.math.BigDecimal ldzcQt;
	/**应收款（家庭资产情况-流程资产）*/
	@Excel(name = "应收款（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "应收款（家庭资产情况-流程资产）")
	private java.math.BigDecimal ldzcYsk;
	/**是否他行开户（家庭资产情况-流程资产）*/
	@Excel(name = "是否他行开户（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "是否他行开户（家庭资产情况-流程资产）")
	private String ldzcSfthkh;
	/**是否他行有存款（家庭资产情况-流程资产）*/
	@Excel(name = "是否他行有存款（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "是否他行有存款（家庭资产情况-流程资产）")
	private String ldzcSfthyck;
	/**他行存款金额（家庭资产情况-流程资产）*/
	@Excel(name = "他行存款金额（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "他行存款金额（家庭资产情况-流程资产）")
	private java.math.BigDecimal ldzcThckje;
	/**合计（家庭资产情况-流程资产）*/
	@Excel(name = "合计（家庭资产情况-流程资产）", width = 15)
    @ApiModelProperty(value = "合计（家庭资产情况-流程资产）")
	private java.math.BigDecimal ldzcHj;
	/**总资产合计*/
	@Excel(name = "总资产合计", width = 15)
    @ApiModelProperty(value = "总资产合计")
	private java.math.BigDecimal zzchj;
	/**我行借款(家庭负债情况)*/
	@Excel(name = "我行借款(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "我行借款(家庭负债情况)")
	private java.math.BigDecimal fzWhjk;
	/**其他银行借款(家庭负债情况)*/
	@Excel(name = "其他银行借款(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "其他银行借款(家庭负债情况)")
	private java.math.BigDecimal fzQtyhjk;
	/**私人借款和其他借款(家庭负债情况)*/
	@Excel(name = "私人借款和其他借款(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "私人借款和其他借款(家庭负债情况)")
	private java.math.BigDecimal fzSrjkhqtjk;
	/**应付款(家庭负债情况)*/
	@Excel(name = "应付款(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "应付款(家庭负债情况)")
	private java.math.BigDecimal fzYfk;
	/**其他负债(家庭负债情况)*/
	@Excel(name = "其他负债(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "其他负债(家庭负债情况)")
	private java.math.BigDecimal fzQtfz;
	/**为他人担保(家庭负债情况)*/
	@Excel(name = "为他人担保(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "为他人担保(家庭负债情况)")
	private java.math.BigDecimal fzWtrdb;
	/**负债合计(家庭负债情况)*/
	@Excel(name = "负债合计(家庭负债情况)", width = 15)
    @ApiModelProperty(value = "负债合计(家庭负债情况)")
	private java.math.BigDecimal fzHj;
	/**家庭净资产*/
	@Excel(name = "家庭净资产", width = 15)
    @ApiModelProperty(value = "家庭净资产")
	private java.math.BigDecimal jtjzc;
	/**种植(家庭收入)*/
	@Excel(name = "种植(家庭收入)", width = 15)
    @ApiModelProperty(value = "种植(家庭收入)")
	private java.math.BigDecimal srZz;
	/**养殖(家庭收入)*/
	@Excel(name = "养殖(家庭收入)", width = 15)
    @ApiModelProperty(value = "养殖(家庭收入)")
	private java.math.BigDecimal srYz;
	/**劳务(家庭收入)*/
	@Excel(name = "劳务(家庭收入)", width = 15)
    @ApiModelProperty(value = "劳务(家庭收入)")
	private java.math.BigDecimal srLw;
	/**工商业(家庭收入)*/
	@Excel(name = "工商业(家庭收入)", width = 15)
    @ApiModelProperty(value = "工商业(家庭收入)")
	private java.math.BigDecimal srGsy;
	/**其他收入(家庭收入)*/
	@Excel(name = "其他收入(家庭收入)", width = 15)
    @ApiModelProperty(value = "其他收入(家庭收入)")
	private java.math.BigDecimal srQtsr;
	/**合计(家庭收入)*/
	@Excel(name = "合计(家庭收入)", width = 15)
    @ApiModelProperty(value = "合计(家庭收入)")
	private java.math.BigDecimal srHj;
	/**教育(家庭支出)*/
	@Excel(name = "教育(家庭支出)", width = 15)
    @ApiModelProperty(value = "教育(家庭支出)")
	private java.math.BigDecimal zcJy;
	/**医疗(家庭支出)*/
	@Excel(name = "医疗(家庭支出)", width = 15)
    @ApiModelProperty(value = "医疗(家庭支出)")
	private java.math.BigDecimal zcYl;
	/**养老(家庭支出)*/
	@Excel(name = "养老(家庭支出)", width = 15)
    @ApiModelProperty(value = "养老(家庭支出)")
	private java.math.BigDecimal zcYlao;
	/**生产成本(家庭支出)*/
	@Excel(name = "生产成本(家庭支出)", width = 15)
    @ApiModelProperty(value = "生产成本(家庭支出)")
	private java.math.BigDecimal zcSccb;
	/**日常生活(家庭支出)*/
	@Excel(name = "日常生活(家庭支出)", width = 15)
    @ApiModelProperty(value = "日常生活(家庭支出)")
	private java.math.BigDecimal zcRcsh;
	@Excel(name = "系统评定原因", width = 15)
	@ApiModelProperty(value = "系统评定原因")
	private String xtpdyy;
	@ApiModelProperty(value = "不予授信情况")
	private String bysxqx;
	@Dict(dicCode = "ly_xend_cpzl")
	private String cpzl;
	private BigDecimal cpzlll;
	@Dict(dicCode = "ly_splc")
	private Integer status;
}
