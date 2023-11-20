package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-01
 * @Version: V1.0
 */
@Data
@TableName("khywxx_etc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khywxx_etc对象", description="1")
public class KhywxxEtc {
    
	/**绑定日期*/
	@Excel(name = "绑定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "绑定日期")
	private Date workDate;
	/**操作网点*/
	@Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
	private String operJgdm;
	/**操作人员*/
	@Excel(name = "操作人员", width = 15)
    @ApiModelProperty(value = "操作人员")
	private String operYggh;
	/**开户机构名称*/
	@Excel(name = "开户机构名称", width = 15)
    @ApiModelProperty(value = "开户机构名称")
	private String openJgmc;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**账户类型(0-贷记卡;1-借记卡;2-活期账户)*/
	@Excel(name = "账户类型(0-贷记卡;1-借记卡;2-活期账户)", width = 15)
    @ApiModelProperty(value = "账户类型(0-贷记卡;1-借记卡;2-活期账户)")
	private String zhlx;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String openJgdm;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**状态(1-正常;2-预解绑)*/
	@Excel(name = "状态(1-正常;2-预解绑)", width = 15)
    @ApiModelProperty(value = "状态(1-正常;2-预解绑)")
	private String status;
	/**预解绑日期*/
	@Excel(name = "预解绑日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预解绑日期")
	private Date yjbrq;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
}
