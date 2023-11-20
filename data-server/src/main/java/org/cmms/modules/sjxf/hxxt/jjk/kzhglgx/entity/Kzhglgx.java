package org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity;

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
 * @Description: 卡账号关联关系
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbsc_link")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbsc_link对象", description="卡账号关联关系")
public class Kzhglgx {
    
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String card;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String isoType;
	/**账户序号(定期一本通账户)*/
	@Excel(name = "账户序号(定期一本通账户)", width = 15)
    @ApiModelProperty(value = "账户序号(定期一本通账户)")
	private String category;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String account;
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private Integer inst;
	/**是否是eChannel(电话银行/网银)默认账户*/
	@Excel(name = "是否是eChannel(电话银行/网银)默认账户", width = 15)
    @ApiModelProperty(value = "是否是eChannel(电话银行/网银)默认账户")
	private String isPrimary;
	/**电话银行/网银*/
	@Excel(name = "电话银行/网银", width = 15)
    @ApiModelProperty(value = "电话银行/网银")
	private String sequence;
	/**电话银行/网银账户产品类型*/
	@Excel(name = "电话银行/网银账户产品类型", width = 15)
    @ApiModelProperty(value = "电话银行/网银账户产品类型")
	private Integer hostType;
	/**电话银行/网银账户产品子类*/
	@Excel(name = "电话银行/网银账户产品子类", width = 15)
    @ApiModelProperty(value = "电话银行/网银账户产品子类")
	private Integer hostCat;
	/**关联类型*/
	@Excel(name = "关联类型", width = 15)
    @ApiModelProperty(value = "关联类型")
	private Integer channel;
	/**账户币种*/
	@Excel(name = "账户币种", width = 15)
    @ApiModelProperty(value = "账户币种")
	private String currency;
	/**最近维护日期*/
    @ApiModelProperty(value = "最近维护日期")
	private Date lastMaintainDate;
	/**最近维护标识*/
	@Excel(name = "最近维护标识", width = 15)
    @ApiModelProperty(value = "最近维护标识")
	private String lastMaintainFlag;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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
/*	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
