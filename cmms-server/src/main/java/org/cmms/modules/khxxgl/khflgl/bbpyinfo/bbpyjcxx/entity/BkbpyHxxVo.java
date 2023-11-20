package org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.ypyxx.entity.BkbpyYpyxx;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

/**
 * @Description: 背靠背评议基础信息
 * @Author: jeecg-boot
 * @Date:   2022-03-20
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BkbpyHxxVo {

	/**序号*/
	@ApiModelProperty(value = "序号")
	private String xh;
	/**户号编码*/
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户号编码*/
	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;
	/**正常贷款余额*/
	@Excel(name = "正常贷款余额", width = 15)
    @ApiModelProperty(value = "正常贷款余额")
	private java.math.BigDecimal zcdkye;
	/**表内不良余额*/
	@Excel(name = "表内不良余额", width = 15)
    @ApiModelProperty(value = "表内不良余额")
	private java.math.BigDecimal bnbldkye;
	/**表外不良余额*/
	@Excel(name = "表外不良余额", width = 15)
    @ApiModelProperty(value = "表外不良余额")
	private java.math.BigDecimal bwbldkye;
	/**是否诉讼*/
	@Excel(name = "是否诉讼", width = 15)
    @ApiModelProperty(value = "是否诉讼")
	@Dict(dicCode = "sfbz")
	private String sfss;
	/**是否服刑*/
	@Excel(name = "是否服刑", width = 15)
    @ApiModelProperty(value = "是否服刑")
	@Dict(dicCode = "sfbz")
	private String sffx;
	/**是否涉毒*/
	@Excel(name = "是否涉毒", width = 15)
    @ApiModelProperty(value = "是否涉毒")
	@Dict(dicCode = "sfbz")
	private String sfsd;
	/**是否五保低保户*/
	@Excel(name = "是否五保低保户", width = 15)
    @ApiModelProperty(value = "是否五保低保户")
	@Dict(dicCode = "sfbz")
	private String sfwbdbh;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;

	@ApiModelProperty(value = "个人信息")
	private List<BkbpyJcxx> bkbpyJcxxList;

	@ApiModelProperty(value = "已评议信息")
	private List<BkbpyYpyxx> bkbpyYpyxxList;

	@ApiModelProperty(value = "户信息")
	private KhglNhhzxxgl hxx;

}
