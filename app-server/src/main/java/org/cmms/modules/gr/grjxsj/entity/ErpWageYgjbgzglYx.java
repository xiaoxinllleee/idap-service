package org.cmms.modules.gr.grjxsj.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 员工基本工资导入表
 * @Author: jeecg-boot
 * @Date:   2022-07-06
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_YGJBGZGL_YX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_YGJBGZGL_YX对象", description="员工基本工资导入表")
public class ErpWageYgjbgzglYx {
    
	/**工资月份*/
	@Excel(name = "工资月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资月份")
	private Date gzyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**职务岗位工资*/
	@Excel(name = "职务岗位工资", width = 15)
    @ApiModelProperty(value = "职务岗位工资")
	private java.math.BigDecimal zwgwgz = BigDecimal.ZERO;
	/**年功工资*/
	@Excel(name = "年功工资", width = 15)
    @ApiModelProperty(value = "年功工资")
	private java.math.BigDecimal nggz = BigDecimal.ZERO;
	/**预发绩效工资*/
	@Excel(name = "预发绩效工资", width = 15)
    @ApiModelProperty(value = "预发绩效工资")
	private java.math.BigDecimal yfjxgz = BigDecimal.ZERO;
	/**学历工资*/
	@Excel(name = "学历工资", width = 15)
    @ApiModelProperty(value = "学历工资")
	private java.math.BigDecimal xlgz = BigDecimal.ZERO;
	/**职称工资*/
	@Excel(name = "职称工资", width = 15)
    @ApiModelProperty(value = "职称工资")
	private java.math.BigDecimal zcgz = BigDecimal.ZERO;
	/**山区补贴*/
	@Excel(name = "山区补贴", width = 15)
    @ApiModelProperty(value = "山区补贴")
	private java.math.BigDecimal sqbt = BigDecimal.ZERO;
	/**学习积分*/
	@Excel(name = "学习积分", width = 15)
    @ApiModelProperty(value = "学习积分")
	private java.math.BigDecimal xxjf = BigDecimal.ZERO;
	/**加班工资*/
	@Excel(name = "加班工资", width = 15)
    @ApiModelProperty(value = "加班工资")
	private java.math.BigDecimal jbgz = BigDecimal.ZERO;
	/**预发工资*/
	@Excel(name = "预发工资", width = 15)
    @ApiModelProperty(value = "预发工资")
	private java.math.BigDecimal yfgz = BigDecimal.ZERO;
	/**补发工资*/
	@Excel(name = "补发工资", width = 15)
    @ApiModelProperty(value = "补发工资")
	private java.math.BigDecimal bfgz = BigDecimal.ZERO;
	/**应发工资合计*/
	@Excel(name = "应发工资合计", width = 15)
    @ApiModelProperty(value = "应发工资合计")
	private java.math.BigDecimal yfgzhj = BigDecimal.ZERO;
	/**其它扣款*/
	@Excel(name = "其它扣款", width = 15)
    @ApiModelProperty(value = "其它扣款")
	private java.math.BigDecimal qtkk = BigDecimal.ZERO;
	/**扣罚款*/
	@Excel(name = "扣罚款", width = 15)
    @ApiModelProperty(value = "扣罚款")
	private java.math.BigDecimal kfk = BigDecimal.ZERO;
	/**个人养老保险*/
	@Excel(name = "个人养老保险", width = 15)
    @ApiModelProperty(value = "个人养老保险")
	private java.math.BigDecimal ylbx = BigDecimal.ZERO;
	/**补扣个人养老保险*/
	@Excel(name = "补扣个人养老保险", width = 15)
    @ApiModelProperty(value = "补扣个人养老保险")
	private java.math.BigDecimal bkylbx = BigDecimal.ZERO;
	/**个人医疗保险*/
	@Excel(name = "个人医疗保险", width = 15)
    @ApiModelProperty(value = "个人医疗保险")
	private java.math.BigDecimal grylbx = BigDecimal.ZERO;
	/**补扣个人医疗保险*/
	@Excel(name = "补扣个人医疗保险", width = 15)
    @ApiModelProperty(value = "补扣个人医疗保险")
	private java.math.BigDecimal bkgrylbx = BigDecimal.ZERO;
	/**个人住房公积金*/
	@Excel(name = "个人住房公积金", width = 15)
    @ApiModelProperty(value = "个人住房公积金")
	private java.math.BigDecimal grzfjj = BigDecimal.ZERO;
	/**补扣个人住房公积金*/
	@Excel(name = "补扣个人住房公积金", width = 15)
    @ApiModelProperty(value = "补扣个人住房公积金")
	private java.math.BigDecimal bkgrzfjj = BigDecimal.ZERO;
	/**个人企业年金*/
	@Excel(name = "个人企业年金", width = 15)
    @ApiModelProperty(value = "个人企业年金")
	private java.math.BigDecimal grqynj = BigDecimal.ZERO;
	/**补扣个人企业年金*/
	@Excel(name = "补扣个人企业年金", width = 15)
    @ApiModelProperty(value = "补扣个人企业年金")
	private java.math.BigDecimal bkgrqynj = BigDecimal.ZERO;
	/**个人所得税*/
	@Excel(name = "个人所得税", width = 15)
    @ApiModelProperty(value = "个人所得税")
	private java.math.BigDecimal grsds = BigDecimal.ZERO;
	/**扣除部分合计*/
	@Excel(name = "扣除部分合计", width = 15)
    @ApiModelProperty(value = "扣除部分合计")
	private java.math.BigDecimal ykgzhj = BigDecimal.ZERO;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**实发工资*/
	@Excel(name = "实发工资", width = 15)
    @ApiModelProperty(value = "实发工资")
	private java.math.BigDecimal sfgz = BigDecimal.ZERO;
	/**调加工资*/
	@Excel(name = "调加工资", width = 15)
    @ApiModelProperty(value = "调加工资")
	private java.math.BigDecimal tjiagz = BigDecimal.ZERO;
	/**调减工资*/
	@Excel(name = "调减工资", width = 15)
    @ApiModelProperty(value = "调减工资")
	private java.math.BigDecimal tjiangz = BigDecimal.ZERO;
	/**个人失业保险*/
	@Excel(name = "个人失业保险", width = 15)
    @ApiModelProperty(value = "个人失业保险")
	private java.math.BigDecimal grsybx = BigDecimal.ZERO;
	/**补扣个人失业保险*/
	@Excel(name = "补扣个人失业保险", width = 15)
    @ApiModelProperty(value = "补扣个人失业保险")
	private java.math.BigDecimal bkgrsybx = BigDecimal.ZERO;
	/**其他应发*/
	@Excel(name = "其他应发", width = 15)
    @ApiModelProperty(value = "其他应发")
	private java.math.BigDecimal qtyf = BigDecimal.ZERO;
	/**剩余绩效兑现*/
	@Excel(name = "剩余绩效兑现", width = 15)
    @ApiModelProperty(value = "剩余绩效兑现")
	private java.math.BigDecimal syjxdx = BigDecimal.ZERO;
	/**工会经费*/
	@Excel(name = "工会经费", width = 15)
    @ApiModelProperty(value = "工会经费")
	private java.math.BigDecimal ghjf = BigDecimal.ZERO;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;

	@TableField(exist = false)
	private java.math.BigDecimal nzkhgz = BigDecimal.ZERO;
	@TableField(exist = false)
	private java.math.BigDecimal zxkhgz = BigDecimal.ZERO;
	@TableField(exist = false)
	private java.math.BigDecimal jxgz = BigDecimal.ZERO;

}
