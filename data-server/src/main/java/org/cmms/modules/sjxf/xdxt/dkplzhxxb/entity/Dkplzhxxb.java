package org.cmms.modules.sjxf.xdxt.dkplzhxxb.entity;

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
 * @Description: 贷款批量置换信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ci_replace_bat_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ci_replace_bat_info对象", description="贷款批量置换信息表")
public class Dkplzhxxb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**明细序号*/
	@Excel(name = "明细序号", width = 15)
    @ApiModelProperty(value = "明细序号")
	private String infoId;
	/**启动序号*/
	@Excel(name = "启动序号", width = 15)
    @ApiModelProperty(value = "启动序号")
	private String startId;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**置换本金*/
	@Excel(name = "置换本金", width = 15)
    @ApiModelProperty(value = "置换本金")
	private String principal;
	/**置换利息*/
	@Excel(name = "置换利息", width = 15)
    @ApiModelProperty(value = "置换利息")
	private String interest;
	/**置换标志*/
	@Excel(name = "置换标志", width = 15)
    @ApiModelProperty(value = "置换标志")
	private String zhbz;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
