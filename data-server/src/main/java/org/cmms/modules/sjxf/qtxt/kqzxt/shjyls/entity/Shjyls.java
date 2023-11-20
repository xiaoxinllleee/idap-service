package org.cmms.modules.sjxf.qtxt.kqzxt.shjyls.entity;

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
 * @Description: 商户交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Cpps_bcprmerchdtl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cpps_bcprmerchdtl对象", description="商户交易流水")
public class Shjyls {
    
	/**流水日期*/
	@Excel(name = "流水日期", width = 15)
    @ApiModelProperty(value = "流水日期")
	private String cleardate;
	/**消息类型*/
	@Excel(name = "消息类型", width = 15)
    @ApiModelProperty(value = "消息类型")
	private String msgtype;
	/**交易处理码*/
	@Excel(name = "交易处理码", width = 15)
    @ApiModelProperty(value = "交易处理码")
	private String proccode;
	/**服务点条件码*/
	@Excel(name = "服务点条件码", width = 15)
    @ApiModelProperty(value = "服务点条件码")
	private String condcode;
	/**检索参考号*/
	@Excel(name = "检索参考号", width = 15)
    @ApiModelProperty(value = "检索参考号")
	private String indexrefno;
	/**商户编号*/
	@Excel(name = "商户编号", width = 15)
    @ApiModelProperty(value = "商户编号")
	private String merchantno;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String merchantname;
	/**终端编号*/
	@Excel(name = "终端编号", width = 15)
    @ApiModelProperty(value = "终端编号")
	private String termid;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String tradetime;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String tradeamount;
	/**手续费借贷标识*/
	@Excel(name = "手续费借贷标识", width = 15)
    @ApiModelProperty(value = "手续费借贷标识")
	private String dcflag;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private String fee;
	/**交易账号*/
	@Excel(name = "交易账号", width = 15)
    @ApiModelProperty(value = "交易账号")
	private String acctno;
	/**交易名称*/
	@Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
	private String tradename;
	/**lasttime*/
	@Excel(name = "lasttime", width = 15)
    @ApiModelProperty(value = "lasttime")
	private String lasttime;
	/**银联流水号*/
	@Excel(name = "银联流水号", width = 15)
    @ApiModelProperty(value = "银联流水号")
	private String ylseqno;
	/**发卡机构*/
	@Excel(name = "发卡机构", width = 15)
    @ApiModelProperty(value = "发卡机构")
	private String cardbrno;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**导入日期*/
    @ApiModelProperty(value = "导入日期")
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
