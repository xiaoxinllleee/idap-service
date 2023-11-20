package org.cmms.modules.tjfx.sjhztj.zhhztj.entity;

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
 * @Description: 支行汇总统计数据
 * @Author: jeecg-boot
 * @Date:   2023-09-05
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_hztj_zh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_hztj_zh对象", description="支行汇总统计数据")
public class VTjfxHztjZh {

	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**本日走访数*/
	@Excel(name = "走访数", width = 15,groupName = "本日")
	@ApiModelProperty(value = "本日走访数")
	private Integer brzfs;
	/**本日有效走访数*/
	@Excel(name = "有效走访数", width = 15,groupName = "本日")
	@ApiModelProperty(value = "本日有效走访数")
	private Integer bryxzfs;
	/**本日完成任务数*/
	@Excel(name = "完成任务数", width = 15,groupName = "本日")
	@ApiModelProperty(value = "本日完成任务数")
	private Integer brwcrws;
	/**本周走访数*/
	@Excel(name = "走访数", width = 15,groupName = "本周")
	@ApiModelProperty(value = "本周走访数")
	private Integer bzzfs;
	/**本周有效走访数*/
	@Excel(name = "有效走访数", width = 15,groupName = "本周")
	@ApiModelProperty(value = "本周有效走访数")
	private Integer bzyxzfs;
	/**本周任务完成数*/
	@Excel(name = "完成任务数", width = 15,groupName = "本周")
	@ApiModelProperty(value = "本周任务完成数")
	private Integer bzwcrws;
	/**本月走访数*/
	@Excel(name = "走访数", width = 15,groupName = "本月")
	@ApiModelProperty(value = "本月走访数")
	private Integer byzfs;
	/**本月有效走访数*/
	@Excel(name = "有效走访数", width = 15,groupName = "本月")
	@ApiModelProperty(value = "本月有效走访数")
	private Integer byyxzfs;
	/**本月完成任务数*/
	@Excel(name = "完成任务数", width = 15,groupName = "本月")
	@ApiModelProperty(value = "本月完成任务数")
	private Integer bywcrws;
	/**累计走访数*/
	@Excel(name = "累计走访数", width = 15)
	@ApiModelProperty(value = "累计走访数")
	private Integer ljzfs;
	/**累计任务完成数*/
	@Excel(name = "累计任务完成数", width = 15)
	@ApiModelProperty(value = "累计任务完成数")
	private Integer ljwcrws;

	@TableField(exist = false)
	private Integer pm;
}
