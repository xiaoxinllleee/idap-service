package org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_dkhtsjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_dkhtsjxx对象", description="贷款合同数据管理")
public class DkdahtsjglVo {

	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
	@ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
	@ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	private String hth;
	/**贷款品种(补充)*/
	@Excel(name = "贷款品种(补充)", width = 15)
	@ApiModelProperty(value = "贷款品种(补充)")
	@Dict(dicCode = "dkzlbc")
	private String dkpzbc;
	/**贷款责任人*/
	@Excel(name = "贷款责任人工号", width = 15)
	@ApiModelProperty(value = "贷款责任人工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String dkzrr;




}
