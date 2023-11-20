package org.cmms.modules.khxxgl.khflgl.shxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShxqImportVo {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格编号", width = 15,dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
    @ApiModelProperty(value = "归属网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	@ExcelVerify(notNull = true)
	private String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15,dicCode ="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	@ExcelVerify(notNull = true)
	private String shmc;
	/**法定代表人*/
	@Excel(name = "法定代表人", width = 15)
    @ApiModelProperty(value = "法定代表人")
	private String fddbr;
	/**法人证件号码*/
	@Excel(name = "法人证件号码", width = 15)
    @ApiModelProperty(value = "法人证件号码")
	@ExcelVerify(notNull = true)
	private String frzjhm;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	@ExcelVerify(notNull = true)
	private String tyshxydm;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
	private String yyzz;
	/**纳税人识别号*/
	@Excel(name = "纳税人识别号", width = 15)
    @ApiModelProperty(value = "纳税人识别号")
	private String nsrsbh;
	/**组织机构代码*/
	@Excel(name = "组织机构代码", width = 15)
    @ApiModelProperty(value = "组织机构代码")
	private String zzjgdm;
	/**注册号*/
	@Excel(name = "注册号", width = 15)
    @ApiModelProperty(value = "注册号")
	private String zch;
	/**经营状态*/
	@Excel(name = "经营状态", width = 15,dicCode = "jyzt")
    @ApiModelProperty(value = "经营状态")
	@Dict(dicCode = "jyzt")
	private String jyzt;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private String zccb;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成立日期")
	private java.util.Date clrq;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lsfs;
	/**更多联系方式*/
	@Excel(name = "更多联系方式", width = 15)
    @ApiModelProperty(value = "更多联系方式")
	private String gdlsfs;
	/**邮箱地址*/
	@Excel(name = "邮箱地址", width = 15)
    @ApiModelProperty(value = "邮箱地址")
	private String yxdz;
	/**更多邮箱地址*/
	@Excel(name = "更多邮箱地址", width = 15)
    @ApiModelProperty(value = "更多邮箱地址")
	private String gdyxdz;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**参保人数*/
	@Excel(name = "参保人数", width = 15)
    @ApiModelProperty(value = "参保人数")
	private Integer cbrs;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15, dicCode = "qylx")
    @ApiModelProperty(value = "商户类型")
	@Dict(dicCode = "qylx")
	private String shlx;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
	//@Dict(dicCode = "cshyfl")
	private String sshy;
	/**曾用名*/
	@Excel(name = "曾用名", width = 15)
    @ApiModelProperty(value = "曾用名")
	private String cym;
	/**网址*/
	@Excel(name = "网址", width = 15)
    @ApiModelProperty(value = "网址")
	private String wz;
	/**经营范围*/
	@Excel(name = "经营范围", width = 15)
    @ApiModelProperty(value = "经营范围")
	private String jyfw;
	/**商户分类*/
	@Excel(name = "商户分类", width = 15,dicCode = "shgl_shfl")
	@ApiModelProperty(value = "商户分类")
	private String shfl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	@ExcelVerify(interHandler = true)
	private String bz;

}
