package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;


/**
 * @Description: 贷款数据监控台账
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_BNDKSJJKTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_BNDKSJJKTZ对象", description="贷款数据监控台账")
public class DkjkptBndksjjktzImport {


	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;

	@Excel(name = "不良形成原因", width = 15,dicCode = "blxcyy")
    @ApiModelProperty(value = "不良形成原因")
	private String blxcyy;

	/**责任界定*/
	@Excel(name = "责任界定", width = 15,dicCode = "zrjd")
    @ApiModelProperty(value = "责任界定")
	private String zrjd;

	/**清收处置措施*/
	@Excel(name = "清收处置措施", width = 15,dicCode = "qsczcs")
    @ApiModelProperty(value = "清收处置措施")
	private String qsczcs;

	/**清收处置时限*/
	@Excel(name = "清收处置时限", width = 15)
    @ApiModelProperty(value = "清收处置时限")
	private String qsczsx;

	/**zyzrr*/
	@Excel(name = "主要责任人", width = 15)
	@ApiModelProperty(value = "主要责任人")
	private String zyzrr;

	/**cyzrr*/
	@Excel(name = "次要责任人", width = 15)
	@ApiModelProperty(value = "次要责任人")
	private String cyzrr;

	/**贷款责任人*/
	@Excel(name = "贷款责任人", width = 15)
	@ApiModelProperty(value = "贷款责任人")
	private String dkzrr;

	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
	@ApiModelProperty(value = "清收责任人")
	private String qszrr;
	/**备注*/


}
