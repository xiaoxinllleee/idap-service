package org.cmms.modules.ygjx.entity;

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
 * @Description: 祁东基本工资
 * @Author: jeecg-boot
 * @Date:   2023-03-01
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_JBGZ140")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_JBGZ140对象", description="祁东基本工资")
public class ErpWageJbgz140 {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private Integer id;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
	private String dw;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**基本工资*/
	@Excel(name = "基本工资", width = 15)
    @ApiModelProperty(value = "基本工资")
	private java.math.BigDecimal jbgz;
	/**岗位工资*/
	@Excel(name = "岗位工资", width = 15)
    @ApiModelProperty(value = "岗位工资")
	private java.math.BigDecimal gwgz;
	/**年功津贴*/
	@Excel(name = "年功津贴", width = 15)
    @ApiModelProperty(value = "年功津贴")
	private java.math.BigDecimal ngjt;
	/**专业技术职务津贴*/
	@Excel(name = "专业技术职务津贴", width = 15)
    @ApiModelProperty(value = "专业技术职务津贴")
	private java.math.BigDecimal zyjszwjt;
	/**地区类别津贴*/
	@Excel(name = "地区类别津贴", width = 15)
    @ApiModelProperty(value = "地区类别津贴")
	private java.math.BigDecimal dqlbjt;
	/**女职工卫生费*/
	@Excel(name = "女职工卫生费", width = 15)
    @ApiModelProperty(value = "女职工卫生费")
	private java.math.BigDecimal nzgwsf;
	/**负责人及其他补助*/
	@Excel(name = "负责人及其他补助", width = 15)
    @ApiModelProperty(value = "负责人及其他补助")
	private java.math.BigDecimal fzrjqtbz;
	/**预发综合绩效工资*/
	@Excel(name = "预发综合绩效工资", width = 15)
    @ApiModelProperty(value = "预发综合绩效工资")
	private java.math.BigDecimal yfzhjxgz;
	/**实发综合绩效工资*/
	@Excel(name = "实发综合绩效工资", width = 15)
    @ApiModelProperty(value = "实发综合绩效工资")
	private java.math.BigDecimal sfzhjxgz;
	/**合规基金*/
	@Excel(name = "合规基金", width = 15)
    @ApiModelProperty(value = "合规基金")
	private java.math.BigDecimal hgjj;
	/**岗位补助*/
	@Excel(name = "岗位补助", width = 15)
    @ApiModelProperty(value = "岗位补助")
	private java.math.BigDecimal gwbz;
	/**退休生活补助*/
	@Excel(name = "退休生活补助", width = 15)
    @ApiModelProperty(value = "退休生活补助")
	private java.math.BigDecimal txshbz;
	/**春节补助*/
	@Excel(name = "春节补助", width = 15)
    @ApiModelProperty(value = "春节补助")
	private java.math.BigDecimal cjbz;
	/**绩效考核加班*/
	@Excel(name = "绩效考核加班", width = 15)
    @ApiModelProperty(value = "绩效考核加班")
	private java.math.BigDecimal jxkhjb;
	/**春节加班*/
	@Excel(name = "春节加班", width = 15)
    @ApiModelProperty(value = "春节加班")
	private java.math.BigDecimal cjjb;
	/**元旦加班*/
	@Excel(name = "元旦加班", width = 15)
    @ApiModelProperty(value = "元旦加班")
	private java.math.BigDecimal ydjb;
	/**清明加班*/
	@Excel(name = "清明加班", width = 15)
    @ApiModelProperty(value = "清明加班")
	private java.math.BigDecimal qmjb;
	/**五一加班*/
	@Excel(name = "五一加班", width = 15)
    @ApiModelProperty(value = "五一加班")
	private java.math.BigDecimal wyjb;
	/**端午加班*/
	@Excel(name = "端午加班", width = 15)
    @ApiModelProperty(value = "端午加班")
	private java.math.BigDecimal dwjb;
	/**中秋加班*/
	@Excel(name = "中秋加班", width = 15)
    @ApiModelProperty(value = "中秋加班")
	private java.math.BigDecimal zqjb;
	/**国庆加班*/
	@Excel(name = "国庆加班", width = 15)
    @ApiModelProperty(value = "国庆加班")
	private java.math.BigDecimal gqjb;
	/**总行加班*/
	@Excel(name = "总行加班", width = 15)
    @ApiModelProperty(value = "总行加班")
	private java.math.BigDecimal zhjb;
	/**补节假日加班*/
	@Excel(name = "补节假日加班", width = 15)
    @ApiModelProperty(value = "补节假日加班")
	private java.math.BigDecimal bjjrjb;
	/**三个中心加班*/
	@Excel(name = "三个中心加班", width = 15)
    @ApiModelProperty(value = "三个中心加班")
	private java.math.BigDecimal sgzxjb;
	/**一季度合规标兵*/
	@Excel(name = "一季度合规标兵", width = 15)
    @ApiModelProperty(value = "一季度合规标兵")
	private java.math.BigDecimal yjdhgbj;
	/**二季度合规标兵*/
	@Excel(name = "二季度合规标兵", width = 15)
    @ApiModelProperty(value = "二季度合规标兵")
	private java.math.BigDecimal ejdhgbj;
	/**三季度合规标兵*/
	@Excel(name = "三季度合规标兵", width = 15)
    @ApiModelProperty(value = "三季度合规标兵")
	private java.math.BigDecimal sjdhgbj;
	/**四季度合规标兵*/
	@Excel(name = "四季度合规标兵", width = 15)
    @ApiModelProperty(value = "四季度合规标兵")
	private java.math.BigDecimal sijdhgbj;
	/**其他加班值班补助*/
	@Excel(name = "其他加班值班补助", width = 15)
    @ApiModelProperty(value = "其他加班值班补助")
	private java.math.BigDecimal qtjbzbbz;
	/**新招员工实习补助*/
	@Excel(name = "新招员工实习补助", width = 15)
    @ApiModelProperty(value = "新招员工实习补助")
	private java.math.BigDecimal xzygsxbz;
	/**产假补助*/
	@Excel(name = "产假补助", width = 15)
    @ApiModelProperty(value = "产假补助")
	private java.math.BigDecimal cjbz2;
	/**子女教育*/
	@Excel(name = "子女教育", width = 15)
    @ApiModelProperty(value = "子女教育")
	private java.math.BigDecimal znjy;
	/**一季度业务考核奖金*/
	@Excel(name = "一季度业务考核奖金", width = 15)
    @ApiModelProperty(value = "一季度业务考核奖金")
	private java.math.BigDecimal yjdywkhjj;
	/**二季度业务考核奖金*/
	@Excel(name = "二季度业务考核奖金", width = 15)
    @ApiModelProperty(value = "二季度业务考核奖金")
	private java.math.BigDecimal ejdywkhjj;
	/**三季度业务考核奖金*/
	@Excel(name = "三季度业务考核奖金", width = 15)
    @ApiModelProperty(value = "三季度业务考核奖金")
	private java.math.BigDecimal sjdywkhjj;
	/**四季度业务考核奖金*/
	@Excel(name = "四季度业务考核奖金", width = 15)
    @ApiModelProperty(value = "四季度业务考核奖金")
	private java.math.BigDecimal sijdywkhjj;
	/**退休节日补助*/
	@Excel(name = "退休节日补助", width = 15)
    @ApiModelProperty(value = "退休节日补助")
	private java.math.BigDecimal txjrbz;
	/**年终补助*/
	@Excel(name = "年终补助", width = 15)
    @ApiModelProperty(value = "年终补助")
	private java.math.BigDecimal nzbz;
	/**年终决算补助*/
	@Excel(name = "年终决算补助", width = 15)
    @ApiModelProperty(value = "年终决算补助")
	private java.math.BigDecimal nzjsbz;
	/**家属补助*/
	@Excel(name = "家属补助", width = 15)
    @ApiModelProperty(value = "家属补助")
	private java.math.BigDecimal jsbz;
	/**稿件奖励*/
	@Excel(name = "稿件奖励", width = 15)
    @ApiModelProperty(value = "稿件奖励")
	private java.math.BigDecimal gjjl;
	/**其他奖励补助*/
	@Excel(name = "其他奖励补助", width = 15)
    @ApiModelProperty(value = "其他奖励补助")
	private java.math.BigDecimal qtjlbz;
	/**住房公积*/
	@Excel(name = "住房公积", width = 15)
    @ApiModelProperty(value = "住房公积")
	private java.math.BigDecimal zfgjj;
	/**养老保险*/
	@Excel(name = "养老保险", width = 15)
    @ApiModelProperty(value = "养老保险")
	private java.math.BigDecimal ylbx;
	/**企业年金*/
	@Excel(name = "企业年金", width = 15)
    @ApiModelProperty(value = "企业年金")
	private java.math.BigDecimal qynj;
	/**医疗保险*/
	@Excel(name = "医疗保险", width = 15)
    @ApiModelProperty(value = "医疗保险")
	private java.math.BigDecimal yilbx;
	/**运营罚款*/
	@Excel(name = "运营罚款", width = 15)
    @ApiModelProperty(value = "运营罚款")
	private java.math.BigDecimal yyfk;
	/**职工自欠扣款*/
	@Excel(name = "职工自欠扣款", width = 15)
    @ApiModelProperty(value = "职工自欠扣款")
	private java.math.BigDecimal zgzqkk;
	/**预扣个人税*/
	@Excel(name = "预扣个人税", width = 15)
    @ApiModelProperty(value = "预扣个人税")
	private java.math.BigDecimal ykgrs;
	/**应扣个税*/
	@Excel(name = "应扣个税", width = 15)
    @ApiModelProperty(value = "应扣个税")
	private java.math.BigDecimal ykgs;
	/**已扣个税*/
	@Excel(name = "已扣个税", width = 15)
    @ApiModelProperty(value = "已扣个税")
	private java.math.BigDecimal yikgs;
	/**稽核罚款*/
	@Excel(name = "稽核罚款", width = 15)
    @ApiModelProperty(value = "稽核罚款")
	private java.math.BigDecimal jhfk;
	/**53号文件责任人不良贷款清收扣款*/
	@Excel(name = "53号文件责任人不良贷款清收扣款", width = 15)
    @ApiModelProperty(value = "53号文件责任人不良贷款清收扣款")
	private java.math.BigDecimal bldkqskk;
	/**已发基本工资扣回*/
	@Excel(name = "已发基本工资扣回", width = 15)
    @ApiModelProperty(value = "已发基本工资扣回")
	private java.math.BigDecimal yfjbgzkh;
	/**预发工资扣回*/
	@Excel(name = "预发工资扣回", width = 15)
    @ApiModelProperty(value = "预发工资扣回")
	private java.math.BigDecimal yfgzkh;
	/**受处分应扣工资*/
	@Excel(name = "受处分应扣工资", width = 15)
    @ApiModelProperty(value = "受处分应扣工资")
	private java.math.BigDecimal scfykgz;
	/**其他罚款*/
	@Excel(name = "其他罚款", width = 15)
    @ApiModelProperty(value = "其他罚款")
	private java.math.BigDecimal qtfk;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**工资月份*/
	@Excel(name = "工资月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资月份")
	private Date gzyf;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**单位编号*/
	@Excel(name = "单位编号", width = 15)
    @ApiModelProperty(value = "单位编号")
	private String dwbh;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**基本工资合计*/
	@Excel(name = "基本工资合计", width = 15)
    @ApiModelProperty(value = "基本工资合计")
	private java.math.BigDecimal jbgzhj;
	/**其他工资合计*/
	@Excel(name = "其他工资合计", width = 15)
    @ApiModelProperty(value = "其他工资合计")
	private java.math.BigDecimal qtgzhj;
	/**代缴代扣合计*/
	@Excel(name = "代缴代扣合计", width = 15)
    @ApiModelProperty(value = "代缴代扣合计")
	private java.math.BigDecimal djdkhj;
	/**应发合计*/
	@Excel(name = "应发合计", width = 15)
    @ApiModelProperty(value = "应发合计")
	private java.math.BigDecimal yfhj;
}
