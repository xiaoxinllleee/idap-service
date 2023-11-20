package org.cmms.modules.khxxgl.khflgl.qyxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QyxxImportVo {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "所属网格", width = 15,dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	@ApiModelProperty(value = "所属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private java.lang.String wgbh;
	/**归属机构*/
	@Excel(name = "所属支行", width = 15,dicCode ="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**商户名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	@ExcelVerify(notNull = true)
	private String qymc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
	@ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**注册号*/
	@Excel(name = "注册号", width = 15)
	@ApiModelProperty(value = "注册号")
	private String zch;
	/**法定代表人*/
	@Excel(name = "法定代表人/负责人姓名", width = 15)
    @ApiModelProperty(value = "法定代表人/负责人姓名")
	private String fddbr;
	/**法定代表人*/
	@Excel(name = "住所", width = 15)
    @ApiModelProperty(value = "住所")
	private String zs;
	/**注册时间*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "成立日期")
	private Date zcsj;
	/**注册到期日*/
	@Excel(name = "核准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "核准日期")
	private Date hzrq;
	/**经营范围*/
	@Excel(name = "经营范围", width = 15)
	@ApiModelProperty(value = "经营范围")
	private String jyfw;
	/**经营状态*/
	@Excel(name = "状态", width = 15,dicCode = "jyzt_qd")
	@ApiModelProperty(value = "状态")
	@Dict(dicCode = "jyzt_qd")
	private String jyzt;
	/**企业联系电话*/
	@Excel(name = "企业联系电话", width = 15)
	@ApiModelProperty(value = "企业联系电话")
	private String qylxdh;
	/**登记类别*/
	@Excel(name = "登记类别", width = 15)
	@ApiModelProperty(value = "登记类别")
	private String djlb;
	/**企业类型*/
	@Excel(name = "企业类型", width = 15,dicCode = "qylx_qd")
	@ApiModelProperty(value = "企业类型")
	@Dict(dicCode = "qylx_qd")
	private String qylx;
	/**联络员姓名*/
	@Excel(name = "联络员姓名", width = 15)
	@ApiModelProperty(value = "联络员姓名")
	private String llyxm;
	/**联络员证件号码*/
	@Excel(name = "联络员证件号码", width = 15)
	@ApiModelProperty(value = "联络员证件号码")
	private String llyzjhm;
	/**注册资本*/
	@Excel(name = "注册资本(万元)", width = 15)
	@ApiModelProperty(value = "注册资本(万元)")
	private String zczb;
	/**实收资本*/
	@Excel(name = "实收资本(万元)", width = 15)
	@ApiModelProperty(value = "实收资本(万元)")
	private String sszb;
	/**营业期限*/
	@Excel(name = "营业期限", width = 15)
	@ApiModelProperty(value = "营业期限")
	private String yyqx;
	/**登记机关*/
	@Excel(name = "登记机关", width = 15)
	@ApiModelProperty(value = "登记机关")
	private String djjg;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
	@ApiModelProperty(value = "邮政编码")
	private String yzbm;
	/**监管单位*/
	@Excel(name = "监管单位", width = 15)
	@ApiModelProperty(value = "监管单位")
	private String jgdw;
	/**行业代码*/
	@Excel(name = "行业代码", width = 15)
	@ApiModelProperty(value = "行业代码")
	@ExcelVerify(interHandler = true)
	private String hydm;

}
