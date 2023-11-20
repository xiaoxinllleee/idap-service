package org.cmms.modules.sjxf.qtxt.czzb.czhnzdflb.entity;

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
 * @Description: 财政惠农字段分录表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_czzb_zdflb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_czzb_zdflb对象", description="财政惠农字段分录表")
public class Czhnzdflb {
    
	/**县级行政区划编码*/
	@Excel(name = "县级行政区划编码", width = 15)
    @ApiModelProperty(value = "县级行政区划编码")
	private String xzqbm;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**分录账号*/
	@Excel(name = "分录账号", width = 15)
    @ApiModelProperty(value = "分录账号")
	private String flzh;
	/**主办行机构码*/
	@Excel(name = "主办行机构码", width = 15)
    @ApiModelProperty(value = "主办行机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zbhjgm;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String czgy;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String djrq;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**账户名称*/
	@Excel(name = "账户名称", width = 15)
    @ApiModelProperty(value = "账户名称")
	private String zhmc;
	/**账户性质*/
	@Excel(name = "账户性质", width = 15)
    @ApiModelProperty(value = "账户性质")
	private String zhxz;
	/**分录账号标识*/
	@Excel(name = "分录账号标识", width = 15)
    @ApiModelProperty(value = "分录账号标识")
	private String flzhbz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark1;
	/**旧分录账号*/
	@Excel(name = "旧分录账号", width = 15)
    @ApiModelProperty(value = "旧分录账号")
	private String jflzh;
	/**旧备注*/
	@Excel(name = "旧备注", width = 15)
    @ApiModelProperty(value = "旧备注")
	private String jremark1;
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
