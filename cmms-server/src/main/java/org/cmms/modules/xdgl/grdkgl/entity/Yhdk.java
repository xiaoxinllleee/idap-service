package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 银行贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_YHDK")
public class Yhdk implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	private String zjhm;
	/**贷款行*/
    @Excel(name = "贷款行", width = 15)
	private String dkyh;
	/**("贷款账号" 字段无用,作为 "到期时间" 使用)*/
	@Excel(name = "到期时间", width = 15)
	private String dkzh;
	/**金额*/
    @Excel(name = "金额", width = 15)
	private java.math.BigDecimal dkje;

	@Excel(name = "余额", width = 15)
	private java.math.BigDecimal dkye;
	/**担保物或担保人*/
    @Excel(name = "担保物或担保人", width = 15)
	private String dbwhdbr;
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
	/**更新时间
*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
}
