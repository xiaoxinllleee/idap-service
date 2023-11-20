package org.cmms.modules.sjxf.xdxt.dkhxqdb.entity;

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
 * @Description: 贷款核销启动表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ci_bad_debts_start")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ci_bad_debts_start对象", description="贷款核销启动表")
public class Dkhxqdb {
    
	/**dataFlag*/
	@Excel(name = "dataFlag", width = 15)
    @ApiModelProperty(value = "dataFlag")
	private String dataFlag;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String startId;
	/**启动贷款核销的模式*/
	@Excel(name = "启动贷款核销的模式", width = 15)
    @ApiModelProperty(value = "启动贷款核销的模式")
	private String startType;
	/**工作流ID*/
	@Excel(name = "工作流ID", width = 15)
    @ApiModelProperty(value = "工作流ID")
	private String flowId;
	/**客户ID*/
	@Excel(name = "客户ID", width = 15)
    @ApiModelProperty(value = "客户ID")
	private String custId;
	/**借据号*/
	@Excel(name = "借据号", width = 15)
    @ApiModelProperty(value = "借据号")
	private String voucherNo;
	/**五级分类损失贷款*/
	@Excel(name = "五级分类损失贷款", width = 15)
    @ApiModelProperty(value = "五级分类损失贷款")
	private String lose;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**部门编号*/
	@Excel(name = "部门编号", width = 15)
    @ApiModelProperty(value = "部门编号")
	private String orgId;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
