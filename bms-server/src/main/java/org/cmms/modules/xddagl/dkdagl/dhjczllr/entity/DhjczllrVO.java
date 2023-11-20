package org.cmms.modules.xddagl.dkdagl.dhjczllr.entity;

import com.alibaba.fastjson.JSONArray;
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
 * @Description: 贷后检查资料录入
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("V_dkjkpt_dhjcbbg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DhjczllrVO {
    
	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**khmc*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**dhdkje*/
	@Excel(name = "单户贷款金额", width = 15)
    @ApiModelProperty(value = "单户贷款金额")
	private Integer dhdkje;
	/**dhdkye*/
	@Excel(name = "单户贷款余额", width = 15)
    @ApiModelProperty(value = "单户贷款余额")
	private Integer dhdkye;
	/**zxjkrq*/
	@Excel(name = "最新借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最新借款日期")
	private Date zxjkrq;
	/**wjid*/
	@Excel(name = "附件id", width = 15)
    @ApiModelProperty(value = "附件id")
	private Long wjid;
	/**fjlx*/
	@Excel(name = "附件类型", width = 15)
    @ApiModelProperty(value = "附件类型")
	@Dict(dicCode = "dhjczllr")
	private Integer fjlx;
	/**fjnf*/
	@Excel(name = "附件年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "附件年份")
	private String fjnf;
	/**fjsjjd*/
	@Excel(name = "附件时间节点", width = 15)
    @ApiModelProperty(value = "附件时间节点")
	@Dict(dicCode = "dhjcfjsjjd")
	private Integer fjsjjd;
	/**wjm*/
	@Excel(name = "文件名", width = 15)
    @ApiModelProperty(value = "文件名")
	private String wjm;

	@ApiModelProperty(value = "类型")
	@Dict(dicCode = "dhjczllrfjlx")
	private String zllx;

	@ApiModelProperty(value = "备注")
	private String bz;

	@ApiModelProperty(value = "附件时间节点")
	@Dict(dicCode = "fjsjjd")
	private String fjsj;
	/**附件数据*/
	@Excel(name = "附件数据", width = 15)
	@ApiModelProperty(value = "附件数据")
	private JSONArray imgdate;
}
