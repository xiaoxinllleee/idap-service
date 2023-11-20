package org.cmms.modules.yxdygl.yxdyglmain.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 网格基本信息视图
 * @Author: jeecg-boot
 * @Date:   2022-11-01
 * @Version: V1.0
 */
@Data
@TableName("v_yxdygl_main")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_yxdygl_main对象", description="网格基本信息视图")
public class VYxdyglMain {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	private String wgbh;
	/**wgmc*/
	@Excel(name = "wgmc", width = 15)
    @ApiModelProperty(value = "wgmc")
	private String wgmc;
	/**wgmcShow*/
	@Excel(name = "wgmcShow", width = 15)
    @ApiModelProperty(value = "wgmcShow")
	private String wgmcShow;
	/**wgxz*/
	@Excel(name = "wgxz", width = 15)
    @ApiModelProperty(value = "wgxz")
	private String wgxz;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
	/**parentId*/
	@Excel(name = "parentId", width = 15)
    @ApiModelProperty(value = "parentId")
	private String parentId;
}
