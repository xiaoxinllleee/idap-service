package org.cmms.modules.khgl.nh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 农户基本信息
 * @Author: jeecg-boot
 * @Date:   2020-06-02
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_nhjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_nhjbxx对象", description="农户基本信息")
public class VKhglNhjbxx {

	/**ID*/
//	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "网格名称", width = 15, dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	@ApiModelProperty(value = "区域编码")
	@Dict(dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	private String ssyxdy;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
	@ApiModelProperty(value = "ssxz")
	@Dict(dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
	@ApiModelProperty(value = "xzc")
	@Dict(dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	private String xzc;
	/**行政组*/
	@Excel(name = "行政组", width = 15)
	@ApiModelProperty(value = "xzz")
	@Dict(dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	private String xzz;
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
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15)
	@ApiModelProperty(value = "民族")
	@Dict(dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk")
	@ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	@ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
	@ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**是否领取社保卡(1.是;2.否)*/
	@Excel(name = "是否领取社保卡(1.是;2.否)", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否领取社保卡(1.是;2.否)")
	@Dict(dicCode = "sfbz")
	private Integer sflqsbk;
	/**是否开通社保卡(1.是;2.否)*/
	@Excel(name = "是否开通社保卡(1.是;2.否)", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通社保卡(1.是;2.否)")
	@Dict(dicCode = "sfbz")
	private Integer sfktsbk;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**系统评定结果(1:灰名单;2:白名单;3:黑名单)*/
	@Excel(name = "系统评定结果(1-灰名单/2-白名单/3-黑名单)", width = 15, dicCode = "pdgl_pdjg")
	@ApiModelProperty(value = "系统评定结果(1:灰名单;2:白名单;3:黑名单)")
	@Dict(dicCode = "xtpdjg")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
	@ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
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
	/**是否吸毒人员*/
	@Excel(name = "是否吸毒人员", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否吸毒人员")
	@Dict(dicCode = "sfbz")
	private String sfxdry;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否贫困户")
	@Dict(dicCode = "sfbz")
	private String sfpkh;
	/**是否低保*/
	@Excel(name = "是否低保", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否低保")
	@Dict(dicCode = "sfbz")
	private String sfdb;
	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否公职人员")
	private String sfgzry;
	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
    @ApiModelProperty(value = "患病记录")
	private String hbjl;
	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
	private java.math.BigDecimal infoRate;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15,dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String sskhjl;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15,dicCode = "whcd")
	@ApiModelProperty(value = "文化程度")
	@Dict(dicCode = "whcd")
	private String whcd;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15,dicCode = "khgl_hkxz")
	@ApiModelProperty(value = "户口性质")
	@Dict(dicCode = "khgl_hkxz")
	private String hkxz;
	/**采集时间*/
	@Excel(name = "采集时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "采集时间")
	private Date cjsj;
	/**采集人*/
	@Excel(name = "采集人", width = 15,dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "采集人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String cjr;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String upDt;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15)
    @ApiModelProperty(value = "更新时间")
	private String upTm;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否采集")
	@Dict(dicCode = "sfbz")
	private Integer sfcj;
	/**是否有评议记录*/
	@Excel(name = "是否有评议记录", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有评议记录")
	@Dict(dicCode = "sfbz")
	private Integer sfypyjl;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
	@ApiModelProperty(value = "客户经理授信额度")
	private BigDecimal khjlsxed;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
	@ApiModelProperty(value = "最终授信额度")
	private BigDecimal nhzzsxed;
	/**外部评议数量*/
	@Excel(name = "外部评议数量", width = 15)
	@ApiModelProperty(value = "wbpysl")
	private Integer wbpysl;
	/**评议信用等级*/
	@Excel(name = "评议信用等级", width = 15)
	@ApiModelProperty(value = "pyxydj")
	@Dict(dicCode = "bkbpy_xydj")
	private String pyxydj;
	/**评议授信额度*/
	@Excel(name = "评议授信额度", width = 15)
	@ApiModelProperty(value = "pysxed")
	private BigDecimal pysxed;
	/**评议平均得分*/
	@Excel(name = "评议平均得分", width = 15)
	@ApiModelProperty(value = "评议平均得分")
	private BigDecimal pypjdf;
	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private Integer sfyxzf;
}
