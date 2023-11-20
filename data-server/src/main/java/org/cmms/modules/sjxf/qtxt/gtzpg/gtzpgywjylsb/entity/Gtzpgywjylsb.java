package org.cmms.modules.sjxf.qtxt.gtzpg.gtzpgywjylsb.entity;

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
 * @Description: 国土招拍挂业务交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_zpg_dzzhjfdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_zpg_dzzhjfdjb对象", description="国土招拍挂业务交易流水表")
public class Gtzpgywjylsb {
    
	/**中间业务类型*/
	@Excel(name = "中间业务类型", width = 15)
    @ApiModelProperty(value = "中间业务类型")
	private String zjywlx;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String dljgm;
	/**竞买号*/
	@Excel(name = "竞买号", width = 15)
    @ApiModelProperty(value = "竞买号")
	private String jmh;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String jysj;
	/**中心流水号*/
	@Excel(name = "中心流水号", width = 15)
    @ApiModelProperty(value = "中心流水号")
	private String zxlsh;
	/**柜员流水号*/
	@Excel(name = "柜员流水号", width = 15)
    @ApiModelProperty(value = "柜员流水号")
	private String gylsh;
	/**前置机流水号*/
	@Excel(name = "前置机流水号", width = 15)
    @ApiModelProperty(value = "前置机流水号")
	private String qzjlsh;
	/**付款人*/
	@Excel(name = "付款人", width = 15)
    @ApiModelProperty(value = "付款人")
	private String fkr;
	/**付款人帐号*/
	@Excel(name = "付款人帐号", width = 15)
    @ApiModelProperty(value = "付款人帐号")
	private String fkrzh;
	/**付款人户名*/
	@Excel(name = "付款人户名", width = 15)
    @ApiModelProperty(value = "付款人户名")
	private String fkrhm;
	/**缴款方式*/
	@Excel(name = "缴款方式", width = 15)
    @ApiModelProperty(value = "缴款方式")
	private String jkfs;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String jyje;
	/**收款帐号*/
	@Excel(name = "收款帐号", width = 15)
    @ApiModelProperty(value = "收款帐号")
	private String skzh;
	/**子帐号*/
	@Excel(name = "子帐号", width = 15)
    @ApiModelProperty(value = "子帐号")
	private String zzh;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String gy;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String sqgy;
	/**标志*/
	@Excel(name = "标志", width = 15)
    @ApiModelProperty(value = "标志")
	private String flag;
	/**核心返回流水号*/
	@Excel(name = "核心返回流水号", width = 15)
    @ApiModelProperty(value = "核心返回流水号")
	private String hostseqno;
	/**渠道流水号*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String chnlseqno;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnldate;
	/**主机返回日期*/
	@Excel(name = "主机返回日期", width = 15)
    @ApiModelProperty(value = "主机返回日期")
	private String hostdate;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
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
