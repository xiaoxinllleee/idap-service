package org.cmms.modules.khjg.gwgzhz.entity;

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
 * @Description: 岗位工资汇总
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Data
@TableName("erp_wage_ygjx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_wage_ygjx对象", description="岗位工资汇总")
public class Gwgzhz {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**gzrq*/
	@Excel(name = "工资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资日期")
	private Date gzrq;
	/**schemeId*/
	@Excel(name = "考核项目", width = 15,dicCode = "scheme_id", dictTable = "V_PMA_A_SCHEME", dicText = "SHOW_NAME")
    @ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "scheme_id", dictTable = "V_PMA_A_SCHEME", dicText = "SHOW_NAME")
	private String schemeId;
	/**zzbz*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**gwbz*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**yggh*/
	@Excel(name = "员工姓名", width = 15,dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String yggh;
	/**ckgz*/
	@Excel(name = "存款工资", width = 15)
    @ApiModelProperty(value = "存款工资")
	private java.math.BigDecimal ckgz;
	/**dkgz*/
	@Excel(name = "贷款工资", width = 15)
    @ApiModelProperty(value = "贷款工资")
	private java.math.BigDecimal dkgz;
	/**dzyhgz*/
	@Excel(name = "电子银行工资", width = 15)
    @ApiModelProperty(value = "电子银行工资")
	private java.math.BigDecimal dzyhgz;
	/**ywlgz*/
	@Excel(name = "业务量工资", width = 15)
    @ApiModelProperty(value = "业务量工资")
	private java.math.BigDecimal ywlgz;
	/**gljxgz*/
	@Excel(name = "管理绩效工资", width = 15)
    @ApiModelProperty(value = "管理绩效工资")
	private java.math.BigDecimal gljxgz;
	/**dqbcgz*/
	@Excel(name = "地区补差工资", width = 15)
    @ApiModelProperty(value = "地区补差工资")
	private java.math.BigDecimal dqbcgz;
	/**jymbgz*/
	@Excel(name = "经营目标工资", width = 15)
    @ApiModelProperty(value = "经营目标工资")
	private java.math.BigDecimal jymbgz;
	/**yqdfgz*/
	@Excel(name = "延期兑付工资", width = 15)
    @ApiModelProperty(value = "延期兑付工资")
	private java.math.BigDecimal yqdfgz;
	/**qtgz*/
	@Excel(name = "其他工资", width = 15)
    @ApiModelProperty(value = "其他工资")
	private java.math.BigDecimal qtgz;
	/**jbgz*/
	@Excel(name = "基本工资", width = 15)
    @ApiModelProperty(value = "基本工资")
	private java.math.BigDecimal jbgz;
	/**bwgz*/
	@Excel(name = "表外工资", width = 15)
    @ApiModelProperty(value = "表外工资")
	private java.math.BigDecimal bwgz;
	/**zxkhgz*/
	@Excel(name = "专项考核工资", width = 15)
    @ApiModelProperty(value = "专项考核工资")
	private java.math.BigDecimal zxkhgz;
	/**nzkhgz*/
	@Excel(name = "年终考核工资", width = 15)
    @ApiModelProperty(value = "年终考核工资")
	private java.math.BigDecimal nzkhgz;
	/**gzhj*/
	@Excel(name = "工资合计", width = 15)
    @ApiModelProperty(value = "工资合计")
	private java.math.BigDecimal gzhj;
	/**zhgzpm*/
	@Excel(name = "支行工资排名", width = 15)
    @ApiModelProperty(value = "支行工资排名")
	private Integer zhgzpm;
	/**qhgzpm*/
	@Excel(name = "全行工资排名", width = 15)
    @ApiModelProperty(value = "全行工资排名")
	private Integer qhgzpm;
	/**createTime*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
}
