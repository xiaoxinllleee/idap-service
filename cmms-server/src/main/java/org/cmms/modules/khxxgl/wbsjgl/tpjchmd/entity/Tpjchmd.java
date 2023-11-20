package org.cmms.modules.khxxgl.wbsjgl.tpjchmd.entity;

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
 * @Description: 脱贫户及监测户名单
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_wbsjgl_tpjchmd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_wbsjgl_tpjchmd对象", description="脱贫户及监测户名单")
public class Tpjchmd {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**客户名称*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**劳动技能*/
	@Excel(name = "劳动技能", width = 15)
    @ApiModelProperty(value = "劳动技能")
	private String ldjn;
	/**致贫原因*/
	@Excel(name = "致贫原因1", width = 15)
    @ApiModelProperty(value = "致贫原因1")
	private String zpyy;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**户类型*/
	@Excel(name = "户类型", width = 15)
    @ApiModelProperty(value = "户类型")
	private String hlx;
	/**易返贫致贫户(监测对象)类型*/
	@Excel(name = "易返贫致贫户(监测对象)类型", width = 15)
    @ApiModelProperty(value = "易返贫致贫户(监测对象)类型")
	private String yfpzpflx;
	/**风险是否消除*/
	@Excel(name = "风险是否消除", width = 15)
    @ApiModelProperty(value = "风险是否消除")
	private String fxsfxc;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
