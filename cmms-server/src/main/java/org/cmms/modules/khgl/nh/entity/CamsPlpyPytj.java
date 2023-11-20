package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 批量评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-29
 * @Version: V1.0
 */
@Data
@TableName("CAMS_PLPY_PYTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_PLPY_PYTJ对象", description="批量评议统计")
public class CamsPlpyPytj {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	/**评议员第一轮*/
	@Excel(name = "评议员第一轮", width = 15)
    @ApiModelProperty(value = "评议员第一轮")
	@Dict(dicCode="PYYZJHM",dictTable="cams_zcsx_nhplpy",dicText="PYYXM")
	private String pyy1;
	/**村组明细第一轮*/
	@Excel(name = "村组明细第一轮", width = 15)
    @ApiModelProperty(value = "村组明细第一轮")
	private String czmx1;
	/**是否结束第一轮*/
	@Excel(name = "是否结束第一轮", width = 15)
    @ApiModelProperty(value = "是否结束第一轮")
	@Dict(dicCode = "sfbz_pad_sfjs")
	private String sfjs1;
	/**是否验收第一轮*/
	@Excel(name = "是否验收第一轮", width = 15)
    @ApiModelProperty(value = "是否验收第一轮")
	@Dict(dicCode = "sfbz_pad_sfys")
	private String sfys1;
	/**评议员第2轮*/
	@Excel(name = "评议员第2轮", width = 15)
    @ApiModelProperty(value = "评议员第2轮")
	@Dict(dicCode="PYYZJHM",dictTable="cams_zcsx_nhplpy",dicText="PYYXM")
	private String pyy2;
	/**村组明细第2轮*/
	@Excel(name = "村组明细第2轮", width = 15)
    @ApiModelProperty(value = "村组明细第2轮")
	private String czmx2;
	/**是否结束第2轮*/
	@Excel(name = "是否结束第2轮", width = 15)
    @ApiModelProperty(value = "是否结束第2轮")
	@Dict(dicCode = "sfbz_pad_sfjs")
	private String sfjs2;
	/**是否验收第2轮*/
	@Excel(name = "是否验收第2轮", width = 15)
    @ApiModelProperty(value = "是否验收第2轮")
	@Dict(dicCode = "sfbz_pad_sfys")
	private String sfys2;
	/**评议员第3轮*/
	@Excel(name = "评议员第3轮", width = 15)
    @ApiModelProperty(value = "评议员第3轮")
	@Dict(dicCode="PYYZJHM",dictTable="cams_zcsx_nhplpy",dicText="PYYXM")
	private String pyy3;
	/**村组明细第3轮*/
	@Excel(name = "村组明细第3轮", width = 15)
    @ApiModelProperty(value = "村组明细第3轮")
	private String czmx3;
	/**是否结束第3轮*/
	@Excel(name = "是否结束第3轮", width = 15)
    @ApiModelProperty(value = "是否结束第3轮")
	@Dict(dicCode = "sfbz_pad_sfjs")
	private String sfjs3;
	/**是否验收第3轮*/
	@Excel(name = "是否验收第3轮", width = 15)
    @ApiModelProperty(value = "是否验收第3轮")
	@Dict(dicCode = "sfbz_pad_sfys")
	private String sfys3;
	/**评议是否完成*/
	@Excel(name = "评议是否完成", width = 15)
    @ApiModelProperty(value = "评议是否完成")
	@Dict(dicCode = "sfbz_pad_sfwc")
	private String pysfwc;
	/**村组明细*/
	@Excel(name = "村组明细", width = 15)
    @ApiModelProperty(value = "村组明细")
	private String czmx;
	/**是否验收*/
	@Excel(name = "是否验收", width = 15)
    @ApiModelProperty(value = "是否验收")
	@Dict(dicCode = "sfbz_pad_sfys")
	private String sfys;
}
