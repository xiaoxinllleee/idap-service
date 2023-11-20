package org.cmms.modules.khgl.grkhgl.vo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khgl.grkhgl.entity.*;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import java.util.Date;
import java.util.List;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
public class KhglKrkhglPage {

	/**id*/
	private String id;

	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "区域编码", width = 15)
	private String ssyxdy;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@TableId(value = "zjhm", type= IdType.INPUT)
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String zz;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15)
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk_cj")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
	private String yxzc;
	/**系统评定结果(1:灰名单;2:白名单;3:黑名单)*/
	@Excel(name = "系统评定结果(1-灰名单/2-白名单/3-黑名单)", width = 15, dicCode = "pdgl_pdjg")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)

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
	private String sfxdry;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15,dicCode = "sfbz")
	private String sfpkh;
	/**是否低保*/
	@Excel(name = "是否低保", width = 15,dicCode = "sfbz")
	private String sfdb;
	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15, dicCode = "sfbz")
	private String sfgzry;
	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15, dicCode = "sfbz")
	private String sfffjz;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
	private String hbjl;

	/**是否领取社保卡(1.是;2.否)*/
	@Excel(name = "是否领取社保卡(1.是;2.否)", width = 15,dicCode = "sfbz")

	private String sflqsbk;
	/**是否开通社保卡(1.是;2.否)*/
	@Excel(name = "是否开通社保卡(1.是;2.否)", width = 15,dicCode = "sfbz")
	private String sfktsbk;

	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
	private java.math.BigDecimal infoRate;
	/**出生年月*/
	@Excel(name = "出生年月", width = 15)
	@ApiModelProperty(value = "出生年月")
	private String csny;
	/**cszy*/
	/**从事职业*/
	@Excel(name = "从事职业", width = 15)
	private String cszy;

	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;

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

	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	private String sskhjl;
	/**是否主客户经理*/
	@Excel(name = "是否主客户经理", width = 15,dicCode = "sfbz")
	private String sfzkhjl;
	/**一级营销单元编号*/
	@Excel(name = "一级营销单元编号", width = 15)
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "二级营销单元编号", width = 15)
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "三级营销单元编号", width = 15)
	private String sjyxdybh;

	/**采集时间*/
	@Excel(name = "采集时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "采集时间")
	private Date cjsj;
	/**采集人*/
	@Excel(name = "采集人", width = 15,dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	private String cjr;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15,dicCode = "sfbz")
	private Integer sfcj;

	@ApiModelProperty(value = "具体从事项目")
	private String jtcsxm;

	@ApiModelProperty(value = "为收集户情况")
	private String sjhyy;

	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;

	@ApiModelProperty(value = "采集状态")
	private String cjzt;

	@ApiModelProperty(value = "采集完成状态")
	private String cjwczt;
	@ApiModelProperty(value = "信息提供者签章")
	private String sign1;
	@ApiModelProperty(value = "授权人签字")
	private String sign2;
	@ApiModelProperty(value = "配偶签字")
	private String sign3;
	/**附件数据*/
	@Excel(name = "附件数据", width = 15)
	@ApiModelProperty(value = "附件数据")
	private JSONObject imgdate;

	/**是否授信对象*/
	@Excel(name = "是否授信对象", width = 15,dicCode = "sfbz")
	private String sfsxdx;

	@ExcelCollection(name="客户花名册信息")
	private List<Khhmcxx> khhmcxxList;

	@ExcelCollection(name="农户评级授信信息")
	private List<CamsZcsxGrpjsxxx> camsZcsxGrpjsxxxList;


}
