package org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo;

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
 * @Description: 农户有效走访规则设置
 * @Author: jeecg-boot
 * @Date:   2022-01-17
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Yxzfmx {
	/**统计日期*/
	private String tjrq;
	/**员工工号*/
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
	private String zbmc;
	/**是否达标*/
	@Excel(name = "是否达标", width = 15)
	private String sfdb;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
}
