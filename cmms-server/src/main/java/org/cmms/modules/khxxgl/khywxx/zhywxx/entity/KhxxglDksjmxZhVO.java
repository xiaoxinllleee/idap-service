package org.cmms.modules.khxxgl.khywxx.zhywxx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户信息管理贷款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_DKSJMX_ZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_DKSJMX_ZH对象", description="客户信息管理贷款数据明细支行")
public class KhxxglDksjmxZhVO {
    
	private Date tjyf;
	private String jgdm;
	private String zjhm;
	private java.math.BigDecimal dkje = new BigDecimal("0");
	private java.math.BigDecimal dkye = new BigDecimal("0");
	private java.math.BigDecimal yrp = new BigDecimal("0");
	private java.math.BigDecimal nrp = new BigDecimal("0");
	private java.math.BigDecimal zcye = new BigDecimal("0");
	private java.math.BigDecimal gzye = new BigDecimal("0");
	private java.math.BigDecimal cjye = new BigDecimal("0");
	private java.math.BigDecimal kyye = new BigDecimal("0");
	private java.math.BigDecimal ssye = new BigDecimal("0");
	private java.math.BigDecimal ncdkye = new BigDecimal("0");
	private java.math.BigDecimal ncnrp = new BigDecimal("0");
	private java.math.BigDecimal ncyrp = new BigDecimal("0");
	private java.math.BigDecimal sydkye = new BigDecimal("0");
	private java.math.BigDecimal synrp = new BigDecimal("0");
	private java.math.BigDecimal syyrp = new BigDecimal("0");

	private java.math.BigDecimal yejsy;
	private int yejsybz;
	private java.math.BigDecimal yejnc;
	private int yejncbz;
}
