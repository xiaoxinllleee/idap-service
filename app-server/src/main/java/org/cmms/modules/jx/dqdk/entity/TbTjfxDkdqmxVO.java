package org.cmms.modules.jx.dqdk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Data
public class TbTjfxDkdqmxVO {
    
	private String zzbz;
	/**岗位标识*/
	private Integer gwbz;
	/**员工工号*/
	private String yggh;
	/**贷款所在机构代码*/
	private String jgdm;
	/**客户编号*/
	private String khbh;
	/**客户名称*/
	private String khmc;
	/**客户类型(1：个人2：企业)*/
	private Integer khlx;
	/**客户性别(1：男2：女)*/
	private Integer khxb;
	/**贷款账号*/
	private String dkzh;
	/**贷款金额*/
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	private java.math.BigDecimal dkye;
	/**发放日期*/
	private Date ffrq;
	/**到期日期*/
	private Date dqrq;
	/**五级分类状态*/
	private String wjflzt;
	/**贷款品种*/
	private String dkpz;
	/**类型*/
	private Integer lx;
	/**录入标识*/
	private Integer lrbz;
	/**录入时间*/
	private Date lrsj;
	/**录入人*/
	private String lrr;
	private String zzjc;
	private String zzmc;
}
