package org.cmms.modules.khgl.nh.vo;

import java.util.List;

import org.cmms.modules.khgl.nh.entity.*;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
public class KhglKhhmcxxPage {


	/**id*/
	private String id;

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	private String jgdm;

	/**所属支行*/
	@Excel(name = "所属支行", width = 15 ,dicCode="zzbz", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "区域编码", width = 15, dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
	private String ssyxdy;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
	private String yxzc;
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
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15,dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk")
	private String hyzk;

	/**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
	@Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15,dicCode = "xtpdjg")
	private Integer xtpdjg;
	
	/**系统评定说明*/
  	@Excel(name = "系统评定说明", width = 15)
	private String xtpdsm;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
	/**是否吸毒人员*/
  	@Excel(name = "是否吸毒人员", width = 15, dicCode = "sfbz")
	private String sfxdry;
	/**是否贫困户*/
  	@Excel(name = "是否贫困户", width = 15, dicCode = "sfbz")
	private String sfpkh;
	/**是否低保*/
  	@Excel(name = "是否低保", width = 15, dicCode = "sfbz")
	private String sfdb;
	/**是否开通信用卡*/
  	@Excel(name = "是否开通信用卡", width = 15, dicCode = "sfbz")
	private String sfktxyk;
	/**是否开通福民卡*/
  	@Excel(name = "是否开通福民卡", width = 15, dicCode = "sfbz")
	private String sfktfmk;
	/**是否公职人员*/
  	@Excel(name = "是否公职人员", width = 15, dicCode = "sfbz")
	private String sfgzry;
	/**是否非法集资*/
  	@Excel(name = "是否非法集资", width = 15, dicCode = "sfbz")
	private String sfffjz;
	/**是否开通扫码付*/
  	@Excel(name = "是否开通扫码付", width = 15, dicCode = "sfbz")
	private String sfktsmf;
	/**是否领取社保卡（1：是 2：否）*/
  	@Excel(name = "是否领取社保卡（1：是 2：否）", width = 15, dicCode = "sfbz")
	private String sflqsbk;
	/**是否开通社保卡（1：是 2：否）*/
  	@Excel(name = "是否开通社保卡（1：是 2：否）", width = 15, dicCode = "sfbz")
	private String sfktsbk;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
	private String hbjl;
	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
	private java.math.BigDecimal infoRate;

	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**录入时间*/
  	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入人*/
  	@Excel(name = "录入人", width = 15)
	private String lrr;

	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	private String xgr;
	
	@ExcelCollection(name="农户采集信息表")
	private List<CamsZcsxNhcjxx> camsZcsxNhcjxxList;
	@ExcelCollection(name="农户房产信息")
	private List<Nhfcxx> nhfcxxList;
	@ExcelCollection(name="与我行相关业务")
	private List<Ywhywwlxx> ywhxgywList;
	@ExcelCollection(name="农户评级授信信息")
	private List<NhPjsxxx> nhPjsxxxList;
	@ExcelCollection(name="农户附件信息")
	private List<Fjgl> fjglList;
	
}
