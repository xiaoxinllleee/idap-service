package org.cmms.modules.khgl.sh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商户花名册
 * @Author: jeecg-boot
 * @Date:   2020-10-09
 * @Version: V1.0
 */
@Data
@TableName("khgl_shhmcxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_shhmcxx对象", description="商户花名册")
public class shhmcxxImport {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**法定代表人*/
	@Excel(name = "法定代表人", width = 15)
    @ApiModelProperty(value = "法定代表人")
	private String fddbr;
	/**法人证件号码*/
	@Excel(name = "法人证件号码", width = 15)
    @ApiModelProperty(value = "法人证件号码")
	private String frzjhm;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
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
	@Excel(name = "经营状态", width = 15, dicCode = "jyzt")
    @ApiModelProperty(value = "经营状态")
	@Dict(dicCode = "jyzt")
	private String jyzt;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private String zczb;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成立日期")
	private Date clrq;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**更多联系方式*/
	@Excel(name = "更多联系方式", width = 15)
    @ApiModelProperty(value = "更多联系方式")
	private String gdlxfs;
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
	private String cbrs;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private String shlx;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
