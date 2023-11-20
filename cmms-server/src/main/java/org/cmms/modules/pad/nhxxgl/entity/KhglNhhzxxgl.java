package org.cmms.modules.pad.nhxxgl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-30
 * @Version: V1.0
 */
@Data
@TableName("khgl_nhhzxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_nhhzxxgl对象", description="1")
public class KhglNhhzxxgl {

	/**是否不良贷款客户*/
	@Excel(name = "是否不良贷款客户", width = 15)
    @ApiModelProperty(value = "是否不良贷款客户")
	private String sfbldkh;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15)
    @ApiModelProperty(value = "是否低保户")
	private String sfdbh;
	/**是否惠农补贴户*/
	@Excel(name = "是否惠农补贴户", width = 15)
	@ApiModelProperty(value = "是否惠农补贴户")
	private String sfhnbth;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15)
    @ApiModelProperty(value = "客户重要程度")
	private String khzycd;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private String khqzyw;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15)
    @ApiModelProperty(value = "客户授信情况")
	private String khsxqk;
	/**客户等级*/
	@Excel(name = "客户等级", width = 15)
    @ApiModelProperty(value = "客户等级")
	private String khdj;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private String hkxz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrry;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**陪访人*/
	@Excel(name = "总行陪访人", width = 15)
    @ApiModelProperty(value = "总行陪访人")
	private String zhpfr;
	/**授信对象证件号*/
	@Excel(name = "授信对象证件号", width = 15)
    @ApiModelProperty(value = "授信对象证件号")
	private String sxdxzjh;
	/**户主证件号码*/
	@Excel(name = "户主证件号码", width = 15)
    @ApiModelProperty(value = "户主证件号码")
	private String hzzjhm;
	/** ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = " ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")

	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	@Dict( dicCode="dybh", dictTable="yxdygl_ejyxdygl", dicText="dymc")
	private String xzc;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String ssyxdy;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户主姓名*/
	@Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "户主姓名")
	private String hzxm;
	/**授信对象*/
	@Excel(name = "授信对象", width = 15)
    @ApiModelProperty(value = "授信对象")
	private String sxdx;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
	/**外部评议*/
	@Excel(name = "外部评议", width = 15)
    @ApiModelProperty(value = "外部评议")
	private String wbpy;
	/**信息完善*/
	@Excel(name = "信息完善", width = 15)
    @ApiModelProperty(value = "信息完善")
	private String xxws;
	/**评级授信*/
	@Excel(name = "评级授信", width = 15)
    @ApiModelProperty(value = "评级授信")
	@Dict(dicCode = "sfsx")
	private String pjsx;
	/**用信状态*/
	@Excel(name = "用信状态", width = 15)
    @ApiModelProperty(value = "用信状态")
	private String yxzt;

	/**是否走访*/
	@Excel(name = "是否走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否走访")
	@Dict(dicCode = "sfbz")
	private String sfzf;

	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;

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
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
	@ApiModelProperty(value = "修改日期")
	private String upDt;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15)
	@ApiModelProperty(value = "修改时间")
	private String upTm;

	/**服务跟踪人*/
	@Excel(name = "服务跟踪人", width = 15)
	@ApiModelProperty(value = "服务跟踪人")
	private String fwgzr;

	private String khfxd;
	private String thywqk;
	/**客户类型描述*/
	@Excel(name = "客户类型描述", width = 15)
	@ApiModelProperty(value = "客户类型描述")
	private String khlxms;
	/**停留时间*/
	@Excel(name = "停留时间", width = 15)
	@ApiModelProperty(value = "停留时间")
	private Integer tlsj;

	/**是否散户*/
	@Excel(name = "是否散户", width = 15)
	@ApiModelProperty(value = "是否散户")
	@Dict(dicCode = "sfbz")
	private String sfsh;

	/**走访时间*/
	@Excel(name = "走访时间", width = 15)
	@ApiModelProperty(value = "走访时间")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date zfrq;

	/**走访地址*/
	@Excel(name = "走访地址", width = 15)
	@ApiModelProperty(value = "走访地址")
	private String zfdz;

	/**走访面见人*/
	@Excel(name = "走访面见人", width = 15)
	@ApiModelProperty(value = "走访面见人")
	private String zfmjr;

	/**与目标客户关系*/
	@Excel(name = "与目标客户关系", width = 15)
	@ApiModelProperty(value = "与目标客户关系")
	private String ymbkhgx;

	/**是否加微信好友*/
	@Excel(name = "是否加微信好友", width = 15)
	@ApiModelProperty(value = "是否加微信好友")
	@Dict(dicCode = "sfbz")
	private String sfjwxhy;

	/**贷款意向书*/
	@Excel(name = "贷款意向书", width = 15)
	@ApiModelProperty(value = "贷款意向书")
	@Dict(dicCode = "sfbz")
	private String dkyxs;

	/**走访人员*/
	@Excel(name = "走访人员", width = 15)
	@ApiModelProperty(value = "走访人员")
	private String zfry;

	@TableField(exist = false)
	@ApiModelProperty(value = "走访原因")
	@Dict(dicCode = "zfyy")
	private String zfyy;
}
