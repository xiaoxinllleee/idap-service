package org.cmms.modules.jx.dkyw.entity;

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
 * @Description: 贷款客户营销登记簿
 * @Author: jeecg-boot
 * @Date:   2022-03-05
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_DKKHYXDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_DKKHYXDJB对象", description="贷款客户营销登记簿")
public class KhgxglDkkhyxdjb {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	private String dkzl;
	/**客户种类*/
	@Excel(name = "客户种类", width = 15)
    @ApiModelProperty(value = "客户种类")
	private Integer khzl;
	/**证件类别*/
	@Excel(name = "证件类别", width = 15)
    @ApiModelProperty(value = "证件类别")
	private Integer zjlb;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**营销比例*/
	@Excel(name = "营销比例", width = 15)
    @ApiModelProperty(value = "营销比例")
	private java.math.BigDecimal yxbl;
	/**申报状态*/
	@Excel(name = "申报状态", width = 15)
    @ApiModelProperty(value = "申报状态")
	private Integer sbzt;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**预约日期*/
	@Excel(name = "预约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预约日期")
	private Date yyrq;
	/**预约编号*/
	@Excel(name = "预约编号", width = 15)
    @ApiModelProperty(value = "预约编号")
	private Long yybh;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
}
