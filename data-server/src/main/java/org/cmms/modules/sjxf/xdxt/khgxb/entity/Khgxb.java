package org.cmms.modules.sjxf.xdxt.khgxb.entity;

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
 * @Description: 客户关系表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_cust_relation")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_cust_relation对象", description="客户关系表")
public class Khgxb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**客户编号2*/
	@Excel(name = "客户编号3", width = 15)
    @ApiModelProperty(value = "客户编号3")
	private String custId3;
	/**客户编号1*/
	@Excel(name = "客户编号1", width = 15)
    @ApiModelProperty(value = "客户编号1")
	private String custId1;
	/**客户编号2*/
	@Excel(name = "客户编号2", width = 15)
    @ApiModelProperty(value = "客户编号2")
	private String custId2;
	/**客户关系类型*/
	@Excel(name = "客户关系类型", width = 15)
    @ApiModelProperty(value = "客户关系类型")
	private String custRelation;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String custRelationId;
	/**是否有效*/
	@Excel(name = "是否有效", width = 15)
    @ApiModelProperty(value = "是否有效")
	private String isEnabled;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**是否同一家庭*/
	@Excel(name = "是否同一家庭", width = 15)
    @ApiModelProperty(value = "是否同一家庭")
	private String isFamily;
	/**调查人*/
	@Excel(name = "调查人", width = 15)
    @ApiModelProperty(value = "调查人")
	private String checkId;
	/**调查日期*/
	@Excel(name = "调查日期", width = 15)
    @ApiModelProperty(value = "调查日期")
	private String checkDate;
	/**户号*/
	@Excel(name = "户号", width = 15)
    @ApiModelProperty(value = "户号")
	private String familyNo;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String isHuzhu;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
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
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
