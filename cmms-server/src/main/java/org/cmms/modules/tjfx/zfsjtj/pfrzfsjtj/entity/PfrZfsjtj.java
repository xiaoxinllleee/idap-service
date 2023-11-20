package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity;

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
 * @Description: 陪访人走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zfsjtj_pfr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zfsjtj_pfr对象", description="陪访人走访数据统计")
public class PfrZfsjtj {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15, dicCode="GWBZ", dictTable="HR_BAS_POST", dicText="GWMC")
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode="GWBZ", dictTable="HR_BAS_POST", dicText="GWMC")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15, dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;
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
}
