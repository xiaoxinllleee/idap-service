package org.cmms.modules.khjg.pszbjg.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 派生指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-11
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_EVL_INDEX_RES")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_EVL_INDEX_RES对象", description="派生指标结果")
public class ParamFEvInRe {

	/**派生指标编号*/
	@Excel(name = "派生指标编号", width = 15)
    @ApiModelProperty(value = "派生指标编号")
	@Dict(dicCode = "INDEX_ID",dictTable = "PMA_F_EVL_INDEX_INFO",dicText="INDEX_NAME",ds = "idap")
	private String indexId;
	/**评价对象ID*/
	@Excel(name = "评价对象ID", width = 15)
    @ApiModelProperty(value = "评价对象ID")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF",dicText="YGXM",ds = "idap")
	private String evlObjId;
	/**余额类型*/
	@Excel(name = "余额类型", width = 15)
    @ApiModelProperty(value = "余额类型")
	@Dict(dicCode = "YE_TYPE")
	private String balTypeId;
	/**日期*/
	@Excel(name = "日期", width = 15)
    @ApiModelProperty(value = "日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String statDate;
	/**指标值*/
	@Excel(name = "指标值", width = 15)
    @ApiModelProperty(value = "指标值")
	private Double indexValue;
	/**评价对象类型*/
	@Excel(name = "评价对象类型", width = 15)
    @ApiModelProperty(value = "评价对象类型")
	@Dict(dicCode = "OBJ")
	private String evlObjType;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	@Dict(dicCode = "BZ_TYPE")
	private String curTypeId;
	/**应用类型*/
	@Excel(name = "应用类型", width = 15)
    @ApiModelProperty(value = "应用类型")
	@Dict(dicCode = "INDEX_APPLY_TYPE")
	private String applyTypeId;
	/**考核方案ID*/
	@Excel(name = "考核方案ID", width = 15)
    @ApiModelProperty(value = "考核方案ID")
	@Dict(dicCode = "SCHEME_ID",dictTable = "PMA_A_SCHEME",dicText="SCHEME_NAME")
	private String schemeId;

	@Dict(dicCode = "ywjgdm",dictTable = "HR_BAS_ORGANIZATION",dicText="ZZJC",ds = "idap")
	private String jgdm;

}
