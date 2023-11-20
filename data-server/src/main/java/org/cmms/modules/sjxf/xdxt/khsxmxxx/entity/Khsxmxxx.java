package org.cmms.modules.sjxf.xdxt.khsxmxxx.entity;

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
 * @Description: 客户授信明细信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_line_credit_detail")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_line_credit_detail对象", description="客户授信明细信息")
public class Khsxmxxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String creditDetailId;
	/**授信种类*/
	@Excel(name = "授信种类", width = 15)
    @ApiModelProperty(value = "授信种类")
	private String creditKind;
	/**授信期限*/
	@Excel(name = "授信期限", width = 15)
    @ApiModelProperty(value = "授信期限")
	private String creditTerm;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal creditValue;
	/**当前冻结金额*/
	@Excel(name = "当前冻结金额", width = 15)
    @ApiModelProperty(value = "当前冻结金额")
	private java.math.BigDecimal currFreezeValue;
	/**使用金额*/
	@Excel(name = "使用金额", width = 15)
    @ApiModelProperty(value = "使用金额")
	private java.math.BigDecimal currValue;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currencyType;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15)
    @ApiModelProperty(value = "结束时间")
	private String endDate;
	/**是否循环*/
	@Excel(name = "是否循环", width = 15)
    @ApiModelProperty(value = "是否循环")
	private String isCycle;
	/**授信状态*/
	@Excel(name = "授信状态", width = 15)
    @ApiModelProperty(value = "授信状态")
	private String isEnabled;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15)
    @ApiModelProperty(value = "开始时间")
	private String startDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更改日期*/
	@Excel(name = "更改日期", width = 15)
    @ApiModelProperty(value = "更改日期")
	private String updateDate;
	/**授信倍数*/
	@Excel(name = "授信倍数", width = 15)
    @ApiModelProperty(value = "授信倍数")
	private java.math.BigDecimal creditMultiple;
	/**授信状态*/
	@Excel(name = "授信状态", width = 15)
    @ApiModelProperty(value = "授信状态")
	private String creditStatus;
	/**是否检查保证金*/
	@Excel(name = "是否检查保证金", width = 15)
    @ApiModelProperty(value = "是否检查保证金")
	private String checkBailFlag;
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
