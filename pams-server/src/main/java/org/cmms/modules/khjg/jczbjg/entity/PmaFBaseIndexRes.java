package org.cmms.modules.khjg.jczbjg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @Description: 基础指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_BASE_INDEX_RES")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_BASE_INDEX_RES对象", description="基础指标结果")
public class PmaFBaseIndexRes {

	/**日期*/
	@Excel(name = "日期", width = 15)
    @ApiModelProperty(value = "日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String statDate;
	/**评价对象ID*/
	@Excel(name = "评价对象ID", width = 15)
    @ApiModelProperty(value = "评价对象ID")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF",dicText="YGXM",ds = "idap")
	private String evlObjId;
	/**基础指标编号*/
	@Excel(name = "基础指标编号", width = 15)
    @ApiModelProperty(value = "基础指标编号")
	@Dict(dicCode = "INDEX_ID",dictTable = "PMA_F_BASE_INDEX_INFO",dicText="INDEX_NAME",ds = "idap")
	private String indexId;
	/**指标值*/
	@Excel(name = "指标值", width = 15)
    @ApiModelProperty(value = "指标值")
	private Double indexValue;
	/**余额类型*/
	@Excel(name = "余额类型", width = 15)
    @ApiModelProperty(value = "余额类型")
	@Dict(dicCode = "YE_TYPE")
	private String balTypeId;
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
	@Excel(name = "创建人",width = 15)
	@ApiModelProperty(value = "应用类型")
	private String createBy;
	@Excel(name = "创建时间",width = 15)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	/**评价对象机构代码*/
	@Excel(name = "评价对象机构代码", width = 15)
	@ApiModelProperty(value = "评价对象机构代码")
	@Dict(dicCode = "YWJGDM", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String jgdm;

	/*@TableField(exist = false)
	private String startTime;
	@TableField(exist = false)
	private String endTime;*/
}
