package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity;

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
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Data
@TableName("V_YWGL_ETCYXXXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_YWGL_ETCYXXXGL对象", description="ETC营销信息管理")
public class VEtcyxxxgl {

	/**tjyf*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据月份")
	private Date tjyf;
	/**frhjgdm*/
	@Excel(name = "法人行机构码", width = 15)
    @ApiModelProperty(value = "法人行机构码")
	private String frhjgdm;
	/**frhjgmc*/
	@Excel(name = "法人行机构名称", width = 15)
    @ApiModelProperty(value = "法人行机构名称")
	private String frhjgmc;
	/**yywdmc*/
	@Excel(name = "营业网点名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "营业网点名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String yywdmc;
	/**yywdjgm*/
	@Excel(name = "网点机构码", width = 15)
    @ApiModelProperty(value = "网点机构码")
	private String yywdjgm;
	/**blqd*/
	@Excel(name = "办理渠道", width = 15)
    @ApiModelProperty(value = "办理渠道")
	private String blqd;
	/**blygbh*/
	@Excel(name = "办理员工编号", width = 15)
    @ApiModelProperty(value = "办理员工编号")
	private String blygbh;
	/**blygxm*/
	@Excel(name = "办理员工姓名", width = 15)
    @ApiModelProperty(value = "办理员工姓名")
	private String blygxm;
	/**khxm*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**khsfzh*/
	@Excel(name = "客户身份证号", width = 15)
    @ApiModelProperty(value = "客户身份证号")
	private String khsfzh;
	/**khsjh*/
    @ApiModelProperty(value = "khsjh")
	private String khsjh;
	/**cphm*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**cpys*/
	@Excel(name = "车牌颜色", width = 15)
    @ApiModelProperty(value = "车牌颜色")
	private String cpys;
	/**hkfs*/
	@Excel(name = "获客方式", width = 15)
    @ApiModelProperty(value = "获客方式")
	private String hkfs;
	/**bdzkh*/
	@Excel(name = "绑定帐卡号", width = 15)
    @ApiModelProperty(value = "bdzkh")
	private String bdzkh;
	/**khjgdm*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String khjgdm;
	/**khrq*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
	/**khsj*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private String khsj;
	/**xhrq*/
	@Excel(name = "销户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "销户日期")
	private Date xhrq;
	/**xhsj*/
	@Excel(name = "销户时间", width = 15)
    @ApiModelProperty(value = "销户时间")
	private String xhsj;
	/**etcbdzt*/
	@Excel(name = "ETC绑定状态", width = 15)
    @ApiModelProperty(value = "ETC绑定状态")
	private String etcbdzt;
	/**yxrgh*/
	@Excel(name = "营销人工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "营销人工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yxrgh;
	/**yxjgdm*/
	@Excel(name = "营销机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "营销机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String yxjgdm;
	/**tjr*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String tjr;
	/**tjrxm*/
	@Excel(name = "推荐人姓名", width = 15)
    @ApiModelProperty(value = "推荐人姓名")
	private String tjrxm;
	/**tjrjg*/
	@Excel(name = "推荐人机构", width = 15)
    @ApiModelProperty(value = "推荐人机构")
	private String tjrjg;
	/**xhczy*/
	@Excel(name = "销户操作员", width = 15)
    @ApiModelProperty(value = "销户操作员")
	private String xhczy;
	/**xhczyxm*/
	@Excel(name = "销户操作员姓名", width = 15)
    @ApiModelProperty(value = "销户操作员姓名")
	private String xhczyxm;
	/**xhczyjg*/
	@Excel(name = "销户操作员机构", width = 15)
    @ApiModelProperty(value = "销户操作员机构")
	private String xhczyjg;
	/**xgsj*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**xgr*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
}
