package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@Data
@ToString
public class CkjkptCkzhglxxVo {

	/**关联ID*/
    @ApiModelProperty(value = "关联ID")
	private Long glid;
    /**组织标识*/
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**柜员号*/
	private String gyh;
	/**客户经理标识*/
	private String khjlbz;
	/**存款账号*/
	private String ckzh;
	/**卡号*/
	private String kh;
	/**账号名称*/
	private String zhmc;
	/**证件号*/
	private String zjhm;
	/**开户机构*/
	/*@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;*/
	/**贷款账号*/
	private String dkzh;
	/**账号性质*/
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
	/**存期*/
	private String cq;
	/**本期起息日*/
	private String bqqxr;
	/**本期截至日*/
	private String bqjzr;
	/**关联比例*/
	private java.math.BigDecimal glbl;
	/**关联标志*/
	@Dict(dicCode = "glbz")
	private Integer glbz;
	/**存款余额*/
	private java.math.BigDecimal ckye;
	/**存款日平*/
	private java.math.BigDecimal ckrpye;
	/**年初存款日平*/
	private java.math.BigDecimal nckrpye;
	/**地址*/
	private String dz;
	/**录入标志*/
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	private String lrczy;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**修改人*/
	private String xgczy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xgsj;
	/**支行代码*/
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "SJZZJC")
	private String zhjgdm;
	/**机构代码*/
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String ywjgdm;
	/**批次号*/
	private Long pch;
	/**有效标志*/
	private Integer yxbz;
	/**存量存款余额*/
	private java.math.BigDecimal clckye;
	/**存量存款日平*/
	private java.math.BigDecimal clckrpye;
	/**预约编号*/
	private Long yybh;

	/**统计月份*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tjyf;
}
