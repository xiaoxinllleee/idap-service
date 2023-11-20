package org.cmms.modules.system.entity;

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
 * @Description: 员工在岗视图
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("VIEW_HR_BAS_STAFF_POST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VIEW_HR_BAS_STAFF_POST对象", description="员工在岗视图")
public class ViewHrBasStaffPost {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private Long id;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**组织名称*/
	@Excel(name = "组织名称", width = 15)
    @ApiModelProperty(value = "组织名称")
	private String zzmc;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**岗位名称*/
	@Excel(name = "岗位名称", width = 15)
    @ApiModelProperty(value = "岗位名称")
	private String gwmc;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String gyh;
	/**入岗类型(1 正式 2 代班 3 实习 4 虚拟 5 离岗)*/
	@Excel(name = "入岗类型(1 正式 2 代班 3 实习 4 虚拟 5 离岗)", width = 15)
    @ApiModelProperty(value = "入岗类型(1 正式 2 代班 3 实习 4 虚拟 5 离岗)")
	private Integer rglx;
	/**入岗日期*/
	@Excel(name = "入岗日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入岗日期")
	private Date rgrq;
	/**离岗日期*/
	@Excel(name = "离岗日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "离岗日期")
	private Date lgrq;
	/**是否参与考核(1 是 2 否)*/
	@Excel(name = "是否参与考核(1 是 2 否)", width = 15)
    @ApiModelProperty(value = "是否参与考核(1 是 2 否)")
	private Integer sfcykh;
	/**是否临退休(1 是 2 否)*/
	@Excel(name = "是否临退休(1 是 2 否)", width = 15)
    @ApiModelProperty(value = "是否临退休(1 是 2 否)")
	private Integer sfltx;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**删除标识(0 否 1 是)*/
	@Excel(name = "删除标识(0 否 1 是)", width = 15)
    @ApiModelProperty(value = "删除标识(0 否 1 是)")
	private Integer scbz;
	/**删除时间*/
	@Excel(name = "删除时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "删除时间")
	private Date scsj;
	/**删除操作员*/
	@Excel(name = "删除操作员", width = 15)
    @ApiModelProperty(value = "删除操作员")
	private String scczy;
}
