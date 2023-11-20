package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity;

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
 * @Description: 支行不良贷款反弹监控
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Data
@TableName("V_dkjkpt_zhbldkftjk_bzr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_dkjkpt_zhbldkftjk_bzr对象", description="支行不良贷款反弹监控")
public class DkjkptZhbldkftjkBzr {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**昨日时间*/
	@Excel(name = "昨日时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "昨日时间")
	private Date zrsj;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15, dicCode="ywjgdm", dictTable="hr_bas_organization", dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    private String jgdm;
	/**正常->关注*/
	@Excel(name = "正常->关注", width = 15)
    @ApiModelProperty(value = "正常->关注")
	private Integer zczgz;
	/**关注->次级*/
	@Excel(name = "关注->次级", width = 15)
    @ApiModelProperty(value = "关注->次级")
	private Integer gzzcj;
	/**次级->可疑*/
	@Excel(name = "次级->可疑", width = 15)
    @ApiModelProperty(value = "次级->可疑")
	private Integer cjzky;
	/**可疑->损失*/
	@Excel(name = "可疑->损失", width = 15)
    @ApiModelProperty(value = "可疑->损失")
	private Integer kyzss;
}
