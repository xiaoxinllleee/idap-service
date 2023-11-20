package org.cmms.modules.tjfx.zfsjtj.nhzfsjmx.vo;

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
 * @Description: 走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-07
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zfsjmx_nh对象", description="走访数据明细")
public class ZfsjmxExp {

	/**统计日期*/
	@Excel(name = "走访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "走访日期")
	private Date tjrq;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15, dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**户主证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String hzzjhm;
	/**网格编号*/
	@Excel(name = "网格名称", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;

	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
}
