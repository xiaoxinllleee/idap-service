package org.cmms.modules.tjfx.khhxxzc.entity;

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
 * @Description: 客户画像_行政村
 * @Author: jeecg-boot
 * @Date:   2022-07-06
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHHX_XZC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHHX_XZC对象", description="客户画像_行政村")
public class KhhxXzc {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**组个数*/
	@Excel(name = "组个数", width = 15)
    @ApiModelProperty(value = "组个数")
	private Integer zgs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**白名单户数*/
	@Excel(name = "白名单户数", width = 15)
    @ApiModelProperty(value = "白名单户数")
	private Integer bmdhs;
	/**灰名单户数*/
	@Excel(name = "灰名单户数", width = 15)
    @ApiModelProperty(value = "灰名单户数")
	private Integer hmdhs;
	/**黑名单户数*/
	@Excel(name = "黑名单户数", width = 15)
    @ApiModelProperty(value = "黑名单户数")
	private Integer heimdhs;
	/**存款户数*/
	@Excel(name = "存款户数", width = 15)
    @ApiModelProperty(value = "存款户数")
	private Integer ckhs;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**定期存款余额*/
	@Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;
	/**活期存款余额*/
	@Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
	private java.math.BigDecimal hqckye;
	/**存款覆盖面*/
	@Excel(name = "存款覆盖面", width = 15)
    @ApiModelProperty(value = "存款覆盖面")
	private java.math.BigDecimal ckfgm;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private Integer dkhs;
	/**贷款覆盖面*/
	@Excel(name = "贷款覆盖面", width = 15)
	@ApiModelProperty(value = "贷款覆盖面")
	private java.math.BigDecimal dkfgm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**表内贷款户数*/
	@Excel(name = "表内贷款户数", width = 15)
    @ApiModelProperty(value = "表内贷款户数")
	private Integer bndkhs;
	/**表内贷款余额*/
	@Excel(name = "表内贷款余额", width = 15)
    @ApiModelProperty(value = "表内贷款余额")
	private java.math.BigDecimal bndkye;
	/**表外贷款户数*/
	@Excel(name = "表外贷款户数", width = 15)
    @ApiModelProperty(value = "表外贷款户数")
	private Integer bwdkhs;
	/**表外贷款余额*/
	@Excel(name = "表外贷款余额", width = 15)
    @ApiModelProperty(value = "表外贷款余额")
	private java.math.BigDecimal bwdkye;

	/**存量户数*/
	@Excel(name = "存量户数", width = 15)
	@ApiModelProperty(value = "存量户数")
	private java.math.BigDecimal clhs;
	/**无贷款户数*/
	@Excel(name = "无贷款户数", width = 15)
	@ApiModelProperty(value = "无贷款户数")
	private java.math.BigDecimal wdkhs;
	/**白名单_无贷款户数*/
	@Excel(name = "白名单_无贷款户数", width = 15)
	@ApiModelProperty(value = "白名单_无贷款户数")
	private java.math.BigDecimal bmdWdkhs;
	/**灰名单_无贷款户数*/
	@Excel(name = "灰名单_无贷款户数", width = 15)
	@ApiModelProperty(value = "灰名单_无贷款户数")
	private java.math.BigDecimal hmdWdkhs;
	/**种养大户*/
	@Excel(name = "种养大户", width = 15)
	@ApiModelProperty(value = "种养大户")
	private java.math.BigDecimal zydh;
	/**种养大户_有贷款*/
	@Excel(name = "种养大户_有贷款", width = 15)
	@ApiModelProperty(value = "种养大户_有贷款")
	private java.math.BigDecimal zydhYdk;
	/**种养大户_无贷款*/
	@Excel(name = "种养大户_无贷款", width = 15)
	@ApiModelProperty(value = "种养大户_无贷款")
	private java.math.BigDecimal zydhWdk;
	/**经营户*/
	@Excel(name = "经营户", width = 15)
	@ApiModelProperty(value = "经营户")
	private java.math.BigDecimal jyh;
	/**经营户_本省*/
	@Excel(name = "经营户_本省", width = 15)
	@ApiModelProperty(value = "经营户_本省")
	private java.math.BigDecimal jyhBs;
	/**经营户_外省*/
	@Excel(name = "经营户_外省", width = 15)
	@ApiModelProperty(value = "经营户_外省")
	private java.math.BigDecimal jyhWs;
	/**外出务工户*/
	@Excel(name = "外出务工户", width = 15)
	@ApiModelProperty(value = "外出务工户")
	private java.math.BigDecimal wcwgh;
	/**外出务工户_本省*/
	@Excel(name = "外出务工户_本省", width = 15)
	@ApiModelProperty(value = "外出务工户_本省")
	private java.math.BigDecimal wcwghBs;
	/**外出务工户_外省*/
	@Excel(name = "外出务工户_外省", width = 15)
	@ApiModelProperty(value = "外出务工户_外省")
	private java.math.BigDecimal wcwghWs;
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

	/**本省经营_主要经营项目*/
	@Excel(name = "本省经营_主要经营项目", width = 15)
	@ApiModelProperty(value = "本省经营_主要经营项目")
	private String zyjyxmBs;
	/**本本省经营_主要经营地点*/
	@Excel(name = "本省经营_主要经营地点", width = 15)
	@ApiModelProperty(value = "本省经营_主要经营地点")
	private String zyjyddBs;
	/**外省经营_主要经营项目*/
	@Excel(name = "外省经营_主要经营项目", width = 15)
	@ApiModelProperty(value = "外省经营_主要经营项目")
	private String zyjyxmWs;
	/**本省经营_主要经营项目*/
	@Excel(name = "外省经营_主要经营地点", width = 15)
	@ApiModelProperty(value = "外省经营_主要经营地点")
	private String zyjyddWs;
}
