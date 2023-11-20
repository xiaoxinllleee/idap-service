package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_ckzhglxx")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_ckzhglxx对象", description="存款账号关联管理")
public class CkjkptCkzhglxx {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscal_date;


	/**关联ID*/
	@Excel(name = "关联ID", width = 15)
    @ApiModelProperty(value = "关联ID")
	private Long glid;

//	@Excel(name = "上级组织名称",width = 15,dicCode = "zzbz",dictTable = "v_hr_bas_organization",dicText = "SJZZJC")
	@Dict(dicCode = "zzbz",dictTable = "v_hr_bas_organization",dicText = "SJZZJC")
	@TableField(exist = false)
	private String sjzzmc;

//	@Excel(name = "上级组织标识",width = 15,dicCode = "sjzzbz_r",dictTable = "v_hr_bas_organization",dicText = "SJZZJC")
	@Dict(dicCode = "zzbz",dictTable = "v_hr_bas_organization",dicText = "sjzzbz_r")
	@TableField(exist = false)
	private String sjzzbz;

	public String getSjzzbz() {
		return this.zzbz;
	}
	public String getSjzzmc() {
		return this.zzbz;
	}

	@Excel(name = "组织标识", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15,dicCode = "gwbz", dictTable = "Hr_bas_post", dicText = "gwmc")
	@ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz", dictTable = "Hr_bas_post", dicText = "gwmc")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String gyh;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
	@ApiModelProperty(value = "卡号")
	private String kh;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String zhmc;
	/**证件号*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**开户机构*/
	/*@Excel(name = "账号所在机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "账号所在机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;*/
	/**贷款账号*/
	@Excel(name = "对应贷款账号", width = 15)
	@ApiModelProperty(value = "对应贷款账号")
	private String dkzh;
	/**账号性质*/
	@Excel(name = "账号性质", width = 15,dicCode = "zhxz")
	@ApiModelProperty(value = "账号性质")
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
	/**存期*/
	@Excel(name = "存期", width = 15)
	@ApiModelProperty(value = "存期")
	private String cq;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
	@ApiModelProperty(value = "本期起息日")
	private String bqqxr;
	/**本期截至日*/
	@Excel(name = "本期截至日", width = 15)
	@ApiModelProperty(value = "本期截至日")
	private String bqjzr;
	/**关联比例*/
	@Excel(name = "关联比率(%)", width = 15)
	@ApiModelProperty(value = "关联比率(%)")
	private java.math.BigDecimal glbl;
	/**关联标志*/
	@Excel(name = "关联标识", width = 15,dicCode = "glbz")
	@ApiModelProperty(value = "关联标识")
	@Dict(dicCode = "glbz")
	private Integer glbz;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平*/
	@Excel(name = "月存款日平余额", width = 15)
	@ApiModelProperty(value = "月存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**年初存款日平*/
	@Excel(name = "年存款日平余额", width = 15)
	@ApiModelProperty(value = "年存款日平余额")
	private java.math.BigDecimal nckrpye;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgczy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**开户机构*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywjgdm;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
	@ApiModelProperty(value = "批次号")
	private Long pch;
	/**有效标志*/
    @ApiModelProperty(value = "有效标志")
	private Integer yxbz;
	/**存量存款余额*/
    @ApiModelProperty(value = "存量存款余额")
	private java.math.BigDecimal clckye;
	/**存量存款日平*/
    @ApiModelProperty(value = "存量存款日平")
	private java.math.BigDecimal clckrpye;
	/**预约编号*/
    @ApiModelProperty(value = "预约编号")
	private Long yybh;



}


