package org.cmms.modules.activiti.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.modules.activiti.dao.ActNodeDao;
import org.cmms.modules.activiti.entity.ActNode;
import org.cmms.modules.activiti.service.ActNodeService;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 流程节点用户接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class ActNodeServiceImpl implements ActNodeService {

    @Autowired
    private ActNodeDao actNodeDao;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private IHrBasPostService hrBasPostService;

    @Autowired
    private IHrBasOrganizationService departmentDao;
    @Override
    public ActNodeDao getRepository() {
        return actNodeDao;
    }

    @Override
    public List<SysUser> findUserByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_USER);
        List<SysUser> list = new ArrayList<>();
        listNode.forEach(e -> {
            SysUser u = sysUserService.getById(e.getRelateId());
            if(u!=null){
                list.add(u);
            }
        });
        return list;
    }

    @Override
    public List<SysRole> findRoleByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_ROLE);
        List<SysRole> list = new ArrayList<>();
        listNode.forEach(e -> {
            SysRole r = roleService.getById(e.getRelateId());
            if(r!=null){
                list.add(r);
            }
        });
        return list;
    }

    @Override
    public List<HrBasPost> findPostByNodeId(String nodeId) {
        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_POST);
        List<HrBasPost> list = new ArrayList<>();
        listNode.forEach(e -> {
            LambdaQueryWrapper<HrBasPost> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(HrBasPost::getGwbz, e.getRelateId());
            HrBasPost post = hrBasPostService.getOne(queryWrapper);
            if (post != null) {
                list.add(post);
            }
        });
        return list;
    }

    @Override
    public List<HrBasOrganization> findDepartmentByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<HrBasOrganization> list = new ArrayList<>();
        listNode.forEach(e -> {
            HrBasOrganization d = departmentDao.queryByZzbz(e.getRelateId());
            if(d!=null){
                list.add(d);
            }
        });
        return list;
    }

    @Override
    public List<String> findDepartmentIdsByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<String> list = new ArrayList<>();
        listNode.forEach(e -> {
            list.add(e.getRelateId());
        });
        return list;
    }

    @Override
    public String findDynamicClassByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DYNAMIC);
        if (!listNode.isEmpty()) {
            return listNode.get(0).getRelateId();
        }
        return null;
    }

    @Override
    public Boolean hasChooseDepHeader(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEP_HEADER);
        if(listNode!=null&&listNode.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean hasCustomUser(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_CUSTOM);
        if(listNode!=null&&listNode.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public void deleteByNodeId(String nodeId) {

        actNodeDao.deleteByNodeId(nodeId);
    }

    @Override
    public void deleteByRelateId(String relateId) {

        actNodeDao.deleteByRelateId(relateId);
    }
}