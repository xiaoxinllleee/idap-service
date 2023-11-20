package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdqysjxgl.entity;

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
 * @Description: 评定区域数据项管理
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_SJX_AREA")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_SJX_AREA对象", description="评定区域数据项管理")
public class Pdqysjxgl {
    
	/**数据项编号*/
	@Excel(name = "数据项编号", width = 15)
    @ApiModelProperty(value = "数据项编号")
	private String sjxno;
	/**数据项ID*/
	@Excel(name = "数据项ID", width = 15)
    @ApiModelProperty(value = "数据项ID")
	private String sjxid;
	/**数据项类型(1 机构、2 部门 3 岗位)*/
	@Excel(name = "数据项类型", width = 15,dicCode = "sjxlx")
    @ApiModelProperty(value = "数据项类型")
	@Dict(dicCode = "sjxlx")
	private Integer sjxlx;
	/**数据项维度*/
	@Excel(name = "数据项维度", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "数据项维度")
	@Dict(dicCode = "rqwd")
	private String sjxwd;
	/**数据项名称*/
	@Excel(name = "数据项名称", width = 15)
    @ApiModelProperty(value = "数据项名称")
	private String sjxmc;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**指标考核数据计算SQL*/
	@Excel(name = "指标考核数据计算SQL", width = 15)
    @ApiModelProperty(value = "指标考核数据计算SQL")
	private String khjssql;
	/**指标工资计算SQL*/
	@Excel(name = "指标工资计算SQL", width = 15)
    @ApiModelProperty(value = "指标工资计算SQL")
	private String gzjssql;
	/**0 启用 1 停用*/
	@Excel(name = "启用标识名称", width = 15,dicCode = "sjxqybz")
    @ApiModelProperty(value = "启用标识名称")
	@Dict(dicCode = "sjxqybz")
	private Integer qybz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**考核方式（1 平衡计分卡 2 贡献率 3按量计酬 ）*/
	@Excel(name = "考核方式", width = 15,dicCode = "khfs")
    @ApiModelProperty(value = "考核方式")
	@Dict(dicCode = "khfs")
	private Integer khfs;
	/**执行批次*/
	@Excel(name = "执行批次", width = 15)
    @ApiModelProperty(value = "执行批次")
	private Integer zxpc;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private Integer zxsx;
	/**考核数据逻辑说明*/
	@Excel(name = "考核数据逻辑说明", width = 15)
    @ApiModelProperty(value = "考核数据逻辑说明")
	private String khjsljsm;
	/**工资计算逻辑说明*/
	@Excel(name = "工资计算逻辑说明", width = 15)
    @ApiModelProperty(value = "工资计算逻辑说明")
	private String gzsjljsm;
}
