package org.cmms.modules.xdgl.grkhpjsx.entity;

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
 * @Description: 个人客户评级授信
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_GRKHPJSX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGL_GRKHPJSX对象", description="个人客户评级授信")
public class Grkhpjsx {


	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键id")
	private String id;

	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;

	/**所属营销单元*/
    @ApiModelProperty(value = "所属营销单元")
	private String qydm;
	/**一级营销单元编号*/
	@Excel(name = "镇", width = 15,dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
    @ApiModelProperty(value = "一级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "村", width = 15,dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
    @ApiModelProperty(value = "二级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "组", width = 15,dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
    @ApiModelProperty(value = "三级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdybh;

	/**所属客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String sskhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**与户主关系*/
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**khlx1*/
    @ApiModelProperty(value = "khlx1")
	private String khlx1;

	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk_cj")
    @ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;

	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode = "sex")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String zz;

	/**从事职业*/
	@Excel(name = "从事职业", width = 15,dicCode = "cszy")
    @ApiModelProperty(value = "从事职业")
	@Dict(dicCode = "cszy")
	private String cszy;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;


	/**模型等级*/
	@Excel(name = "模型等级", width = 15,dicCode = "pjsx_pddj")
	@ApiModelProperty(value = "xtpddj")
	@Dict(dicCode = "pjsx_pddj")
	private String xtpddj;
	/**模型得分*/
	@Excel(name = "模型得分", width = 15)
	@ApiModelProperty(value = "xtpddf")
	private java.math.BigDecimal xtpddf;

	/**xtpdyy*/
	@ApiModelProperty(value = "xtpdyy")
	private String xtpdyy;
	/**xtsxed*/
	@Excel(name = "模型授信额度", width = 15)
	@ApiModelProperty(value = "xtsxed")
	private java.math.BigDecimal xtsxed;
	/**zzpddj*/
	@Excel(name = "最终评定等级", width = 15,dicCode = "pjsx_pddj")
	@ApiModelProperty(value = "zzpddj")
	@Dict(dicCode = "pjsx_pddj")
	private String zzpddj;
	/**zzsxed*/
	@Excel(name = "最终授信额度", width = 15)
	@ApiModelProperty(value = "zzsxed")
	private java.math.BigDecimal zzsxed;

	/**意见*/
	@Excel(name = "意见", width = 15)
	@ApiModelProperty(value = "意见")
	private String yj;

	/**流程状态*/
	@Excel(name = "流程状态", width = 15,dicCode = "lczt")
	@ApiModelProperty(value = "流程状态")
	@Dict(dicCode = "lczt")
	private String status;


	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date createTime;

	/**客户类型*/
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khsf")
	private String khlx;
	/**gdzcZfts*/
    @ApiModelProperty(value = "gdzcZfts")
	private Integer gdzcZfts;
	/**gdzcZfmj*/
    @ApiModelProperty(value = "gdzcZfmj")
	private java.math.BigDecimal gdzcZfmj;
	/**gdzcZfjz*/
    @ApiModelProperty(value = "gdzcZfjz")
	private java.math.BigDecimal gdzcZfjz;
	/**gdzcQt*/
    @ApiModelProperty(value = "gdzcQt")
	private java.math.BigDecimal gdzcQt;
	/**gdzcHj*/
    @ApiModelProperty(value = "gdzcHj")
	private java.math.BigDecimal gdzcHj;
	/**ldzcXjjwhck*/
    @ApiModelProperty(value = "ldzcXjjwhck")
	private java.math.BigDecimal ldzcXjjwhck;
	/**ldzcQt*/
    @ApiModelProperty(value = "ldzcQt")
	private java.math.BigDecimal ldzcQt;
	/**ldzcYsk*/
    @ApiModelProperty(value = "ldzcYsk")
	private java.math.BigDecimal ldzcYsk;
	/**ldzcSfthkh*/
    @ApiModelProperty(value = "ldzcSfthkh")
	@Dict(dicCode = "sfbz")
	private String ldzcSfthkh;
	/**ldzcSfthyck*/
    @ApiModelProperty(value = "ldzcSfthyck")
	@Dict(dicCode = "sfbz")
	private String ldzcSfthyck;
	/**ldzcThckje*/
    @ApiModelProperty(value = "ldzcThckje")
	private java.math.BigDecimal ldzcThckje;
	/**ldzcHj*/
    @ApiModelProperty(value = "ldzcHj")
	private java.math.BigDecimal ldzcHj;
	/**fzWhjk*/
    @ApiModelProperty(value = "fzWhjk")
	private java.math.BigDecimal fzWhjk;
	/**fzQtyhjk*/
    @ApiModelProperty(value = "fzQtyhjk")
	private java.math.BigDecimal fzQtyhjk;
	/**fzSrjkhqtjk*/
    @ApiModelProperty(value = "fzSrjkhqtjk")
	private java.math.BigDecimal fzSrjkhqtjk;
	/**fzYfk*/
    @ApiModelProperty(value = "fzYfk")
	private java.math.BigDecimal fzYfk;
	/**fzQtfz*/
    @ApiModelProperty(value = "fzQtfz")
	private java.math.BigDecimal fzQtfz;
	/**fzWtrdb*/
    @ApiModelProperty(value = "fzWtrdb")
	private java.math.BigDecimal fzWtrdb;
	/**fzHj*/
    @ApiModelProperty(value = "fzHj")
	private java.math.BigDecimal fzHj;
	/**srZz*/
    @ApiModelProperty(value = "srZz")
	private java.math.BigDecimal srZz;
	/**srYz*/
    @ApiModelProperty(value = "srYz")
	private java.math.BigDecimal srYz;
	/**srLw*/
    @ApiModelProperty(value = "srLw")
	private java.math.BigDecimal srLw;
	/**srGsy*/
    @ApiModelProperty(value = "srGsy")
	private java.math.BigDecimal srGsy;
	/**srQtsr*/
    @ApiModelProperty(value = "srQtsr")
	private java.math.BigDecimal srQtsr;
	/**srHj*/
    @ApiModelProperty(value = "srHj")
	private java.math.BigDecimal srHj;
	/**zcJy*/
    @ApiModelProperty(value = "zcJy")
	private java.math.BigDecimal zcJy;
	/**zcYl*/
    @ApiModelProperty(value = "zcYl")
	private java.math.BigDecimal zcYl;
	/**zcYlao*/
    @ApiModelProperty(value = "zcYlao")
	private java.math.BigDecimal zcYlao;
	/**zcSccb*/
    @ApiModelProperty(value = "zcSccb")
	private java.math.BigDecimal zcSccb;
	/**zcRcsh*/
    @ApiModelProperty(value = "zcRcsh")
	private java.math.BigDecimal zcRcsh;
	/**zcQtzc*/
    @ApiModelProperty(value = "zcQtzc")
	private java.math.BigDecimal zcQtzc;
	/**zcHj*/
    @ApiModelProperty(value = "zcHj")
	private java.math.BigDecimal zcHj;
	/**zzchj*/
    @ApiModelProperty(value = "zzchj")
	private java.math.BigDecimal zzchj;
	/**jtjzc*/
    @ApiModelProperty(value = "jtjzc")
	private java.math.BigDecimal jtjzc;
	/**jtjsr*/
    @ApiModelProperty(value = "jtjsr")
	private java.math.BigDecimal jtjsr;
	/**shsyBlsh*/
    @ApiModelProperty(value = "shsyBlsh")
	private String shsyBlsh;
	/**shsySfqk*/
    @ApiModelProperty(value = "shsySfqk")
	private String shsySfqk;
	/**shsySfygld*/
    @ApiModelProperty(value = "shsySfygld")
	private String shsySfygld;
	/**shsySfdjns*/
    @ApiModelProperty(value = "shsySfdjns")
	private String shsySfdjns;
	/**shsySflqbz*/
    @ApiModelProperty(value = "shsySflqbz")
	private String shsySflqbz;
	/**shsySfxsfz*/
    @ApiModelProperty(value = "shsySfxsfz")
	private String shsySfxsfz;
	/**shsySfss*/
    @ApiModelProperty(value = "shsySfss")
	@Dict(dicCode = "sfss")
	private String shsySfss;
	/**dhzpjPxpj*/
    @ApiModelProperty(value = "dhzpjPxpj")
	private String dhzpjPxpj;
	/**dhzpjXypj*/
    @ApiModelProperty(value = "dhzpjXypj")
	private String dhzpjXypj;
	/**cpdj*/
    @ApiModelProperty(value = "cpdj")
	@Dict(dicCode = "pjsx_pddj")
	private String cpdj;
	/**qtbzqk*/
    @ApiModelProperty(value = "qtbzqk")
	private String qtbzqk;
	/**fzlx*/
    @ApiModelProperty(value = "fzlx")
	private String fzlx;
	/**sfjdlkpkh*/
    @ApiModelProperty(value = "sfjdlkpkh")
	private String sfjdlkpkh;


	/**更新人*/
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**id*/
	@ApiModelProperty(value = "流程ID串")
	private String procDefId;

	/**id*/
	@ApiModelProperty(value = "流程ID")
	private String procInstId;

	/**id*/
	@ApiModelProperty(value = "bussinessId")
	private String bussinessId;

	/**id*/
	@ApiModelProperty(value = "title")
	private String title;
	/**id*/
	@ApiModelProperty(value = "jtrs")
	private String jtrs;



	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;

	@ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;

	@ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal cknrpye;


	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;

	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;

	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;

	@ApiModelProperty(value = "表外不良贷款")
	private java.math.BigDecimal bwbldkye;

	@ApiModelProperty(value = "手机银行")
	private java.math.BigDecimal sjyhsl;

	@ApiModelProperty(value = "网上银行")
	private java.math.BigDecimal wsyhsl;

	@ApiModelProperty(value = "社保卡")
	private java.math.BigDecimal sbksl;

	@ApiModelProperty(value = "ETC")
	private java.math.BigDecimal etcsl;


	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;

}
