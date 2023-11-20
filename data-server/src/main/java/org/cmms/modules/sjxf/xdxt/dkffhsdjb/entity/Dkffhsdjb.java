package org.cmms.modules.sjxf.xdxt.dkffhsdjb.entity;

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
 * @Description: 贷款发放/回收登记薄
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ddkffhsdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ddkffhsdjb对象", description="贷款发放/回收登记薄")
public class Dkffhsdjb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**借方发生额*/
	@Excel(name = "借方发生额", width = 15)
    @ApiModelProperty(value = "借方发生额")
	private java.math.BigDecimal jffse;
	/**贷方发生额*/
	@Excel(name = "贷方发生额", width = 15)
    @ApiModelProperty(value = "贷方发生额")
	private java.math.BigDecimal dffse;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String zh;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**借据号*/
	@Excel(name = "借据号", width = 15)
    @ApiModelProperty(value = "借据号")
	private String jjh;
	/**发放/回收日期*/
	@Excel(name = "发放/回收日期", width = 15)
    @ApiModelProperty(value = "发放/回收日期")
	private String rq;
	/**本金*/
	@Excel(name = "本金", width = 15)
    @ApiModelProperty(value = "本金")
	private java.math.BigDecimal bj;
	/**柜员*/
	@Excel(name = "柜员", width = 15)
    @ApiModelProperty(value = "柜员")
	private String gy;
	/**标志*/
	@Excel(name = "标志", width = 15)
    @ApiModelProperty(value = "标志")
	private String bz;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**四级分类*/
	@Excel(name = "四级分类", width = 15)
    @ApiModelProperty(value = "四级分类")
	private String sjfl;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String ret1;
	/**借据号*/
	@Excel(name = "借据号", width = 15)
    @ApiModelProperty(value = "借据号")
	private String voucherNo;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String ret2;
	/**中心流水号*/
	@Excel(name = "中心流水号", width = 15)
    @ApiModelProperty(value = "中心流水号")
	private String zxlsh;
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
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
