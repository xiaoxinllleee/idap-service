package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity;

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
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Data
@TableName("Credit_dkkhglrgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_dkkhglrgl对象", description="贷款客户关联人管理")
public class DkkhglrglImportVo {
	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
	@ApiModelProperty(value = "借款人证件号码")
	private String jkrzjhm;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
	@ApiModelProperty(value = "借款人名称")
	private String jkrmc;

	/**关联人证件号码*/
	@Excel(name = "关联人证件号码", width = 15)
	@ApiModelProperty(value = "关联人证件号码")
	private String glrzjhm;


	/**关联人证件类型*/
	@Excel(name = "关联人证件类型", width = 15,dicCode = "dkjkpt_zjlx")
	@ApiModelProperty(value = "关联人证件类型")
	@Dict(dicCode = "dkjkpt_zjlx")
	private String glrzjlx;

	/**关联人姓名*/
	@Excel(name = "关联人姓名", width = 15)
	@ApiModelProperty(value = "关联人姓名")
	private String glrxm;
	/**关联关系*/
	@Excel(name = "关联关系", width = 15,dicCode = "dkjkpt_glgx")
	@ApiModelProperty(value = "关联关系")
	@Dict(dicCode = "dkjkpt_glgx")
	private Integer glgx;
	/**关联人工作单位*/
	@Excel(name = "关联人工作单位", width = 15)
	@ApiModelProperty(value = "关联人工作单位")
	private String glrgzdw;
	/**关联人联系电话*/
	@Excel(name = "关联人联系电话", width = 15)
	@ApiModelProperty(value = "关联人联系电话")
	private String glrlxdh;

}
