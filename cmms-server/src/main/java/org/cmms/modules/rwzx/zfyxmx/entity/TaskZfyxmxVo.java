package org.cmms.modules.rwzx.zfyxmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.sbxj.fxzdxjjlb.entity.SbxxFjVO;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 走访营销明细
 * @Author: jeecg-boot
 * @Date:   2023-07-17
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_ZFYXMX对象", description="走访营销明细")
public class TaskZfyxmxVo {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**任务id*/
	@Excel(name = "任务id", width = 15)
    @ApiModelProperty(value = "任务id")
	private String rwid;
	/**明细数据id*/
	@Excel(name = "明细数据id", width = 15)
    @ApiModelProperty(value = "明细数据id")
	private String mxsjid;
	/**营销人走访人*/
	@Excel(name = "营销人走访人", width = 15)
	@ApiModelProperty(value = "营销人走访人")
	private String yxzfr;
	/**营销类型*/
	@Excel(name = "营销类型", width = 15,dicCode = "zfyxlx")
    @ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "zfyxlx")
	private String yxlx;
	/**营销方式*/
	@Excel(name = "营销方式", width = 15,dicCode = "zfyxfs")
    @ApiModelProperty(value = "营销方式")
	@Dict(dicCode = "zfyxfs")
	private String yxfs;
	/**营销结果*/
	@Excel(name = "营销结果", width = 15,dicCode = "yxjg")
    @ApiModelProperty(value = "营销结果")
	@Dict(dicCode = "yxjg")
	private String yxjg;
	/**营销产品*/
	@Excel(name = "营销产品", width = 15,dicCode = "fxd_yxcp")
    @ApiModelProperty(value = "营销产品")
	@Dict(dicCode = "fxd_yxcp")
	private String yxcp;
	/**营销失败原因*/
	@Excel(name = "营销失败原因", width = 15)
    @ApiModelProperty(value = "营销失败原因")
	private String yxsbyy;
	/**客户意向*/
	@Excel(name = "客户意向", width = 15,dicCode = "fxd_yxcp")
    @ApiModelProperty(value = "客户意向")
	@Dict(dicCode = "fxd_yxcp")
	private String khyx;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String jd;
	/**维度*/
	@Excel(name = "维度", width = 15)
    @ApiModelProperty(value = "维度")
	private String wd;
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;

	/**创建人*/
	@Excel(name = "创建人", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建人")
	private Date createTime;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
	private String createBy;
	/**更新人*/
	@Excel(name = "更新人", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新人")
	private Date updateTime;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15)
    @ApiModelProperty(value = "更新时间")
	private String updateBy;

	private String grnsr;
	private String jtnsr;
	private String zz;
	private String sfycdg;
	private String wgcs;

	private List<SbxxFjVO> fjxxList;

	@Dict(dicCode = "yx_khlx2")
	private String khlx2yx;

	private String lxfs;
}
