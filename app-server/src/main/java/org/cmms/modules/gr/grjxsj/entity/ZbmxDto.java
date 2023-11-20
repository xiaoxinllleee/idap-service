package org.cmms.modules.gr.grjxsj.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZbmxDto {

	private String zbid;
	private String zzbz;			//组织标识
	private String zbmc;			//指标名称，从表erp_bas_zbk中获取
	private BigDecimal zbdj;		//指标单价
	private BigDecimal zbjg;		//指标结果
	private BigDecimal zbgz;		//指标工资


	
	

		
}
