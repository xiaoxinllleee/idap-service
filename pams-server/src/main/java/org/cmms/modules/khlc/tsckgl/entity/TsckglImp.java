package org.cmms.modules.khlc.tsckgl.entity;

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
 * @Description: 特殊存款管理
 * @Author: jeecg-boot
 * @Date:   2023-08-22
 * @Version: V1.0
 */
@Data
@TableName("erp_bas_tsckgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_bas_tsckgl对象", description="特殊存款管理")
public class TsckglImp {
    
	/**机构代码*/
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**证件号码*/
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**客户名称*/
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**开户日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
	/**科目号*/
    @ApiModelProperty(value = "科目号")
	private String subjNo;
	/**科目号名称*/
    @ApiModelProperty(value = "科目号名称")
	private String subjName;
	/**存款标签*/
	@Excel(name = "存款标签", width = 15, dicCode = "ckbq")
    @ApiModelProperty(value = "存款标签")
	@Dict(dicCode = "ckbq")
	private String ckbq;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
