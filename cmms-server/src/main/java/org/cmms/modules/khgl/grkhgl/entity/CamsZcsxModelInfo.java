package org.cmms.modules.khgl.grkhgl.entity;

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
 * @Description: 模型详情
 * @Author: jeecg-boot
 * @Date:   2020-08-27
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_MODEL_INFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_MODEL_INFO对象", description="模型详情")
public class CamsZcsxModelInfo {
    
	/**系统评价等级*/
	@Excel(name = "系统评价等级", width = 15)
    @ApiModelProperty(value = "系统评价等级")
	private String xtpddj;
	/**系统评价得分*/
	@Excel(name = "系统评价得分", width = 15)
    @ApiModelProperty(value = "系统评价得分")
	private String xtpjdf;
	/**系统授信金额*/
	@Excel(name = "系统授信金额", width = 15)
    @ApiModelProperty(value = "系统授信金额")
	private String xtsxje;
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**户名编码*/
	@Excel(name = "户名编码", width = 15)
    @ApiModelProperty(value = "户名编码")
	private String hhbm;
	/**证件号*/
	@Excel(name = "证件号", width = 15)
    @ApiModelProperty(value = "证件号")
	private String zjhm;
	/**家庭人数*/
	@Excel(name = "家庭人数", width = 15)
    @ApiModelProperty(value = "家庭人数")
	private String jtrs;
	/**家庭人数得分*/
	@Excel(name = "家庭人数得分", width = 15)
    @ApiModelProperty(value = "家庭人数得分")
	private String jtrsdf;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**年龄得分*/
	@Excel(name = "年龄得分", width = 15)
    @ApiModelProperty(value = "年龄得分")
	private String nldf;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**健康状况得分*/
	@Excel(name = "健康状况得分", width = 15)
    @ApiModelProperty(value = "健康状况得分")
	private String jkzkdf;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**婚姻状况得分*/
	@Excel(name = "婚姻状况得分", width = 15)
    @ApiModelProperty(value = "婚姻状况得分")
	private String hyzkdf;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String zc;
	/**以上部分总得分*/
	@Excel(name = "以上部分总得分", width = 15)
    @ApiModelProperty(value = "以上部分总得分")
	private String scoreOne;
	/**我行活期余额*/
	@Excel(name = "我行活期余额", width = 15)
    @ApiModelProperty(value = "我行活期余额")
	private String hq;
	/**我行活期余额得分*/
	@Excel(name = "我行活期余额得分", width = 15)
    @ApiModelProperty(value = "我行活期余额得分")
	private String hqdf;
	/**我行定期余额*/
	@Excel(name = "我行定期余额", width = 15)
    @ApiModelProperty(value = "我行定期余额")
	private String dq;
	/**我行定期余额得分*/
	@Excel(name = "我行定期余额得分", width = 15)
    @ApiModelProperty(value = "我行定期余额得分")
	private String dqdf;
	/**定活期总分*/
	@Excel(name = "定活期总分", width = 15)
    @ApiModelProperty(value = "定活期总分")
	private String scoreTwo;
	/**存入交易额*/
	@Excel(name = "存入交易额", width = 15)
    @ApiModelProperty(value = "存入交易额")
	private String crjye;
	/**支出交易额*/
	@Excel(name = "支出交易额", width = 15)
    @ApiModelProperty(value = "支出交易额")
	private String zcjye;
	/**存入和支出得分*/
	@Excel(name = "存入和支出得分", width = 15)
    @ApiModelProperty(value = "存入和支出得分")
	private String crzcdf;
	/**存入交易次数*/
	@Excel(name = "存入交易次数", width = 15)
    @ApiModelProperty(value = "存入交易次数")
	private String zcjycs;
	/**支出交易次数*/
	@Excel(name = "支出交易次数", width = 15)
    @ApiModelProperty(value = "支出交易次数")
	private String crjycs;
	/**zcjycsdf*/
	@Excel(name = "zcjycsdf", width = 15)
    @ApiModelProperty(value = "zcjycsdf")
	private String zcjycsdf;
	/**crjycsdf*/
	@Excel(name = "crjycsdf", width = 15)
    @ApiModelProperty(value = "crjycsdf")
	private String crjycsdf;
	/**存款账号数量*/
	@Excel(name = "存款账号数量", width = 15)
    @ApiModelProperty(value = "存款账号数量")
	private String ckzhsl;
	/**ckzhsldf*/
	@Excel(name = "ckzhsldf", width = 15)
    @ApiModelProperty(value = "ckzhsldf")
	private String ckzhsldf;
	/**手机银行*/
	@Excel(name = "手机银行", width = 15)
    @ApiModelProperty(value = "手机银行")
	private String sjyhjls;
	/**sjyhjlsdf*/
	@Excel(name = "sjyhjlsdf", width = 15)
    @ApiModelProperty(value = "sjyhjlsdf")
	private String sjyhjlsdf;
	/**口袋零钱*/
	@Excel(name = "口袋零钱", width = 15)
    @ApiModelProperty(value = "口袋零钱")
	private String kdlqjls;
	/**kdlqjlsdf*/
	@Excel(name = "kdlqjlsdf", width = 15)
    @ApiModelProperty(value = "kdlqjlsdf")
	private String kdlqjlsdf;
	/**网银*/
	@Excel(name = "网银", width = 15)
    @ApiModelProperty(value = "网银")
	private String wyjls;
	/**wyjlsdf*/
	@Excel(name = "wyjlsdf", width = 15)
    @ApiModelProperty(value = "wyjlsdf")
	private String wyjlsdf;
	/**etc*/
	@Excel(name = "etc", width = 15)
    @ApiModelProperty(value = "etc")
	private String etcjls;
	/**etcjlsdf*/
	@Excel(name = "etcjlsdf", width = 15)
    @ApiModelProperty(value = "etcjlsdf")
	private String etcjlsdf;
	/**农信银*/
	@Excel(name = "农信银", width = 15)
    @ApiModelProperty(value = "农信银")
	private String nxyjls;
	/**nxyjlsdf*/
	@Excel(name = "nxyjlsdf", width = 15)
    @ApiModelProperty(value = "nxyjlsdf")
	private String nxyjlsdf;
	/**水电代收*/
	@Excel(name = "水电代收", width = 15)
    @ApiModelProperty(value = "水电代收")
	private String sfds;
	/**sfdsdf*/
	@Excel(name = "sfdsdf", width = 15)
    @ApiModelProperty(value = "sfdsdf")
	private String sfdsdf;
	/**本行关系总得分*/
	@Excel(name = "本行关系总得分", width = 15)
    @ApiModelProperty(value = "本行关系总得分")
	private String scoreThree;
	/**逾期等级*/
	@Excel(name = "逾期等级", width = 15)
    @ApiModelProperty(value = "逾期等级")
	private String yqdj;
	/**预期等级理由*/
	@Excel(name = "预期等级理由", width = 15)
    @ApiModelProperty(value = "预期等级理由")
	private String yqdjly;
	/**逾期信息得分*/
	@Excel(name = "逾期信息得分", width = 15)
    @ApiModelProperty(value = "逾期信息得分")
	private String yqdjdf;
	/**社会评价*/
	@Excel(name = "社会评价", width = 15)
    @ApiModelProperty(value = "社会评价")
	private String shpj;
	/**社会评价理由*/
	@Excel(name = "社会评价理由", width = 15)
    @ApiModelProperty(value = "社会评价理由")
	private String shpjly;
	/**社会评价得分*/
	@Excel(name = "社会评价得分", width = 15)
    @ApiModelProperty(value = "社会评价得分")
	private String shpjdf;
	/**社会评级得分理由*/
	@Excel(name = "社会评级得分理由", width = 15)
    @ApiModelProperty(value = "社会评级得分理由")
	private String shpjdfly;
	private String zcdf;
	private String cszy;
	private String cszydf;
}
