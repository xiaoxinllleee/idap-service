package org.cmms.modules.sjxf.qtxt.zhyw.wxqyxxb.entity;

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
 * @Description: 微信签约信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Fbuss_tz_wxqyxxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Fbuss_tz_wxqyxxdjb对象", description="微信签约信息表")
public class Wxqyxxb {
    
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String acctno;
	/**签约账号或卡号*/
	@Excel(name = "签约账号或卡号", width = 15)
    @ApiModelProperty(value = "签约账号或卡号")
	private String signno;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String customname;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String certtype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String certno;
	/**开通类型*/
	@Excel(name = "开通类型", width = 15)
    @ApiModelProperty(value = "开通类型")
	private String noticeflag;
	/**签约机构*/
	@Excel(name = "签约机构", width = 15)
    @ApiModelProperty(value = "签约机构")
	private String signbankno;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String signdate;
	/**解约机构*/
	@Excel(name = "解约机构", width = 15)
    @ApiModelProperty(value = "解约机构")
	private String rescbanno;
	/**解约日期*/
	@Excel(name = "解约日期", width = 15)
    @ApiModelProperty(value = "解约日期")
	private String rescdate;
	/**控制标志*/
	@Excel(name = "控制标志", width = 15)
    @ApiModelProperty(value = "控制标志")
	private String controlflag;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
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
	/**旧签约账号或卡号*/
	@Excel(name = "旧签约账号或卡号", width = 15)
    @ApiModelProperty(value = "旧签约账号或卡号")
	private String oldsignno;
	/**旧账号*/
	@Excel(name = "旧账号", width = 15)
    @ApiModelProperty(value = "旧账号")
	private String oldacctno;
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
