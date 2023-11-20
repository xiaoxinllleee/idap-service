package org.cmms.modules.gzap.grrwsz.entity;

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
 * @Description: 个人任务设置
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Data
@TableName("GZAP_GRRWSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GZAP_GRRWSZ对象", description="个人任务设置")
public class GzapGrrwsz {
    
	/**任务维度*/
	@Excel(name = "任务维度", width = 15)
    @ApiModelProperty(value = "任务维度")
	@Dict(dicCode = "rwwd")
	private String rwwd;
	/**任务日期*/
	@Excel(name = "任务日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "任务日期")
	private Date rwrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**客户经理标志*/
	@Excel(name = "客户经理标志", width = 15)
    @ApiModelProperty(value = "客户经理标志")
	private String khjlbz;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**存款净增任务*/
	@Excel(name = "存款净增任务", width = 15)
    @ApiModelProperty(value = "存款净增任务")
	private java.math.BigDecimal ckjzrw;
	/**存款日平净增任务*/
	@Excel(name = "存款日平净增任务", width = 15)
    @ApiModelProperty(value = "存款日平净增任务")
	private java.math.BigDecimal ckrpjzrw;
	/**存款客户数净增任务*/
	@Excel(name = "存款客户数净增任务", width = 15)
    @ApiModelProperty(value = "存款客户数净增任务")
	private java.math.BigDecimal ckkhsjzrw;
	/**贷款净增任务*/
	@Excel(name = "贷款净增任务", width = 15)
    @ApiModelProperty(value = "贷款净增任务")
	private java.math.BigDecimal dkjzrw;
	/**贷款日平净增任务*/
	@Excel(name = "贷款日平净增任务", width = 15)
    @ApiModelProperty(value = "贷款日平净增任务")
	private java.math.BigDecimal dkrpjzrw;
	/**法人贷款客户净增任务*/
	@Excel(name = "法人贷款客户净增任务", width = 15)
    @ApiModelProperty(value = "法人贷款客户净增任务")
	private java.math.BigDecimal frdkkhjzrw;
	/**个人贷款客户净增任务*/
	@Excel(name = "个人贷款客户净增任务", width = 15)
    @ApiModelProperty(value = "个人贷款客户净增任务")
	private java.math.BigDecimal grdkkhjzrw;
	/**手机银行净增任务*/
	@Excel(name = "手机银行净增任务", width = 15)
    @ApiModelProperty(value = "手机银行净增任务")
	private java.math.BigDecimal sjyhjzrw;
	/**ETC净增任务*/
	@Excel(name = "ETC净增任务", width = 15)
    @ApiModelProperty(value = "ETC净增任务")
	private java.math.BigDecimal etcjzrw;
	/**E支付净增任务*/
	@Excel(name = "E支付净增任务", width = 15)
    @ApiModelProperty(value = "E支付净增任务")
	private java.math.BigDecimal ezfjzrw;
	/**任务完成时间*/
	@Excel(name = "任务完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "任务完成时间")
	private Date rwwcsj;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
