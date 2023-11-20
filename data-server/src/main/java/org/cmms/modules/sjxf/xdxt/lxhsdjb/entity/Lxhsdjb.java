package org.cmms.modules.sjxf.xdxt.lxhsdjb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 利息回收登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_lxhsdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_lxhsdjb对象", description="利息回收登记簿")
public class Lxhsdjb {

	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**货币号*/
	@Excel(name = "货币号", width = 15)
    @ApiModelProperty(value = "货币号")
	private String hbh;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgm;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**正常形态贷款帐号*/
	@Excel(name = "正常形态贷款帐号", width = 15)
    @ApiModelProperty(value = "正常形态贷款帐号")
	private String zh;
	/**对应账号*/
	@Excel(name = "对应账号", width = 15)
    @ApiModelProperty(value = "对应账号")
	private String zh1;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String jdbz;
	/**对应科目*/
	@Excel(name = "对应科目", width = 15)
    @ApiModelProperty(value = "对应科目")
	private String dykm;
	/**对应账号*/
	@Excel(name = "对应账号", width = 15)
    @ApiModelProperty(value = "对应账号")
	private String dyzh;
	/**起息日*/
	@Excel(name = "起息日", width = 15)
    @ApiModelProperty(value = "起息日")
	private String qxr;
	/**结息日*/
	@Excel(name = "结息日", width = 15)
    @ApiModelProperty(value = "结息日")
	private String jxr;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal ll;
	/**积数*/
	@Excel(name = "积数", width = 15)
    @ApiModelProperty(value = "积数")
	private java.math.BigDecimal js;
	/**日数*/
	@Excel(name = "日数", width = 15)
    @ApiModelProperty(value = "日数")
	private String rs;
	/**收息日*/
	@Excel(name = "收息日", width = 15)
    @ApiModelProperty(value = "收息日")
	private String sxr;
	/**入帐标志*/
	@Excel(name = "入帐标志", width = 15)
    @ApiModelProperty(value = "入帐标志")
	private String rzbz;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String re1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String re2;
	/**四级分类*/
	@Excel(name = "四级分类", width = 15)
    @ApiModelProperty(value = "四级分类")
	private String sjfl;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**核心借据号*/
	@Excel(name = "核心借据号", width = 15)
    @ApiModelProperty(value = "核心借据号")
	private String jjh;
	/**信贷借据号*/
	@Excel(name = "信贷借据号", width = 15)
    @ApiModelProperty(value = "信贷借据号")
	private String voucherNo;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal lx;
	/**合计利息*/
	@Excel(name = "合计利息", width = 15)
    @ApiModelProperty(value = "合计利息")
	private java.math.BigDecimal hjlx;
	/**贷款本金*/
	@Excel(name = "贷款本金", width = 15)
    @ApiModelProperty(value = "贷款本金")
	private java.math.BigDecimal dkbj;
	/**入账标志*/
	@Excel(name = "入账标志", width = 15)
    @ApiModelProperty(value = "入账标志")
	private String shouldPayTerm;
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
