package org.cmms.modules.pad.dagl.wjxx.entity;

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
 * @Description: 文件信息
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_wjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_wjxx对象", description="文件信息")
public class ErpBasWjxx {
    
	/**文件id*/
	@Excel(name = "文件id", width = 15)
    @ApiModelProperty(value = "文件id")
	private Integer wjid;
	/**业务id*/
	@Excel(name = "业务id", width = 15)
    @ApiModelProperty(value = "业务id")
	private Integer ywid;
	/**业务类型（1：表外贷款）*/
	@Excel(name = "业务类型（1：表外贷款）", width = 15)
    @ApiModelProperty(value = "业务类型（1：表外贷款）")
	private Integer ywlx;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String wjlj;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String fwlj;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String lrr;
	/**dkzh*/
	@Excel(name = "dkzh", width = 15)
    @ApiModelProperty(value = "dkzh")
	private String dkzh;
	/**附件类型;1贷款档案资料2贷款催收资料3贷款诉讼审批表4其他资料*/
	@Excel(name = "附件类型;1贷款档案资料2贷款催收资料3贷款诉讼审批表4其他资料", width = 15)
    @ApiModelProperty(value = "附件类型;1贷款档案资料2贷款催收资料3贷款诉讼审批表4其他资料")
	@Dict(dicCode = "tplx")
	private Integer fjlx;
}
