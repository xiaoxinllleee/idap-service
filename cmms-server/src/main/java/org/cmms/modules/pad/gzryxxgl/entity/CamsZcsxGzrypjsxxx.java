package org.cmms.modules.pad.gzryxxgl.entity;

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
 * @Description: 公职人员评级授信表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GZRYPJSXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GZRYPJSXXX对象", description="公职人员评级授信表")
public class CamsZcsxGzrypjsxxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**公职人员ID*/
	@Excel(name = "公职人员ID", width = 15)
    @ApiModelProperty(value = "公职人员ID")
	private String gzryid;
	/**公职人员名称*/
	@Excel(name = "公职人员名称", width = 15)
    @ApiModelProperty(value = "公职人员名称")
	private String khmc;
	/**房地产资产合计*/
	@Excel(name = "房地产资产合计", width = 15)
    @ApiModelProperty(value = "房地产资产合计")
	private String fczchj;
	/**地产数量*/
	@Excel(name = "地产数量", width = 15)
    @ApiModelProperty(value = "地产数量")
	private String dcsl;
	/**地产价值*/
	@Excel(name = "地产价值", width = 15)
    @ApiModelProperty(value = "地产价值")
	private String dcjz;
	/**地产详情说明*/
	@Excel(name = "地产详情说明", width = 15)
    @ApiModelProperty(value = "地产详情说明")
	private String dcxqsm;
	/**交通工具数量*/
	@Excel(name = "交通工具数量", width = 15)
    @ApiModelProperty(value = "交通工具数量")
	private String jtgjsl;
	/**交通工具价值*/
	@Excel(name = "交通工具价值", width = 15)
    @ApiModelProperty(value = "交通工具价值")
	private String jtgjjz;
	/**交通工具详情说明*/
	@Excel(name = "交通工具详情说明", width = 15)
    @ApiModelProperty(value = "交通工具详情说明")
	private String jtgjxqsm;
	/**存款数量*/
	@Excel(name = "存款数量", width = 15)
    @ApiModelProperty(value = "存款数量")
	private String cksl;
	/**存款价值*/
	@Excel(name = "存款价值", width = 15)
    @ApiModelProperty(value = "存款价值")
	private String ckjz;
	/**存款详情说明*/
	@Excel(name = "存款详情说明", width = 15)
    @ApiModelProperty(value = "存款详情说明")
	private String ckxqsm;
	/**有价单证数量*/
	@Excel(name = "有价单证数量", width = 15)
    @ApiModelProperty(value = "有价单证数量")
	private String yjdzsl;
	/**有价单证价值*/
	@Excel(name = "有价单证价值", width = 15)
    @ApiModelProperty(value = "有价单证价值")
	private String yjdzjz;
	/**有价单证详情说明*/
	@Excel(name = "有价单证详情说明", width = 15)
    @ApiModelProperty(value = "有价单证详情说明")
	private String yjdzxqsm;
	/**股权数量*/
	@Excel(name = "股权数量", width = 15)
    @ApiModelProperty(value = "股权数量")
	private String gqsl;
	/**股权价值*/
	@Excel(name = "股权价值", width = 15)
    @ApiModelProperty(value = "股权价值")
	private String gqjz;
	/**股权详情说明*/
	@Excel(name = "股权详情说明", width = 15)
    @ApiModelProperty(value = "股权详情说明")
	private String gqxqsm;
	/**其他资产数量*/
	@Excel(name = "其他资产数量", width = 15)
    @ApiModelProperty(value = "其他资产数量")
	private String qtzcsl;
	/**其他资产价值*/
	@Excel(name = "其他资产价值", width = 15)
    @ApiModelProperty(value = "其他资产价值")
	private String qtzcjz;
	/**其他资产详情说明*/
	@Excel(name = "其他资产详情说明", width = 15)
    @ApiModelProperty(value = "其他资产详情说明")
	private String qtzcxqsm;
	/**年总收入数量*/
	@Excel(name = "年总收入数量", width = 15)
    @ApiModelProperty(value = "年总收入数量")
	private String nzsrsl;
	/**年总收入价值*/
	@Excel(name = "年总收入价值", width = 15)
    @ApiModelProperty(value = "年总收入价值")
	private String nzsrjz;
	/**年总收入详情说明*/
	@Excel(name = "年总收入详情说明", width = 15)
    @ApiModelProperty(value = "年总收入详情说明")
	private String nzsrxqsm;
	/**所有资产合计*/
	@Excel(name = "所有资产合计", width = 15)
    @ApiModelProperty(value = "所有资产合计")
	private String sszchj;
	/**本系统借款债权人*/
	@Excel(name = "本系统借款债权人", width = 15)
    @ApiModelProperty(value = "本系统借款债权人")
	private String bxtjkzqr;
	/**本系统借款数量*/
	@Excel(name = "本系统借款数量", width = 15)
    @ApiModelProperty(value = "本系统借款数量")
	private String bxtjksl;
	/**本系统借款详情说明*/
	@Excel(name = "本系统借款详情说明", width = 15)
    @ApiModelProperty(value = "本系统借款详情说明")
	private String bxtjkxqsm;
	/**他行借款债权人*/
	@Excel(name = "他行借款债权人", width = 15)
    @ApiModelProperty(value = "他行借款债权人")
	private String thjkzqr;
	/**他行借款数量*/
	@Excel(name = "他行借款数量", width = 15)
    @ApiModelProperty(value = "他行借款数量")
	private String thjksl;
	/**他行借款详情说明*/
	@Excel(name = "他行借款详情说明", width = 15)
    @ApiModelProperty(value = "他行借款详情说明")
	private String thjkxqsm;
	/**信用卡债权人*/
	@Excel(name = "信用卡债权人", width = 15)
    @ApiModelProperty(value = "信用卡债权人")
	private String xykzqr;
	/**信用卡数量*/
	@Excel(name = "信用卡数量", width = 15)
    @ApiModelProperty(value = "信用卡数量")
	private String xyksl;
	/**信用卡详情说明*/
	@Excel(name = "信用卡详情说明", width = 15)
    @ApiModelProperty(value = "信用卡详情说明")
	private String xykxqsm;
	/**其他负债债权人*/
	@Excel(name = "其他负债债权人", width = 15)
    @ApiModelProperty(value = "其他负债债权人")
	private String qtfzzqr;
	/**其他负债数量*/
	@Excel(name = "其他负债数量", width = 15)
    @ApiModelProperty(value = "其他负债数量")
	private String qtfzsl;
	/**其他负债详情说明*/
	@Excel(name = "其他负债详情说明", width = 15)
    @ApiModelProperty(value = "其他负债详情说明")
	private String qtfzxqsm;
	/**家庭年开支债权人*/
	@Excel(name = "家庭年开支债权人", width = 15)
    @ApiModelProperty(value = "家庭年开支债权人")
	private String jtnkzzqr;
	/**家庭年开支数量*/
	@Excel(name = "家庭年开支数量", width = 15)
    @ApiModelProperty(value = "家庭年开支数量")
	private String jtnkzsl;
	/**家庭年开支详情说明*/
	@Excel(name = "家庭年开支详情说明", width = 15)
    @ApiModelProperty(value = "家庭年开支详情说明")
	private String jtnkzxqsm;
	/**所有负债合计*/
	@Excel(name = "所有负债合计", width = 15)
    @ApiModelProperty(value = "所有负债合计")
	private String ssfzhj;
	/**thzhsz*/
	@Excel(name = "thzhsz", width = 15)
    @ApiModelProperty(value = "thzhsz")
	private String thzhsz;
	/**dqjyxmcysj*/
	@Excel(name = "dqjyxmcysj", width = 15)
    @ApiModelProperty(value = "dqjyxmcysj")
	private String dqjyxmcysj;
	/**jyzjqqhfx*/
	@Excel(name = "jyzjqqhfx", width = 15)
    @ApiModelProperty(value = "jyzjqqhfx")
	private String jyzjqqhfx;
	/**yhdkqk*/
	@Excel(name = "yhdkqk", width = 15)
    @ApiModelProperty(value = "yhdkqk")
	private String yhdkqk;
	/**yhlxchjl*/
	@Excel(name = "yhlxchjl", width = 15)
    @ApiModelProperty(value = "yhlxchjl")
	private String yhlxchjl;
	/**jycsnl*/
	@Excel(name = "jycsnl", width = 15)
    @ApiModelProperty(value = "jycsnl")
	private String jycsnl;
	/**zcfzl*/
	@Excel(name = "zcfzl", width = 15)
    @ApiModelProperty(value = "zcfzl")
	private String zcfzl;
	/**zyzjzb*/
	@Excel(name = "zyzjzb", width = 15)
    @ApiModelProperty(value = "zyzjzb")
	private String zyzjzb;
	/**nsrzzl*/
	@Excel(name = "nsrzzl", width = 15)
    @ApiModelProperty(value = "nsrzzl")
	private String nsrzzl;
	/**gjcyzc*/
	@Excel(name = "gjcyzc", width = 15)
    @ApiModelProperty(value = "gjcyzc")
	private String gjcyzc;
	/**dkhyld*/
	@Excel(name = "dkhyld", width = 15)
    @ApiModelProperty(value = "dkhyld")
	private String dkhyld;
	/**dgysyld*/
	@Excel(name = "dgysyld", width = 15)
    @ApiModelProperty(value = "dgysyld")
	private String dgysyld;
	/**zhjzsl*/
	@Excel(name = "zhjzsl", width = 15)
    @ApiModelProperty(value = "zhjzsl")
	private String zhjzsl;
	/**yxyswlsj*/
	@Excel(name = "yxyswlsj", width = 15)
    @ApiModelProperty(value = "yxyswlsj")
	private String yxyswlsj;
	/**rsbx*/
	@Excel(name = "rsbx", width = 15)
    @ApiModelProperty(value = "rsbx")
	private String rsbx;
	/**ccbx*/
	@Excel(name = "ccbx", width = 15)
    @ApiModelProperty(value = "ccbx")
	private String ccbx;
	/**总评得分小计*/
	@Excel(name = "总评得分小计", width = 15)
    @ApiModelProperty(value = "总评得分小计")
	private String zpdfxj;
	/**净资产*/
	@Excel(name = "净资产", width = 15)
    @ApiModelProperty(value = "净资产")
	private String shjzc;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
    @ApiModelProperty(value = "系统测算评定等级")
	private String xtcspddj;
	/**系统测算授信额度*/
	@Excel(name = "系统测算授信额度", width = 15)
    @ApiModelProperty(value = "系统测算授信额度")
	private java.math.BigDecimal xtcssxed;
	/**调整后等级*/
	@Excel(name = "调整后等级", width = 15)
    @ApiModelProperty(value = "调整后等级")
	private String tzhdj;
	/**等级调整事项*/
	@Excel(name = "等级调整事项", width = 15)
    @ApiModelProperty(value = "等级调整事项")
	private String djtzsx;
	/**客户经理评定等级*/
	@Excel(name = "客户经理评定等级", width = 15)
    @ApiModelProperty(value = "客户经理评定等级")
	private String khjlpddj;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
    @ApiModelProperty(value = "客户经理授信额度")
	private java.math.BigDecimal khjlsxed;
	/**内部测试额度*/
	@Excel(name = "内部测试额度", width = 15)
    @ApiModelProperty(value = "内部测试额度")
	private String zzpddj;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
    @ApiModelProperty(value = "最终授信额度")
	private String zzsxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String createBy;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**授信对象所属支行*/
	@Excel(name = "授信对象所属支行", width = 15)
    @ApiModelProperty(value = "授信对象所属支行")
	private String sxdxsszh;
}
