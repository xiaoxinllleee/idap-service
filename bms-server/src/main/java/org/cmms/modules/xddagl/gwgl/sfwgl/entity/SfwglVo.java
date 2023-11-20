package org.cmms.modules.xddagl.gwgl.sfwgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("V_xddagl_sfwgl_ckqk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_xddagl_sfwgl_ckqk对象", description="收发文管理")
public class SfwglVo {

	/**bt*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
	private String bt;
	/**fwbh*/
	@Excel(name = "发文编号", width = 15)
    @ApiModelProperty(value = "发文编号")
	private String fwbh;
	/**cwdw*/
	@Excel(name = "发文部室标识", width = 15)
    @ApiModelProperty(value = "发文部室标识")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String cwdw;
	/**fzr*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
	private String fzr;
	/**qfrq*/
	@Excel(name = "发文日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发文日期")
	private Date qfrq;
	/**bz*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;

}
