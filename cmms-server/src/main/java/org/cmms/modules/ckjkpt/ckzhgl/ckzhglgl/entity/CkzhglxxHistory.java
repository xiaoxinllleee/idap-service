package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity;

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
 * @Description: 存款账号关联管理(历史备份数据)
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_ckzhglxx_his")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_ckzhglxx_his对象", description="存款账号关联管理(历史备份数据)")
public class CkzhglxxHistory {

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
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
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
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String zhmc;
	/**关联标志*/
	@Excel(name = "关联标志", width = 15)
    @ApiModelProperty(value = "关联标志")
	@Dict(dicCode = "glbz")
	private Integer glbz;
	/**账号性质*/
	@Excel(name = "账号性质", width = 15)
    @ApiModelProperty(value = "账号性质")
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
	/**关联比例*/
	@Excel(name = "关联比例", width = 15)
    @ApiModelProperty(value = "关联比例")
	private java.math.BigDecimal glbl;
	/**有效标志*/
	@Excel(name = "有效标志", width = 15)
    @ApiModelProperty(value = "有效标志")
	private Integer yxbz;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String ywjgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**存量存款余额*/
	@Excel(name = "存量存款余额", width = 15)
    @ApiModelProperty(value = "存量存款余额")
	private java.math.BigDecimal clckye;
	/**存量存款日平*/
	@Excel(name = "存量存款日平", width = 15)
    @ApiModelProperty(value = "存量存款日平")
	private java.math.BigDecimal clckrpye;
	/**存款日平*/
	@Excel(name = "存款日平", width = 15)
    @ApiModelProperty(value = "存款日平")
	private java.math.BigDecimal ckrpye;
	/**年初存款日平*/
	@Excel(name = "年初存款日平", width = 15)
    @ApiModelProperty(value = "年初存款日平")
	private java.math.BigDecimal nckrpye;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**预约编号*/
	@Excel(name = "预约编号", width = 15)
    @ApiModelProperty(value = "预约编号")
	private Long yybh;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private Long pch;
	/**证件号*/
	@Excel(name = "证件号", width = 15)
    @ApiModelProperty(value = "证件号")
	private String zjhm;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
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
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
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
	private String lrczy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgczy;
}
