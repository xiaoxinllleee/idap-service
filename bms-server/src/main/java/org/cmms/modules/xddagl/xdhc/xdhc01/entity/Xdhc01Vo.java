package org.cmms.modules.xddagl.xdhc.xdhc01.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_bndksjjktz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_bndksjjktz对象", description="信贷T+1核查")
public class Xdhc01Vo {
	//贷款帐号,不良形成原因,责任界定,清收处置措施,清收处置时限,主要责任人,次要责任人,清收责任人
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**不良形成原因*/
	@Excel(name = "不良形成原因", width = 15)
	@ApiModelProperty(value = "不良形成原因")
	@Dict(dicCode = "blxcyy")
	private String blxcyy;
	/**责任界定*/
	@Excel(name = "责任界定", width = 15)
	@ApiModelProperty(value = "责任界定")
	@Dict(dicCode = "zrjd")
	private String zrjd;
	/**清收处置措施*/
	@Excel(name = "清收处置措施", width = 15)
	@ApiModelProperty(value = "清收处置措施")
	@Dict(dicCode = "qsczcs")
	private String qsczcs;
	/**清收处置时限*/
	@Excel(name = "清收处置时限", width = 15)
	@ApiModelProperty(value = "清收处置时限")
	private String qsczsx;
	/**zyzrr*/
	@Excel(name = "主要负责人", width = 15)
	@ApiModelProperty(value = "主要负责人")
	private String zyzrr;
	/**cyzrr*/
	@Excel(name = "次要责任人", width = 15)
	@ApiModelProperty(value = "次要责任人")
	private String cyzrr;
	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
	@ApiModelProperty(value = "清收责任人")
	private String qszrr;



}
