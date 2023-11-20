package org.cmms.modules.system.service;

import org.cmms.modules.system.entity.AppHrBasOrganization;
import org.cmms.modules.system.entity.HrBasOrganization;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.model.DepartIdHrModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 组织机构管理
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface IHrBasOrganizationService extends IService<HrBasOrganization> {


    String getYwjgdmByZzbz(String zzbz);

    /**
     * 查询所有部门信息,并分节点进行显示
     * @return
     */
    List<HrBasOrganizationTreeModel> queryTreeList(String ywjgQuery);

    /**
     * 查询所有部门DepartId信息,并分节点进行显示
     * @return
     */
    public List<DepartIdHrModel> queryDepartIdTreeList();

    /**
     * 保存部门数据
     * @param sysDepart
     */
    void saveDepartData(HrBasOrganization sysDepart, String username);

    /**
     * 更新depart数据
     * @param sysDepart
     * @return
     */
    Boolean updateDepartDataById(HrBasOrganization sysDepart,String username);

    /**
     * 删除depart数据
     * @param id
     * @return
     */
    /* boolean removeDepartDataById(String id); */

    /**
     * 根据关键字搜索相关的部门数据
     * @param keyWord
     * @return
     */
    List<HrBasOrganizationTreeModel> searhBy(String keyWord);

    /**
     * 根据部门id删除并删除其可能存在的子级部门
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 查询SysDepart集合
     * @param userId
     * @return
     */
    public List<HrBasOrganization> queryUserDeparts(String userId);


    /**
     * 根据部门id批量删除并删除其可能存在的子级部门
     * @param ids
     * @return
     */
    void deleteBatchWithChildren(List<String> ids);

    public HrBasOrganization queryByYwjgdm(String ywjgdm);

    public HrBasOrganization queryByZzbz(String zzbz);

    public List<HrBasOrganization> queryAuthOrgList(String zzbz);

    public Map<String,String> getZzbzByZzjc();

    /**
     * 根据组织标志查询本组织与下级组织信息
     * @param zzbz
     * @return
     */
    public List<HrBasOrganization> queryZzxxTreeByZzbz(String zzbz);

    public List<HrBasOrganization> getTreeData(String userId);

    public List<AppHrBasOrganization> getAppHrBasOrganizationList(String type,String sfjgdm);

    public List<HrBasOrganization> queryZzbzZh();

    /**
     * 根据导入excel的组织机构信息转换
     *
     * @param text 结果
     * @param code 条件
     * @param branchName 条件值
     * @return
     */
    String queryYwjgdmByZzjcLike(@Param("text") String text,
                                 @Param("code") String code,
                                 @Param("branchName") String branchName);

}
