package org.cmms.modules.jylrhs.jylrsj.zhbpftfyhz.entity;

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
 * @Description: 报批分摊费用（汇总）
 * @Author: jeecg-boot
 * @Date:   2023-09-14
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_zhbpftfy_hz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_zhbpftfy_hz对象", description="报批分摊费用（汇总）")
public class JylrhsZhbpftfyHz {

	/**会计/记账年份*/
	@Excel(name = "会计/记账年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "会计/记账年份")
	private Date fiscalYear;
	/**业务机构代码*/
	@Excel(name = "业务机构代码/组织标识", width = 18)
	@ApiModelProperty(value = "业务机构代码/组织标识")
	private String ywjgdm;
	/**业务机构名称*/
	@Excel(name = "业务机构", width = 15)
    @ApiModelProperty(value = "业务机构")
	private String ywjg;
	/**业务宣传费*/
	@Excel(name = "业务宣传费", width = 15)
    @ApiModelProperty(value = "业务宣传费")
	private java.math.BigDecimal ywxcf660101;
	/**广告费*/
	@Excel(name = "广告费", width = 15)
    @ApiModelProperty(value = "广告费")
	private java.math.BigDecimal ggf660102;
	/**印刷费*/
	@Excel(name = "印刷费", width = 15)
    @ApiModelProperty(value = "印刷费")
	private java.math.BigDecimal ysf660103;
	/**业务招待费*/
	@Excel(name = "业务招待费", width = 15)
    @ApiModelProperty(value = "业务招待费")
	private java.math.BigDecimal ywzdf660104;
	/**电子设备运转费*/
	@Excel(name = "电子设备运转费", width = 15)
    @ApiModelProperty(value = "电子设备运转费")
	private java.math.BigDecimal dzsbyzf660105;
	/**钞币运送费*/
	@Excel(name = "钞币运送费", width = 15)
    @ApiModelProperty(value = "钞币运送费")
	private java.math.BigDecimal cbysf660106;
	/**安全保卫费*/
	@Excel(name = "安全保卫费", width = 15)
    @ApiModelProperty(value = "安全保卫费")
	private java.math.BigDecimal aqbwf660107;
	/**保险费*/
	@Excel(name = "保险费", width = 15)
    @ApiModelProperty(value = "保险费")
	private java.math.BigDecimal bxf660108;
	/**邮电费*/
	@Excel(name = "邮电费", width = 15)
    @ApiModelProperty(value = "邮电费")
	private java.math.BigDecimal ydf660109;
	/**诉讼费*/
	@Excel(name = "诉讼费", width = 15)
    @ApiModelProperty(value = "诉讼费")
	private java.math.BigDecimal ssf660110;
	/**公证费*/
	@Excel(name = "公证费", width = 15)
    @ApiModelProperty(value = "公证费")
	private java.math.BigDecimal gzf660111;
	/**咨询费*/
	@Excel(name = "咨询费", width = 15)
    @ApiModelProperty(value = "咨询费")
	private java.math.BigDecimal zxf660112;
	/**审计费*/
	@Excel(name = "审计费", width = 15)
    @ApiModelProperty(value = "审计费")
	private java.math.BigDecimal sjf660113;
	/**监管费*/
	@Excel(name = "监管费", width = 15)
    @ApiModelProperty(value = "监管费")
	private java.math.BigDecimal jgf660114;
	/**技术转让费*/
	@Excel(name = "技术转让费", width = 15)
    @ApiModelProperty(value = "技术转让费")
	private java.math.BigDecimal jszrf660115;
	/**研究开发费*/
	@Excel(name = "研究开发费", width = 15)
    @ApiModelProperty(value = "研究开发费")
	private java.math.BigDecimal yjkff660116;
	/**外事费*/
	@Excel(name = "外事费", width = 15)
    @ApiModelProperty(value = "外事费")
	private java.math.BigDecimal csf660117;
	/**公杂费*/
	@Excel(name = "公杂费", width = 15)
    @ApiModelProperty(value = "公杂费")
	private java.math.BigDecimal gzf660118;
	/**差旅费*/
	@Excel(name = "差旅费", width = 15)
    @ApiModelProperty(value = "差旅费")
	private java.math.BigDecimal clf660119;
	/**水电费*/
	@Excel(name = "水电费", width = 15)
    @ApiModelProperty(value = "水电费")
	private java.math.BigDecimal sdf660120;
	/**会议费*/
	@Excel(name = "会议费", width = 15)
    @ApiModelProperty(value = "会议费")
	private java.math.BigDecimal hyf660121;
	/**绿化费*/
	@Excel(name = "绿化费", width = 15)
    @ApiModelProperty(value = "绿化费")
	private java.math.BigDecimal lhf660122;
	/**理(董)事会费*/
	@Excel(name = "理(董)事会费", width = 15)
    @ApiModelProperty(value = "理(董)事会费")
	private java.math.BigDecimal lshf660123;
	/**会费*/
	@Excel(name = "会费", width = 15)
    @ApiModelProperty(value = "会费")
	private java.math.BigDecimal hf660124;
	/**税费*/
	@Excel(name = "税费", width = 15)
    @ApiModelProperty(value = "税费")
	private java.math.BigDecimal sf660125;
	/**交通工具耗用费*/
	@Excel(name = "交通工具耗用费", width = 15)
    @ApiModelProperty(value = "交通工具耗用费")
	private java.math.BigDecimal jtgjhyf660126;
	/**开办费*/
	@Excel(name = "开办费", width = 15)
    @ApiModelProperty(value = "开办费")
	private java.math.BigDecimal kbf660127;
	/**管理费*/
	@Excel(name = "管理费", width = 15)
    @ApiModelProperty(value = "管理费")
	private java.math.BigDecimal glf660128;
	/**物业费*/
	@Excel(name = "物业费", width = 15)
    @ApiModelProperty(value = "物业费")
	private java.math.BigDecimal wyf660129;
	/**职工工资*/
	@Excel(name = "职工工资", width = 15)
    @ApiModelProperty(value = "职工工资")
	private java.math.BigDecimal zggz660130;
	/**职工福利费*/
	@Excel(name = "职工福利费", width = 15)
    @ApiModelProperty(value = "职工福利费")
	private java.math.BigDecimal zgflf660131;
	/**职工教育经费*/
	@Excel(name = "职工教育经费", width = 15)
    @ApiModelProperty(value = "职工教育经费")
	private java.math.BigDecimal zgjyjf660132;
	/**工会经费*/
	@Excel(name = "工会经费", width = 15)
    @ApiModelProperty(value = "工会经费")
	private java.math.BigDecimal ghjf660133;
	/**劳动保护费*/
	@Excel(name = "劳动保护费", width = 15)
    @ApiModelProperty(value = "劳动保护费")
	private java.math.BigDecimal ldbhf660134;
	/**基本养老保险金*/
	@Excel(name = "基本养老保险金", width = 15)
    @ApiModelProperty(value = "基本养老保险金")
	private java.math.BigDecimal jbylbxj660135;
	/**基本医疗保险金*/
	@Excel(name = "基本医疗保险金", width = 15)
    @ApiModelProperty(value = "基本医疗保险金")
	private java.math.BigDecimal jbylbxj660136;
	/**工伤保险金*/
	@Excel(name = "工伤保险金", width = 15)
    @ApiModelProperty(value = "工伤保险金")
	private java.math.BigDecimal gsbxj660137;
	/**生育保险金*/
	@Excel(name = "生育保险金", width = 15)
    @ApiModelProperty(value = "生育保险金")
	private java.math.BigDecimal sybxj660138;
	/**失业保险金*/
	@Excel(name = "失业保险金", width = 15)
    @ApiModelProperty(value = "失业保险金")
	private java.math.BigDecimal sybxj660139;
	/**补充养老保险金*/
	@Excel(name = "补充养老保险金", width = 15)
    @ApiModelProperty(value = "补充养老保险金")
	private java.math.BigDecimal bcylbxj660140;
	/**补充医疗保险金*/
	@Excel(name = "补充医疗保险金", width = 15)
    @ApiModelProperty(value = "补充医疗保险金")
	private java.math.BigDecimal bcylbxj660141;
	/**股份支付*/
	@Excel(name = "股份支付", width = 15)
    @ApiModelProperty(value = "股份支付")
	private java.math.BigDecimal gfzf660142;
	/**辞退福利*/
	@Excel(name = "辞退福利", width = 15)
    @ApiModelProperty(value = "辞退福利")
	private java.math.BigDecimal ctfl660143;
	/**非货币性福利*/
	@Excel(name = "非货币性福利", width = 15)
    @ApiModelProperty(value = "非货币性福利")
	private java.math.BigDecimal fhbxfl660144;
	/**住房公积金*/
	@Excel(name = "住房公积金", width = 15)
    @ApiModelProperty(value = "住房公积金")
	private java.math.BigDecimal zfgjj660145;
	/**取暖及降温费*/
	@Excel(name = "取暖及降温费", width = 15)
    @ApiModelProperty(value = "取暖及降温费")
	private java.math.BigDecimal qnjwf660146;
	/**租赁费*/
	@Excel(name = "租赁费", width = 15)
    @ApiModelProperty(value = "租赁费")
	private java.math.BigDecimal zlf660147;
	/**修理费*/
	@Excel(name = "修理费", width = 15)
    @ApiModelProperty(value = "修理费")
	private java.math.BigDecimal xlf660148;
	/**低值易耗品摊销*/
	@Excel(name = "低值易耗品摊销", width = 15)
    @ApiModelProperty(value = "低值易耗品摊销")
	private java.math.BigDecimal dzyhptx660149;
	/**长期待摊费用摊销*/
	@Excel(name = "长期待摊费用摊销", width = 15)
    @ApiModelProperty(value = "长期待摊费用摊销")
	private java.math.BigDecimal cqdtfytx660150;
	/**无形资产摊销*/
	@Excel(name = "无形资产摊销", width = 15)
    @ApiModelProperty(value = "无形资产摊销")
	private java.math.BigDecimal wxzctx660151;
	/**固定资产折旧费*/
	@Excel(name = "固定资产折旧费", width = 15)
    @ApiModelProperty(value = "固定资产折旧费")
	private java.math.BigDecimal gdzczjf660152;
	/**业务岗位劳务派遣人员劳务费*/
	@Excel(name = "业务岗位", width = 15, groupName = "劳务派遣人员劳务费")
    @ApiModelProperty(value = "业务岗位劳务派遣人员劳务费")
	private java.math.BigDecimal ywgwlwf66015301;
	/**工勤岗位劳务派遣人员劳务费*/
	@Excel(name = "工勤岗位", width = 15, groupName = "劳务派遣人员劳务费")
    @ApiModelProperty(value = "工勤岗位劳务派遣人员劳务费")
	private java.math.BigDecimal gqgwlwf66015302;
	/**劳务外包费用*/
	@Excel(name = "劳务外包费用", width = 15)
    @ApiModelProperty(value = "劳务外包费用")
	private java.math.BigDecimal lwwbfy66015303;

	/**租赁负债利息费用*/
	@Excel(name = "租赁负债利息费用", width = 15)
	@ApiModelProperty(value = "租赁负债利息费用")
	private java.math.BigDecimal zlfzlxfy660154;
	/**使用权资产折旧费用*/
	@Excel(name = "使用权资产折旧费用", width = 15)
	@ApiModelProperty(value = "使用权资产折旧费用")
	private java.math.BigDecimal syqzczjfy660155;

	/**其他费用*/
	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
	private java.math.BigDecimal qtfy660199;
	/**6601合计*/
	@Excel(name = "6601合计", width = 15)
    @ApiModelProperty(value = "6601合计")
	private java.math.BigDecimal hj6601;

	/**6403科目*/
	@Excel(name = "6403科目", width = 15)
	@ApiModelProperty(value = "6403科目")
	private java.math.BigDecimal km6403;

	/**6421科目*/
	@Excel(name = "6421科目", width = 15)
    @ApiModelProperty(value = "6421科目")
	private java.math.BigDecimal km6421;
	/**6602科目*/
	@Excel(name = "6602科目", width = 15)
    @ApiModelProperty(value = "6602科目")
	private java.math.BigDecimal km6602;
	/**6711科目*/
	@Excel(name = "6711科目", width = 15)
    @ApiModelProperty(value = "6711科目")
	private java.math.BigDecimal km6711;
	/**6901以前年度利得(弃用)*/
	//@Excel(name = "6901以前年度利得", width = 16)
    @ApiModelProperty(value = "6901以前年度利得(弃用)")
	private java.math.BigDecimal yqndld690101;
	/**6901以前年度损失(弃用)*/
	//@Excel(name = "6901以前年度损失", width = 16)
    @ApiModelProperty(value = "6901以前年度损失(弃用)")
	private java.math.BigDecimal yqndss690102;
	/**6901科目*/
	@Excel(name = "6901科目", width = 15)
    @ApiModelProperty(value = "6901科目")
	private java.math.BigDecimal km6901;
	/**合计*/
	@Excel(name = "合计", width = 15)
	@ApiModelProperty(value = "合计")
	private java.math.BigDecimal total;
	/**操作类型*/
	//@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	@Excel(name = "录入/操作时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}
