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
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
public class KhxxglCksjmxZhVO {
    
	private Date tjyf;
	private String jgdm;
	private String zjhm;
	private java.math.BigDecimal ye = new BigDecimal("0");
	private java.math.BigDecimal yrp= new BigDecimal("0");
	private java.math.BigDecimal nrp= new BigDecimal("0");
	private java.math.BigDecimal dsye= new BigDecimal("0");
	private java.math.BigDecimal dsyrp= new BigDecimal("0");
	private java.math.BigDecimal dsnrp= new BigDecimal("0");
	private java.math.BigDecimal dgye= new BigDecimal("0");
	private java.math.BigDecimal dgyrp= new BigDecimal("0");
	private java.math.BigDecimal dgnrp= new BigDecimal("0");
	private java.math.BigDecimal hqye= new BigDecimal("0");
	private java.math.BigDecimal hqyrp= new BigDecimal("0");
	private java.math.BigDecimal hqnrp= new BigDecimal("0");
	private java.math.BigDecimal dqye= new BigDecimal("0");
	private java.math.BigDecimal dqyrp= new BigDecimal("0");
	private java.math.BigDecimal dqnrp= new BigDecimal("0");
	private java.math.BigDecimal ybtye= new BigDecimal("0");
	private java.math.BigDecimal ybtyrp= new BigDecimal("0");
	private java.math.BigDecimal ybtnrp= new BigDecimal("0");
	private java.math.BigDecimal ncye= new BigDecimal("0");
	private java.math.BigDecimal ncyrp= new BigDecimal("0");
	private java.math.BigDecimal ncnrp= new BigDecimal("0");
	private java.math.BigDecimal syye= new BigDecimal("0");
	private java.math.BigDecimal syyrp= new BigDecimal("0");
	private java.math.BigDecimal synrp= new BigDecimal("0");
	private java.math.BigDecimal syhqye= new BigDecimal("0");
	private java.math.BigDecimal nchqye= new BigDecimal("0");
	private java.math.BigDecimal sydqye= new BigDecimal("0");
	private java.math.BigDecimal ncdqye= new BigDecimal("0");

	//存款余额部分
	private java.math.BigDecimal yejsy;
	private int yejsybz;
	private java.math.BigDecimal yejnc;
	private int yejncbz;

	//获取余额部分
	private java.math.BigDecimal hqjsy;
	private int hqjsybz;
	private java.math.BigDecimal hqjnc;
	private int hqjncbz;

	//定期余额部分
	private java.math.BigDecimal dqjsy;
	private int dqjsybz;
	private java.math.BigDecimal dqjnc;
	private int dqjncbz;
}
