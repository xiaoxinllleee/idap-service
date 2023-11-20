package org.cmms.modules.sjxf.xdxt.dkplzhqdb.entity;

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
 * @Description: 贷款批量置换启动表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_replace_bat_start")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_replace_bat_start对象", description="贷款批量置换启动表")
public class Dkplzhqdb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**启动序号*/
	@Excel(name = "启动序号", width = 15)
    @ApiModelProperty(value = "启动序号")
	private String startId;
	/**启动时间*/
	@Excel(name = "启动时间", width = 15)
    @ApiModelProperty(value = "启动时间")
	private String startDate;
	/**流程流水编号*/
	@Excel(name = "流程流水编号", width = 15)
    @ApiModelProperty(value = "流程流水编号")
	private String flowId;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**机构编号*/
	@Excel(name = "机构编号", width = 15)
    @ApiModelProperty(value = "机构编号")
	private String orgId;
	/**置换笔数*/
	@Excel(name = "置换笔数", width = 15)
    @ApiModelProperty(value = "置换笔数")
	private String offNum;
	/**置换标志*/
	@Excel(name = "置换标志", width = 15)
    @ApiModelProperty(value = "置换标志")
	private String zhbz;
	/**置换方式*/
	@Excel(name = "置换方式", width = 15)
    @ApiModelProperty(value = "置换方式")
	private String replaceType;
	/**置换本金合计*/
	@Excel(name = "置换本金合计", width = 15)
    @ApiModelProperty(value = "置换本金合计")
	private String offPrincipal;
	/**置换利息合计*/
	@Excel(name = "置换利息合计", width = 15)
    @ApiModelProperty(value = "置换利息合计")
	private String offInterest;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**置换协议号*/
	@Excel(name = "置换协议号", width = 15)
    @ApiModelProperty(value = "置换协议号")
	private String protocolNo;
	/**通知书号*/
	@Excel(name = "通知书号", width = 15)
    @ApiModelProperty(value = "通知书号")
	private String noticeNo;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
	private String fileName;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
