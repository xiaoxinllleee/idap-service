package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqckzhgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@Data
@TableName("CKJKPT_TDRYCKGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CKJKPT_TDRYCKGL对象", description="1")
public class CkjkptTdrqckzhglImport {

	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String subAcctNo;
	/**证件号*/
	@Excel(name = "证件号", width = 15)
    @ApiModelProperty(value = "证件号")
	private String identNo;
/*	*//**开户机构号*//*
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String branchNo;
	*//**客户姓名*//*
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String custName;*/
}
