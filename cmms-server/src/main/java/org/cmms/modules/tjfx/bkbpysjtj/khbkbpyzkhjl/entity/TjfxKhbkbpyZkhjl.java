package org.cmms.modules.tjfx.bkbpysjtj.khbkbpyzkhjl.entity;

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
 * @Description: 客户经理背靠背评议明细
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHBKBPY_ZKHJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHBKBPY_ZKHJL对象", description="客户经理背靠背评议明细")
public class TjfxKhbkbpyZkhjl {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**评议户数*/
	@Excel(name = "评议户数", width = 15)
    @ApiModelProperty(value = "评议户数")
	private Long pyhs;
	/**其中电子评议人数*/
	@Excel(name = "其中电子评议人数", width = 15)
    @ApiModelProperty(value = "其中电子评议人数")
	private Long qzdzpyrs;
	/**其中纸质评议人数*/
	@Excel(name = "其中纸质评议人数", width = 15)
    @ApiModelProperty(value = "其中纸质评议人数")
	private Long qzzzpyrs;
	/**其中开会评议人数*/
	@Excel(name = "其中开会评议人数", width = 15)
    @ApiModelProperty(value = "其中开会评议人数")
	private Long qzhypyrs;
	/**其中电话评议人数*/
	@Excel(name = "其中电话评议人数", width = 15)
    @ApiModelProperty(value = "其中电话评议人数")
	private Long qzdhpyrs;
	/**其中微信评议人数*/
	@Excel(name = "其中微信评议人数", width = 15)
    @ApiModelProperty(value = "其中微信评议人数")
	private Long qzwxpyrs;
	/**责任人*/
	@Excel(name = "责任人", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**评议户数排名*/
	@Excel(name = "评议户数排名", width = 15)
    @ApiModelProperty(value = "评议户数排名")
	private Long pyhspm;
}
