package org.cmms.modules.sjxf.xdxt.jgxx.entity;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 机构信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_sm_dept")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_sm_dept对象", description="机构信息")
public class Jgxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**五级分类流程分支标准*/
	@Excel(name = "五级分类流程分支标准", width = 15)
    @ApiModelProperty(value = "五级分类流程分支标准")
	private String fvStd;
	/**对应客户编号*/
	@Excel(name = "对应客户编号", width = 15)
    @ApiModelProperty(value = "对应客户编号")
	private String custId;
	/**综合业务结算代码*/
	@Excel(name = "综合业务结算代码", width = 15)
    @ApiModelProperty(value = "综合业务结算代码")
	private String deptAcno;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String deptCd;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String deptId;
	/**机构级别*/
	@Excel(name = "机构级别", width = 15)
    @ApiModelProperty(value = "机构级别")
	private String deptLevel;
	/**上级机构机构代码*/
	@Excel(name = "上级机构机构代码", width = 15)
    @ApiModelProperty(value = "上级机构机构代码")
	private String deptParent;
	/**机构类型*/
	@Excel(name = "机构类型", width = 15)
    @ApiModelProperty(value = "机构类型")
	private String deptType;
	/**金融机构代码*/
	@Excel(name = "金融机构代码", width = 15)
    @ApiModelProperty(value = "金融机构代码")
	private String finInsCode;
	/**金融机构代码名称*/
	@Excel(name = "金融机构代码名称", width = 15)
    @ApiModelProperty(value = "金融机构代码名称")
	private String finInsName;
	/**金融机构密码*/
	@Excel(name = "金融机构密码", width = 15)
    @ApiModelProperty(value = "金融机构密码")
	private String finInsPwd;
	/**是否可用*/
	@Excel(name = "是否可用", width = 15)
    @ApiModelProperty(value = "是否可用")
	private String isEnabled;
	/**是否上报*/
	@Excel(name = "是否上报", width = 15)
    @ApiModelProperty(value = "是否上报")
	private String isReport;
	/**机构领导编号*/
	@Excel(name = "机构领导编号", width = 15)
    @ApiModelProperty(value = "机构领导编号")
	private String principalId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**机构电话*/
	@Excel(name = "机构电话", width = 15)
    @ApiModelProperty(value = "机构电话")
	private String deptTel;
	/**金融机构许可证号码*/
	@Excel(name = "金融机构许可证号码", width = 15)
    @ApiModelProperty(value = "金融机构许可证号码")
	private String finInsAdmiNo;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
	private String shopCardNo;
	/**是否市辖区*/
	@Excel(name = "是否市辖区", width = 15)
    @ApiModelProperty(value = "是否市辖区")
	private String isCantonal;
	/**机构地址*/
	@Excel(name = "机构地址", width = 15)
    @ApiModelProperty(value = "机构地址")
	private String deptAddr;
	/**是否办理贷款业务*/
	@Excel(name = "是否办理贷款业务", width = 15)
    @ApiModelProperty(value = "是否办理贷款业务")
	private String isCredit;
	/**加贷审会审批权限(金额)*/
	@Excel(name = "加贷审会审批权限(金额)", width = 15)
    @ApiModelProperty(value = "加贷审会审批权限(金额)")
	private java.math.BigDecimal checkValueOne;
	/**风险管理委员会审批权限(金额)*/
	@Excel(name = "风险管理委员会审批权限(金额)", width = 15)
    @ApiModelProperty(value = "风险管理委员会审批权限(金额)")
	private java.math.BigDecimal checkValueTwo;
	/**贴现权限(金额)*/
	@Excel(name = "贴现权限(金额)", width = 15)
    @ApiModelProperty(value = "贴现权限(金额)")
	private java.math.BigDecimal checkValueThree;
	/**承兑汇票权限(金额)*/
	@Excel(name = "承兑汇票权限(金额)", width = 15)
    @ApiModelProperty(value = "承兑汇票权限(金额)")
	private java.math.BigDecimal checkValueFour;
	/**风险管理部权限(金额)*/
	@Excel(name = "风险管理部权限(金额)", width = 15)
    @ApiModelProperty(value = "风险管理部权限(金额)")
	private java.math.BigDecimal checkValueFive;
	/**业务发展部权限(金额)*/
	@Excel(name = "业务发展部权限(金额)", width = 15)
    @ApiModelProperty(value = "业务发展部权限(金额)")
	private java.math.BigDecimal checkValueSix;
	/**信贷管理部权限(金额)*/
	@Excel(name = "信贷管理部权限(金额)", width = 15)
    @ApiModelProperty(value = "信贷管理部权限(金额)")
	private java.math.BigDecimal checkValueSeven;
	/**是否需要现场照像*/
	@Excel(name = "是否需要现场照像", width = 15)
    @ApiModelProperty(value = "是否需要现场照像")
	private String isCamera;
	/**贷款余额上限*/
	@Excel(name = "贷款余额上限", width = 15)
    @ApiModelProperty(value = "贷款余额上限")
	private java.math.BigDecimal checkValueEight;
	/**是否动态取贷审会成员*/
	@Excel(name = "是否动态取贷审会成员", width = 15)
    @ApiModelProperty(value = "是否动态取贷审会成员")
	private String isDynamic;
	/**贷审会总成员数*/
	@Excel(name = "贷审会总成员数", width = 15)
    @ApiModelProperty(value = "贷审会总成员数")
	private String examAllNum;
	/**贷审会动态成员数*/
	@Excel(name = "贷审会动态成员数", width = 15)
    @ApiModelProperty(value = "贷审会动态成员数")
	private String examDynamicNum;
	/**是否动态取风管会成员*/
	@Excel(name = "是否动态取风管会成员", width = 15)
    @ApiModelProperty(value = "是否动态取风管会成员")
	private String isRiskDynamic;
	/**风管会总成员数*/
	@Excel(name = "风管会总成员数", width = 15)
    @ApiModelProperty(value = "风管会总成员数")
	private String riskAllNum;
	/**风管会动态成员数*/
	@Excel(name = "风管会动态成员数", width = 15)
    @ApiModelProperty(value = "风管会动态成员数")
	private String riskDynamicNum;
	/**正常减值/计提比例*/
	@Excel(name = "正常减值/计提比例", width = 15)
    @ApiModelProperty(value = "正常减值/计提比例")
	private java.math.BigDecimal normalRatio;
	/**关注减值/计提比例*/
	@Excel(name = "关注减值/计提比例", width = 15)
    @ApiModelProperty(value = "关注减值/计提比例")
	private java.math.BigDecimal concernRatio;
	/**次级减值/计提比例*/
	@Excel(name = "次级减值/计提比例", width = 15)
    @ApiModelProperty(value = "次级减值/计提比例")
	private java.math.BigDecimal secondaryRatio;
	/**可疑减值/计提比例*/
	@Excel(name = "可疑减值/计提比例", width = 15)
    @ApiModelProperty(value = "可疑减值/计提比例")
	private java.math.BigDecimal supicousRatio;
	/**损失减值/计提比例*/
	@Excel(name = "损失减值/计提比例", width = 15)
    @ApiModelProperty(value = "损失减值/计提比例")
	private java.math.BigDecimal loseRatio;
	/**综合计提测算序号*/
	@Excel(name = "综合计提测算序号", width = 15)
    @ApiModelProperty(value = "综合计提测算序号")
	private String devalueRatioId;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private java.math.BigDecimal checkValueNine;
	/**机构属性*/
	@Excel(name = "机构属性", width = 15)
    @ApiModelProperty(value = "机构属性")
	private String deptAttribute;
	/**机构统计属性*/
	@Excel(name = "机构统计属性", width = 15)
    @ApiModelProperty(value = "机构统计属性")
	private String deptCount;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
