package org.cmms.modules.khgl.khxx.entity;

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
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_khjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_khjbxx对象", description="客户信息管理")
public class vKhglKhjbxx {

	/**ID*/
	//@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "区域编码", width = 15, dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
	@ApiModelProperty(value = "区域编码")
	@Dict(dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
	private String ssyxdy;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15,dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "ssxz")
	@Dict( dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "xzc")
	@Dict( dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	private String xzc;
	/**行政组*/
	@Excel(name = "行政组", width = 15,dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "xzz")
	@Dict( dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	private String xzz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	private String jgdm;
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
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	@ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
	@ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**系统评定结果(1:灰名单;2:白名单;3:黑名单)*/
	@Excel(name = "系统评定结果(1-灰名单/2-白名单/3-黑名单)", width = 15, dicCode = "pdgl_pdjg")
	@ApiModelProperty(value = "系统评定结果(1:灰名单;2:白名单;3:黑名单)")
	@Dict(dicCode = "pdgl_pdjg")
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
	/**infoRate*/
	@Excel(name = "infoRate", width = 15)
	@ApiModelProperty(value = "infoRate")
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
	/**upDt*/
	@Excel(name = "upDt", width = 15)
	@ApiModelProperty(value = "upDt")
	private String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
	@ApiModelProperty(value = "upTm")
	private String upTm;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否采集")
	@Dict(dicCode = "sfbz")
	private Integer sfcj;

	/**是否采集*/
	@Excel(name = "是否有评议记录", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有评议记录")
	@Dict(dicCode = "sfbz")
	private Integer sfypyjl;
}
