package org.cmms.modules.sjxf.qtxt.kqzxt.shxxglb.entity;

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
 * @Description: 商户信息管理表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Cpps_bcpbmerchadm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cpps_bcpbmerchadm对象", description="商户信息管理表")
public class Shxxglbsb {
    
	/**商户号*/
	@Excel(name = "商户号", width = 15)
    @ApiModelProperty(value = "商户号")
	private String merchantno;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String merchantname;
	/**绑定账户号*/
	@Excel(name = "绑定账户号", width = 15)
    @ApiModelProperty(value = "绑定账户号")
	private String oldaccno;
	/**绑定账户号*/
	@Excel(name = "绑定账户号", width = 15)
    @ApiModelProperty(value = "绑定账户号")
	private String newaccno;
	/**账户户名*/
	@Excel(name = "账户户名", width = 15)
    @ApiModelProperty(value = "账户户名")
	private String acctname;
	/**账户开户机构*/
	@Excel(name = "账户开户机构", width = 15)
    @ApiModelProperty(value = "账户开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String accbrno;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserved1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String reserved2;
	/**预留3*/
	@Excel(name = "预留3", width = 15)
    @ApiModelProperty(value = "预留3")
	private String reserved3;
	/**zdh*/
	@Excel(name = "zdh", width = 15)
    @ApiModelProperty(value = "zdh")
	private String zdh;
	/**merchantmcc*/
	@Excel(name = "merchantmcc", width = 15)
    @ApiModelProperty(value = "merchantmcc")
	private String merchantmcc;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束如期*/
	@Excel(name = "数据结束如期", width = 15)
    @ApiModelProperty(value = "数据结束如期")
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
