package org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.entity;

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
 * @Description: 信贷档案上传进度统计
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("V_XDDAGL_DASCJD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_XDDAGL_DASCJD对象", description="信贷档案上传进度统计")
public class XddascjdtjVo {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**总档案数*/
	@Excel(name = "总档案数", width = 15)
    @ApiModelProperty(value = "总档案数")
	private Long zdas;
	/**已上传数*/
	@Excel(name = "已上交数", width = 15)
    @ApiModelProperty(value = "已上交数")
	private Long ysjs;

}
