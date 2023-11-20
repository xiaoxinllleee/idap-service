package org.cmms.modules.sjxf.qtxt.cwglxt.fxdzcwjfldjb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 非信贷资产五级分类登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_non_crassetreg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_non_crassetreg对象", description="非信贷资产五级分类登记簿")
public class Fxdzcwjfldjb {
    
	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
    @ApiModelProperty(value = "资产编号")
	private String assetNo;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
	private String assetName;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15)
    @ApiModelProperty(value = "所属机构")
	private String brNo;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
    @ApiModelProperty(value = "资产类型")
	private String assetType;
	/**核算科目*/
	@Excel(name = "核算科目", width = 15)
    @ApiModelProperty(value = "核算科目")
	private String accNo;
	/**账面价值*/
	@Excel(name = "账面价值", width = 15)
    @ApiModelProperty(value = "账面价值")
	private Long depBal;
	/**资产取得日期*/
	@Excel(name = "资产取得日期", width = 15)
    @ApiModelProperty(value = "资产取得日期")
	private Integer opnDate;
	/**分类时间*/
	@Excel(name = "分类时间", width = 15)
    @ApiModelProperty(value = "分类时间")
	private Integer sortDate;
	/**预计损失*/
	@Excel(name = "预计损失", width = 15)
    @ApiModelProperty(value = "预计损失")
	private Long preLossBal;
	/**预计损失率*/
	@Excel(name = "预计损失率", width = 15)
    @ApiModelProperty(value = "预计损失率")
	private Integer preLossPer;
	/**背景资料*/
	@Excel(name = "背景资料", width = 15)
    @ApiModelProperty(value = "背景资料")
	private String backgroundInfo;
	/**风险因素分析*/
	@Excel(name = "风险因素分析", width = 15)
    @ApiModelProperty(value = "风险因素分析")
	private String riskFactorAly;
	/**损失测算*/
	@Excel(name = "损失测算", width = 15)
    @ApiModelProperty(value = "损失测算")
	private String lostTestDsp;
	/**初分意见*/
	@Excel(name = "初分意见", width = 15)
    @ApiModelProperty(value = "初分意见")
	private String firstSort;
	/**初分意见说明*/
	@Excel(name = "初分意见说明", width = 15)
    @ApiModelProperty(value = "初分意见说明")
	private String firstSortDisp;
	/**处置措施及管理办法*/
	@Excel(name = "处置措施及管理办法", width = 15)
    @ApiModelProperty(value = "处置措施及管理办法")
	private String handleDisp;
	/**初分类操作员*/
	@Excel(name = "初分类操作员", width = 15)
    @ApiModelProperty(value = "初分类操作员")
	private String frstSortTel;
	/**初分类日期*/
	@Excel(name = "初分类日期", width = 15)
    @ApiModelProperty(value = "初分类日期")
	private Integer firstSortDate;
	/**初审意见*/
	@Excel(name = "初审意见", width = 15)
    @ApiModelProperty(value = "初审意见")
	private String firstAppRs;
	/**初审认定结果*/
	@Excel(name = "初审认定结果", width = 15)
    @ApiModelProperty(value = "初审认定结果")
	private String firstAppDisp;
	/**初审操作员*/
	@Excel(name = "初审操作员", width = 15)
    @ApiModelProperty(value = "初审操作员")
	private Long firstAppTel;
	/**初审日期*/
	@Excel(name = "初审日期", width = 15)
    @ApiModelProperty(value = "初审日期")
	private Integer firstAppDate;
	/**初始认定结果*/
	@Excel(name = "初始认定结果", width = 15)
    @ApiModelProperty(value = "初始认定结果")
	private String firstCfrmRs;
	/**初始认定结果说明*/
	@Excel(name = "初始认定结果说明", width = 15)
    @ApiModelProperty(value = "初始认定结果说明")
	private String firstCfrmDisp;
	/**初始认定人*/
	@Excel(name = "初始认定人", width = 15)
    @ApiModelProperty(value = "初始认定人")
	private String firstCfrmTel;
	/**初始认定日期*/
	@Excel(name = "初始认定日期", width = 15)
    @ApiModelProperty(value = "初始认定日期")
	private Integer firstCfrmDate;
	/**认定状态*/
	@Excel(name = "认定状态", width = 15)
    @ApiModelProperty(value = "认定状态")
	private Integer assetSts;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
