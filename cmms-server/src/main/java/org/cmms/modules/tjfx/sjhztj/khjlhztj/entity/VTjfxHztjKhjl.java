package org.cmms.modules.tjfx.sjhztj.khjlhztj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 全行汇总统计
 * @Author: jeecg-boot
 * @Date:   2023-09-05
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_hztj_khjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_hztj_khjl对象", description="全行汇总统计")
public class VTjfxHztjKhjl {
    
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
    @ApiModelProperty(value = "岗位标志")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
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

	/**排名 */
	@TableField(exist = false)
	private Integer pm;
}
