package org.cmms.modules.sjxf.xdxt.khzcxx.entity;

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
 * @Description: 客户注册信息
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_base对象", description="客户注册信息")
public class Khzcxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**乡*/
	@Excel(name = "乡", width = 15)
    @ApiModelProperty(value = "乡")
	private String xiang;
	/**村*/
	@Excel(name = "村", width = 15)
    @ApiModelProperty(value = "村")
	private String cun;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	private String zu;
	/**客户分类*/
	@Excel(name = "客户分类", width = 15)
    @ApiModelProperty(value = "客户分类")
	private String custClass;
	/**客户中文名称*/
	@Excel(name = "客户中文名称", width = 15)
    @ApiModelProperty(value = "客户中文名称")
	private String custCn;
	/**客户英文名称*/
	@Excel(name = "客户英文名称", width = 15)
    @ApiModelProperty(value = "客户英文名称")
	private String custEn;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**客户关系*/
	@Excel(name = "客户关系", width = 15)
    @ApiModelProperty(value = "客户关系")
	private String custRelation;
	/**客户简称*/
	@Excel(name = "客户简称", width = 15)
    @ApiModelProperty(value = "客户简称")
	private String custSi;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**即期信用等级*/
	@Excel(name = "即期信用等级", width = 15)
    @ApiModelProperty(value = "即期信用等级")
	private String immediateEvaluate;
	/**黑名单*/
	@Excel(name = "黑名单", width = 15)
    @ApiModelProperty(value = "黑名单")
	private String isReport;
	/**年度信用等*/
	@Excel(name = "年度信用等", width = 15)
    @ApiModelProperty(value = "年度信用等")
	private String yearEvaluate;
	/**客户地区码*/
	@Excel(name = "客户地区码", width = 15)
    @ApiModelProperty(value = "客户地区码")
	private String custAreacode;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String custType1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String custType2;
	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15)
    @ApiModelProperty(value = "客户类型3")
	private String custType3;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private String custBusadd;
	/**客户电话号码*/
	@Excel(name = "客户电话号码", width = 15)
    @ApiModelProperty(value = "客户电话号码")
	private String custTel;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**客户属性*/
	@Excel(name = "客户属性", width = 15)
    @ApiModelProperty(value = "客户属性")
	private String custAttribute;
	/**ecif客户号*/
	@Excel(name = "ecif客户号", width = 15)
    @ApiModelProperty(value = "ecif客户号")
	private String ecifCustId;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String coreCustId;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
