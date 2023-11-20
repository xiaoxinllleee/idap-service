package org.cmms.modules.rwzx.rwmxsj.entity;

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
 * @Description: 贷款流失户明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("v_TASK_RWMX_DKLSH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_TASK_RWMX_DKLSH对象", description="贷款流失户明细")
public class TaskRwmxDklsh {
    
	/**任务id*/
	@Excel(name = "任务id", width = 15)
    @ApiModelProperty(value = "任务id")
	private java.lang.String rwid;
	/**对象类型*/
	@Excel(name = "对象类型", width = 15,dicCode = "dxlx")
	@ApiModelProperty(value = "对象类型")
	@Dict(dicCode = "dxlx")
	private String dxlx;

	/**对象ID*/
	@Excel(name = "对象ID", width = 15)
	@ApiModelProperty(value = "对象ID")
	private String dxid;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	@ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private String wgbh;

	/**原组织标志*/
	@Excel(name = "原支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "原支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private java.lang.String zzbz;
	/**原机构代码*//*
	@Excel(name = "原机构代码", width = 15)
    @ApiModelProperty(value = "原机构代码")
	private java.lang.String brNo;*/
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private java.lang.String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private java.lang.String identNo;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	private java.lang.String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
	@ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同起始日期*/
	@Excel(name = "合同起始日期", width = 15)
	@ApiModelProperty(value = "合同起始日期")
	private java.lang.String htqsrq;
	/**合同结束日期*/
	@Excel(name = "合同结束日期", width = 15)
	@ApiModelProperty(value = "合同结束日期")
	private java.lang.String htdqrq;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
	@ApiModelProperty(value = "签约日期")
	private java.lang.String qyrq;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs")
	@ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
	private java.lang.String dbfs;
	/**借款原因*/
	@Excel(name = "借款原因", width = 15)
	@ApiModelProperty(value = "借款原因")
	private java.lang.String jkyy;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
	@ApiModelProperty(value = "第一责任人")
	private java.lang.String dyzrr;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
	@ApiModelProperty(value = "电话号码")
	private java.lang.String dhhm;

	/**备用号码*/
	@Excel(name = "备用号码", width = 15)
	@ApiModelProperty(value = "备用号码")
	private String byhm;

	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String sjhm;

	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
	@ApiModelProperty(value = "常住地址")
	private String zz;
	
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;

	/**类型*/
	@Excel(name = "类型", width = 15,dicCode = "khlx1")
	@ApiModelProperty(value = "类型")
	@Dict(dicCode = "khlx1")
	private String lx;


	/**客户等级*/
	@Excel(name = "客户等级", width = 15,dicCode = "khdj_yx")
	@ApiModelProperty(value = "客户等级")
	@Dict(dicCode = "khdj_yx")
	private String khdj;

	/**名单情况*/
	@Excel(name = "名单情况", width = 15, dicCode = "xtpdjg")
	@ApiModelProperty(value = "名单情况")
	@Dict(dicCode = "xtpdjg")
	private java.lang.String khlx;

	/**户籍所在网格*/
	@Excel(name = "户籍所在网格", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "户籍所在网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String hjszwg;


	/**户籍所属支行*/
	@Excel(name = "户籍所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "户籍所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String hjsszh;

	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;

	/**数据时间*/
	@Excel(name = "数据时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据时间")
	private java.util.Date createTime;

	@TableField(exist = false)
	private String ssyxdy;

	@TableField("KHLX2YX")
	@Dict(dicCode = "yx_khlx2")
	private String khlx2yx;

	@TableField(value = "zfcs")
	private Integer zfcs;
}
