package org.cmms.modules.sjxf.xdxt.htedb.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 合同额度表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_contractlinecredit")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_contractlinecredit对象", description="合同额度表")
public class Htedb {

	/**dataFlag*/
	@Excel(name = "dataFlag", width = 15)
    @ApiModelProperty(value = "dataFlag")
	private String dataFlag;
	/**合同额度流水号*/
	@Excel(name = "合同额度流水号", width = 15)
    @ApiModelProperty(value = "合同额度流水号")
	private String creditDetailId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**额度种类*/
	@Excel(name = "额度种类", width = 15)
    @ApiModelProperty(value = "额度种类")
	private String creditKind;
	/**额度期限*/
	@Excel(name = "额度期限", width = 15)
    @ApiModelProperty(value = "额度期限")
	private String creditTerm;
	/**合同额度金额*/
	@Excel(name = "合同额度金额", width = 15)
    @ApiModelProperty(value = "合同额度金额")
	private java.math.BigDecimal creditValue;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String startDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String endDate;
	/**冻结金额*/
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
	private java.math.BigDecimal currFreezeValue;
	/**使用金额*/
	@Excel(name = "使用金额", width = 15)
    @ApiModelProperty(value = "使用金额")
	private java.math.BigDecimal currValue;
	/**是否循环*/
	@Excel(name = "是否循环", width = 15)
    @ApiModelProperty(value = "是否循环")
	private String isCycle;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**机构编号*/
	@Excel(name = "机构编号", width = 15)
    @ApiModelProperty(value = "机构编号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String deptId;
	/**终止操作员编号*/
	@Excel(name = "终止操作员编号", width = 15)
    @ApiModelProperty(value = "终止操作员编号")
	private String endUserId;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**合同状态*/
	@Excel(name = "合同状态", width = 15)
    @ApiModelProperty(value = "合同状态")
	private String contractStatus;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**原系统标识*/
	@Excel(name = "原系统标识", width = 15)
    @ApiModelProperty(value = "原系统标识")
	private String sourceId;
	/**loadType*/
	@Excel(name = "loadType", width = 15)
    @ApiModelProperty(value = "loadType")
	private String loadType;
	/**legalNo*/
	@Excel(name = "legalNo", width = 15)
    @ApiModelProperty(value = "legalNo")
	private String legalNo;
}
