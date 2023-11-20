package org.cmms.modules.sjxf.xdxt.lcrwlbxxb.entity;

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
 * @Description: 流程任务列表信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_sm_task_rec")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_sm_task_rec对象", description="流程任务列表信息表")
public class Lcrwlbxxb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**备注4*/
	@Excel(name = "备注4", width = 15)
    @ApiModelProperty(value = "备注4")
	private String temp4;
	/**同意期限*/
	@Excel(name = "同意期限", width = 15)
    @ApiModelProperty(value = "同意期限")
	private String temp3;
	/**同意利率*/
	@Excel(name = "同意利率", width = 15)
    @ApiModelProperty(value = "同意利率")
	private String temp2;
	/**同意金额*/
	@Excel(name = "同意金额", width = 15)
    @ApiModelProperty(value = "同意金额")
	private String temp1;
	/**动作编号*/
	@Excel(name = "动作编号", width = 15)
    @ApiModelProperty(value = "动作编号")
	private String actionId;
	/**完成任务时间*/
	@Excel(name = "完成任务时间", width = 15)
    @ApiModelProperty(value = "完成任务时间")
	private String completeDate;
	/**接收任务时间*/
	@Excel(name = "接收任务时间", width = 15)
    @ApiModelProperty(value = "接收任务时间")
	private String creatDate;
	/**当前任务号*/
	@Excel(name = "当前任务号", width = 15)
    @ApiModelProperty(value = "当前任务号")
	private String currTaskId;
	/**强制执行时间*/
	@Excel(name = "强制执行时间", width = 15)
    @ApiModelProperty(value = "强制执行时间")
	private String forceTime;
	/**催办时间*/
	@Excel(name = "催办时间", width = 15)
    @ApiModelProperty(value = "催办时间")
	private String hastenTime;
	/**无*/
	@Excel(name = "无", width = 15)
    @ApiModelProperty(value = "无")
	private String isShow;
	/**下一个处理流水编号*/
	@Excel(name = "下一个处理流水编号", width = 15)
    @ApiModelProperty(value = "下一个处理流水编号")
	private String nextStep;
	/**下一个任务号*/
	@Excel(name = "下一个任务号", width = 15)
    @ApiModelProperty(value = "下一个任务号")
	private String nextTaskId;
	/**前一个处理流水编号*/
	@Excel(name = "前一个处理流水编号", width = 15)
    @ApiModelProperty(value = "前一个处理流水编号")
	private String previousStep;
	/**remark*/
	@Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
	private String remark;
	/**处理人员角色编号*/
	@Excel(name = "处理人员角色编号", width = 15)
    @ApiModelProperty(value = "处理人员角色编号")
	private String roleId;
	/**步骤号*/
	@Excel(name = "步骤号", width = 15)
    @ApiModelProperty(value = "步骤号")
	private String stepOn;
	/**处理人员编号*/
	@Excel(name = "处理人员编号", width = 15)
    @ApiModelProperty(value = "处理人员编号")
	private String userId;
	/**工作流编号*/
	@Excel(name = "工作流编号", width = 15)
    @ApiModelProperty(value = "工作流编号")
	private String wfFlowId;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String wfFlowOn;
	/**业务工作流编号*/
	@Excel(name = "业务工作流编号", width = 15)
    @ApiModelProperty(value = "业务工作流编号")
	private String wfIsFlowOn;
	/**部门编号*/
	@Excel(name = "部门编号", width = 15)
    @ApiModelProperty(value = "部门编号")
	private String deptId;
	/**代理操作员号*/
	@Excel(name = "代理操作员号", width = 15)
    @ApiModelProperty(value = "代理操作员号")
	private String trueUserId;
	/**是否结束标志*/
	@Excel(name = "是否结束标志", width = 15)
    @ApiModelProperty(value = "是否结束标志")
	private String isEnd;
	/**启动人员编号*/
	@Excel(name = "启动人员编号", width = 15)
    @ApiModelProperty(value = "启动人员编号")
	private String startUserId;
	/**启动部门编号*/
	@Excel(name = "启动部门编号", width = 15)
    @ApiModelProperty(value = "启动部门编号")
	private String startDeptId;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}
