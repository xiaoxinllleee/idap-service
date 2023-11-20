package org.cmms.modules.khpjsx.pjxmsz.entity;

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
 * @Description: 评级项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("PJSX_PJXMSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_PJXMSZ对象", description="评级项目设置")
public class PjsxPjxmszImport {
    

	/**项目编号*/
	@Excel(name = "项目编号", width = 15)
	private String xmbh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
	private String xmmc;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
	private Integer pxxh;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15 ,dicCode = "khlx")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15,dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfqy;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;

}
