package org.cmms.modules.activiti.vo;

import org.cmms.common.constant.ActivitiConstant;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.history.HistoricTaskInstance;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
@Accessors(chain = true)
public class HistoricTaskVo {

    private String id;

    @Excel(name = "标题", width = 50)
    private String title;

    @Excel(name = "任务名称", width = 15)
    private String name;

    private String key;

    private String description;

    private String executionId;

    private String assignee;

    private String owner;
    private String ownerUsername;

    private String procDefId;

    private String procInstId;
    @Excel(name = "流程发起人", width = 15)
    private String applyer;

    private String applyerUsername;

    private String category;

    private Integer priority;
    @Excel(name = "审批操作", width = 15)
    private String deleteReason;
    @Excel(name = "审批意见", width = 15)
    private String comment;

    private java.math.BigDecimal jysxed;

    private Long duration;

    private Long workTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueTime;
    @Excel(name = "所属流程", width = 15)
    private String processName;

    private String routeName;

    private String businessKey;

    private String tableId;

    private List<Assignee> assignees;


    private Integer version;

    public HistoricTaskVo(HistoricTaskInstance task){
        this.id = task.getId();
        this.name = task.getName();
        this.key = task.getTaskDefinitionKey();
        this.description = task.getDescription();
        this.executionId = task.getExecutionId();
        this.assignee = task.getAssignee();
        this.owner = task.getOwner();
        this.procDefId = task.getProcessDefinitionId();
        this.procInstId = task.getProcessInstanceId();
        this.priority = task.getPriority();
        this.category = task.getCategory();
        this.deleteReason = getMyDeleteReason(task.getDeleteReason());
        this.duration = task.getDurationInMillis();
        this.workTime = task.getWorkTimeInMillis();
        this.createTime = task.getCreateTime();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.dueTime = task.getDueDate();
    }

    public String getMyDeleteReason(String deleteReason){

        if(StrUtil.isBlank(deleteReason)){
            return "";
        }
        if(ActivitiConstant.PASSED_FLAG.equals(deleteReason)){
            deleteReason = "审批通过";
        }else if(ActivitiConstant.BACKED_FLAG.equals(deleteReason)){
            deleteReason = "审批驳回";
        }else if(deleteReason.contains(ActivitiConstant.DELETE_PRE)){
            String reason = "";
            if(ActivitiConstant.DELETE_PRE.equals(deleteReason)){
                reason = "未填写";
            }else if(deleteReason.length()>8){
                reason = deleteReason.substring(8);
            }
            deleteReason = "删除撤回-原因"+reason;
        }else if(deleteReason.contains(ActivitiConstant.CANCEL_PRE)){
            String reason = "";
            if(ActivitiConstant.CANCEL_PRE.equals(deleteReason)){
                reason = "未填写";
            }else if(deleteReason.length()>9){
                reason = deleteReason.substring(9);
            }
            deleteReason = "发起人撤回-原因"+reason;
        }else{
            deleteReason="已删除-原因"+deleteReason;
        }
        return deleteReason;
    }
}
