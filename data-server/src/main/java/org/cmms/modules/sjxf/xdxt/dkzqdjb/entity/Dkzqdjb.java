package org.cmms.modules.sjxf.xdxt.dkzqdjb.entity;

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
 * @Description: 贷款展期登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ddkzqdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ddkzqdjb对象", description="贷款展期登记簿")
public class Dkzqdjb {

	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgm;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String zh;
	/**柜员*/
	@Excel(name = "柜员", width = 15)
    @ApiModelProperty(value = "柜员")
	private String gyh;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**上利率*/
	@Excel(name = "上利率", width = 15)
    @ApiModelProperty(value = "上利率")
	private java.math.BigDecimal sll;
	/**上到期日期*/
	@Excel(name = "上到期日期", width = 15)
    @ApiModelProperty(value = "上到期日期")
	private String sdqrq;
	/**展期利率*/
	@Excel(name = "展期利率", width = 15)
    @ApiModelProperty(value = "展期利率")
	private String zqll;
	/**展期到期日期*/
	@Excel(name = "展期到期日期", width = 15)
    @ApiModelProperty(value = "展期到期日期")
	private String zqrq;
	/**是否已展期*/
	@Excel(name = "是否已展期", width = 15)
    @ApiModelProperty(value = "是否已展期")
	private String bz;
	/**利息调整金额*/
	@Excel(name = "利息调整金额", width = 15)
    @ApiModelProperty(value = "利息调整金额")
	private java.math.BigDecimal lxtz;
	/**展期金额*/
	@Excel(name = "展期金额", width = 15)
    @ApiModelProperty(value = "展期金额")
	private java.math.BigDecimal zqje;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
