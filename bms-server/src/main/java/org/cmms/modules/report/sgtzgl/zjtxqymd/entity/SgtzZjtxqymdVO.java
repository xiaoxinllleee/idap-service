package org.cmms.modules.report.sgtzgl.zjtxqymd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 专精特性企业名单
 * @Author: jeecg-boot
 * @Date:   2023-08-22
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_ZJTXQYMD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_ZJTXQYMD对象", description="专精特性企业名单")
public class SgtzZjtxqymdVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**基本户所属银行*/
	@Excel(name = "基本户所属银行", width = 15)
    @ApiModelProperty(value = "基本户所属银行")
	private String jbhssyh;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
	private String lx;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
    @ApiModelProperty(value = "负责人")
	private String fzr;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**所属县区*/
	@Excel(name = "所属县区", width = 15)
    @ApiModelProperty(value = "所属县区")
	private String ssxq;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**对接支行*/
	@Excel(name = "对接支行", width = 15)
    @ApiModelProperty(value = "对接支行")
	private String djzh;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
}
