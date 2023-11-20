package org.cmms.modules.khxxgl.khywxx.qhywxx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户信息管理贷款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_DKSJMX_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_DKSJMX_QH对象", description="客户信息管理贷款数据明细全行")
public class KhxxglDksjmxQhVO {
    
	private Date tjyf;
	private String zjhm;
	private BigDecimal dkje = new BigDecimal("0");
	private BigDecimal dkye = new BigDecimal("0");
	private BigDecimal yrp = new BigDecimal("0");
	private BigDecimal nrp = new BigDecimal("0");
	private BigDecimal zcye = new BigDecimal("0");
	private BigDecimal gzye = new BigDecimal("0");
	private BigDecimal cjye = new BigDecimal("0");
	private BigDecimal kyye = new BigDecimal("0");
	private BigDecimal ssye = new BigDecimal("0");
	private BigDecimal ncdkye = new BigDecimal("0");
	private BigDecimal ncnrp = new BigDecimal("0");
	private BigDecimal ncyrp = new BigDecimal("0");
	private BigDecimal sydkye = new BigDecimal("0");
	private BigDecimal synrp = new BigDecimal("0");
	private BigDecimal syyrp = new BigDecimal("0");

	private BigDecimal yejsy;
	private int yejsybz;
	private BigDecimal yejnc;
	private int yejncbz;
}
