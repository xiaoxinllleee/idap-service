package org.cmms.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.YouBianCodeUtil;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygrggl.service.impl.HrBasStaffPostServiceImpl;
import org.cmms.modules.system.entity.AppHrBasOrganization;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDepart;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.mapper.HrBasOrganizationMapper;
import org.cmms.modules.system.model.DepartIdHrModel;
import org.cmms.modules.system.model.DepartIdModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;
import org.cmms.modules.system.model.SysDepartTreeModel;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.system.util.FindsDepartsChildrenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 组织机构管理
 * @Author: cmms
 * @Date: 2019-09-18
 * @Version: V1.0
 */
@Service
public class HrBasOrganizationServiceImpl extends ServiceImpl<HrBasOrganizationMapper, HrBasOrganization> implements IHrBasOrganizationService {
    @Autowired
    private HrBasOrganizationMapper hrBasOrganizationMapper;
    @Autowired
    ISysRoleService sysRoleService;
    @Autowired
    IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * queryTreeList 对应 queryTreeList 查询所有的部门数据,以树结构形式响应给前端
     */
/*
    @Cacheable(value = CacheConstant.DEPART_INFO_CACHE)
*/

    HashMap<String, String> hrMap = null;

    @Override
    public String getYwjgdmByZzbz(String zzbz) {
        if (CollUtil.isEmpty(hrMap)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.isNotNull("ywjgdm");
            List<HrBasOrganization> hrBasOrganizationList = hrBasOrganizationMapper.selectList(queryWrapper);
            if (hrBasOrganizationList != null && hrBasOrganizationList.size() > 0) {
                hrMap = new HashMap<>();
                for (int i = 0; i < hrBasOrganizationList.size(); i++) {
                    hrMap.put(hrBasOrganizationList.get(i).getZzbz(), hrBasOrganizationList.get(i).getYwjgdm());
                }
            }
        }
        return StringUtils.isNoneBlank(hrMap.get(zzbz)) ? hrMap.get(zzbz) : StringUtils.EMPTY;
    }


    @Override
    public List<HrBasOrganizationTreeModel> queryTreeList(String ywjgQuery) {
        LambdaQueryWrapper<HrBasOrganization> query = new LambdaQueryWrapper<HrBasOrganization>();
        //query.eq(HrBasOrganization::getQybz, 1d);
        if ("1".equals(ywjgQuery)) {
            query.eq(HrBasOrganization::getYwjgbz, "1");
            query.or().eq(HrBasOrganization::getZzjb, "3");
        }
        query.orderByAsc(HrBasOrganization::getPxxh);
        List<HrBasOrganization> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<HrBasOrganizationTreeModel> listResult = FindsDepartsChildrenUtil.wrapTreeDataToTreeListHr(list);
        return listResult;
    }

    /*
        @Cacheable(value = CacheConstant.DEPART_IDMODEL_CACHE)
    */
    @Override
    public List<DepartIdHrModel> queryDepartIdTreeList() {
        LambdaQueryWrapper<HrBasOrganization> query = new LambdaQueryWrapper<HrBasOrganization>();
        // query.eq(HrBasOrganization::getQybz, 1);
        query.orderByAsc(HrBasOrganization::getPxxh);
        List<HrBasOrganization> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<DepartIdHrModel> listResult = FindsDepartsChildrenUtil.wrapTreeDataToDepartIdTreeListHr(list);
        return listResult;
    }

    /**
     * saveDepartData 对应 add 保存用户在页面添加的新的部门对象数据
     */
    @Override
    @Transactional
    public void saveDepartData(HrBasOrganization sysDepart, String username) {
        if (sysDepart != null && username != null) {
            if (sysDepart.getSjzzbz() == null) {
                sysDepart.setSjzzbz("");
            }
            String s = UUID.randomUUID().toString().replace("-", "");
            sysDepart.setZzbz(s);
            // 先判断该对象有无父级ID,有则意味着不是最高级,否则意味着是最高级
            // 获取父级ID
            String parentId = sysDepart.getSjzzbz();
            String[] codeArray = generateOrgCode(parentId);
            sysDepart.setYwjgdm(codeArray[0]);
            String orgType = codeArray[1];
            sysDepart.setZzlb(orgType);
            sysDepart.setQybz("1");
            this.save(sysDepart);
        }

    }

    /**
     * saveDepartData 的调用方法,生成部门编码和部门类型
     *
     * @param parentId
     * @return
     */
    private String[] generateOrgCode(String parentId) {
        //update-begin--Author:Steve  Date:20190201 for：组织机构添加数据代码调整
        LambdaQueryWrapper<HrBasOrganization> query = new LambdaQueryWrapper<HrBasOrganization>();
        LambdaQueryWrapper<HrBasOrganization> query1 = new LambdaQueryWrapper<HrBasOrganization>();
        String[] strArray = new String[2];
        // 创建一个List集合,存储查询返回的所有SysDepart对象
        List<HrBasOrganization> departList = new ArrayList<>();
        // 定义新编码字符串
        String newOrgCode = "";
        // 定义旧编码字符串
        String oldOrgCode = "";
        // 定义部门类型
        String orgType = "";
        // 如果是最高级,则查询出同级的org_code, 调用工具类生成编码并返回
        if (StringUtil.isNullOrEmpty(parentId)) {
            // 线判断数据库中的表是否为空,空则直接返回初始编码
            query1.eq(HrBasOrganization::getSjzzbz, "");
            query1.orderByDesc(HrBasOrganization::getYwjgdm);
            departList = this.list(query1);
            if (departList == null || departList.size() == 0) {
                strArray[0] = YouBianCodeUtil.getNextYouBianCode(null);
                strArray[1] = "1";
                return strArray;
            } else {
                HrBasOrganization depart = departList.get(0);
                oldOrgCode = depart.getYwjgdm();
                orgType = depart.getZzlb().toString();
                newOrgCode = YouBianCodeUtil.getNextYouBianCode(oldOrgCode);
            }
        } else { // 反之则查询出所有同级的部门,获取结果后有两种情况,有同级和没有同级
            // 封装查询同级的条件
            query.eq(HrBasOrganization::getSjzzbz, parentId);
            // 降序排序
            query.orderByDesc(HrBasOrganization::getYwjgdm);
            // 查询出同级部门的集合
            List<HrBasOrganization> parentList = this.list(query);
            // 查询出父级部门
            HrBasOrganization depart = this.getById(parentId);
            // 获取父级部门的Code
            String parentCode = depart.getYwjgdm();
            // 根据父级部门类型算出当前部门的类型
            orgType = String.valueOf(Integer.valueOf(depart.getZzlb()) + 1);
            // 处理同级部门为null的情况
            if (parentList == null || parentList.size() == 0) {
                // 直接生成当前的部门编码并返回
                newOrgCode = YouBianCodeUtil.getSubYouBianCode(parentCode, null);
            } else { //处理有同级部门的情况
                // 获取同级部门的编码,利用工具类
                String subCode = parentList.get(0).getYwjgdm();
                // 返回生成的当前部门编码
                newOrgCode = YouBianCodeUtil.getSubYouBianCode(parentCode, subCode);
            }
        }
        // 返回最终封装了部门编码和部门类型的数组
        strArray[0] = newOrgCode;
        strArray[1] = orgType;
        return strArray;
        //update-end--Author:Steve  Date:20190201 for：组织机构添加数据代码调整
    }


    /**
     * removeDepartDataById 对应 delete方法 根据ID删除相关部门数据
     *
     */
    /*
     * @Override
     *
     * @Transactional public boolean removeDepartDataById(String id) {
     * System.out.println("要删除的ID 为=============================>>>>>"+id); boolean
     * flag = this.removeById(id); return flag; }
     */

    /**
     * updateDepartDataById 对应 edit 根据部门主键来更新对应的部门数据
     */
    @Override
    @Transactional
    public Boolean updateDepartDataById(HrBasOrganization sysDepart, String username) {
        if (sysDepart != null && username != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public void deleteBatchWithChildren(List<String> ids) {
        List<String> idList = new ArrayList<String>();
        for (String id : ids) {
            idList.add(id);
            this.checkChildrenExists(id, idList);
        }
        this.removeByIds(idList);

    }

    /**
     * <p>
     * 根据关键字搜索相关的部门数据
     * </p>
     */
    @Override
    public List<HrBasOrganizationTreeModel> searhBy(String keyWord) {
        LambdaQueryWrapper<HrBasOrganization> query = new LambdaQueryWrapper<HrBasOrganization>();
        query.like(HrBasOrganization::getZzjc, keyWord);
        //update-begin--Author:huangzhilin  Date:20140417 for：[bugfree号]组织机构搜索回显优化--------------------
        HrBasOrganizationTreeModel model = new HrBasOrganizationTreeModel();
        List<HrBasOrganization> departList = this.list(query);
        List<HrBasOrganizationTreeModel> newList = new ArrayList<>();
        if (departList.size() > 0) {
            for (HrBasOrganization depart : departList) {
                model = new HrBasOrganizationTreeModel(depart);
                model.setChildren(null);
                //update-end--Author:huangzhilin  Date:20140417 for：[bugfree号]组织机构搜索功回显优化----------------------
                newList.add(model);
            }
            return newList;
        }
        return null;
    }

    /**
     * 根据部门id删除并且删除其可能存在的子级任何部门
     */
    @Override
    public boolean delete(String id) {
        List<String> idList = new ArrayList<>();
        idList.add(id);
        this.checkChildrenExists(id, idList);
        //清空部门树内存
        //FindsDepartsChildrenUtil.clearDepartIdModel();
        boolean ok = this.removeByIds(idList);
        return ok;
    }

    /**
     * delete 方法调用
     *
     * @param id
     * @param idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        LambdaQueryWrapper<HrBasOrganization> query = new LambdaQueryWrapper<HrBasOrganization>();
        query.eq(HrBasOrganization::getSjzzbz, id);
        List<HrBasOrganization> departList = this.list(query);
        if (departList != null && departList.size() > 0) {
            for (HrBasOrganization depart : departList) {
                idList.add(depart.getZzbz());
                this.checkChildrenExists(depart.getZzbz(), idList);
            }
        }
    }

    @Override
    public List<HrBasOrganization> queryUserDeparts(String userId) {
        return baseMapper.queryUserDeparts(userId);
    }

    public HrBasOrganization queryByYwjgdm(String ywjgdm) {
        return hrBasOrganizationMapper.queryByYwjgdm(ywjgdm);
    }

    public HrBasOrganization queryByZzbz(String zzbz) {
        return hrBasOrganizationMapper.queryByZzbz(zzbz);
    }

    @Override
    public List<HrBasOrganization> queryAuthOrgList(String zzbz) {
        return hrBasOrganizationMapper.queryAuthOrgList(zzbz);
    }

    @Override
    public Map<String, String> getZzbzByZzjc() {
        List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationMapper.selectList(null);
        if (CollUtil.isNotEmpty(hrBasOrganizations)) {
            return hrBasOrganizations.stream().collect(Collectors.toMap(HrBasOrganization::getZzjc, HrBasOrganization::getZzbz,
                    (value1, value2) -> {
                        return value2;
                    }));
        }
        return null;
    }

    @Override
    public List<HrBasOrganization> queryZzxxTreeByZzbz(String zzbz) {
        return hrBasOrganizationMapper.queryZzxxTreeByZzbz(zzbz);
    }

    @Override
    public List<HrBasOrganization> getTreeData(String userId) {
        List<HrBasOrganization> organizationList = hrBasOrganizationMapper.getTreeData(userId);
        List<HrBasOrganization> tree = new ArrayList<>();
        for (int i = 0; i < organizationList.size(); i++) {
            HrBasOrganization hrBasOrganization = organizationList.get(i);
            if ("1".equals(hrBasOrganization.getLevel())) {
                tree.add(hrBasOrganization);
            }
            List<HrBasOrganization> children = new ArrayList<>();
            for (HrBasOrganization node : organizationList) {
                if (node.getSjzzbz().equals(hrBasOrganization.getZzbz())) {
                    children.add(node);
                }
            }
            hrBasOrganization.setChildren(children);
        }
        return tree;
    }

    @Override
    public List<AppHrBasOrganization> getAppHrBasOrganizationList(String type, String sfjgdm) {
        QueryWrapper queryWrapper = new QueryWrapper();

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            //判断是否是支行行长
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + sysUser.getUsername());
            if (QydmEnums.LIANYUAN.getQydmCode().equals(qydm)) {

            } else {
                boolean roleCode = sysRoleService.isRoleCode(sysUser.getUsername());
                if (roleCode) {
                    //查所在zzbz
                    HrBasStaffPost byNowDate = hrBasStaffPostService.getByNowDate(sysUser.getWorkNo());
                    if (StringUtils.isNotBlank(byNowDate.getZzbz())) {
                        queryWrapper.eq("zzbz", byNowDate.getZzbz());
                    }
                }
            }
        }

        if ("1".equals(type)) {
            queryWrapper.ne("zzlb", 3);
        }
        if ("1".equals(sfjgdm)) {
            queryWrapper.isNotNull("ywjgdm");
        }
        queryWrapper.ne("sjzzbz", 0);
        //查询区域编码
        SysDic sysDic = sysDicService.queryByCode("101001");
        System.out.println(sysDic.getValue() + "=====区域编码======");
        //天易用ywjgdm排序
        if ("095".equals(sysDic.getValue())) {
            queryWrapper.orderByAsc("ywjgdm");
        } else {
            queryWrapper.orderByAsc("zzbz");
        }
        List<HrBasOrganization> hrBasOrganizations = baseMapper.selectList(queryWrapper);

        //先分出支行和网点
        List<HrBasOrganization> zh = new ArrayList<>();
        List<HrBasOrganization> wd = new ArrayList<>();

        for (int i = 0; i < hrBasOrganizations.size(); i++) {
            HrBasOrganization hrBasOrganization = hrBasOrganizations.get(i);
            if ("1".equals(hrBasOrganization.getSjzzbz())) {
                zh.add(hrBasOrganization);
            } else {
                wd.add(hrBasOrganization);
            }
        }

        List<AppHrBasOrganization> list = new ArrayList<>();

        for (int i = 0; i < zh.size(); i++) {
            AppHrBasOrganization appHrBasOrganization = new AppHrBasOrganization();
            HrBasOrganization hrBasOrganization = zh.get(i);
            appHrBasOrganization.setText(hrBasOrganization.getZzjc());
            if ("1".equals(sfjgdm)) {
                appHrBasOrganization.setValue(hrBasOrganization.getYwjgdm());
            } else {
                appHrBasOrganization.setValue(hrBasOrganization.getZzbz());
            }

            String zzbz = hrBasOrganization.getZzbz();
            List<AppHrBasOrganization> child = new ArrayList<>();
            for (int j = 0; j < wd.size(); j++) {
                HrBasOrganization hrBasOrganization1 = wd.get(j);
                if (zzbz.equals(hrBasOrganization1.getSjzzbz())) {
                    AppHrBasOrganization children = new AppHrBasOrganization();
                    children.setText(hrBasOrganization1.getZzjc());
                    if ("1".equals(sfjgdm)) {
                        children.setValue(hrBasOrganization1.getYwjgdm());
                    } else {
                        children.setValue(hrBasOrganization1.getZzbz());
                    }
                    child.add(children);
                }
            }

            if (CollUtil.isNotEmpty(child)) {
                //如果是多个网点 把自己本网点加进去
                AppHrBasOrganization selt = new AppHrBasOrganization();
                selt.setText(hrBasOrganization.getZzjc());
                if ("1".equals(sfjgdm)) {
                    selt.setValue(hrBasOrganization.getYwjgdm() + "-" + hrBasOrganization.getYwjgdm());
                } else {
                    selt.setValue(hrBasOrganization.getZzbz() + "-" + hrBasOrganization.getZzbz());
                }
                child.add(selt);
                appHrBasOrganization.setChildren(child);
            }

            list.add(appHrBasOrganization);
        }
        return list;
    }

    @Override
    public List<HrBasOrganization> queryZzbzZh() {
        return baseMapper.queryZzbzZh();
    }

    @Override
    public String queryYwjgdmByZzjcLike(String text, String code, String branchName) {
        return baseMapper.queryYwjgdmByZzjcLike(text, code, branchName);
    }
}
