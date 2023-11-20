package org.cmms.modules.khgl.khxx.entity;

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
 * @Description: 网上银行
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Data
@TableName("KHYWXX_WSYH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHYWXX_WSYH对象", description="网上银行")
public class KhywxxWsyhPc {

	/**开户网点机构名称*/
	@Excel(name = "开户网点机构名称", width = 15)
    @ApiModelProperty(value = "开户网点机构名称")
	private String openJgmc;
	/**销户网点机构名称*/
	@Excel(name = "销户网点机构名称", width = 15)
    @ApiModelProperty(value = "销户网点机构名称")
	private String cancelJgmc;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String wykhbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date openDate;
	/**开户网点机构号*/
	@Excel(name = "开户网点机构号", width = 15)
    @ApiModelProperty(value = "开户网点机构号")
	private String openJgdm;
	/**开户柜员*/
	@Excel(name = "开户柜员", width = 15)
    @ApiModelProperty(value = "开户柜员")
	private String openGyh;
	/**开户类型(0-网银;1-手机银行)*/
	@Excel(name = "开户类型(0-网银;1-手机银行)", width = 15)
    @ApiModelProperty(value = "开户类型(0-网银;1-手机银行)")
	private String openType;
	/**开销户状态(1-销户;2-开户)*/
	@Excel(name = "开销户状态(1-销户;2-开户)", width = 15)
    @ApiModelProperty(value = "开销户状态(1-销户;2-开户)")
	private String status;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "销户日期")
	private Date cancelDate;
	/**销户网点机构号*/
	@Excel(name = "销户网点机构号", width = 15)
    @ApiModelProperty(value = "销户网点机构号")
	private String cancelJgdm;
	/**销户柜员*/
	@Excel(name = "销户柜员", width = 15)
    @ApiModelProperty(value = "销户柜员")
	private String cancelGyh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
}
