package org.cmms.modules.khgl.grkhgl.entity;

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
 * @Description: 个人客户
 * @Author: jeecg-boot
 * @Date:   2020-07-03
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_grkhgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_grkhgl对象", description="个人客户")
public class vKhglKrkhgl {

	/**ID*/
	//@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**机构代码*/
	@ApiModelProperty(value = "机构代码")
	private String jgdm;

	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
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

	/**所属营销单元*/
	@ApiModelProperty(value = "区域编码")
	@Dict(dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
	private String ssyxdy;


	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String khjl;


	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;

	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
	@ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@TableId(value = "zjhm", type=IdType.INPUT)
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;


	/**出生年月*/
	@Excel(name = "出生年月", width = 15)
	@ApiModelProperty(value = "出生年月")
	private String csny;

	/**客户类型*/
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;

	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	//@Excel(name = "年龄", width = 15)
	//@ApiModelProperty(value = "年龄")
	//private String nl;
	/**民族*/
	//@Excel(name = "民族", width = 15)
	//@ApiModelProperty(value = "民族")
	//@Dict(dicCode = "mz")
	//private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk_cj")
	@ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk_cj")
	private String hyzk;
	/**cszy*/
	/**从事职业*/
	@Excel(name = "从事职业", width = 15,dicCode = "cszy")
	@ApiModelProperty(value = "cszy")
	private String cszy;

	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String zz;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;

	@Excel(name = "采集状态", width = 15,dicCode = "cjzt")
	@ApiModelProperty(value = "采集状态")
	@Dict(dicCode = "cjzt")
	private String cjzt;
	/**原所属乡镇*/
	@ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;

	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgr;


	/**是否主客户经理*/
	@ApiModelProperty(value = "是否主客户经理")
	@Dict(dicCode = "sfbz")
	private String sfzkhjl;


	/**采集时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "采集时间")
	private Date cjsj;
	/**采集人*/
	@Excel(name = "采集人", width = 15,dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "采集人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String cjr;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否采集")
	@Dict(dicCode = "sfbz")
	private Integer sfcj;

	/**是否授信对象*/
	@Excel(name = "是否授信对象", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否授信对象")
	@Dict(dicCode = "sfbz")
	private String sfsxdx;

	/**家庭人数*/
	//@ApiModelProperty(value = "家庭人数")
	//private String jtrs;

	/**职业*/
	//@ApiModelProperty(value = "职业")
	//private String zy;

	/**健康状况*/
	//@ApiModelProperty(value = "健康状况")
	//private String jkzk;
	/**最高学历*/
	//@ApiModelProperty(value = "最高学历")
	//private String zgxl;
	/**职称*/
	//@ApiModelProperty(value = "职称")
	//private String zc;
	/**电子签名1*/
	@ApiModelProperty(value = "电子签名1")
	private String sign1;
	/**电子签名2*/
	@ApiModelProperty(value = "电子签名2")
	private String sign2;
	/**电子签名3*/
	@ApiModelProperty(value = "电子签名3")
	private String sign3;
	/**采集完成状态*/
	@ApiModelProperty(value = "采集完成状态")
	@Dict(dicCode = "cjwczt")
	private String cjwczt;
	@ApiModelProperty(value = "为收集户情况")
	private String sjhyy;

	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;

	@ApiModelProperty(value = "具体从事项目")
	private String jtcsxm;

	@TableField(exist=false)
	private String year;
}
