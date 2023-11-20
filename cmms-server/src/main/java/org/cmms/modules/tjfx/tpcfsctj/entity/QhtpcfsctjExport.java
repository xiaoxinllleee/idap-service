package org.cmms.modules.tjfx.tpcfsctj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 图片重复上传统计导出
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
@Data
public class QhtpcfsctjExport {
	/**图片编码*/
	@Excel(name = "图片编码", width = 15)
	@ApiModelProperty(value = "图片编码")
	private String md5;
	/**重复上传户数*/
	@Excel(name = "重复上传户数", width = 15)
	@ApiModelProperty(value = "重复上传户数")
	private Integer cfschs;

	@ExcelCollection(name = "重复上传详情")
	private List<TpcfsctjExport> tpcfsctjList;
}
