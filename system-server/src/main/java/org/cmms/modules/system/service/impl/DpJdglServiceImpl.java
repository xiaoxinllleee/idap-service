package org.cmms.modules.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.mapper.DpJdglMapper;
import org.cmms.modules.system.model.DpJdglTreeModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;
import org.cmms.modules.system.service.IDpJdglService;
import org.cmms.modules.system.util.FindsDpjdChildrenUtil;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 绩效自动任务节点管理
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Service

public class DpJdglServiceImpl extends ServiceImpl<DpJdglMapper, DpJdgl> implements IDpJdglService {
    @Override
    public List<DpJdglTreeModel> queryTreeList() {
        LambdaQueryWrapper<DpJdgl> query = new LambdaQueryWrapper<DpJdgl>();
        query.orderByAsc(DpJdgl::getXh);
        List<DpJdgl> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<DpJdglTreeModel> listResult = FindsDpjdChildrenUtil.wrapTreeDataToTreeList(list);
        return listResult;
    }


    /**
     * <p>
     * 根据关键字搜索相关的部门数据
     * </p>
     */
    @Override
    public List<DpJdglTreeModel> searhBy(String keyWord) {
        LambdaQueryWrapper<DpJdgl> query = new LambdaQueryWrapper<DpJdgl>();
        query.like(DpJdgl::getJdmc, keyWord);
        //update-begin--Author:huangzhilin  Date:20140417 for：[bugfree号]组织机构搜索回显优化--------------------
        DpJdglTreeModel model = new DpJdglTreeModel();
        List<DpJdgl> dpJdglList = this.list(query);
        List<DpJdglTreeModel> newList = new ArrayList<>();
        if(dpJdglList.size() > 0) {
            for(DpJdgl dpjd : dpJdglList) {
                model = new DpJdglTreeModel(dpjd);
                model.setChildren(null);
                //update-end--Author:huangzhilin  Date:20140417 for：[bugfree号]组织机构搜索功回显优化----------------------
                newList.add(model);
            }
            return newList;
        }
        return null;
    }
}
