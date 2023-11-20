package org.cmms.modules.ywgl.ywl.ywlbgl.entity;

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
 * @Description: 业务类别管理
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YWLBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YWLBXX对象", description="业务类别管理")
public class YwlbglVO {

	/**业务类别代码*/
	@Excel(name = "业务类别代码", width = 15)
	@ApiModelProperty(value = "业务类别代码")
	private String ywlbdm;
	/**业务类别名称*/
	@Excel(name = "业务类别名称", width = 15)
	@ApiModelProperty(value = "业务类别名称")
	private String ywlbmc;
	/**对应交易码*/
	@Excel(name = "对应交易码", width = 15)
	@ApiModelProperty(value = "对应交易码")
	private String dyjym;
	/**折算笔数*/
	@Excel(name = "折算笔数", width = 15)
	@ApiModelProperty(value = "折算笔数")
	private java.math.BigDecimal zsbs;
	/**折算计算式*/
	@Excel(name = "折算计算式", width = 15)
	@ApiModelProperty(value = "折算计算式")
	private String zsbseval;
	/**分配标志*/
	@Excel(name = "分配标志", width = 15)
	@ApiModelProperty(value = "分配标志")
	private Integer fpbz;
	/**分配比例*/
	@Excel(name = "分配比例", width = 15)
	@ApiModelProperty(value = "分配比例")
	private java.math.BigDecimal fpbl;
	/**有效标志 0无效 1有效*/
	@Excel(name = "有效标志", width = 15)
	@ApiModelProperty(value = "有效标志")
	@Dict(dicCode = "yxbz")
	private Integer yxbz;

}
