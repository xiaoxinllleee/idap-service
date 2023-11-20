package org.cmms.modules.activiti.controller;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.constant.CommonSendStatus;
import org.cmms.common.exception.XbootException;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.common.utils.ResultUtil;
import org.cmms.common.vo.PageVo;
import org.cmms.common.vo.Result;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActProcess;
import org.cmms.modules.activiti.entity.business.Leave;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.activiti.service.business.LeaveService;
import org.cmms.modules.activiti.service.mybatis.IActService;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.system.entity.SysAnnouncement;
import org.cmms.modules.system.entity.SysAnnouncementSend;
import org.cmms.modules.system.service.ISysAnnouncementSendService;
import org.cmms.modules.system.service.ISysAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "业务申请管理接口")
@RequestMapping("/actBusiness")
@Transactional
public class ActBusinessController {

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private IActService iActService;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private LeaveService leaveService;
    @Autowired
    ISysAnnouncementService sysAnnouncementService;
    @Autowired
    ISysAnnouncementSendService sysAnnouncementSendService;

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取申请列表")
    public Result<Page<ActBusiness>> getByCondition(ActBusiness actBusiness,
                                                    SearchVo searchVo,
                                                    PageVo pageVo){

        Page<ActBusiness> page = actBusinessService.findByCondition(actBusiness, searchVo, PageUtil.initPage(pageVo));
         List<ActBusiness>  alist=page.getContent();
         page.getContent().forEach(e -> {
            if(StrUtil.isNotBlank(e.getProcDefId())){
                ActProcess actProcess = actProcessService.get(e.getProcDefId());
                e.setRouteName(actProcess.getRouteName()).setProcessName(actProcess.getName()).setVersion(actProcess.getVersion());
            }
            if(ActivitiConstant.STATUS_DEALING.equals(e.getStatus())){
                // 关联当前任务
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(e.getProcInstId()).list();
                if(taskList!=null&&taskList.size()==1){
                    e.setCurrTaskName(taskList.get(0).getName());
                }else if(taskList!=null&&taskList.size()>1){
                    StringBuilder sb = new StringBuilder();
                    for(int i=0;i<taskList.size()-1;i++){
                        sb.append(taskList.get(i).getName()+"、");
                    }
                    sb.append(taskList.get(taskList.size()-1).getName());
                    e.setCurrTaskName(sb.toString());
                }
            }
        });
        return new ResultUtil<Page<ActBusiness>>().setData(page);
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ApiOperation(value = "提交申请 启动流程")
    public Result<Object> apply(ActBusiness act){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ActBusiness actBusiness = actBusinessService.get(act.getId());
        if(actBusiness==null){
            return ResultUtil.error("actBusiness表中该id不存在");
        }
        act.setTableId(actBusiness.getTableId());
        // 根据你的业务需求放入相应流程所需变量
//        act = putParams(act);
        Map map=new HashMap();
        map.put("type",2);
        act.setParams(map);
        String processInstanceId = actProcessService.startProcess(act);
        actBusiness.setProcInstId(processInstanceId);
        actBusiness.setStatus(ActivitiConstant.STATUS_DEALING);
        actBusiness.setResult(ActivitiConstant.RESULT_DEALING);
        actBusiness.setApplyTime(new Date());
        actBusinessService.update(actBusiness);

        //获取业务处理类
        String procDefId = actBusiness.getProcDefId();
        String key = procDefId.split(":")[0];
        ActTaskHandler actTaskHandler = ActTaskUtil.getHandler(key);
        if (actTaskHandler != null) {
            BusinessInfoVo businessInfoVo = new BusinessInfoVo();
            businessInfoVo.setTableId(actBusiness.getTableId());
            actTaskHandler.doSubmit(businessInfoVo);
        }

        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/appApply", method = RequestMethod.POST)
    @ApiOperation(value = "提交申请 启动流程")
    public Result<Object> appApply(@RequestBody ActBusiness act){
        System.out.println(act.toString());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        ActBusiness actBusiness = actBusinessService.get(act.getId());
        if(actBusiness==null){
            return ResultUtil.error("actBusiness表中该id不存在");
        }
        act.setTableId(actBusiness.getTableId());
        // 根据你的业务需求放入相应流程所需变量
//        act = putParams(act);
        Map map=new HashMap();
        map.put("type",2);
        act.setParams(map);
        String processInstanceId = actProcessService.startProcess(act);
        actBusiness.setProcInstId(processInstanceId);
        actBusiness.setStatus(ActivitiConstant.STATUS_DEALING);
        actBusiness.setResult(ActivitiConstant.RESULT_DEALING);
        actBusiness.setApplyTime(new Date());
        actBusinessService.update(actBusiness);

        String id = IdUtil.simpleUUID();
        if (act.getSendMessage()){
            SysAnnouncement sysAnnouncement = new SysAnnouncement();
            sysAnnouncement.setTitile(act.getProcessName());
            sysAnnouncement.setMsgContent(act.getTitle());
            sysAnnouncement.setStartTime(new Date());
            sysAnnouncement.setSender(sysUser.getUsername());
            sysAnnouncement.setPriority("L");
            sysAnnouncement.setMsgCategory(CommonConstant.MSG_CATEGORY_3);
            sysAnnouncement.setMsgType(CommonConstant.MSG_TYPE_UESR);
            sysAnnouncement.setSendStatus(CommonSendStatus.PUBLISHED_STATUS_1);
            sysAnnouncement.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
            sysAnnouncement.setCreateBy(sysUser.getUsername());
            sysAnnouncement.setCreateTime(new Date());
            sysAnnouncement.setSendTime(new Date());
            sysAnnouncement.setId(id);
            sysAnnouncementService.save(sysAnnouncement);

            String[] assignees = act.getAssignees();
            if (assignees.length>0){
                for (int i = 0; i < assignees.length; i++) {
                    SysAnnouncementSend announcementSend = new SysAnnouncementSend();
                    announcementSend.setAnntId(id);
                    announcementSend.setUserId(assignees[i]);
                    announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
                    announcementSend.setReadTime(new Date());
                    announcementSend.setCreateTime(new Date());
                    announcementSend.setCreateBy(sysUser.getUsername());
                    sysAnnouncementSendService.save(announcementSend);
                }
            }
        }
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ApiOperation(value = "流程选择组件启动流程")
    public Result<Object> start(ActBusiness act){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        ActBusiness actBusiness = actBusinessService.get(act.getId());
        if(actBusiness==null){
            return ResultUtil.error("actBusiness表中该id不存在");
        }
        act.setTableId(actBusiness.getTableId());
        // 根据你的业务需求放入相应流程所需变量
        act = putParams(act);
        String processInstanceId = actProcessService.startProcess(act);
        actBusiness.setProcDefId(act.getProcDefId());
        actBusiness.setTitle(act.getTitle());
        actBusiness.setProcInstId(processInstanceId);
        actBusiness.setStatus(ActivitiConstant.STATUS_DEALING);
        actBusiness.setResult(ActivitiConstant.RESULT_DEALING);
        actBusiness.setCreateBy(sysUser.getRealname());
        actBusiness.setApplyTime(new Date());
        actBusinessService.update(actBusiness);
        return ResultUtil.success("操作成功");
    }

    /**
     * 放入相应流程所需变量 此处仅做演示
     * 【推荐使用绑定监听器设置变量 更加灵活】
     * @param act
     * @return
     */
    public ActBusiness putParams(ActBusiness act){

        if(StrUtil.isBlank(act.getTableId())){
            throw new XbootException("关联业务表TableId不能为空");
        }
        // 如果属于请假流程
        Leave leave = leaveService.get(act.getTableId());
        if(leave!=null){
            // 放入变量
            act.getParams().put("duration", leave.getDuration());
        }
        return act;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ApiOperation(value = "撤回申请")
    public Result<Object> cancel(@RequestParam String id,
                                 @RequestParam String procInstId,
                                 @RequestParam(required = false) String reason){

        if(StrUtil.isBlank(reason)){
            reason = "";
        }
        runtimeService.deleteProcessInstance(procInstId, "canceled-"+reason);
        ActBusiness actBusiness = actBusinessService.get(id);
        actBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
        actBusiness.setResult(ActivitiConstant.RESULT_TO_SUBMIT);
        actBusinessService.update(actBusiness);
        return ResultUtil.success("操作成功");
    }


    @RequestMapping("/appCancel")
    public Result<Object> appCancel( String id,
                                    String procInstId,
                                    String reason){

        if(StrUtil.isBlank(reason)){
            reason = "";
        }
        runtimeService.deleteProcessInstance(procInstId, "canceled-"+reason);
        ActBusiness actBusiness = actBusinessService.get(id);
        actBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
        actBusiness.setResult(ActivitiConstant.RESULT_TO_SUBMIT);
        actBusinessService.update(actBusiness);
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "通过id删除草稿状态申请")
    public Result<Object> delByIds(@RequestParam String[] ids){

        for(String id : ids){
            ActBusiness actBusiness = actBusinessService.get(id);
            if(!ActivitiConstant.STATUS_TO_APPLY.equals(actBusiness.getStatus())){
                return ResultUtil.error("删除失败, 仅能删除草稿状态的申请");
            }
            // 删除关联业务表
            ActProcess actProcess = actProcessService.get(actBusiness.getProcDefId());
            PageUtil.SQLInject(actProcess.getBusinessTable());
            try {
                iActService.deleteBusiness(actProcess.getBusinessTable(), actBusiness.getTableId());
            }catch (Exception e){
            }
            actBusinessService.delete(id);
        }
        return ResultUtil.success("删除成功");
    }


    @PostMapping(value = "/appDelByIds")
    @ApiOperation(value = "通过id删除草稿状态申请")
    public Result<Object> appDelByIds(@RequestBody ActBusiness id){
        System.out.println(id.getId());
        if (StringUtils.isNotBlank(id.getId())) {
            ActBusiness actBusiness = actBusinessService.get(id.getId());
            System.out.println(actBusiness.toString());
            if (!ActivitiConstant.STATUS_TO_APPLY.equals(actBusiness.getStatus())) {
                return ResultUtil.error("删除失败, 仅能删除草稿状态的申请");
            }
            // 删除关联业务表
            ActProcess actProcess = actProcessService.get(actBusiness.getProcDefId());
            System.out.println(actProcess.toString());
            PageUtil.SQLInject(actProcess.getBusinessTable());
            try {
                iActService.deleteBusiness(actProcess.getBusinessTable(), actBusiness.getTableId());
            } catch (Exception e) {
            }
            actBusinessService.delete(id);
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.success("删除成功");
    }
}
