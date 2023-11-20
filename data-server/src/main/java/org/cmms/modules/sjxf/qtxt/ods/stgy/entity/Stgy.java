package org.cmms.modules.sjxf.qtxt.ods.stgy.entity;

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
 * @Description: 实体柜员
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Emp_con")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Emp_con对象", description="实体柜员")
public class Stgy {
    
	/**新机构号*/
	@Excel(name = "新机构号", width = 15)
    @ApiModelProperty(value = "新机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String newBrNo;
	/**旧机构号*/
	@Excel(name = "旧机构号", width = 15)
    @ApiModelProperty(value = "旧机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String oldBrNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String brName;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String custName;
	/**新柜员号(员工号)*/
	@Excel(name = "新柜员号(员工号)", width = 15)
    @ApiModelProperty(value = "新柜员号(员工号)")
	private String newTlNo;
	/**旧柜员号*/
	@Excel(name = "旧柜员号", width = 15)
    @ApiModelProperty(value = "旧柜员号")
	private String oldTlNo;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String identNo;
	/**新系统柜员等级*/
	@Excel(name = "新系统柜员等级", width = 15)
    @ApiModelProperty(value = "新系统柜员等级")
	private String theLevel;
	/**新系统岗位名称*/
	@Excel(name = "新系统岗位名称", width = 15)
    @ApiModelProperty(value = "新系统岗位名称")
	private String gwName;
	/**新员工号*/
	@Excel(name = "新员工号", width = 15)
    @ApiModelProperty(value = "新员工号")
	private String newTlNo1;
	/**旧员工号*/
	@Excel(name = "旧员工号", width = 15)
    @ApiModelProperty(value = "旧员工号")
	private String oldTlNo1;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String organId;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
