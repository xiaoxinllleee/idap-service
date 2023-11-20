package org.cmms.modules.khgl.grkhgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 个人客户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRPJSXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRPJSXXX对象", description="个人客户评级授信信息")
public class Pjsxxx {

	@Excel(name = "花名册id", width = 15)
	@ApiModelProperty(value = "花名册id")
	private String hmcId;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
	@ApiModelProperty(value = "区域代码")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**家庭成员是否有不良嗜好*/
	@Excel(name = "家庭成员是否有不良嗜好", width = 15)
    @ApiModelProperty(value = "家庭成员是否有不良嗜好")
	private String shsyBlsh;
	/**家庭成员是否勤快*/
	@Excel(name = "家庭成员是否勤快", width = 15)
    @ApiModelProperty(value = "家庭成员是否勤快")
	private String shsySfqk;
	/**家庭成员是否有民间高息贷款*/
	@Excel(name = "家庭成员是否有民间高息贷款", width = 15)
    @ApiModelProperty(value = "家庭成员是否有民间高息贷款")
	private String shsySfygld;
	/**家庭成员是否有打架、闹事等不良行为*/
	@Excel(name = "家庭成员是否有打架、闹事等不良行为", width = 15)
    @ApiModelProperty(value = "家庭成员是否有打架、闹事等不良行为")
	private String shsySfdjns;
	/**家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）*/
	@Excel(name = "家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）", width = 15)
    @ApiModelProperty(value = "家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）")
	private String shsySflqbz;
	/**家庭成员是否有刑事犯罪记录*/
	@Excel(name = "家庭成员是否有刑事犯罪记录", width = 15)
    @ApiModelProperty(value = "家庭成员是否有刑事犯罪记录")
	private String shsySfxsfz;
	/**家庭成员是否涉诉*/
	@Excel(name = "家庭成员是否涉诉", width = 15)
    @ApiModelProperty(value = "家庭成员是否涉诉")
	private String shsySfss;
	/**其他补助情况*/
	@Excel(name = "其他补助情况", width = 15)
	@ApiModelProperty(value = "其他补助情况")
	private String qtbzqk;
	/**犯罪类型*/
	@Excel(name = "犯罪类型", width = 15)
	@ApiModelProperty(value = "犯罪类型")
	private String fzlx;
	/**是否建档立卡贫困户*/
	@Excel(name = "是否建档立卡贫困户", width = 15)
	@ApiModelProperty(value = "是否建档立卡贫困户")
	private String sfjdlkpkh;

	/**对户主评价-品行评价*/
	@Excel(name = "对户主评价-品行评价", width = 15)
    @ApiModelProperty(value = "对户主评价-品行评价")
	private String dhzpjPxpj;
	/**对户主评价-信用评价*/
	@Excel(name = "对户主评价-信用评价", width = 15)
    @ApiModelProperty(value = "对户主评价-信用评价")
	private String dhzpjXypj;
	/**初评等级*/
	@Excel(name = "初评等级", width = 15)
    @ApiModelProperty(value = "初评等级")
	private String cpdj;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;




}
