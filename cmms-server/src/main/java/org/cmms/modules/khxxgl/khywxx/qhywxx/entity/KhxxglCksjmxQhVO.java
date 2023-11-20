package org.cmms.modules.khxxgl.khywxx.qhywxx.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户信息管理存款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
public class KhxxglCksjmxQhVO {
    
	private Date tjyf;
	private String zjhm;
	private BigDecimal ye = new BigDecimal("0");
	private BigDecimal yrp= new BigDecimal("0");
	private BigDecimal nrp= new BigDecimal("0");
	private BigDecimal dsye= new BigDecimal("0");
	private BigDecimal dsyrp= new BigDecimal("0");
	private BigDecimal dsnrp= new BigDecimal("0");
	private BigDecimal dgye= new BigDecimal("0");
	private BigDecimal dgyrp= new BigDecimal("0");
	private BigDecimal dgnrp= new BigDecimal("0");
	private BigDecimal hqye= new BigDecimal("0");
	private BigDecimal hqyrp= new BigDecimal("0");
	private BigDecimal hqnrp= new BigDecimal("0");
	private BigDecimal dqye= new BigDecimal("0");
	private BigDecimal dqyrp= new BigDecimal("0");
	private BigDecimal dqnrp= new BigDecimal("0");
	private BigDecimal ybtye= new BigDecimal("0");
	private BigDecimal ybtyrp= new BigDecimal("0");
	private BigDecimal ybtnrp= new BigDecimal("0");
	private BigDecimal ncye= new BigDecimal("0");
	private BigDecimal ncyrp= new BigDecimal("0");
	private BigDecimal ncnrp= new BigDecimal("0");
	private BigDecimal syye= new BigDecimal("0");
	private BigDecimal syyrp= new BigDecimal("0");
	private BigDecimal synrp= new BigDecimal("0");
	private BigDecimal syhqye= new BigDecimal("0");
	private BigDecimal nchqye= new BigDecimal("0");
	private BigDecimal sydqye= new BigDecimal("0");
	private BigDecimal ncdqye= new BigDecimal("0");

	//存款余额部分
	private BigDecimal yejsy;
	private int yejsybz;
	private BigDecimal yejnc;
	private int yejncbz;

	//获取余额部分
	private BigDecimal hqjsy;
	private int hqjsybz;
	private BigDecimal hqjnc;
	private int hqjncbz;

	//定期余额部分
	private BigDecimal dqjsy;
	private int dqjsybz;
	private BigDecimal dqjnc;
	private int dqjncbz;
}
