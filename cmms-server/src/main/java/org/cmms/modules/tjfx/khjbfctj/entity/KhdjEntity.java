package org.cmms.modules.tjfx.khjbfctj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_KHDJSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHDJ_KHDJSZ对象", description="1")
public class KhdjEntity {

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**等级编号*/
	@Excel(name = "等级编号", width = 15)
    @ApiModelProperty(value = "等级编号")
	private String djbh;
	/**等级名称*/
	@Excel(name = "等级名称", width = 15)
    @ApiModelProperty(value = "等级名称")
	private String djmc;
	/**等级描述*/
	@Excel(name = "等级描述", width = 15)
    @ApiModelProperty(value = "等级描述")
	private String djms;
}
