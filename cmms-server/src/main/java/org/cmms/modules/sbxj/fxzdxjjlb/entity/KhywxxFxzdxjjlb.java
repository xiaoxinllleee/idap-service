package org.cmms.modules.sbxj.fxzdxjjlb.entity;

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
 * @Description: 巡检记录表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khywxx_FXZDXJJLB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khywxx_FXZDXJJLB对象", description="巡检记录表")
public class KhywxxFxzdxjjlb {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**站点编号*/
	@Excel(name = "站点编号", width = 15)
    @ApiModelProperty(value = "站点编号")
	private String zdbh;
	/**巡检人员*/
	@Excel(name = "巡检人工号", width = 15)
	@ApiModelProperty(value = "巡检人员")
	private String xjrgh;
	/**巡检人员*/
	@Excel(name = "巡检人员", width = 15)
    @ApiModelProperty(value = "巡检人员")
	private String xjry;
	/**巡检次数*/
	@Excel(name = "巡检次数", width = 15)
    @ApiModelProperty(value = "巡检次数")
	private String xjcs;
	/**巡检时间*/
	@Excel(name = "巡检时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "巡检时间")
	private Date xjsj;
	/**是否设备改造*/
	@Excel(name = "是否设备改造", width = 15)
    @ApiModelProperty(value = "是否设备改造")
	@Dict(dicCode = "sfbz")
	private String sfsbgz;
	/**是否设备正常*/
	@Excel(name = "是否设备正常", width = 15)
    @ApiModelProperty(value = "是否设备正常")
	@Dict(dicCode = "sfbz")
	private String sfsbzc;
	/**是否培训*/
	@Excel(name = "是否培训", width = 15)
    @ApiModelProperty(value = "是否培训")
	@Dict(dicCode = "sfbz")
	private String sfpx;
	/**交易单保管是否符合规定*/
	@Excel(name = "交易单保管是否符合规定", width = 15)
    @ApiModelProperty(value = "交易单保管是否符合规定")
	@Dict(dicCode = "sfbz")
	private String jydbgsffhgd;
	/**登记簿是否正常*/
	@Excel(name = "登记簿是否正常", width = 15)
    @ApiModelProperty(value = "登记簿是否正常")
	@Dict(dicCode = "sfbz")
	private String djbsfzc;
	/**交易流程及操作*/
	@Excel(name = "交易流程及操作", width = 15)
    @ApiModelProperty(value = "交易流程及操作")
	@Dict(dicCode = "xjpx")
	private String jylc;
	/**账务核对及常见问题处理*/
	@Excel(name = "账务核对及常见问题处理", width = 15)
    @ApiModelProperty(value = "账务核对及常见问题处理")
	@Dict(dicCode = "xjpx")
	private String cwhd;
	/**风险防范*/
	@Excel(name = "风险防范", width = 15)
    @ApiModelProperty(value = "风险防范")
	@Dict(dicCode = "xjpx")
	private String fxff;
	/**不出租或出借或给他人使用福祥E站设备*/
	@Excel(name = "不出租或出借或给他人使用福祥E站设备", width = 15)
    @ApiModelProperty(value = "不出租或出借或给他人使用福祥E站设备")
	@Dict(dicCode = "xjpx")
	private String bcz;
	/**签购单保管*/
	@Excel(name = "签购单保管", width = 15)
    @ApiModelProperty(value = "签购单保管")
	@Dict(dicCode = "xjpx")
	private String qgdbg;
	/**故障处理*/
	@Excel(name = "故障处理", width = 15)
    @ApiModelProperty(value = "故障处理")
	@Dict(dicCode = "xjpx")
	private String gzcl;
	/**商户问题反馈及建议 (非必填项)*/
	@Excel(name = "商户问题反馈及建议 (非必填项)", width = 15)
    @ApiModelProperty(value = "商户问题反馈及建议 (非必填项)")
	private String shwt;
	/**巡查结果*/
	@Excel(name = "巡查结果", width = 15)
    @ApiModelProperty(value = "巡查结果")
	@Dict(dicCode = "xjjg")
	private String xcjg;
	/**巡查结果说明*/
	@Excel(name = "巡查结果说明", width = 15)
    @ApiModelProperty(value = "巡查结果说明")
	private String xcjgsm;
	/**巡检人员*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String jd;
	/**巡检人员*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String wd;
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
}
