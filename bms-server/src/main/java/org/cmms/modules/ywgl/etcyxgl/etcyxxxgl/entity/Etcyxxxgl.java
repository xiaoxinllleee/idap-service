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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_etcyxxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_etcyxxxgl对象", description="ETC营销信息管理")
public class Etcyxxxgl {
    
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**统计月份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "数据月份")
	@ExcelVerify(notNull = true)
	private Date tjyf;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**法人行机构码*/
	@Excel(name = "法人行机构码", width = 15)
    @ApiModelProperty(value = "法人行机构码")
	private String frhjgdm;
	/**法人行机构名称*/
	@Excel(name = "法人行机构名称", width = 15)
    @ApiModelProperty(value = "法人行机构名称")
	private String frhjgmc;
	/**营业网点名称*/
	@Excel(name = "营业网点名称", width = 15)
    @ApiModelProperty(value = "营业网点名称")
	private String yywdmc;
	/**网点机构码*/
	@Excel(name = "网点机构码", width = 15)
    @ApiModelProperty(value = "网点机构码")
	private String yywdjgm;
	/**办理渠道*/
	@Excel(name = "办理渠道", width = 15)
    @ApiModelProperty(value = "办理渠道")
	private String blqd;
	/**办理员工编号*/
	@Excel(name = "办理员工编号", width = 15)
    @ApiModelProperty(value = "办理员工编号")
	private String blygbh;
	/**办理员工姓名*/
	@Excel(name = "办理员工姓名", width = 15)
    @ApiModelProperty(value = "办理员工姓名")
	private String blygxm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**客户身份证号*/
	@Excel(name = "客户身份证号", width = 15)
    @ApiModelProperty(value = "客户身份证号")
	@ExcelVerify(notNull = true)
	private String khsfzh;
	/**客户手机号*/
	@Excel(name = "客户手机号", width = 15)
    @ApiModelProperty(value = "客户手机号")
	private String khsjh;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**车牌颜色*/
	@Excel(name = "车牌颜色", width = 15)
    @ApiModelProperty(value = "车牌颜色")
	private String cpys;
	/**获客方式*/
	@Excel(name = "获客方式", width = 15)
    @ApiModelProperty(value = "获客方式")
	private String hkfs;
	/**绑定帐卡号*/
	@Excel(name = "绑定帐卡号", width = 15)
    @ApiModelProperty(value = "绑定帐卡号")
	private String bdzkh;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String khjgdm;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	@ExcelVerify(notNull = true)
	private Date khrq;
	/**开户时间(时分秒)*/
	@Excel(name = "开户时间(时分秒)", width = 15)
    @ApiModelProperty(value = "开户时间(时分秒)")
	@ExcelVerify(notNull = true)
	private String khsj;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "销户日期")
	private Date xhrq;
	/**销户时间(时分秒)*/
	@Excel(name = "销户时间(时分秒)", width = 15)
    @ApiModelProperty(value = "销户时间(时分秒)")
	private String xhsj;
	/**ETC绑定状态*/
	@Excel(name = "ETC绑定状态", width = 15)
    @ApiModelProperty(value = "ETC绑定状态")
	private String etcbdzt;
	/**营销人工号*/
	@Excel(name = "营销人工号", width = 15)
    @ApiModelProperty(value = "营销人工号")
	private String yxrgh;
	/**营销机构代码*/
	@Excel(name = "营销机构代码", width = 15)
    @ApiModelProperty(value = "营销机构代码")
	private String yxjgdm;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String tjr;
	/**推荐人姓名*/
	@Excel(name = "推荐人姓名", width = 15)
    @ApiModelProperty(value = "推荐人姓名")
	private String tjrxm;
	/**推荐人机构*/
	@Excel(name = "推荐人机构", width = 15)
    @ApiModelProperty(value = "推荐人机构")
	private String tjrjg;
	/**销户操作员*/
	@Excel(name = "销户操作员", width = 15)
    @ApiModelProperty(value = "销户操作员")
	private String xhczy;
	/**销户操作员姓名*/
	@Excel(name = "销户操作员姓名", width = 15)
    @ApiModelProperty(value = "销户操作员姓名")
	private String xhczyxm;
	/**销户操作员机构*/
	@Excel(name = "销户操作员机构", width = 15)
    @ApiModelProperty(value = "销户操作员机构")
	@ExcelVerify(interHandler = true)
	private String xhczyjg;
}
