package org.cmms.modules.sjxf.qtxt.cwglxt.jydsxxb.entity;

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
 * @Description: 交易对手信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_cpty_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_cpty_info对象", description="交易对手信息表")
public class Jydsxxb {
    
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String version;
	/**组织机构代码证*/
	@Excel(name = "组织机构代码证", width = 15)
    @ApiModelProperty(value = "组织机构代码证")
	private String ogzCode;
	/**交易对手名称*/
	@Excel(name = "交易对手名称", width = 15)
    @ApiModelProperty(value = "交易对手名称")
	private String ctpyName;
	/**交易对手种类*/
	@Excel(name = "交易对手种类", width = 15)
    @ApiModelProperty(value = "交易对手种类")
	private String ctpyBustype;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
	private String operatLic;
	/**金融许可证*/
	@Excel(name = "金融许可证", width = 15)
    @ApiModelProperty(value = "金融许可证")
	private String financialLic;
	/**税务登记证*/
	@Excel(name = "税务登记证", width = 15)
    @ApiModelProperty(value = "税务登记证")
	private String taxRegCertf;
	/**法人代表名称*/
	@Excel(name = "法人代表名称", width = 15)
    @ApiModelProperty(value = "法人代表名称")
	private String legalName;
	/**法人代表身份证*/
	@Excel(name = "法人代表身份证", width = 15)
    @ApiModelProperty(value = "法人代表身份证")
	private String legalCardid;
	/**上级机构授权*/
	@Excel(name = "上级机构授权", width = 15)
    @ApiModelProperty(value = "上级机构授权")
	private String agenAuth;
	/**资产总额(元)*/
	@Excel(name = "资产总额(元)", width = 15)
    @ApiModelProperty(value = "资产总额(元)")
	private java.math.BigDecimal totalAssets;
	/**负债总额(元)*/
	@Excel(name = "负债总额(元)", width = 15)
    @ApiModelProperty(value = "负债总额(元)")
	private java.math.BigDecimal totalLiabts;
	/**所有者权益总额(元)*/
	@Excel(name = "所有者权益总额(元)", width = 15)
    @ApiModelProperty(value = "所有者权益总额(元)")
	private java.math.BigDecimal totalStocks;
	/**股本金总额(元)*/
	@Excel(name = "股本金总额(元)", width = 15)
    @ApiModelProperty(value = "股本金总额(元)")
	private java.math.BigDecimal totalEquity;
	/**存款总额(元)*/
	@Excel(name = "存款总额(元)", width = 15)
    @ApiModelProperty(value = "存款总额(元)")
	private java.math.BigDecimal totalDeposits;
	/**资本充足率(%)*/
	@Excel(name = "资本充足率(%)", width = 15)
    @ApiModelProperty(value = "资本充足率(%)")
	private java.math.BigDecimal capAdeqyRatio;
	/**托管账户户名*/
	@Excel(name = "托管账户户名", width = 15)
    @ApiModelProperty(value = "托管账户户名")
	private String dpbkName;
	/**托管机构*/
	@Excel(name = "托管机构", width = 15)
    @ApiModelProperty(value = "托管机构")
	private String dpbkBrno;
	/**托管账号*/
	@Excel(name = "托管账号", width = 15)
    @ApiModelProperty(value = "托管账号")
	private String dpbkAcno;
	/**资信状况*/
	@Excel(name = "资信状况", width = 15)
    @ApiModelProperty(value = "资信状况")
	private String creditStatus;
	/**主体评级*/
	@Excel(name = "主体评级", width = 15)
    @ApiModelProperty(value = "主体评级")
	private String mainlv;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String addres;
	/**主维护部门*/
	@Excel(name = "主维护部门", width = 15)
    @ApiModelProperty(value = "主维护部门")
	private String depNo;
	/**主维护联社*/
	@Excel(name = "主维护联社", width = 15)
    @ApiModelProperty(value = "主维护联社")
	private String upBrNo;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**交易对手类型*/
	@Excel(name = "交易对手类型", width = 15)
    @ApiModelProperty(value = "交易对手类型")
	private String ctpyType;
	/**交易对手子类*/
	@Excel(name = "交易对手子类", width = 15)
    @ApiModelProperty(value = "交易对手子类")
	private String categoryType;
	/**区域类型*/
	@Excel(name = "区域类型", width = 15)
    @ApiModelProperty(value = "区域类型")
	private String placeFlag;
	/**是否债券承销商*/
	@Excel(name = "是否债券承销商", width = 15)
    @ApiModelProperty(value = "是否债券承销商")
	private String isBondseller;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
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
