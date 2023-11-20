package org.cmms.modules.activiti.service;

import org.cmms.base.XbootBaseService;
import org.cmms.modules.activiti.entity.ActNode;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;

import java.util.List;

/**
 * 流程节点用户接口
 * @author Exrick
 */
public interface ActNodeService extends XbootBaseService<ActNode,String> {

    /**
     * 通过nodeId获取用户
     * @param nodeId
     * @return
     */
    List<SysUser> findUserByNodeId(String nodeId);

    /**
     * 通过nodeId获取角色
     * @param nodeId
     * @return
     */
    List<SysRole> findRoleByNodeId(String nodeId);

    /**
     * 通过nodeId获取岗位
     * @param nodeId
     * @return
     */
    List<HrBasPost> findPostByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门
     * @param nodeId
     * @return
     */
    List<HrBasOrganization> findDepartmentByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门id
     * @param nodeId
     * @return
     */
    List<String> findDepartmentIdsByNodeId(String nodeId);

    /**
     * 通过nodeId获取动态类名
     * @param nodeId
     * @return
     */
    public String findDynamicClassByNodeId(String nodeId);

    /**
     * 是否多级连续主管
     * @param nodeId
     * @return
     */
    Boolean hasChooseDepHeader(String nodeId);

    /**
     * 是否自选用户
     * @param nodeId
     * @return
     */
    Boolean hasCustomUser(String nodeId);

    /**
     * 通过nodeId删除
     * @param nodeId
     */
    void deleteByNodeId(String nodeId);

    /**
     * 通过relateId删除
     * @param relateId
     */
    void deleteByRelateId(String relateId);
}