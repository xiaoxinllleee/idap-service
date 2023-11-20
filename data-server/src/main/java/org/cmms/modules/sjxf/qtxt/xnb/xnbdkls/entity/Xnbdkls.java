package org.cmms.modules.sjxf.qtxt.xnb.xnbdkls.entity;

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
 * @Description: 新农保代扣流水
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_xnbmdkpayjnl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_xnbmdkpayjnl对象", description="新农保代扣流水")
public class Xnbdkls {
    
	/**平台受理日期*/
	@Excel(name = "平台受理日期", width = 15)
    @ApiModelProperty(value = "平台受理日期")
	private String workdate;
	/**平台业务序号*/
	@Excel(name = "平台业务序号", width = 15)
    @ApiModelProperty(value = "平台业务序号")
	private String workseqid;
	/**平台受理时间*/
	@Excel(name = "平台受理时间", width = 15)
    @ApiModelProperty(value = "平台受理时间")
	private String worktime;
	/**操作网点*/
	@Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
	private String brno;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerno;
	/**文件类型*/
	@Excel(name = "文件类型", width = 15)
    @ApiModelProperty(value = "文件类型")
	private String filetype;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15)
    @ApiModelProperty(value = "操作类型")
	private String opertype;
	/**社保机构代码*/
	@Excel(name = "社保机构代码", width = 15)
    @ApiModelProperty(value = "社保机构代码")
	private String socialorgcode;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String contractno;
	/**代扣账户账号*/
	@Excel(name = "代扣账户账号", width = 15)
    @ApiModelProperty(value = "代扣账户账号")
	private String acctno;
	/**缴费人员序号*/
	@Excel(name = "缴费人员序号", width = 15)
    @ApiModelProperty(value = "缴费人员序号")
	private String payseqno;
	/**缴费人员姓名*/
	@Excel(name = "缴费人员姓名", width = 15)
    @ApiModelProperty(value = "缴费人员姓名")
	private String truename;
	/**缴费人员身份证号*/
	@Excel(name = "缴费人员身份证号", width = 15)
    @ApiModelProperty(value = "缴费人员身份证号")
	private String identno;
	/**险种编码*/
	@Excel(name = "险种编码", width = 15)
    @ApiModelProperty(value = "险种编码")
	private String socialkind;
	/**缴费金额*/
	@Excel(name = "缴费金额", width = 15)
    @ApiModelProperty(value = "缴费金额")
	private String payamt;
	/**行政区划*/
	@Excel(name = "行政区划", width = 15)
    @ApiModelProperty(value = "行政区划")
	private String xzqh;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String address;
	/**反馈交易类型*/
	@Excel(name = "反馈交易类型", width = 15)
    @ApiModelProperty(value = "反馈交易类型")
	private String backtype;
	/**反馈交易信息*/
	@Excel(name = "反馈交易信息", width = 15)
    @ApiModelProperty(value = "反馈交易信息")
	private String backmsg;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
	private String filename;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String filepath;
	/**回执文件*/
	@Excel(name = "回执文件", width = 15)
    @ApiModelProperty(value = "回执文件")
	private String backfile;
	/**回执路径*/
	@Excel(name = "回执路径", width = 15)
    @ApiModelProperty(value = "回执路径")
	private String backpath;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserved1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String reserved2;
	/**预留3*/
	@Excel(name = "预留3", width = 15)
    @ApiModelProperty(value = "预留3")
	private String reserved3;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String batchno;
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
