package org.cmms.modules.khlc.jczbgl.entity;

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
 * @Description: 指标库管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_ZBK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_ZBK对象", description="指标库管理")
public class ErpBasZbk {

	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
	@ApiModelProperty(value = "指标ID")
	private java.lang.String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
	@ApiModelProperty(value = "指标名称")
	private java.lang.String zbmc;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**指标类别(1 存款 2 贷款 3 电子银行 4 业务量 5 管理绩效 6 地区补差 7 其他 8 经营目标)*/
	@Excel(name = "指标类别", width = 15,dicCode = "zblb")
	@ApiModelProperty(value = "指标类别")
	@Dict(dicCode = "zblb")
	private String zblb;
	@Excel(name = "工资类别", width = 15)
	@ApiModelProperty(value = "工资类别")
	private String gzlb;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;


}
