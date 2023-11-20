package org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity;

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
 * @Description: 整村授信行政村进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zcsxjdb_cun")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zcsxjdb_cun对象", description="整村授信行政村进度表")
public class Zcsxxzcjdb {

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String zkhjl;
	/**父网格编号*/
	//@Excel(name = "父网格编号", width = 15)
    @ApiModelProperty(value = "父网格编号")
	private String parentWgbh;
	/**网格编号*/
	@Excel(name = "行政村", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	/**年份*/
	@ApiModelProperty(value = "年份")
	private String nf;
	/**实施批次*/
	@Excel(name = "实施批次", width = 15,dicCode = "ty_sspc")
	@ApiModelProperty(value = "实施批次")
	@Dict(dicCode = "ty_sspc")
	private String pc;
	/**村民小组个数*/
	@Excel(name = "村民小组个数", width = 15)
    @ApiModelProperty(value = "村民小组个数")
	private Integer cmxzgs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
	@ApiModelProperty(value = "总户数")
	private Long zhs;
	/**完成户数*/
	@Excel(name = "评议/采集户数", width = 15)
	@ApiModelProperty(value = "完成户数")
	private Long wchs;
	/**采集户数*/
	@Excel(name = "非白名单采集户数", width = 15)
	@ApiModelProperty(value = "采集户数")
	private Long cjhs;
	/**存量户数*/
	@Excel(name = "存量户数", width = 15)
    @ApiModelProperty(value = "存量户数")
	private Integer clhs;
	/**灰名单户数*/
	@Excel(name = "灰名单户数", width = 15)
    @ApiModelProperty(value = "灰名单户数")
	private Integer hmdhs;
	/**黑名单户数*/
	@Excel(name = "黑名单户数", width = 15)
    @ApiModelProperty(value = "黑名单户数")
	private Integer heimdhs;
	/**初筛白名单户数*/
	@Excel(name = "初筛白名单户数", width = 15)
	@ApiModelProperty(value = "初筛白名单户数")
	private Integer bmdhs;
	/**是否合格*/
	//@Excel(name = "是否合格", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否合格")
	@Dict(dicCode = "sfbz")
	private Integer zcsxYssfhg;
	/**初评白名单完成户数*/
	@Excel(name = "完成户数", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评完成户数")
	private Integer bkbcpBmdWchs;
	/**初评白名单完成比率*/
	@Excel(name = "白名单完成比例", width = 15,groupName = "背靠背初评")
	@ApiModelProperty(value = "白名单完成比例")
	private java.math.BigDecimal bkbcpBmdWcbl;
	/**初评完成比率*/
//	@Excel(name = "白名单完成比率", width = 15,groupName = "背靠背初评")
//	@ApiModelProperty(value = "白名单完成比率")
//	private java.math.BigDecimal bkbcpWcbl;
	/**初评本月完成户数*/
	//@Excel(name = "初评本月完成户数", width = 15)
    @ApiModelProperty(value = "初评本月完成户数")
	private Integer bkbcpBywchs;
	/**初评本周完成户数*/
	//@Excel(name = "初评本周完成户数", width = 15)
    @ApiModelProperty(value = "初评本周完成户数")
	private Integer bkbcpBzwchs;
	/**初评上月完成户数*/
	//@Excel(name = "初评上月完成户数", width = 15)
    @ApiModelProperty(value = "初评上月完成户数")
	private Integer bkbcpSywchs;
	/**初评上周完成户数*/
	//@Excel(name = "初评上周完成户数", width = 15)
    @ApiModelProperty(value = "初评上周完成户数")
	private Integer bkbcpSzwchs;
	/**初评较上月完成户数*/
//	@Excel(name = "本月新增", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评较上月完成户数")
	private Integer bkbcpBmdJsywchs;
	/**初评较上周完成户数*/
//	@Excel(name = "本周新增", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评较上周完成户数")
	private Integer bkbcpBmdJszwchs;
	/**初评授信对象人数*/
	@Excel(name = "初评授信对象人数", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评授信对象人数")
	private Integer bkbcpSxdxrs;
	/**初评额度汇总*/
	@Excel(name = "初评额度汇总(万元)", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评额度汇总")
	private java.math.BigDecimal bkbcpEdhz;
	/**初评完成小组个数*/
	@Excel(name = "完成小组个数", width = 15,groupName = "背靠背初评")
	@ApiModelProperty(value = "完成小组个数")
	private Integer bkbcpWcxzgs;
	/**初评完成户数*/
	//@Excel(name = "初评完成户数", width = 15)
    @ApiModelProperty(value = "初评完成户数")
	private Integer bkbcpWchs;
	/**初评白名单本月完成户数*/
	//@Excel(name = "初评白名单本月完成户数", width = 15)
    @ApiModelProperty(value = "初评白名单本月完成户数")
	private Integer bkbcpBmdBywchs;
	/**初评白名单本周完成户数*/
	//@Excel(name = "初评白名单本周完成户数", width = 15)
    @ApiModelProperty(value = "初评白名单本周完成户数")
	private Integer bkbcpBmdBzwchs;
	/**初评白名单上月完成户数*/
	//@Excel(name = "初评白名单上月完成户数", width = 15)
    @ApiModelProperty(value = "初评白名单上月完成户数")
	private Integer bkbcpBmdSywchs;
	/**初评白名单上周完成户数*/
	//@Excel(name = "初评白名单上周完成户数", width = 15)
    @ApiModelProperty(value = "初评白名单上周完成户数")
	private Integer bkbcpBmdSzwchs;
	/**初评较上月完成户数*/
//	@Excel(name = "较上月", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "较上月")
	private Integer bkbcpJsywchs;
	/**初评较上周完成户数*/
//	@Excel(name = "较上周", width = 15,groupName = "背靠背初评")
    @ApiModelProperty(value = "初评较上周完成户数")
	private Integer bkbcpJszwchs;
	/**复评完成户数*/
	@Excel(name = "完成户数", width = 15,groupName = "背靠背复评")
    @ApiModelProperty(value = "完成户数")
	private Integer bkbfpWchs;
	/**复评授信对象人数*/
	@Excel(name = "复评授信对象人数", width = 15,groupName = "背靠背复评")
    @ApiModelProperty(value = "复评授信对象人数")
	private Integer bkbfpSxdxrs;
	/**复评额度汇总*/
	@Excel(name = "复评额度汇总(万元)", width = 15,groupName = "背靠背复评")
    @ApiModelProperty(value = "复评额度汇总")
	private java.math.BigDecimal bkbfpEdhz;
	/**复评授信对象人数*/
	@Excel(name = "完成小组个数", width = 15,groupName = "背靠背复评")
	@ApiModelProperty(value = "完成小组个数")
	private Integer bkbfpWcxzgs;
	/**支行审定白名单户数*/
	@Excel(name = "支行审定白名单户数", width = 15,groupName = "行内审定")
    @ApiModelProperty(value = "支行审定白名单户数")
	private Integer zhsdBmdhs;
	/**支行审定授信对象人数*/
	@Excel(name = "支行审定授信对象人数", width = 15,groupName = "行内审定")
    @ApiModelProperty(value = "支行审定授信对象人数")
	private Integer zhsdSxdxrs;
	/**支行审定额度汇总*/
	@Excel(name = "审定额度汇总(万元)", width = 15,groupName = "行内审定")
    @ApiModelProperty(value = "审定额度汇总")
	private java.math.BigDecimal zhsdEdhz;
	/**支行审定完成时间*/
	@Excel(name = "支行审定完成时间", width = 15, format = "yyyy-MM-dd",groupName = "行内审定")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "支行审定完成时间")
	private Date zhsdWcsj;
	/**惠农快贷导入户数*/
	@Excel(name = "导入成功客户数", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "导入成功客户数")
	private Integer hnkdDrhs;
	/**惠农快贷额度汇总*/
	@Excel(name = "导入额度汇总(万元)", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "导入额度汇总")
	private java.math.BigDecimal hnkdEdhz;
	/**支行审定惠农快贷比*/
	@Excel(name = "支行审定白名单导入惠农快贷占比", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "支行审定白名单导入惠农快贷占比")
	private java.math.BigDecimal hnkdZhsdHnkdZb;
	/**惠农快贷电话回访户数*/
	@Excel(name = "电话回访客户数", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "电话回访客户数")
	private Integer hnkdDhhfhs;
	/**惠农快贷签约户数*/
	@Excel(name = "惠农快贷签约客户数", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "惠农快贷签约客户数")
	private Integer hnkdQyhs;
	/**惠农快贷签约额度汇总*/
	@Excel(name = "惠农快贷签约额度汇总(万元)", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "惠农快贷签约额度汇总")
	private java.math.BigDecimal hnkdQyedhz;
	/**惠农快贷签约率*/
	@Excel(name = "惠农快贷白名单签约率", width = 15,groupName = "惠农快贷")
    @ApiModelProperty(value = "惠农快贷白名单签约率")
	private java.math.BigDecimal hnkdBmdQyl;
	/**惠农快贷数据日期*/
//	@Excel(name = "惠农快贷数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "惠农快贷数据日期")
	private Date hnkdSjrq;
	/**实地走访营销客户数*/
	@Excel(name = "实地走访营销客户数", width = 15,groupName = "精准营销")
    @ApiModelProperty(value = "实地走访营销客户数")
	private Integer jzyxSdzfYxhs;
	/**电话走访营销户数*/
	@Excel(name = "电话走访营销客户数", width = 15,groupName = "精准营销")
    @ApiModelProperty(value = "电话走访营销客户数")
	private Integer jzyxDhzfYxhs;
	/**精准营销总户数*/
	@Excel(name = "精准营销总客户数", width = 15,groupName = "精准营销")
    @ApiModelProperty(value = "精准营销总客户数")
	private Integer jzyxZhs;
	/**精准营销本月户数*/
	//@Excel(name = "精准营销本月户数", width = 15)
    @ApiModelProperty(value = "精准营销本月户数")
	private Integer jzyxByhs;
	/**精准营销本周户数*/
	//@Excel(name = "精准营销本周户数", width = 15)
    @ApiModelProperty(value = "精准营销本周户数")
	private Integer jzyxBzhs;
	/**精准营销上月户数*/
	//@Excel(name = "精准营销上月户数", width = 15)
    @ApiModelProperty(value = "精准营销上月户数")
	private Integer jzyxSyhs;
	/**精准营销上周户数*/
	//@Excel(name = "精准营销上周户数", width = 15)
    @ApiModelProperty(value = "精准营销上周户数")
	private Integer jzyxSzhs;
	/**精准营销较上月户数*/
	@Excel(name = "本月新增", width = 15,groupName = "精准营销")
	@ApiModelProperty(value = "精准营销较上月户数")
	private Integer jzyxJsyhs;
	/**精准营销较上周户数*/
	@Excel(name = "本周新增", width = 15,groupName = "精准营销")
	@ApiModelProperty(value = "精准营销较上周户数")
	private Integer jzyxJszhs;
	/**精准营销完成比率*/
	@Excel(name = "精准营销完成比率", width = 15,groupName = "精准营销")
    @ApiModelProperty(value = "精准营销完成比率")
	private java.math.BigDecimal jzyxWcbl;
	/**贷款合同新增客户数*/
	@Excel(name = "新增客户数", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "新增客户数")
	private Integer dkhtXzkhs;
	/**贷款合同本月新增户数*/
	//@Excel(name = "贷款合同本月新增户数", width = 15)
    @ApiModelProperty(value = "贷款合同本月新增户数")
	private Integer dkhtByxzkhs;
	/**贷款合同本周新增户数*/
	//@Excel(name = "贷款合同本周新增户数", width = 15)
    @ApiModelProperty(value = "贷款合同本周新增户数")
	private Integer dkhtBzxzkhs;
	/**贷款合同上月新增户数*/
	//@Excel(name = "贷款合同上月新增户数", width = 15)
    @ApiModelProperty(value = "贷款合同上月新增户数")
	private Integer dkhtSyxzkhs;
	/**贷款合同上周新增户数*/
	//@Excel(name = "贷款合同上周新增户数", width = 15)
    @ApiModelProperty(value = "贷款合同上周新增户数")
	private Integer dkhtSzxzkhs;
	/**贷款合同较上月新增户数*/
	@Excel(name = "本月新增", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "较上月")
	private Integer dkhtJsyxzkhs;
	/**贷款合同较上周新增户数*/
	@Excel(name = "本周新增", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "贷款合同较上周新增户数")
	private Integer dkhtJszxzkhs;
	/**贷款合同年轻客户数*/
	@Excel(name = "年轻客户签约户数", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "贷款合同年轻客户数")
	private Integer dkhtNqkhs;
	/**贷款合同签约额度汇总*/
	@Excel(name = "新增签约额度汇总(万元)", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "新增签约额度汇总")
	private java.math.BigDecimal dkhtQyedhz;
	/**贷款合同年轻签约度汇总*/
	@Excel(name = "年轻客户签约金额", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "年轻客户签约金额")
	private java.math.BigDecimal dkhtNqkhQyedhz;
	/**贷款合同审定签约率*/
	@Excel(name = "支行审定白名单新增签约率", width = 15,groupName = "新增贷款合同客户数")
    @ApiModelProperty(value = "贷款合同审定签约率")
	private java.math.BigDecimal dkhtZhsdQyl;
	/**入库日期*/
//	@Excel(name = "入库日期", width = 15, format = "yyyy-MM-dd",groupName = "新增贷款合同客户数")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "入库日期")
	private Date rkrq;
	/**用信新增客户数*/
	@Excel(name = "新增用信户数", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信新增客户数")
	private Integer dkyxXzkhs;
	/**用信本月新增客户数*/
	//@Excel(name = "用信本月新增客户数", width = 15)
    @ApiModelProperty(value = "用信本月新增客户数")
	private Integer dkyxByxzkhs;
	/**用信本周新增客户数*/
	//@Excel(name = "用信本周新增客户数", width = 15)
    @ApiModelProperty(value = "用信本周新增客户数")
	private Integer dkyxBzxzkhs;
	/**用信上月新增客户数*/
	//@Excel(name = "用信上月新增客户数", width = 15)
    @ApiModelProperty(value = "用信上月新增客户数")
	private Integer dkyxSyxzkhs;
	/**用信上周新增客户数*/
	//@Excel(name = "用信上周新增客户数", width = 15)
    @ApiModelProperty(value = "用信上周新增客户数")
	private Integer dkyxSzxzkhs;
	/**用信较上月新增客户数*/
	@Excel(name = "本月新增", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信较上月新增客户数")
	private Integer dkyxJsyxzkhs;
	/**用信较上周新增客户数*/
//	@Excel(name = "本周新增", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信较上周新增客户数")
	private Integer dkyxJszxzkhs;
	/**用信额度汇总*/
	@Excel(name = "新增用信额度汇总(万元)", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信额度汇总")
	private java.math.BigDecimal dkyxEdhz;
	/**用信新增有效用信客户数*/
	@Excel(name = "新增有效用信户数", width = 1,groupName = "新增用信")
    @ApiModelProperty(value = "新增有效用信户数")
	private Integer dkyxXzyxyxkhs;
	/**用信本月新增有效用信客户数*/
	//@Excel(name = "用信本月新增有效用信客户数", width = 15)
    @ApiModelProperty(value = "用信本月新增有效用信客户数")
	private Integer dkyxByxzyxyxkhs;
	/**用信本周新增有效用信客户数*/
	//@Excel(name = "用信本周新增有效用信客户数", width = 15)
    @ApiModelProperty(value = "用信本周新增有效用信客户数")
	private Integer dkyxBzxzyxyxkhs;
	/**用信上月新增有效用信客户数*/
	//@Excel(name = "用信上月新增有效用信客户数", width = 15)
    @ApiModelProperty(value = "用信上月新增有效用信客户数")
	private Integer dkyxSyxzyxyxkhs;
	/**用信上周新增有效用信客户数*/
	//@Excel(name = "用信上周新增有效用信客户数", width = 15)
    @ApiModelProperty(value = "用信上周新增有效用信客户数")
	private Integer dkyxSzxzyxyxkhs;
	/**用信较上月新增有效用信客户数*/
	@Excel(name = "本月新增", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信较上月新增有效用信客户数")
	private Integer dkyxJsyxzyxyxkhs;
	/**用信较上周新增有效用信客户数*/
//	@Excel(name = "本周新增", width = 15,groupName = "新增用信")
    @ApiModelProperty(value = "用信较上周新增有效用信客户数")
	private Integer dkyxJszxzyxyxkhs;

	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}
