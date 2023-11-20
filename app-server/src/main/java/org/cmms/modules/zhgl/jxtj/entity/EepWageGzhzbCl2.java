package org.cmms.modules.zhgl.jxtj.entity;

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
 * @Description: 慈利工资表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_GZHZB_CL2")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_GZHZB_CL2对象", description="慈利工资表")
public class EepWageGzhzbCl2 {
    
	/**工资日期*/
	@Excel(name = "工资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资日期")
	private java.util.Date gzrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private java.lang.String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private java.lang.String yggh;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
	private java.lang.String sfzh;
	/**岗位绩效-存款时点净增*/
	@Excel(name = "岗位绩效-存款时点净增", width = 15)
    @ApiModelProperty(value = "岗位绩效-存款时点净增")
	private java.math.BigDecimal gwjxCksdjz;
	/**岗位绩效-贷款时点净增*/
	@Excel(name = "岗位绩效-贷款时点净增", width = 15)
    @ApiModelProperty(value = "岗位绩效-贷款时点净增")
	private java.math.BigDecimal gwjxDksdjz;
	/**岗位绩效-利息收入*/
	@Excel(name = "岗位绩效-利息收入", width = 15)
    @ApiModelProperty(value = "岗位绩效-利息收入")
	private java.math.BigDecimal gwjxLxsr;
	/**岗位绩效-不良贷款率*/
	@Excel(name = "岗位绩效-不良贷款率", width = 15)
    @ApiModelProperty(value = "岗位绩效-不良贷款率")
	private java.math.BigDecimal gwjxBldkl;
	/**岗位绩效-到期贷款收回率*/
	@Excel(name = "岗位绩效-到期贷款收回率", width = 15)
    @ApiModelProperty(value = "岗位绩效-到期贷款收回率")
	private java.math.BigDecimal gwjxDqdkshl;
	/**岗位绩效-合计*/
	@Excel(name = "岗位绩效-合计", width = 15)
    @ApiModelProperty(value = "岗位绩效-合计")
	private java.math.BigDecimal gwjxHj;
	/**管理绩效*/
	@Excel(name = "管理绩效", width = 15)
    @ApiModelProperty(value = "管理绩效")
	private java.math.BigDecimal gljx;
	/**工作质量*/
	@Excel(name = "工作质量", width = 15)
    @ApiModelProperty(value = "工作质量")
	private java.math.BigDecimal gzzl;
	/**存款业务计价-定期日均净增*/
	@Excel(name = "存款业务计价-定期日均净增", width = 15)
    @ApiModelProperty(value = "存款业务计价-定期日均净增")
	private java.math.BigDecimal ckywjjDqrjjz;
	/**存款业务计价-活期日均净增*/
	@Excel(name = "存款业务计价-活期日均净增", width = 15)
    @ApiModelProperty(value = "存款业务计价-活期日均净增")
	private java.math.BigDecimal ckywjjHqrjjz;
	/**贷款业务计价-当月贷款利息收回*/
	@Excel(name = "贷款业务计价-当月贷款利息收回", width = 15)
    @ApiModelProperty(value = "贷款业务计价-当月贷款利息收回")
	private java.math.BigDecimal dkywjjDydklxsh;
	/**贷款业务计价-贷款余额净增*/
	@Excel(name = "贷款业务计价-贷款余额净增", width = 15)
    @ApiModelProperty(value = "贷款业务计价-贷款余额净增")
	private java.math.BigDecimal dkywjjDkyejz;
	/**贷款业务计价-贷款余额速增*/
	@Excel(name = "贷款业务计价-贷款余额速增", width = 15)
    @ApiModelProperty(value = "贷款业务计价-贷款余额速增")
	private java.math.BigDecimal dkywjjDkyesz;
	/**贷款业务计价-应补贷款归行资金*/
	@Excel(name = "贷款业务计价-应补贷款归行资金", width = 15)
    @ApiModelProperty(value = "贷款业务计价-应补贷款归行资金")
	private java.math.BigDecimal dkywjjYbdkghzj;
	/**贷款资产质量-到期贷款收回率*/
	@Excel(name = "贷款资产质量-到期贷款收回率", width = 15)
    @ApiModelProperty(value = "贷款资产质量-到期贷款收回率")
	private java.math.BigDecimal dkzczjDqdkshl;
	/**贷款资产质量-表内贷款不良率控制*/
	@Excel(name = "贷款资产质量-表内贷款不良率控制", width = 15)
    @ApiModelProperty(value = "贷款资产质量-表内贷款不良率控制")
	private java.math.BigDecimal dkzczjBndkbllkz;
	/**贷款资产质量-表内贷款关注率控制*/
	@Excel(name = "贷款资产质量-表内贷款关注率控制", width = 15)
    @ApiModelProperty(value = "贷款资产质量-表内贷款关注率控制")
	private java.math.BigDecimal dkzczjBndkgzlkz;
	/**贷款资产质量-贷款逾贷比*/
	@Excel(name = "贷款资产质量-贷款逾贷比", width = 15)
    @ApiModelProperty(value = "贷款资产质量-贷款逾贷比")
	private java.math.BigDecimal dkzczjDkydb;
	/**贷款资产质量-贷款风险责任金扣留*/
	@Excel(name = "贷款资产质量-贷款风险责任金扣留", width = 15)
    @ApiModelProperty(value = "贷款资产质量-贷款风险责任金扣留")
	private java.math.BigDecimal dkzczjDkfxzrjkl;
	/**贷款资产质量-贷款风险责任金返还*/
	@Excel(name = "贷款资产质量-贷款风险责任金返还", width = 15)
    @ApiModelProperty(value = "贷款资产质量-贷款风险责任金返还")
	private java.math.BigDecimal dkzczjDkfxzrjfh;
	/**贷款资产质量-贷款形态下迁扣留*/
	@Excel(name = "贷款资产质量-贷款形态下迁扣留", width = 15)
    @ApiModelProperty(value = "贷款资产质量-贷款形态下迁扣留")
	private java.math.BigDecimal dkzczjDkxtxqkl;
	/**贷款资产质量-贷款形态下迁返还*/
	@Excel(name = "贷款资产质量-贷款形态下迁返还", width = 15)
    @ApiModelProperty(value = "贷款资产质量-贷款形态下迁返还")
	private java.math.BigDecimal dkzczjDkxtxqfh;
	/**阶段性奖励-网点阶段性奖励*/
	@Excel(name = "阶段性奖励-网点阶段性奖励", width = 15)
    @ApiModelProperty(value = "阶段性奖励-网点阶段性奖励")
	private java.math.BigDecimal jdxjlWdjdxjl;
	/**电子银行产品-电子银行*/
	@Excel(name = "电子银行产品-电子银行", width = 15)
    @ApiModelProperty(value = "电子银行产品-电子银行")
	private java.math.BigDecimal dzyhcpDzyh;
	/**业务量计价-个人经办业务量*/
	@Excel(name = "业务量计价-个人经办业务量", width = 15)
    @ApiModelProperty(value = "业务量计价-个人经办业务量")
	private java.math.BigDecimal ywljjGrjbywl;
	/**业务量计价-经办现金流量*/
	@Excel(name = "业务量计价-经办现金流量", width = 15)
    @ApiModelProperty(value = "业务量计价-经办现金流量")
	private java.math.BigDecimal ywljjJbxjll;
	/**"模拟利润-支行模拟利润
"*/
	@Excel(name = "模拟利润-支行模拟利润 ", width = 15)
    @ApiModelProperty(value = "模拟利润-支行模拟利润")
	private java.math.BigDecimal mnlrZhmllr;
	/**上月剩余未扣*/
	@Excel(name = "上月剩余未扣", width = 15)
    @ApiModelProperty(value = "上月剩余未扣")
	private java.math.BigDecimal sysywk;
	/**工资合计*/
	@Excel(name = "工资合计", width = 15)
    @ApiModelProperty(value = "工资合计")
	private java.math.BigDecimal gzhj;
	/**应发工资*/
	@Excel(name = "应发工资", width = 15)
    @ApiModelProperty(value = "应发工资")
	private java.math.BigDecimal yfgz;
	/**实发工资*/
	@Excel(name = "实发工资", width = 15)
    @ApiModelProperty(value = "实发工资")
	private java.math.BigDecimal sfgz;
	/**调整后实发工资合计*/
	@Excel(name = "调整后实发工资合计", width = 15)
    @ApiModelProperty(value = "调整后实发工资合计")
	private java.math.BigDecimal tzhsfgzhj;
	/**调整工资与实发工资对比*/
	@Excel(name = "调整工资与实发工资对比", width = 15)
    @ApiModelProperty(value = "调整工资与实发工资对比")
	private java.math.BigDecimal tzgzysfgzdb;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**支行工资排名*/
	@Excel(name = "支行工资排名", width = 15)
    @ApiModelProperty(value = "支行工资排名")
	private java.lang.Integer zhpm;
	/**全行工资排名*/
	@Excel(name = "全行工资排名", width = 15)
    @ApiModelProperty(value = "全行工资排名")
	private java.lang.Integer qhpm;
	/**调整人*/
	@Excel(name = "调整人", width = 15)
    @ApiModelProperty(value = "调整人")
	private java.lang.String tzr;
	/**调整时间*/
	@Excel(name = "调整时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "调整时间")
	private java.util.Date tzsj;
	/**信息状态*/
	@Excel(name = "信息状态", width = 15)
    @ApiModelProperty(value = "信息状态")
	private java.lang.String xxzt;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private java.lang.Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**业务规模补贴*/
	@Excel(name = "业务规模补贴", width = 15)
    @ApiModelProperty(value = "业务规模补贴")
	private java.math.BigDecimal ywgmbt;
	/**全行人均绩效*/
	@Excel(name = "全行人均绩效", width = 15)
    @ApiModelProperty(value = "全行人均绩效")
	private java.math.BigDecimal qhrjjx;
	/**虚拟员工-虚拟员工分配*/
	@Excel(name = "虚拟员工-虚拟员工分配", width = 15)
    @ApiModelProperty(value = "虚拟员工-虚拟员工分配")
	private java.math.BigDecimal xnygfp;
}
