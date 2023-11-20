package org.cmms.modules.khlc.csgl.csml.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;
import org.cmms.modules.khlc.csgl.csml.mapper.PmaFParamMenuMapper;
import org.cmms.modules.khlc.csgl.csml.model.PmaFParamMenuTreeModel;
import org.cmms.modules.khlc.csgl.csml.service.IPmaFParamMenuService;
import org.cmms.modules.util.PmaFParamMenuChildrenUtil;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 参数目录
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Service

public class PmaFParamMenuServiceImpl extends ServiceImpl<PmaFParamMenuMapper, PmaFParamMenu> implements IPmaFParamMenuService {
    @Override
    public List<PmaFParamMenuTreeModel> queryTreeList() {
        LambdaQueryWrapper<PmaFParamMenu> query = new LambdaQueryWrapper<PmaFParamMenu>();
        query.orderByAsc(PmaFParamMenu::getCreateTime);
        List<PmaFParamMenu> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<PmaFParamMenuTreeModel> listResult = PmaFParamMenuChildrenUtil.wrapTreeDataToTreeList(list);
        return listResult;
    }
}
