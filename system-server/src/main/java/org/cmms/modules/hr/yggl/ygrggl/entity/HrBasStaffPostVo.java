package org.cmms.modules.hr.yggl.ygrggl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工调动中间表
 * @Author: jeecg-boot
 * @Date:   2022-11-15
 * @Version: V1.0
 */
@Data
public class HrBasStaffPostVo {

	/**工号/账号*/
	@Excel(name = "工号/账号", width = 15)
    @ApiModelProperty(value = "工号/账号")
	@ExcelVerify(notNull = true)
	private String yggh;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String gyh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	@ExcelVerify(notNull = true)
	private String ygxm;
	/**原所属支行标识*/
	@Excel(name = "原所属支行名称", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "原所属支行标识")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ExcelVerify(notNull = true)
	private String ysszhbs;
	/**原岗位标识*/
	@Excel(name = "原岗位名称", width = 15, dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
    @ApiModelProperty(value = "原岗位标识")
	@Dict(dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	@ExcelVerify(notNull = true)
	private String ygwbs;
	/**原权限组织*/
	@Excel(name="原权限组织",width = 15,dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    @ApiModelProperty(value = "原权限组织")
	@Dict(dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
	private String yqxzz;
	/**原角色分配id*/
	@Excel(name="原角色分配",width = 15,dictTable ="sys_role",dicText = "role_name",dicCode = "id")
    @ApiModelProperty(value = "原角色分配id")
	@Dict(dictTable ="sys_role",dicText = "role_name",dicCode = "id")
	private String yjsfp;
	/**离岗日期（原岗位离岗日期）*/
	@Excel(name = "离岗日期（原岗位离岗日期）", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "离岗日期（原岗位离岗日期）")
	private Date lgrq;
	/**调入支行*/
	@Excel(name = "调入支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "调入支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String drzz;
	/**入岗日期（调入日期）*/
	@Excel(name = "调入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "调入日期")
	private Date rgrq;
	/**现岗位标识*/
	@Excel(name = "调入岗位名称", width = 15, dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
    @ApiModelProperty(value = "调入岗位名称")
	@Dict(dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	private String xgwbs;
	/**现角色分配id*/
	@Excel(name="现角色分配",width = 15,dictTable ="sys_role",dicText = "role_name",dicCode = "id")
    @ApiModelProperty(value = "现角色分配id")
	@Dict(dictTable ="sys_role",dicText = "role_name",dicCode = "id")
	private String xjsfp;
	/**现权限组织*/
	@Excel(name="现权限组织",width = 15,dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    @ApiModelProperty(value = "现权限组织")
	@Dict(dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
	private String xqxzz;
	/**现登录密码*/
	@Excel(name = "现登录密码", width = 15)
    @ApiModelProperty(value = "现登录密码")
	private String xdlmm;
	/**现网格所属支行*/
	@Excel(name="现网格所属支行",width = 15,dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
    @ApiModelProperty(value = "现网格所属支行")
	@Dict(dictTable ="hr_bas_organization",dicText = "zzjc",dicCode = "zzbz")
	@ExcelVerify(interHandler = true)
	private String xwgsszh;
}
