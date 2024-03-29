package org.cmms.modules.activiti.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.constant.CommonSendStatus;
import org.cmms.common.exception.XbootException;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.utils.ResultUtil;
import org.cmms.common.utils.SnowFlakeUtil;
import org.cmms.common.vo.PageVo;
import org.cmms.common.vo.Result;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActProcess;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.activiti.service.mybatis.IHistoryIdentityService;
import org.cmms.modules.activiti.service.mybatis.IRunIdentityService;
//import org.cmms.modules.activiti.utils.MessageUtil;
import org.cmms.modules.activiti.vo.*;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.cmms.modules.system.entity.SysAnnouncement;
import org.cmms.modules.system.entity.SysAnnouncementSend;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysAnnouncementSendService;
import org.cmms.modules.system.service.ISysAnnouncementService;
import org.cmms.modules.system.service.ISysUserService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "任务管理接口")
@RequestMapping("/actTask")
@Transactional
public class ActTaskController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IHistoryIdentityService iHistoryIdentityService;

    @Autowired
    private IRunIdentityService iRunIdentityService;

    @Autowired
    private IActXendSplsService iActXendSplsService;
    @Autowired
    ISysAnnouncementService sysAnnouncementService;
    @Autowired
    ISysAnnouncementSendService sysAnnouncementSendService;
    /*@Autowired
    private MessageUtil messageUtil;*/

    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    @ApiOperation(value = "代办列表")
    public Result<Object> todoList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String title,
                                   @RequestParam(required = false) String categoryId,
                                   @RequestParam(required = false) Integer priority,
                                   SearchVo searchVo,
                                   PageVo pageVo){
        System.out.println("1111111111111111111111111111111111");
        ActPage<TaskVo> page = new ActPage<TaskVo>();
        List<TaskVo> list = new ArrayList<>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        TaskQuery query = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        // 多条件搜索
        if("createTime".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByTaskCreateTime().asc();
        }else if("priority".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().asc();
        }else if("priority".equals(pageVo.getSort())&&"desc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().desc();
        }else{
            query.orderByTaskCreateTime().desc();
        }
        if(StrUtil.isNotBlank(name)){
            query.taskNameLike("%"+name+"%");
        }

        if(StrUtil.isNotBlank(categoryId)){
            query.taskCategory(categoryId);
        }
        if(priority!=null){
            query.taskPriority(priority);
        }
        if (StrUtil.isNotBlank(title)) {
            List<ActBusiness> actBusinessList = actBusinessService.findByTitleLike("%" + title + "%");
            List<String> procInstIdList = actBusinessList.stream().filter(item -> StringUtils.isNotEmpty(item.getProcInstId())).map(ActBusiness::getProcInstId).collect(Collectors.toList());
            if (procInstIdList.isEmpty()) {
                procInstIdList.add("Empty");
            }
            query.processInstanceIdIn(procInstIdList);
        }
        if(StrUtil.isNotBlank(searchVo.getStartDate())&& StrUtil.isNotBlank(searchVo.getEndDate())){
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            query.taskCreatedAfter(start);
            query.taskCreatedBefore(DateUtil.endOfDay(end));
        }

        page.setTotalElements(query.count());
        int first =  (pageVo.getPageNumber()-1) * pageVo.getPageSize();
        List<Task> taskList = query.listPage(first, pageVo.getPageSize());

        // 转换vo
        taskList.forEach(e -> {
            TaskVo tv = new TaskVo(e);

            // 关联委托人
            if(StrUtil.isNotBlank(tv.getOwner())){
                SysUser  o = userService.getById(tv.getOwner());
                tv.setOwner(o.getRealname()).setOwnerUsername(o.getUsername());
            }
            List<IdentityLink> identityLinks = runtimeService.getIdentityLinksForProcessInstance(tv.getProcInstId());
            for(IdentityLink ik : identityLinks){
                // 关联发起人
                if("starter".equals(ik.getType())&& StrUtil.isNotBlank(ik.getUserId())){
                    SysUser s = userService.getById(ik.getUserId());
                    tv.setApplyer(s.getRealname()).setApplyerUsername(s.getUsername());
                }
            }
            // 关联流程信息
            ActProcess actProcess = actProcessService.get(tv.getProcDefId());
            if(actProcess!=null){
                tv.setProcessName(actProcess.getName());
                tv.setRouteName(actProcess.getRouteName());
                tv.setVersion(actProcess.getVersion());
            }
            // 关联业务key
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(tv.getProcInstId()).singleResult();
            tv.setBusinessKey(pi.getBusinessKey());
            ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
            if(actBusiness!=null){
                tv.setTableId(actBusiness.getTableId());
                tv.setTitle(actBusiness.getTitle());
            }

            list.add(tv);
        });
        page.setContent(list);
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/doneList", method = RequestMethod.GET)
    @ApiOperation(value = "已办列表")
    public Result<Object> doneList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String title,
                                   @RequestParam(required = false) String categoryId,
                                   @RequestParam(required = false) Integer priority,
                                   SearchVo searchVo,
                                   PageVo pageVo){

        ActPage<HistoricTaskVo> page = new ActPage<HistoricTaskVo>();
        List<HistoricTaskVo> list = new ArrayList<>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().or().taskCandidateUser(userId).
                taskAssignee(userId).endOr().finished();

        // 多条件搜索
        if("createTime".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceEndTime().asc();
        }else if("priority".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().asc();
        }else if("priority".equals(pageVo.getSort())&&"desc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().desc();
        }else if("duration".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceDuration().asc();
        }else if("duration".equals(pageVo.getSort())&&"desc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceDuration().desc();
        }else{
            query.orderByHistoricTaskInstanceEndTime().desc();
        }
        if(StrUtil.isNotBlank(name)){
            query.taskNameLike("%"+name+"%");
        }
        if(StrUtil.isNotBlank(categoryId)){
            query.taskCategory(categoryId);
        }

        if(priority!=null){
            query.taskPriority(priority);
        }
        if (StrUtil.isNotBlank(title)) {
            List<ActBusiness> actBusinessList = actBusinessService.findByTitleLike("%" + title + "%");
            List<String> procInstIdList = actBusinessList.stream().filter(item -> StringUtils.isNotEmpty(item.getProcInstId())).map(ActBusiness::getProcInstId).collect(Collectors.toList());
            if (procInstIdList.isEmpty()) {
                procInstIdList.add("Empty");
            }
            query.processInstanceIdIn(procInstIdList);
        }
        if(StrUtil.isNotBlank(searchVo.getStartDate())&& StrUtil.isNotBlank(searchVo.getEndDate())){
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            query.taskCompletedAfter(start);
            query.taskCompletedBefore(DateUtil.endOfDay(end));
        }

        page.setTotalElements((long) query.list().size());
        int first =  (pageVo.getPageNumber()-1) * pageVo.getPageSize();
        List<HistoricTaskInstance> taskList = query.listPage(first, pageVo.getPageSize());
        // 转换vo
        taskList.forEach(e -> {
            HistoricTaskVo htv = new HistoricTaskVo(e);
            // 关联委托人
            if(StrUtil.isNotBlank(htv.getOwner())){
                SysUser o = userService.getById(htv.getOwner());
                htv.setOwner(o.getRealname()).setOwnerUsername(o.getUsername());
            }
            List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForProcessInstance(htv.getProcInstId());
            for(HistoricIdentityLink hik : identityLinks){
                // 关联发起人
                if("starter".equals(hik.getType())&& StrUtil.isNotBlank(hik.getUserId())){
                    SysUser s = userService.getById(hik.getUserId());
                    htv.setApplyer(s.getRealname()).setApplyerUsername(s.getUsername());
                }
            }
            // 关联审批意见
            List<Comment> comments = taskService.getTaskComments(htv.getId(), "comment");
            if(comments!=null&&comments.size()>0){
                htv.setComment(comments.get(0).getFullMessage());
            }
            // 关联流程信息
            ActProcess actProcess = actProcessService.get(htv.getProcDefId());
            if(actProcess!=null){
                htv.setProcessName(actProcess.getName());
                htv.setRouteName(actProcess.getRouteName());
                htv.setVersion(actProcess.getVersion());
            }
            // 关联业务key
            HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(htv.getProcInstId()).singleResult();
            htv.setBusinessKey(hpi.getBusinessKey());
            ActBusiness actBusiness = actBusinessService.get(hpi.getBusinessKey());
            if(actBusiness!=null){
                htv.setTableId(actBusiness.getTableId());
                htv.setTitle(actBusiness.getTitle());
            }

            list.add(htv);
        });
        page.setContent(list);
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/exportDoneList", method = RequestMethod.GET)
    @ApiOperation(value = "已办列表")
    public ModelAndView exportDoneList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String categoryId,
                                   @RequestParam(required = false) Integer priority,
                                   SearchVo searchVo,
                                   PageVo pageVo){

        List<HistoricTaskVo> list = new ArrayList<>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().or().taskCandidateUser(userId).
                taskAssignee(userId).endOr().finished();

        // 多条件搜索
        if("createTime".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceEndTime().asc();
        }else if("priority".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().asc();
        }else if("priority".equals(pageVo.getSort())&&"desc".equals(pageVo.getOrder())){
            query.orderByTaskPriority().desc();
        }else if("duration".equals(pageVo.getSort())&&"asc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceDuration().asc();
        }else if("duration".equals(pageVo.getSort())&&"desc".equals(pageVo.getOrder())){
            query.orderByHistoricTaskInstanceDuration().desc();
        }else{
            query.orderByHistoricTaskInstanceEndTime().desc();
        }
        if(StrUtil.isNotBlank(name)){
            query.taskNameLike("%"+name+"%");
        }
        if(StrUtil.isNotBlank(categoryId)){
            query.taskCategory(categoryId);
        }
        if(priority!=null){
            query.taskPriority(priority);
        }
        if(StrUtil.isNotBlank(searchVo.getStartDate())&& StrUtil.isNotBlank(searchVo.getEndDate())){
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            query.taskCompletedAfter(start);
            query.taskCompletedBefore(DateUtil.endOfDay(end));
        }

        List<HistoricTaskInstance> taskList = query.list();
        // 转换vo
        taskList.forEach(e -> {
            HistoricTaskVo htv = new HistoricTaskVo(e);
            // 关联委托人
            if(StrUtil.isNotBlank(htv.getOwner())){
                SysUser o = userService.getById(htv.getOwner());
                htv.setOwner(o.getRealname()).setOwnerUsername(o.getUsername());
            }
            List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForProcessInstance(htv.getProcInstId());
            for(HistoricIdentityLink hik : identityLinks){
                // 关联发起人
                if("starter".equals(hik.getType())&& StrUtil.isNotBlank(hik.getUserId())){
                    SysUser s = userService.getById(hik.getUserId());
                    htv.setApplyer(s.getRealname()).setApplyerUsername(s.getUsername());
                }
            }
            // 关联审批意见
            List<Comment> comments = taskService.getTaskComments(htv.getId(), "comment");
            if(comments!=null&&comments.size()>0){
                htv.setComment(comments.get(0).getFullMessage());
            }
            // 关联流程信息
            ActProcess actProcess = actProcessService.get(htv.getProcDefId());
            if(actProcess!=null){
                htv.setProcessName(actProcess.getName());
                htv.setRouteName(actProcess.getRouteName());
                htv.setVersion(actProcess.getVersion());
            }
            // 关联业务key
            HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(htv.getProcInstId()).singleResult();
            htv.setBusinessKey(hpi.getBusinessKey());
            ActBusiness actBusiness = actBusinessService.get(hpi.getBusinessKey());
            if(actBusiness!=null){
                htv.setTableId(actBusiness.getTableId());
                htv.setTitle(actBusiness.getTitle());
            }

            list.add(htv);
        });
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "我的已办"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, HistoricTaskVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("我的已办报表", "导出人:" + sysUser.getRealname(), "我的已办"));
        mv.addObject(NormalExcelConstants.DATA_LIST, list);
        return mv;
    }

    @RequestMapping(value = "/historicFlow/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "流程流转历史")
    public Result<Object> historicFlow(@ApiParam("流程实例id") @PathVariable String id){

        List<HistoricTaskVo> list = new ArrayList<>();

        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(id).orderByHistoricTaskInstanceEndTime().asc().list();

        // 转换vo
        taskList.forEach(e -> {
            HistoricTaskVo htv = new HistoricTaskVo(e);
            List<Assignee> assignees = new ArrayList<>();
            // 关联分配人（委托用户时显示该人）
            if(StrUtil.isNotBlank(htv.getAssignee())){
                SysUser assignee = userService.getById(htv.getAssignee());
                SysUser owner = userService.getById(htv.getOwner());
                assignees.add(new Assignee(assignee.getRealname(),
                        assignee.getUsername()+"【受 "+owner.getRealname()+"("+owner.getUsername()+") 委托】", true));
            }
            List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForTask(e.getId());
            // 获取实际审批用户id
            String userId = iHistoryIdentityService.findUserIdByTypeAndTaskId(ActivitiConstant.EXECUTOR_TYPE, e.getId());

            for(HistoricIdentityLink hik : identityLinks){
                // 关联候选用户（分配的候选用户审批人）
                if("candidate".equals(hik.getType())&& StrUtil.isNotBlank(hik.getUserId())){
                    try {
                        SysUser  u = userService.getById(hik.getUserId());
                        if(u!=null){
                            Assignee assignee = new Assignee(u.getRealname(), u.getUsername(), false);
                            if(StrUtil.isNotBlank(userId)&&userId.equals(hik.getUserId())){
                                assignee.setIsExecutor(true);
                            }
                            assignees.add(assignee);
                        }
                    }catch (Exception e1){
                    }

                }
            }
            htv.setAssignees(assignees);
            // 关联审批意见
            List<Comment> comments = taskService.getTaskComments(htv.getId(), "comment");
            if(comments!=null&&comments.size()>0){
                htv.setComment(comments.get(0).getFullMessage());
            }
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("taskid",htv.getId());
            long count = iActXendSplsService.count(queryWrapper);
            if(count > 0) {
                List<ActXendSpls> spls = iActXendSplsService.list(queryWrapper);
                if(spls!=null&&spls.size()>0){
                    htv.setJysxed(spls.get(0).getJyed());
                }
            }
            list.add(htv);
        });
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    @ApiOperation(value = "任务节点审批通过")
    public Result<Object> pass(@ApiParam("任务id") @RequestParam String id,
                               @ApiParam("流程实例id") @RequestParam String procInstId,
                               @ApiParam("下个节点审批人") @RequestParam(required = false) String[] assignees,
                               @ApiParam("优先级") @RequestParam(required = false) Integer priority,
                               @ApiParam("意见评论") @RequestParam(required = false) String comment,
                               @ApiParam("建议额度") @RequestParam(required = false) Double jysxed,
                               @ApiParam("评定等级") @RequestParam(required = false) String pddj,
                               @ApiParam("关联表id") @RequestParam(required = false) String tableId,
                               @RequestParam(required = false) String hhbm, @RequestParam(required = false) String zjhm,
                               @RequestParam(required = false) String key,@RequestParam(required = false) String spid,
                               @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                               @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                               @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){

        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        if(jysxed==null){
            jysxed = 0d;
        }

        taskService.addComment(id, procInstId, comment);
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if(StrUtil.isNotBlank(task.getOwner())&&!("RESOLVED").equals(task.getDelegationState().toString())){
            // 未解决的委托任务 先resolve
            String oldAssignee = task.getAssignee();
            taskService.resolveTask(id);
            taskService.setAssignee(id, oldAssignee);
        }
        taskService.complete(id);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
        ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
        // 记录实际审批人员
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = userService.getUserByName(loginUser.getUsername());
        BusinessInfoVo businessInfoVo = new BusinessInfoVo();
        businessInfoVo.setSpId(spid);
        businessInfoVo.setProcInstId(procInstId);
        businessInfoVo.setTaskId(id);
        businessInfoVo.setHhbm(hhbm);
        businessInfoVo.setZjhm(zjhm);
        businessInfoVo.setJysxed(new BigDecimal(jysxed));
        businessInfoVo.setPddj(pddj);
        businessInfoVo.setSpyj(comment);
        businessInfoVo.setUserId(sysUser.getId());
        businessInfoVo.setUserName(sysUser.getUsername());
        businessInfoVo.setYggh(sysUser.getWorkNo());
        businessInfoVo.setTableId(tableId);
        //获取业务处理类
        ActTaskHandler actTaskHandler = ActTaskUtil.getHandler(key);
        // 判断下一个节点
        if(tasks!=null&&tasks.size()>0){
            for(Task t : tasks){
                if(assignees==null||assignees.length<1){
                    // 如果下个节点未分配审批人为空 取消结束流程
                    List<SysUser> users = actProcessService.getNode(t.getTaskDefinitionKey()).getUsers();
                    if(users==null||users.size()==0){
                        runtimeService.deleteProcessInstance(procInstId, "canceled-审批节点未分配审批人，流程自动中断取消");
                        actBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
                        actBusiness.setResult(ActivitiConstant.RESULT_TO_SUBMIT);
                        actBusinessService.update(actBusiness);
                        break;
                    }else{
                        // 避免重复添加
                        List<String> list = iRunIdentityService.selectByConditions(t.getId(), "candidate");
                        if(list==null||list.size()==0) {
                            // 分配了节点负责人分发给全部
                            for (SysUser user : users) {
                                taskService.addCandidateUser(t.getId(), user.getId());
                                // 异步发消息
                                //messageUtil.sendActMessage(user.getId(), ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                            }
                            taskService.setPriority(t.getId(), task.getPriority());
                        }
                    }
                }else{
                    // 避免重复添加
                    List<String> list = iRunIdentityService.selectByConditions(t.getId(), "candidate");
                    if(list==null||list.size()==0) {
                        for(String assignee : assignees){
                            taskService.addCandidateUser(t.getId(), assignee);
                            // 异步发消息
                            //messageUtil.sendActMessage(assignee, ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                            taskService.setPriority(t.getId(), priority);
                        }
                    }
                }
            }
        } else {
            //流程完成
            actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
            actBusiness.setResult(ActivitiConstant.RESULT_PASS);
            actBusinessService.update(actBusiness);
            //调用最终通过方法
            if (actTaskHandler != null) {
                actTaskHandler.doFinalPass(businessInfoVo);
            }
            // 异步发消息
            //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_PASS_CONTENT, sendMessage, sendSms, sendEmail);
        }


        //调用每步通过方法
        if (actTaskHandler != null) {
            actTaskHandler.doPass(businessInfoVo);
        }
        iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                ActivitiConstant.EXECUTOR_TYPE, sysUser.getId(), id, procInstId);
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/passAll", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过")
    public Result<Object> passAll(@ApiParam("任务ids") @RequestParam String[] ids,
                                  @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                                  @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                                  @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){

        int count = 0;
        for(String id : ids){
            Task task = taskService.createTaskQuery().taskId(id).singleResult();
            ProcessNodeVo next = actProcessService.getNextNode(task.getProcessDefinitionId(), task.getTaskDefinitionKey(), null);
            if(ActivitiConstant.NODE_TYPE_CUSTOM.equals(next.getType())){
                count++;
                continue;
            }
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            if(StrUtil.isNotBlank(task.getOwner())&&!("RESOLVED").equals(task.getDelegationState().toString())){
                String oldAssignee = task.getAssignee();
                taskService.resolveTask(id);
                taskService.setAssignee(id, oldAssignee);
            }
            taskService.complete(id);
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
            // 判断下一个节点
            if(tasks!=null&&tasks.size()>0){
                for(Task t : tasks){
                    List<SysUser> users = actProcessService.getNode(t.getTaskDefinitionKey()).getUsers();
                    // 如果下个节点未分配审批人为空 取消结束流程
                    if(users==null||users.size()==0){
                        runtimeService.deleteProcessInstance(pi.getId(), "canceled-审批节点未分配审批人，流程自动中断取消");
                        ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
                        actBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
                        actBusiness.setResult(ActivitiConstant.RESULT_TO_SUBMIT);
                        actBusinessService.update(actBusiness);
                        break;
                    }else{
                        // 避免重复添加
                        List<String> list = iRunIdentityService.selectByConditions(t.getId(), "candidate");
                        if(list==null||list.size()==0) {
                            // 分配了节点负责人分发给全部
                            for (SysUser user : users) {
                                taskService.addCandidateUser(t.getId(), user.getId());
                                // 异步发消息
                               // messageUtil.sendActMessage(user.getId(), ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                                taskService.setPriority(t.getId(), task.getPriority());
                            }
                        }
                    }
                }
            } else {
                ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
                actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
                actBusiness.setResult(ActivitiConstant.RESULT_PASS);
                actBusinessService.update(actBusiness);
                // 异步发消息
                //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_PASS_CONTENT, sendMessage, sendSms, sendEmail);
            }
            // 记录实际审批人员
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user =userService.getUserByName(sysUser.getUsername());
            String userId = user.getId();
            iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                    ActivitiConstant.EXECUTOR_TYPE, userId, id, pi.getId());
        }
        String customCount = "";
        if(count>0){
            customCount = "，跳过了"+count+"个自选审批人节点";
        }
        return ResultUtil.success("成功批量通过了"+(ids.length-count)+"条数据"+customCount);
    }

    @RequestMapping(value = "/getBackList/{procInstId}", method = RequestMethod.GET)
    @ApiOperation(value = "获取可返回的节点")
    public Result<Object> getBackList(@PathVariable String procInstId){

        List<HistoricTaskVo> list = new ArrayList<>();
        List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(procInstId)
                .finished().taskDeleteReason("completed").list();

        taskInstanceList.forEach(e -> {
            HistoricTaskVo htv = new HistoricTaskVo(e);
            list.add(htv);
        });

        // 去重
        LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
        List<HistoricTaskVo> newList = new ArrayList<>();
        list.forEach(e->{
            if(set.add(e.getName())){
                newList.add(e);
            }
        });

        return ResultUtil.data(newList);
    }

    @RequestMapping(value = "/backToTask", method = RequestMethod.POST)
    @ApiOperation(value = "任务节点审批驳回至指定历史节点")
    public Result<Object> backToTask(@ApiParam("任务id") @RequestParam String id,
                                     @ApiParam("驳回指定节点key") @RequestParam String backTaskKey,
                                     @ApiParam("流程实例id") @RequestParam String procInstId,
                                     @ApiParam("流程定义id") @RequestParam String procDefId,
                                     @ApiParam("原节点审批人") @RequestParam(required = false) String[] assignees,
                                     @ApiParam("优先级") @RequestParam(required = false) Integer priority,
                                     @ApiParam("意见评论") @RequestParam(required = false) String comment,
                                     @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                                     @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                                     @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){


        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        taskService.addComment(id, procInstId, comment);
        // 取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(procDefId);
        // 获取历史任务的Activity
        ActivityImpl hisActivity = definition.findActivity(backTaskKey);
        // 实现跳转
        managementService.executeCommand(new JumpTask(procInstId, hisActivity.getId()));
        // 重新分配原节点审批人
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(e->{
                for(String assignee:assignees){
                    taskService.addCandidateUser(e.getId(), assignee);
                    // 异步发消息
                    //messageUtil.sendActMessage(assignee, ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                }
                if(priority!=null){
                    taskService.setPriority(e.getId(), priority);
                }
            });
        }
        // 记录实际审批人员
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                ActivitiConstant.EXECUTOR_TYPE, userId, id, procInstId);
        return ResultUtil.success("操作成功");
    }

    public class JumpTask implements Command<ExecutionEntity> {

        private String procInstId;
        private String activityId;

        public JumpTask(String procInstId, String activityId) {
            this.procInstId = procInstId;
            this.activityId = activityId;
        }

        @Override
        public ExecutionEntity execute(CommandContext commandContext) {

            ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findExecutionById(procInstId);
            executionEntity.destroyScope("backed");
            ProcessDefinitionImpl processDefinition = executionEntity.getProcessDefinition();
            ActivityImpl activity = processDefinition.findActivity(activityId);
            executionEntity.executeActivity(activity);

            return executionEntity;
        }
    }

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ApiOperation(value = "任务节点审批驳回至发起人")
    public Result<Object> back(@ApiParam("任务id") @RequestParam String id,
                               @ApiParam("流程实例id") @RequestParam String procInstId,
                               @ApiParam("意见评论") @RequestParam(required = false) String comment,
                               @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                               @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                               @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){


        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        taskService.addComment(id, procInstId, comment);
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        // 删除流程实例
        runtimeService.deleteProcessInstance(procInstId, "backed");
        ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
        actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
        actBusiness.setResult(ActivitiConstant.RESULT_FAIL);
        actBusinessService.update(actBusiness);
        // 异步发消息
        //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_BACK_CONTENT, sendMessage, sendSms, sendEmail);
        // 记录实际审批人员
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                ActivitiConstant.EXECUTOR_TYPE, userId, id, procInstId);
        return ResultUtil.success("操作成功");
    }



    @RequestMapping(value = "/backAll", method = RequestMethod.POST)
    @ApiOperation(value = "批量驳回至发起人")
    public Result<Object> backAll(@ApiParam("流程实例ids") @RequestParam String[] procInstIds,
                                  @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                                  @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                                  @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        for(String procInstId:procInstIds){
            // 记录实际审批人员
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
            tasks.forEach(t->{
                iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                        ActivitiConstant.EXECUTOR_TYPE, userId, t.getId(), procInstId);
            });
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            // 删除流程实例
            try {
                runtimeService.deleteProcessInstance(procInstId, ActivitiConstant.BACKED_FLAG);
            }catch (Exception e){
                throw new XbootException("请确保无重复所属的流程，或尝试对单条数据进行驳回");
            }
            ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
            actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
            actBusiness.setResult(ActivitiConstant.RESULT_FAIL);
            actBusinessService.update(actBusiness);
            // 异步发消息
            //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_BACK_CONTENT, sendMessage, sendSms, sendEmail);
        }
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/delegate", method = RequestMethod.POST)
    @ApiOperation(value = "委托他人代办")
    public Result<Object> delegate(@ApiParam("任务id") @RequestParam String id,
                                   @ApiParam("委托用户id") @RequestParam String userId,
                                   @ApiParam("流程实例id") @RequestParam String procInstId,
                                   @ApiParam("意见评论") @RequestParam(required = false) String comment,
                                   @ApiParam("是否发送站内消息") @RequestParam(defaultValue = "false") Boolean sendMessage,
                                   @ApiParam("是否发送短信通知") @RequestParam(defaultValue = "false") Boolean sendSms,
                                   @ApiParam("是否发送邮件通知") @RequestParam(defaultValue = "false") Boolean sendEmail){

        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        taskService.addComment(id, procInstId, comment);
        taskService.delegateTask(id, userId);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userIdstr = user.getId();
        taskService.setOwner(id, userIdstr);
        // 异步发消息
        //messageUtil.sendActMessage(userId, ActivitiConstant.MESSAGE_DELEGATE_CONTENT, sendMessage, sendSms, sendEmail);
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/deleteHistoric", method = RequestMethod.POST)
    @ApiOperation(value = "删除任务历史")
    public Result<Object> deleteHistoric(@ApiParam("任务id") @RequestParam String[] ids){

        for(String id : ids){
            historyService.deleteHistoricTaskInstance(id);
        }
        return ResultUtil.success("操作成功");
    }




    @RequestMapping("/appPass")
    public Result<Object> appPass(String id,
                                String procInstId,
                                String[] assignees,
                                Integer priority, String comment,
                               String key, Boolean sendMessage,String title,
                               Boolean sendSms,
                               Boolean sendEmail,String processName,String applyerUsername){

        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        taskService.addComment(id, procInstId, comment);
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if(StrUtil.isNotBlank(task.getOwner())&&!("RESOLVED").equals(task.getDelegationState().toString())){
            // 未解决的委托任务 先resolve
            String oldAssignee = task.getAssignee();
            taskService.resolveTask(id);
            taskService.setAssignee(id, oldAssignee);
        }
        taskService.complete(id);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
        ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
        // 记录实际审批人员
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = userService.getUserByName(loginUser.getUsername());
//        BusinessInfoVo businessInfoVo = new BusinessInfoVo();
//        businessInfoVo.setSpId(spid);
//        businessInfoVo.setProcInstId(procInstId);
//        businessInfoVo.setTaskId(id);
//        businessInfoVo.setHhbm(hhbm);
//        businessInfoVo.setZjhm(zjhm);
//        businessInfoVo.setJysxed(new BigDecimal(jysxed));
//        businessInfoVo.setPddj(pddj);
//        businessInfoVo.setSpyj(comment);
//        businessInfoVo.setUserId(sysUser.getId());
//        businessInfoVo.setUserName(sysUser.getUsername());
//        businessInfoVo.setYggh(sysUser.getWorkNo());
//        businessInfoVo.setTableId(tableId);
        //获取业务处理类
        ActTaskHandler actTaskHandler = ActTaskUtil.getHandler(key);
        List<SysUser> users = null;
        // 判断下一个节点
        if(tasks!=null&&tasks.size()>0){
            for(Task t : tasks){
                if(assignees==null||assignees.length<1){
                    // 如果下个节点未分配审批人为空 取消结束流程
                    users = actProcessService.getNode(t.getTaskDefinitionKey()).getUsers();
                    if(users==null||users.size()==0){
                        runtimeService.deleteProcessInstance(procInstId, "canceled-审批节点未分配审批人，流程自动中断取消");
                        actBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
                        actBusiness.setResult(ActivitiConstant.RESULT_TO_SUBMIT);
                        actBusinessService.update(actBusiness);
                        break;
                    }else{
                        // 避免重复添加
                        List<String> list = iRunIdentityService.selectByConditions(t.getId(), "candidate");
                        if(list==null||list.size()==0) {
                            // 分配了节点负责人分发给全部
                            for (SysUser user : users) {
                                taskService.addCandidateUser(t.getId(), user.getId());
                                // 异步发消息
                                //messageUtil.sendActMessage(user.getId(), ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                            }
                            taskService.setPriority(t.getId(), task.getPriority());
                        }
                    }
                }else{
                    // 避免重复添加
                    List<String> list = iRunIdentityService.selectByConditions(t.getId(), "candidate");
                    if(list==null||list.size()==0) {
                        for(String assignee : assignees){
                            taskService.addCandidateUser(t.getId(), assignee);
                            // 异步发消息
                            //messageUtil.sendActMessage(assignee, ActivitiConstant.MESSAGE_TODO_CONTENT, sendMessage, sendSms, sendEmail);
                            taskService.setPriority(t.getId(), priority);
                        }
                    }
                }
            }
        } else {
            //流程完成
            actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
            actBusiness.setResult(ActivitiConstant.RESULT_PASS);
            actBusinessService.update(actBusiness);
            //调用最终通过方法
            if (actTaskHandler != null) {
                //actTaskHandler.doFinalPass(businessInfoVo);
            }
            // 异步发消息
            //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_PASS_CONTENT, sendMessage, sendSms, sendEmail);
        }


        //发消息
        String antid = IdUtil.simpleUUID();
        if (sendMessage){
            SysAnnouncement sysAnnouncement = new SysAnnouncement();
            sysAnnouncement.setTitile(processName);
            sysAnnouncement.setMsgContent(title+"  " +comment);
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
            sysAnnouncement.setId(antid);
            sysAnnouncementService.save(sysAnnouncement);

            //还有没下一个人
            if (CollUtil.isNotEmpty(users)){
                for (int i = 0; i < users.size(); i++) {
                    SysAnnouncementSend announcementSend = new SysAnnouncementSend();
                    announcementSend.setAnntId(antid);
                    announcementSend.setUserId(users.get(i).getId());
                    announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
                    announcementSend.setReadTime(new Date());
                    announcementSend.setCreateTime(new Date());
                    announcementSend.setCreateBy(sysUser.getUsername());
                    sysAnnouncementSendService.save(announcementSend);
                }
            }else {
                SysAnnouncementSend announcementSend = new SysAnnouncementSend();
                announcementSend.setAnntId(antid);
                SysUser userByName = sysUserService.getUserByName(applyerUsername);
                announcementSend.setUserId(userByName.getId());
                announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
                announcementSend.setReadTime(new Date());
                announcementSend.setCreateTime(new Date());
                announcementSend.setCreateBy(sysUser.getUsername());
                sysAnnouncementSendService.save(announcementSend);
            }
        }



        //调用每步通过方法
        if (actTaskHandler != null) {
            //actTaskHandler.doPass(businessInfoVo);
        }
        iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                ActivitiConstant.EXECUTOR_TYPE, sysUser.getId(), id, procInstId);
        return ResultUtil.success("操作成功");
    }


    @RequestMapping("/appBack")
    public Result<Object> appBack(String id,
                               String procInstId,
                               String comment,
                               Boolean sendMessage,
                               Boolean sendSms,
                               Boolean sendEmail){


        if(StrUtil.isBlank(comment)){
            comment = "";
        }
        taskService.addComment(id, procInstId, comment);
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        // 删除流程实例
        runtimeService.deleteProcessInstance(procInstId, "backed");
        ActBusiness actBusiness = actBusinessService.get(pi.getBusinessKey());
        actBusiness.setStatus(ActivitiConstant.STATUS_FINISH);
        actBusiness.setResult(ActivitiConstant.RESULT_FAIL);
        actBusinessService.update(actBusiness);
        // 异步发消息
        //messageUtil.sendActMessage(actBusiness.getUserId(), ActivitiConstant.MESSAGE_BACK_CONTENT, sendMessage, sendSms, sendEmail);
        // 记录实际审批人员
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user =userService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        iHistoryIdentityService.insert(SnowFlakeUtil.nextId().toString(),
                ActivitiConstant.EXECUTOR_TYPE, userId, id, procInstId);
        return ResultUtil.success("操作成功");
    }
}
