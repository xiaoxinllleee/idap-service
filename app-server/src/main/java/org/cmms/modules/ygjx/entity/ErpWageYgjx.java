package org.cmms.modules.ygjx.entity;

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
 * @Description: 员工绩效合计
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_YGJX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_YGJX对象", description="员工绩效合计")
public class ErpWageYgjx {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**工资日期*/
	@Excel(name = "工资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资日期")
	private Date gzrq;
	/**存款工资*/
	@Excel(name = "存款工资", width = 15)
    @ApiModelProperty(value = "存款工资")
	private java.math.BigDecimal ckgz;
	/**贷款工资*/
	@Excel(name = "贷款工资", width = 15)
    @ApiModelProperty(value = "贷款工资")
	private java.math.BigDecimal dkgz;
	/**电子银行工资*/
	@Excel(name = "电子银行工资", width = 15)
    @ApiModelProperty(value = "电子银行工资")
	private java.math.BigDecimal dzyhgz;
	/**业务量工资*/
	@Excel(name = "业务量工资", width = 15)
    @ApiModelProperty(value = "业务量工资")
	private java.math.BigDecimal ywlgz;
	/**管理绩效工资*/
	@Excel(name = "管理绩效工资", width = 15)
    @ApiModelProperty(value = "管理绩效工资")
	private java.math.BigDecimal gljxgz;
	/**地区补差工资*/
	@Excel(name = "地区补差工资", width = 15)
    @ApiModelProperty(value = "地区补差工资")
	private java.math.BigDecimal dqbcgz;
	/**工资合计*/
	@Excel(name = "工资合计", width = 15)
    @ApiModelProperty(value = "工资合计")
	private java.math.BigDecimal gzhj;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String yggh;
	/**支行工资排名*/
	@Excel(name = "支行工资排名", width = 15)
    @ApiModelProperty(value = "支行工资排名")
	private Integer zhgzpm;
	/**全行工资排名*/
	@Excel(name = "全行工资排名", width = 15)
    @ApiModelProperty(value = "全行工资排名")
	private Integer qhgzpm;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**考核维度*/
	@Excel(name = "考核维度", width = 15)
    @ApiModelProperty(value = "考核维度")
	private String khwd;
	/**其它*/
	@Excel(name = "其它", width = 15)
    @ApiModelProperty(value = "其它")
	private java.math.BigDecimal qt;
	/**经营目标工资*/
	@Excel(name = "经营目标工资", width = 15)
    @ApiModelProperty(value = "经营目标工资")
	private java.math.BigDecimal jymbgz;
	/**延期兑付工资*/
	@Excel(name = "延期兑付工资", width = 15)
    @ApiModelProperty(value = "延期兑付工资")
	private java.math.BigDecimal yqdfgz;
	/**基本工资*/
	@Excel(name = "基本工资", width = 15)
    @ApiModelProperty(value = "基本工资")
	private java.math.BigDecimal jbgz;
	/**表外工资*/
	@Excel(name = "表外工资", width = 15)
    @ApiModelProperty(value = "表外工资")
	private java.math.BigDecimal bwgz;
	/**专项考核工资*/
	@Excel(name = "专项考核工资", width = 15)
    @ApiModelProperty(value = "专项考核工资")
	private java.math.BigDecimal zxkhgz;
	/**年终考核工资*/
	@Excel(name = "年终考核工资", width = 15)
    @ApiModelProperty(value = "年终考核工资")
	private java.math.BigDecimal nzkhgz;
	/**总系数*/
	@Excel(name = "总系数", width = 15)
    @ApiModelProperty(value = "总系数")
	private java.math.BigDecimal zxs;
	/**非系数工作合计（原gzhj）gzhj为总系数*fxsgzhj*/
	@Excel(name = "非系数工作合计（原gzhj）gzhj为总系数*fxsgzhj", width = 15)
    @ApiModelProperty(value = "非系数工作合计（原gzhj）gzhj为总系数*fxsgzhj")
	private java.math.BigDecimal fxsgzhj;
	/**系数工资合计*/
	@Excel(name = "系数工资合计", width = 15)
    @ApiModelProperty(value = "系数工资合计")
	private java.math.BigDecimal xsgzhj;
}
