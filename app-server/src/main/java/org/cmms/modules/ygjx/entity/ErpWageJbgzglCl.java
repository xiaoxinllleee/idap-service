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
 * @Description: 慈利基本工资管理
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_JBGZGL_CL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_JBGZGL_CL对象", description="慈利基本工资管理")
public class ErpWageJbgzglCl {
    
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	@TableId(type = IdType.NONE)
	private Long xh;
	/**单位编号*/
	@Excel(name = "单位编号", width = 15)
    @ApiModelProperty(value = "单位编号")
	private String dwbh;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String dwmc;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String zjhm;
	/**岗位工资*/
	@Excel(name = "岗位工资", width = 15)
    @ApiModelProperty(value = "岗位工资")
	private java.math.BigDecimal gwgz;
	/**职称工资*/
	@Excel(name = "职称工资", width = 15)
    @ApiModelProperty(value = "职称工资")
	private java.math.BigDecimal zcgz;
	/**文凭工资*/
	@Excel(name = "文凭工资", width = 15)
    @ApiModelProperty(value = "文凭工资")
	private java.math.BigDecimal wpgz;
	/**工龄工资*/
	@Excel(name = "工龄工资", width = 15)
    @ApiModelProperty(value = "工龄工资")
	private java.math.BigDecimal glgz;
	/**岗位晋升工资*/
	@Excel(name = "岗位晋升工资", width = 15)
    @ApiModelProperty(value = "岗位晋升工资")
	private java.math.BigDecimal gwjsgz;
	/**岗位下调工资*/
	@Excel(name = "岗位下调工资", width = 15)
    @ApiModelProperty(value = "岗位下调工资")
	private java.math.BigDecimal gwxtgz;
	/**年功津贴*/
	@Excel(name = "年功津贴", width = 15)
    @ApiModelProperty(value = "年功津贴")
	private java.math.BigDecimal ngjt;
	/**地区津贴*/
	@Excel(name = "地区津贴", width = 15)
    @ApiModelProperty(value = "地区津贴")
	private java.math.BigDecimal dqjt;
	/**下调津贴*/
	@Excel(name = "下调津贴", width = 15)
    @ApiModelProperty(value = "下调津贴")
	private java.math.BigDecimal xtjt;
	/**调整工资*/
	@Excel(name = "调整工资", width = 15)
    @ApiModelProperty(value = "调整工资")
	private java.math.BigDecimal dzgz;
	/**小计*/
	@Excel(name = "小计", width = 15)
    @ApiModelProperty(value = "小计")
	private java.math.BigDecimal xj;
	/**其他补助*/
	@Excel(name = "其他补助", width = 15)
    @ApiModelProperty(value = "其他补助")
	private java.math.BigDecimal qtbz;
	/**加班*/
	@Excel(name = "加班", width = 15)
    @ApiModelProperty(value = "加班")
	private java.math.BigDecimal jb;
	/**病事假扣款*/
	@Excel(name = "病事假扣款", width = 15)
    @ApiModelProperty(value = "病事假扣款")
	private java.math.BigDecimal bsjkk;
	/**应发合计*/
	@Excel(name = "应发合计", width = 15)
    @ApiModelProperty(value = "应发合计")
	private java.math.BigDecimal yfhj;
	/**养老*/
	@Excel(name = "养老", width = 15)
    @ApiModelProperty(value = "养老")
	private java.math.BigDecimal yl;
	/**医保*/
	@Excel(name = "医保", width = 15)
    @ApiModelProperty(value = "医保")
	private java.math.BigDecimal yb;
	/**失业保险*/
	@Excel(name = "失业保险", width = 15)
    @ApiModelProperty(value = "失业保险")
	private java.math.BigDecimal sybx;
	/**住房公积金*/
	@Excel(name = "住房公积金", width = 15)
    @ApiModelProperty(value = "住房公积金")
	private java.math.BigDecimal zfgjj;
	/**企业年金*/
	@Excel(name = "企业年金", width = 15)
    @ApiModelProperty(value = "企业年金")
	private java.math.BigDecimal qynj;
	/**本月工会会费*/
	@Excel(name = "本月工会会费", width = 15)
    @ApiModelProperty(value = "本月工会会费")
	private java.math.BigDecimal byghhf;
	/**扣个人所得税*/
	@Excel(name = "扣个人所得税", width = 15)
    @ApiModelProperty(value = "扣个人所得税")
	private java.math.BigDecimal kgrsds;
	/**应扣合计*/
	@Excel(name = "应扣合计", width = 15)
    @ApiModelProperty(value = "应扣合计")
	private java.math.BigDecimal ykhj;
	/**实发合计*/
	@Excel(name = "实发合计", width = 15)
    @ApiModelProperty(value = "实发合计")
	private java.math.BigDecimal sfhj;
	/**扣留保证金*/
	@Excel(name = "扣留保证金", width = 15)
    @ApiModelProperty(value = "扣留保证金")
	private java.math.BigDecimal kblzj;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**工资月份*/
	@Excel(name = "工资月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资月份")
	private Date gzyf;
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
	/**卫生津贴*/
	@Excel(name = "卫生津贴", width = 15)
    @ApiModelProperty(value = "卫生津贴")
	private java.math.BigDecimal wsjt;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
}
