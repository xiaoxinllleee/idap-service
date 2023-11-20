package org.cmms.modules.sjxf.xdxt.khsqxx.entity;

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
 * @Description: 客户授权信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_cust_preview")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_cust_preview对象", description="客户授权信息")
public class Khsqxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	@TableField(value = "cust_preview_id")
	private String custPreviewId;
	/**所在机构编号*/
	@Excel(name = "所在机构编号", width = 15)
    @ApiModelProperty(value = "所在机构编号")
	private String deptId;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String endDate;
	/**托管状态标志*/
	@Excel(name = "托管状态标志", width = 15)
    @ApiModelProperty(value = "托管状态标志")
	private String entrust;
	/**是否可用*/
	@Excel(name = "是否可用", width = 15)
    @ApiModelProperty(value = "是否可用")
	private String isEnabled;
	/**是否主客户经理*/
	@Excel(name = "是否主客户经理", width = 15)
    @ApiModelProperty(value = "是否主客户经理")
	private String isMaster;
	/**起始日期*/
	@Excel(name = "起始日期", width = 15)
    @ApiModelProperty(value = "起始日期")
	private String startDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**是否注册机构*/
	@Excel(name = "是否注册机构", width = 15)
    @ApiModelProperty(value = "是否注册机构")
	private String isRegOrg;
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
