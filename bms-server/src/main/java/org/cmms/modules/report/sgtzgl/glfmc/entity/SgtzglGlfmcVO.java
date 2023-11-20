package org.cmms.modules.report.sgtzgl.glfmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 关联方名册
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_glfmc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_glfmc对象", description="关联方名册")
public class SgtzglGlfmcVO {

	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private Integer xh;
	/**关联方名称*/
	@Excel(name = "关联方名称", width = 15)
	@ApiModelProperty(value = "关联方名称")
	private String glfmc;
	/**关联方证件号码*/
	@Excel(name = "关联方证件号码", width = 15)
	@ApiModelProperty(value = "关联方证件号码")
	private String glfzjhm;
	/**贷款*/
	@Excel(name = "贷款", width = 15)
	@ApiModelProperty(value = "贷款")
	private java.math.BigDecimal dk;
	/**其他*/
	@Excel(name = "其他（担保、表外）", width = 15)
	@ApiModelProperty(value = "其他（担保、表外）")
	private java.math.BigDecimal qt;
	/**合计*/
	@Excel(name = "合计", width = 15)
	@ApiModelProperty(value = "合计")
	private java.math.BigDecimal hj;
	/**关联方类别*/
	@Excel(name = "关联方类别", width = 15)
	@ApiModelProperty(value = "关联方类别")
	private String glflb;
	/**上一级关联方名称*/
	@Excel(name = "上一级关联方名称", width = 15)
	@ApiModelProperty(value = "上一级关联方名称")
	private String syjglfmc;
	/**上一级关联证件号码*/
	@Excel(name = "上一级关联证件号码", width = 15)
	@ApiModelProperty(value = "上一级关联证件号码")
	private String syjglfzjhm;
	/**关联方性质*/
	@Excel(name = "关联方性质", width = 15)
	@ApiModelProperty(value = "关联方性质")
	private String glfxz;
	/**关联关系说明*/
	@Excel(name = "关联关系说明", width = 15)
	@ApiModelProperty(value = "关联关系说明")
	private String glgxsm;
	/**关联日期*/
	@Excel(name = "关联日期", width = 15)
	@ApiModelProperty(value = "关联日期")
	//@ExcelVerify(interHandler = true)
	private String glrq;

}
