package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity;

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
 * @Description: 客户经理走访统计-当日
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zfsjtj_khjl_dr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zfsjtj_khjl_dr对象", description="客户经理走访统计-当日")
public class KhjlZfsjtjDrVo {
    

	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
	@ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**本日有效走访数*/
	@Excel(name = "本日有效走访数", width = 15)
    @ApiModelProperty(value = "本日有效走访数")
	private Integer bryxzfs;
	/**本周有效走访数*/
	@Excel(name = "本周有效走访数", width = 15)
    @ApiModelProperty(value = "本周有效走访数")
	private Integer bzyxzfs;
	/**本月有效走访数*/
	@Excel(name = "本月有效走访数", width = 15)
    @ApiModelProperty(value = "本月有效走访数")
	private Integer byyxzfs;
	/**本季有效走访数*/
	@Excel(name = "本季有效走访数", width = 15)
    @ApiModelProperty(value = "本季有效走访数")
	private Integer bjyxzfs;
	/**累计有效走访数*/
	@Excel(name = "累计有效走访数", width = 15)
    @ApiModelProperty(value = "累计有效走访数")
	private Integer ljyxzfs;
	/**累计二次走访数*/
	@Excel(name = "累计二次走访数", width = 15)
	@ApiModelProperty(value = "累计二次走访数")
	private Integer ljeczfs;

	private Integer wxzfs;

	/** 本日被他行走访人数 */
	private Integer brbthzfs;
	/** 本周被他行走访人数 */
	private Integer bzbthzfs;
	/** 本周被他行走访名单 */
	private String bzbthzfmd;

}
