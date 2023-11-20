package org.cmms.modules.sjxf.xdxt.bzrxx.entity;

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
 * @Description: 保证人信息
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_assurer")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_assurer对象", description="保证人信息")
public class Bzrxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**保证效力*/
	@Excel(name = "保证效力", width = 15)
    @ApiModelProperty(value = "保证效力")
	private String bzxl;
	/**代偿能力*/
	@Excel(name = "代偿能力", width = 15)
    @ApiModelProperty(value = "代偿能力")
	private String dcnl;
	/**核保人*/
	@Excel(name = "核保人", width = 15)
    @ApiModelProperty(value = "核保人")
	private String checkUserId;
	/**担保合同号*/
	@Excel(name = "担保合同号", width = 15)
    @ApiModelProperty(value = "担保合同号")
	private String contactNo;
	/**担保币种*/
	@Excel(name = "担保币种", width = 15)
    @ApiModelProperty(value = "担保币种")
	private String currencyType;
	/**借款人客户名称*/
	@Excel(name = "借款人客户名称", width = 15)
    @ApiModelProperty(value = "借款人客户名称")
	private String custId;
	/**实际保证能力*/
	@Excel(name = "实际保证能力", width = 15)
    @ApiModelProperty(value = "实际保证能力")
	private java.math.BigDecimal factSureCaption;
	/**公证书编号*/
	@Excel(name = "公证书编号", width = 15)
    @ApiModelProperty(value = "公证书编号")
	private String guarantyNdNo;
	/**保证人对外已担保金额*/
	@Excel(name = "保证人对外已担保金额", width = 15)
    @ApiModelProperty(value = "保证人对外已担保金额")
	private java.math.BigDecimal hasAssurerAmt;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**是否保证中心*/
	@Excel(name = "是否保证中心", width = 15)
    @ApiModelProperty(value = "是否保证中心")
	private String isAssurerCenter;
	/**是否反担保*/
	@Excel(name = "是否反担保", width = 15)
    @ApiModelProperty(value = "是否反担保")
	private String isCg;
	/**是否变更*/
	@Excel(name = "是否变更", width = 15)
    @ApiModelProperty(value = "是否变更")
	private String isChange;
	/**是否核保*/
	@Excel(name = "是否核保", width = 15)
    @ApiModelProperty(value = "是否核保")
	private String isCheck;
	/**是否可用*/
	@Excel(name = "是否可用", width = 15)
    @ApiModelProperty(value = "是否可用")
	private String isEnabled;
	/**是否公正*/
	@Excel(name = "是否公正", width = 15)
    @ApiModelProperty(value = "是否公正")
	private String isNotary;
	/**公证号*/
	@Excel(name = "公证号", width = 15)
    @ApiModelProperty(value = "公证号")
	private String notarizationNo;
	/**公正人*/
	@Excel(name = "公正人", width = 15)
    @ApiModelProperty(value = "公正人")
	private String notary;
	/**公正日期*/
	@Excel(name = "公正日期", width = 15)
    @ApiModelProperty(value = "公正日期")
	private String notaryDate;
	/**公正机构*/
	@Excel(name = "公正机构", width = 15)
    @ApiModelProperty(value = "公正机构")
	private String notaryOrg;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**董事会决议*/
	@Excel(name = "董事会决议", width = 15)
    @ApiModelProperty(value = "董事会决议")
	private String resolutionDesc;
	/**股东授权范围*/
	@Excel(name = "股东授权范围", width = 15)
    @ApiModelProperty(value = "股东授权范围")
	private String shImpowerDesc;
	/**分担比例,所占份额*/
	@Excel(name = "分担比例,所占份额", width = 15)
    @ApiModelProperty(value = "分担比例,所占份额")
	private java.math.BigDecimal shareProp;
	/**担保人客户编号*/
	@Excel(name = "担保人客户编号", width = 15)
    @ApiModelProperty(value = "担保人客户编号")
	private String sureCustId;
	/**担保关系*/
	@Excel(name = "担保关系", width = 15)
    @ApiModelProperty(value = "担保关系")
	private String sureRelation;
	/**保证资格*/
	@Excel(name = "保证资格", width = 15)
    @ApiModelProperty(value = "保证资格")
	private String sureRight;
	/**保证方式*/
	@Excel(name = "保证方式", width = 15)
    @ApiModelProperty(value = "保证方式")
	private String sureType;
	/**担保金额*/
	@Excel(name = "担保金额", width = 15)
    @ApiModelProperty(value = "担保金额")
	private java.math.BigDecimal suretyAmt;
	/**担保起始日期*/
	@Excel(name = "担保起始日期", width = 15)
    @ApiModelProperty(value = "担保起始日期")
	private String suretyBeginDate;
	/**担保到期日期*/
	@Excel(name = "担保到期日期", width = 15)
    @ApiModelProperty(value = "担保到期日期")
	private String suretyEndDate;
	/**担保期限*/
	@Excel(name = "担保期限", width = 15)
    @ApiModelProperty(value = "担保期限")
	private String suretyTerm;
	/**保证人最高额担保金额*/
	@Excel(name = "保证人最高额担保金额", width = 15)
    @ApiModelProperty(value = "保证人最高额担保金额")
	private java.math.BigDecimal tiptopAssurerAmt;
	/**解除担保时间*/
	@Excel(name = "解除担保时间", width = 15)
    @ApiModelProperty(value = "解除担保时间")
	private String unchainSureTime;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**保证方式2*/
	@Excel(name = "保证方式2", width = 15)
    @ApiModelProperty(value = "保证方式2")
	private String assurerDetail2;
	/**保证方式1*/
	@Excel(name = "保证方式1", width = 15)
    @ApiModelProperty(value = "保证方式1")
	private String assurerDetail;
	/**争议解决方式*/
	@Excel(name = "争议解决方式", width = 15)
    @ApiModelProperty(value = "争议解决方式")
	private String jjfs;
	/**仲裁机构全称*/
	@Excel(name = "仲裁机构全称", width = 15)
    @ApiModelProperty(value = "仲裁机构全称")
	private String zcjg;
	/**其他事项*/
	@Excel(name = "其他事项", width = 15)
    @ApiModelProperty(value = "其他事项")
	private String qtsx;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String qyrq;
	/**签约地点*/
	@Excel(name = "签约地点", width = 15)
    @ApiModelProperty(value = "签约地点")
	private String qydd;
	/**合同份数*/
	@Excel(name = "合同份数", width = 15)
    @ApiModelProperty(value = "合同份数")
	private String fs;
	/**承诺函编号*/
	@Excel(name = "承诺函编号", width = 15)
    @ApiModelProperty(value = "承诺函编号")
	private String acceptanceCd;
	/**承诺函签定日*/
	@Excel(name = "承诺函签定日", width = 15)
    @ApiModelProperty(value = "承诺函签定日")
	private String acceptanceSignDate;
	/**流出编号*/
	@Excel(name = "流出编号", width = 15)
    @ApiModelProperty(value = "流出编号")
	private String assurerId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**情况描述*/
	@Excel(name = "情况描述", width = 15)
    @ApiModelProperty(value = "情况描述")
	private String cgDesc;
	/**反担人名称*/
	@Excel(name = "反担人名称", width = 15)
    @ApiModelProperty(value = "反担人名称")
	private String cgName;
	/**反担保日期*/
	@Excel(name = "反担保日期", width = 15)
    @ApiModelProperty(value = "反担保日期")
	private String checkAssureDate;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String checkAssureDesc;
	/**反担保人名称*/
	@Excel(name = "反担保人名称", width = 15)
    @ApiModelProperty(value = "反担保人名称")
	private String checkAssureName;
	/**催收登记日期*/
	@Excel(name = "催收登记日期", width = 15)
    @ApiModelProperty(value = "催收登记日期")
	private String urgedSignDate;
	/**代偿归还日期*/
	@Excel(name = "代偿归还日期", width = 15)
    @ApiModelProperty(value = "代偿归还日期")
	private String subrogationDate;
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
