package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 保存担保
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_BZDB")
public class Bzdb implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	private String zjhm;

	/**担保人*/
    @Excel(name = "担保人", width = 15)
	private String dbr;
	/**资产*/
    @Excel(name = "资产", width = 15)
	private java.math.BigDecimal dbrzz;
	/**负债*/
    @Excel(name = "负债", width = 15)
	private java.math.BigDecimal dbrfz;
	/**负债中银行贷款*/
    @Excel(name = "负债中银行贷款", width = 15)
	private java.math.BigDecimal dbrfzzdk;
	/**经营盈利情况 1良好，2较好，3一般，4差*/
    @Excel(name = "经营盈利情况 1良好，2较好，3一般，4差", width = 15)
	private String dbrylqk;

	/**担保人身份证号*/
	@Excel(name = "担保人身份证号", width = 15)
	@ApiModelProperty(value = "担保人身份证号")
	private String dbrzjhm;
	/**担保人联系方式*/
	@Excel(name = "担保人联系方式", width = 15)
	@ApiModelProperty(value = "担保人联系方式")
	private String dbrlxfs;
	/**担保人地址*/
	@Excel(name = "担保人地址", width = 15)
	@ApiModelProperty(value = "担保人地址")
	private String dbrdz;
	/**担保人性别*/
	@Excel(name = "担保人性别", width = 15)
	@ApiModelProperty(value = "担保人性别")
	private String dbrxb;
	/**担保人婚姻状况*/
	@Excel(name = "担保人婚姻状况", width = 15)
	@ApiModelProperty(value = "担保人婚姻状况")
	private String dbrhyzk;
	/**担保人配偶姓名*/
	@Excel(name = "担保人配偶姓名", width = 15)
	@ApiModelProperty(value = "担保人配偶姓名")
	private String dbrpoxm;
	/**担保人配偶身份证号*/
	@Excel(name = "担保人配偶身份证号", width = 15)
	@ApiModelProperty(value = "担保人配偶身份证号")
	private String dbrpozjhm;
	/**担保人配偶联系方式*/
	@Excel(name = "担保人配偶联系方式", width = 15)
	@ApiModelProperty(value = "担保人配偶联系方式")
	private String dbrpolxfs;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
}
