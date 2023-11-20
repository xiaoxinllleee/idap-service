package org.cmms.modules.sjxf.qtxt.dsxf.dsxfxxxxb.entity;

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
 * @Description: 代收学费学校信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dsxfflb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dsxfflb对象", description="代收学费学校信息表")
public class Dsxfxxxxb {
    
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String ywzl;
	/**学校编码*/
	@Excel(name = "学校编码", width = 15)
    @ApiModelProperty(value = "学校编码")
	private String xxbm;
	/**学校名称*/
	@Excel(name = "学校名称", width = 15)
    @ApiModelProperty(value = "学校名称")
	private String xxmc;
	/**收款单位帐号*/
	@Excel(name = "收款单位帐号", width = 15)
    @ApiModelProperty(value = "收款单位帐号")
	private String skdwzh;
	/**收款单位帐号名称*/
	@Excel(name = "收款单位帐号名称", width = 15)
    @ApiModelProperty(value = "收款单位帐号名称")
	private String skdwzhmc;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgm;
	/**开通标志*/
	@Excel(name = "开通标志", width = 15)
    @ApiModelProperty(value = "开通标志")
	private String flag;
	/**查询标志*/
	@Excel(name = "查询标志", width = 15)
    @ApiModelProperty(value = "查询标志")
	private String cxflag;
	/**旧收款单位帐号*/
	@Excel(name = "旧收款单位帐号", width = 15)
    @ApiModelProperty(value = "旧收款单位帐号")
	private String jskdwzh;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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
