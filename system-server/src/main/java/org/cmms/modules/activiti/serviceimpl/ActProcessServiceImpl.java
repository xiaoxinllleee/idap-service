package org.cmms.modules.activiti.serviceimpl;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.common.exception.XbootException;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.activiti.dao.ActProcessDao;
import org.cmms.modules.activiti.dynamic.DynamicHandler;
import org.cmms.modules.activiti.dynamic.DynamicUtil;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActProcess;
import org.cmms.modules.activiti.service.ActNodeService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.activiti.service.ActStarterService;
//import org.cmms.modules.activiti.utils.MessageUtil;
import org.cmms.modules.activiti.vo.ProcessNodeVo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * 流程管理接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class ActProcessServiceImpl implements ActProcessService {

    @Autowired
    private ActProcessDao actProcessDao;


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActNodeService actNodeService;

    @Autowired
    IDictValueQuery iDictValueQuery;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;

/*    @Autowired
    private ActProcessService actProcessService;*/

    @Autowired
    private ActStarterService actStarterService;

  /*  @Autowired
    private MessageUtil messageUtil;*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ActProcessDao getRepository() {
        return actProcessDao;
    }

    @Override
    public Page<ActProcess> findByCondition(Boolean showLatest, ActProcess actProcess, SearchVo searchVo, Pageable pageable) {

        return actProcessDao.findAll(new Specification<ActProcess>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ActProcess> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> processKeyField = root.get("processKey");
                Path<String> categoryIdField = root.get("categoryId");
                Path<Integer> statusField = root.get("status");
                Path<Date> createTimeField = root.get("createTime");
                Path<Boolean> latestField = root.get("latest");

                List<Predicate> list = new ArrayList<Predicate>();

                // 模糊搜素
                if(StrUtil.isNotBlank(actProcess.getName())){
                    list.add(cb.like(nameField,'%'+actProcess.getName()+'%'));
                }
                if(StrUtil.isNotBlank(actProcess.getProcessKey())){
                    list.add(cb.like(processKeyField,'%'+actProcess.getProcessKey()+'%'));
                }

                // 分类
                if(StrUtil.isNotBlank(actProcess.getCategoryId())){
                    list.add(cb.equal(categoryIdField, actProcess.getCategoryId()));
                }

                // 状态
                if(actProcess.getStatus()!=null){
                    list.add(cb.equal(statusField, actProcess.getStatus()));
                }
                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&& StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                // 只显示最新
                if(showLatest!=null&&showLatest){
                    list.add(cb.equal(latestField, true));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public ActProcess findByProcessKeyAndLatest(String processKey, Boolean latest) {

        return actProcessDao.findByProcessKeyAndLatest(processKey, latest);
    }

    @Override
    public void setAllOldByProcessKey(String processKey) {

        List<ActProcess> list = actProcessDao.findByProcessKey(processKey);
        if(list==null||list.size()==0){
            return;
        }
        list.forEach(item -> {
            item.setLatest(false);
        });
        actProcessDao.saveAll(list);
    }

    @Override
    public void setLatestByProcessKey(String processKey) {

        ActProcess actProcess = actProcessDao.findTopByProcessKeyOrderByVersionDesc(processKey);
        if(actProcess==null){
            return;
        }
        actProcess.setLatest(true);
        actProcessDao.save(actProcess);
    }

    @Override
    public List<ActProcess> findByCategoryId(String categoryId) {

        return actProcessDao.findByCategoryId(categoryId);
    }

    @Override
    public String startProcess(ActBusiness actBusiness) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SysUser sysusers =userService.getUserByName(sysUser.getUsername());
        String userId = sysusers.getId();
        // 权限判断
        ActProcess actProcess = this.get(actBusiness.getProcDefId());
        if(actProcess==null){
            throw new XbootException("流程定义不存在");
        }
        if(actProcess.getAllUser()!=null&&!actProcess.getAllUser()){
            // 判断有无权限
            if(!actStarterService.hasRecord(actBusiness.getProcDefId(), userId)){
                throw new XbootException("抱歉，您无权发起该流程");
            }
        }
        // 启动流程用户
        identityService.setAuthenticatedUserId(userId);
        // 启动流程 需传入业务表id变量
        actBusiness.getParams().put("tableId", actBusiness.getTableId());
        ProcessInstance pi = runtimeService.startProcessInstanceById(actBusiness.getProcDefId(), actBusiness.getId(), actBusiness.getParams());
        // 设置流程实例名称
        runtimeService.setProcessInstanceName(pi.getId(), actBusiness.getTitle());
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for(Task task : tasks){
            if(actBusiness.getFirstGateway()){
                // 网关类型
                List<SysUser> users = getNode(task.getTaskDefinitionKey()).getUsers();
                // 如果下个节点未分配审批人为空 取消结束流程
                if(users==null||users.size()==0){
                    throw new XbootException("有任务节点未分配任何候选审批人，发起流程失败");
                }else{
                    // 分配了节点负责人分发给全部
                    for(SysUser user : users){
                        taskService.addCandidateUser(task.getId(), user.getId());
                        // 异步发消息
                        //messageUtil.sendActMessage(user.getId(), ActivitiConstant.MESSAGE_TODO_CONTENT, actBusiness.getSendMessage(), actBusiness.getSendSms(), actBusiness.getSendEmail());
                    }
                }
            }else {
                // 分配第一个任务用户
                for (String assignee : actBusiness.getAssignees()) {
                    taskService.addCandidateUser(task.getId(), assignee);
                    // 异步发消息
                    //messageUtil.sendActMessage(assignee, ActivitiConstant.MESSAGE_TODO_CONTENT, actBusiness.getSendMessage(), actBusiness.getSendSms(), actBusiness.getSendEmail());
                }
            }
            // 设置任务优先级
            taskService.setPriority(task.getId(), actBusiness.getPriority());
        }
        return pi.getId();
    }

    @Override
    public ProcessNodeVo getFirstNode(String procDefId, String tableId) {

        BpmnModel bpmnModel = repositoryService.getBpmnModel(procDefId);

        ProcessNodeVo node = new ProcessNodeVo();

        List<Process> processes = bpmnModel.getProcesses();
        Collection<FlowElement> elements = processes.get(0).getFlowElements();
        // 流程开始节点
        StartEvent startEvent = null;
        for (FlowElement element : elements) {
            if (element instanceof StartEvent) {
                startEvent = (StartEvent) element;
                break;
            }
        }
        FlowElement e = null;
        // 判断开始后的流向节点
        SequenceFlow sequenceFlow = startEvent.getOutgoingFlows().get(0);
        for (FlowElement element : elements) {
            if(element.getId().equals(sequenceFlow.getTargetRef())){
                if(element instanceof UserTask){
                    e = element;
                    node.setType(ActivitiConstant.NODE_TYPE_TASK);
                    break;
                }else if(element instanceof ExclusiveGateway){
                    e = element;
                    node.setType(ActivitiConstant.NODE_TYPE_EG);
                    break;
                }else if(element instanceof ParallelGateway){
                    e = element;
                    node.setType(ActivitiConstant.NODE_TYPE_PG);
                    break;
                }else{
                    throw new XbootException("流程设计错误，开始节点后只能是用户任务节点、排他网关、并行网关");
                }
            }
        }
        // 排他、平行网关直接返回
        if(e instanceof ExclusiveGateway || e instanceof ParallelGateway){
            return node;
        }
        node.setTitle(e.getName());
        // 是否为自选用户节点
        Boolean customUser = actNodeService.hasCustomUser(e.getId());
        if(customUser){
            node.setType(ActivitiConstant.NODE_TYPE_CUSTOM);
        }else{
            // 设置关联用户
            List<SysUser> users = getNodetUsers(e.getId(), tableId);
            node.setUsers(removeDuplicate(users));
        }
        return node;
    }

    @Override
    public ProcessNodeVo getNextNode(String procDefId, String currActId, String tableId) {

        ProcessNodeVo node = new ProcessNodeVo();

        // 当前执行节点id
        ProcessDefinitionEntity dfe = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(procDefId);
        // 获取所有节点
        List<ActivityImpl> activitiList = dfe.getActivities();
        // 判断出当前流程所处节点，根据路径获得下一个节点实例
        for(ActivityImpl activityImpl : activitiList){
            if (activityImpl.getId().equals(currActId)) {
                // 获取下一个节点
                List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();

                PvmActivity pvmActivity = pvmTransitions.get(0).getDestination();

                String type = pvmActivity.getProperty("type").toString();
                if("userTask".equals(type)){
                    // 用户任务节点
                    Boolean customUser = actNodeService.hasCustomUser(pvmActivity.getId());
                    if(customUser){
                        // 是否为自选用户节点
                        node.setType(ActivitiConstant.NODE_TYPE_CUSTOM);
                    }else {
                        node.setType(ActivitiConstant.NODE_TYPE_TASK);
                        node.setTitle(pvmActivity.getProperty("name").toString());
                        // 设置关联用户
                        List<SysUser> users = getNodetUsers(pvmActivity.getId(), tableId);
                        node.setUsers(removeDuplicate(users));
                    }
                }else if("exclusiveGateway".equals(type)){
                    // 排他网关
                    node.setType(ActivitiConstant.NODE_TYPE_EG);
                }else if("parallelGateway".equals(type)){
                    // 平行网关
                    node.setType(ActivitiConstant.NODE_TYPE_PG);
                }else if("endEvent".equals(type)){
                    // 结束
                    node.setType(ActivitiConstant.NODE_TYPE_END);
                }else{
                    throw new XbootException("流程设计错误，包含无法处理的节点");
                }
                break;
            }
        }

        return node;
    }

    @Override
    public ProcessNodeVo getNode(String nodeId) {

        ProcessNodeVo node = new ProcessNodeVo();
        // 设置关联用户
        List<SysUser> users = getNodetUsers(nodeId, null);
        node.setUsers(removeDuplicate(users));
        return node;
    }

    @Override
    public void updateCategoryTitle(String categoryId, String categoryTitle) {

        actProcessDao.updateCategoryTitle(categoryId, categoryTitle);
    }

    /**
     * 设置节点审批人
     * @param nodeId
     */
    public List<SysUser> getNodetUsers(String nodeId, String tableId){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String org=sysUser.getOrgCode();
        //浏阳：如果是分理处 必须去支行行长审批
        //当用户是分理处的时候 修改支行code
        System.out.println("====="+org);
        if (StringUtils.isNotBlank(sysUser.getOrgCode())){
            String sjzzbz = iDictValueQuery.sjzzbz(sysUser.getOrgCode());
            if (sjzzbz != null)
                org = sjzzbz;
        }
        List<SysUser> users = actNodeService.findUserByNodeId(nodeId);
        // 设置关联角色的用户
        List<SysRole> roles = actNodeService.findRoleByNodeId(nodeId);
        for(SysRole r : roles){
            List<SysUser> userList = userService.findUserByRoleId(r.getId(),org);
            if (userList == null || userList.isEmpty()) {
                userList = userService.findUsersByRoleId(r.getRoleCode());
            }
            users.addAll(userList);
        }
        // 设置关联岗位的用户
        List<HrBasPost> posts = actNodeService.findPostByNodeId(nodeId);
        for(HrBasPost post: posts) {
            List<SysUser> userList = userService.findUserByPostAndOrg(post.getGwbz(), org);
            users.addAll(userList);
        }
        String dynamicClass = actNodeService.findDynamicClassByNodeId(nodeId);
        if (StringUtils.isNotEmpty(dynamicClass)) {
            DynamicHandler dynamicHandler = DynamicUtil.getHandler(dynamicClass);
            if (dynamicHandler != null) {
                if (StringUtils.isEmpty(tableId)) {
                    throw new XbootException("请求参数错误，表单ID为空");
                }
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("nodeId", nodeId);
                paramMap.put("tableId", tableId);
                List<SysUser> userList = dynamicHandler.getDynamicUser(paramMap);
                users.addAll(userList);
            } else {
                throw new XbootException("获取动态设置类异常");
            }
        }
        //部门负责人暂时没有 20200701 by liuxiangqun
      /*  // 设置关联部门负责人
        List<String> departmentIds = actNodeService.findDepartmentIdsByNodeId(nodeId);
        List<DepartmentHeader> departments = departmentHeaderService.findByDepartmentIdIn(departmentIds);
        for (DepartmentHeader d : departments){
            User user = userService.get(d.getUserId());
            users.add(user);
        }
        // 判断获取部门负责人
        if(actNodeService.hasChooseDepHeader(nodeId)){
            String depId = securityUtil.getCurrUser().getDepartmentId();
            if(StrUtil.isNotBlank(depId)){
                List<DepartmentHeader> departmentHeaders = departmentHeaderService.findByDepartmentIdIn(Collections.singletonList(depId));
                for (DepartmentHeader d : departmentHeaders){
                    User user = userService.get(d.getUserId());
                    users.add(user);
                }
            }
        }*/
        return users;
    }

    /**
     * 去重
     * @param list
     * @return
     */
    private List<SysUser> removeDuplicate(List<SysUser> list) {

        LinkedHashSet<SysUser> set = new LinkedHashSet<>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        entityManager.clear();
        list.forEach(u -> {
            u.setPassword(null);
        });
        return list;
    }
}