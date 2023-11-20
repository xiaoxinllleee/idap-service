package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity;

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
public class KhjlZfsjtjDr {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
	@ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**本日走访数*/
	@Excel(name = "本日走访数", width = 15)
    @ApiModelProperty(value = "本日走访数")
	private Integer brzfs;
	/**本周走访数*/
	@Excel(name = "本周走访数", width = 15)
    @ApiModelProperty(value = "本周走访数")
	private Integer bzzfs;
	/**本月走访数*/
	@Excel(name = "本月走访数", width = 15)
    @ApiModelProperty(value = "本月走访数")
	private Integer byzfs;
	/**本季走访数*/
	@Excel(name = "本季走访数", width = 15)
    @ApiModelProperty(value = "本季走访数")
	private Integer bjzfs;
	/**本年走访数*/
	@Excel(name = "本年走访数", width = 15)
    @ApiModelProperty(value = "本年走访数")
	private Integer bnzfs;
	/**累计走访数*/
	@Excel(name = "累计走访数", width = 15)
    @ApiModelProperty(value = "累计走访数")
	private Integer ljzfs;
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
	/**本年有效走访数*/
	@Excel(name = "本年有效走访数", width = 15)
    @ApiModelProperty(value = "本年有效走访数")
	private Integer bnyxzfs;
	/**累计有效走访数*/
	@Excel(name = "累计有效走访数", width = 15)
    @ApiModelProperty(value = "累计有效走访数")
	private Integer ljyxzfs;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;

	/**本日二次走访数*/
	@Excel(name = "本日二次走访数", width = 15)
	@ApiModelProperty(value = "本日二次走访数")
	private Integer breczfs;
	/**本周二次走访数*/
	@Excel(name = "本周二次走访数", width = 15)
	@ApiModelProperty(value = "本周二次走访数")
	private Integer bzeczfs;
	/**本月二次走访数*/
	@Excel(name = "本月二次走访数", width = 15)
	@ApiModelProperty(value = "本月二次走访数")
	private Integer byeczfs;
	/**本季二次走访数*/
	@Excel(name = "本季二次走访数", width = 15)
	@ApiModelProperty(value = "本季二次走访数")
	private Integer bjeczfs;
	/**本年二次走访数*/
	@Excel(name = "本年二次走访数", width = 15)
	@ApiModelProperty(value = "本年二次走访数")
	private Integer bneczfs;
	/**累计二次走访数*/
	@Excel(name = "累计二次走访数", width = 15)
	@ApiModelProperty(value = "累计二次走访数")
	private Integer ljeczfs;
	/**全行排名*/
	@Excel(name = "本年有效走访全行排名", width = 15)
	@ApiModelProperty(value = "本年有效走访全行排名")
	private Integer qhpm;

}
