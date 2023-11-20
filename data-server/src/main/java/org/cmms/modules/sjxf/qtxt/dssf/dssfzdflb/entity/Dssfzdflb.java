package org.cmms.modules.sjxf.qtxt.dssf.dssfzdflb.entity;

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
 * @Description: 代收水费自动分录表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dssf_zdflb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dssf_zdflb对象", description="代收水费自动分录表")
public class Dssfzdflb {
    
	/**地市代码*/
	@Excel(name = "地市代码", width = 15)
    @ApiModelProperty(value = "地市代码")
	private String dsdm;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**过渡账号*/
	@Excel(name = "过渡账号", width = 15)
    @ApiModelProperty(value = "过渡账号")
	private String flzh;
	/**主办行机构码*/
	@Excel(name = "主办行机构码", width = 15)
    @ApiModelProperty(value = "主办行机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zbhjgm;
	/**账户性质*/
	@Excel(name = "账户性质", width = 15)
    @ApiModelProperty(value = "账户性质")
	private String zhxz;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String czgy;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String djrq;
	/**归集账号*/
	@Excel(name = "归集账号", width = 15)
    @ApiModelProperty(value = "归集账号")
	private String remark;
	/**过渡账户名称*/
	@Excel(name = "过渡账户名称", width = 15)
    @ApiModelProperty(value = "过渡账户名称")
	private String remark1;
	/**归集账户名称*/
	@Excel(name = "归集账户名称", width = 15)
    @ApiModelProperty(value = "归集账户名称")
	private String remark2;
	/**主办行机构名称*/
	@Excel(name = "主办行机构名称", width = 15)
    @ApiModelProperty(value = "主办行机构名称")
	private String zbhjgmc;
	/**旧过渡账号*/
	@Excel(name = "旧过渡账号", width = 15)
    @ApiModelProperty(value = "旧过渡账号")
	private String oldFlzh;
	/**旧归集账号*/
	@Excel(name = "旧归集账号", width = 15)
    @ApiModelProperty(value = "旧归集账号")
	private String oldRemark;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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
