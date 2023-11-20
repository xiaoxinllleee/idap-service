package org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity;

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
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_wgxxtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgxxtj对象", description="网格信息统计")
public class Wgxxtj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**总人数*/
	@Excel(name = "总人数", width = 15)
    @ApiModelProperty(value = "总人数")
	private Integer zrs;
	/**党员人数*/
	@Excel(name = "党员人数", width = 15)
    @ApiModelProperty(value = "党员人数")
	private Integer dyrs;
	/**公职人员人数*/
	@Excel(name = "公职人员人数", width = 15)
    @ApiModelProperty(value = "公职人员人数")
	private Integer gzryrs;
	/**特岗教师人数*/
	@Excel(name = "特岗教师人数", width = 15)
    @ApiModelProperty(value = "特岗教师人数")
	private Integer tgjsrs;
	/**外部社保卡*/
	@Excel(name = "外部社保卡", width = 15)
    @ApiModelProperty(value = "外部社保卡")
	private Integer wbsbk;
	/**脱贫监测户*/
	@Excel(name = "脱贫监测户", width = 15)
    @ApiModelProperty(value = "脱贫监测户")
	private Integer tpjch;
	/**五保低保户*/
	@Excel(name = "五保低保户", width = 15)
    @ApiModelProperty(value = "五保低保户")
	private Integer wbdbh;
	/**重大疾病人数*/
	@Excel(name = "重大疾病人数", width = 15)
    @ApiModelProperty(value = "重大疾病人数")
	private Integer zdjbrs;
	/**诈骗人员*/
	@Excel(name = "诈骗人员", width = 15)
    @ApiModelProperty(value = "诈骗人员")
	private Integer zpry;
	/**非法集资人数*/
	@Excel(name = "非法集资人数", width = 15)
    @ApiModelProperty(value = "非法集资人数")
	private Integer ffjzrs;
	/**吸毒人员*/
	@Excel(name = "吸毒人员", width = 15)
    @ApiModelProperty(value = "吸毒人员")
	private Integer xdry;
	/**服刑人员*/
	@Excel(name = "服刑人员", width = 15)
    @ApiModelProperty(value = "服刑人员")
	private Integer fxry;
	/**诉讼人数*/
	@Excel(name = "诉讼人数", width = 15)
    @ApiModelProperty(value = "诉讼人数")
	private Integer ssrs;
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
