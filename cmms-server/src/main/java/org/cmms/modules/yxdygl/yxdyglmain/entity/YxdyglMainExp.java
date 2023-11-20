package org.cmms.modules.yxdygl.yxdyglmain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 网格基本信息
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
public class YxdyglMainExp {
    
	/**网格名称*/
	@Excel(name = "网格名称", width = 15)
	@ExcelVerify(notNull = true)
	private String wgmc;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
	@ExcelVerify(notNull = true)
	private String wgbh;

	@Excel(name = "上级网格名称(可以为空)", width = 15)
	private String parentName;

	@ExcelVerify(notNull = true)
	@Excel(name = "上级网格编号", width = 15)
	private String parentId;

	@Excel(name = "单元性质(镇,村,组,城区街道,社区,商圈)", width = 30, dicCode = "wgxz")
	@ExcelVerify(notNull = true)
	private String wgxz;

	@Excel(name = "所属支行(支行简称即可)", width = 15)
	private String zzbz;

}
