package org.cmms.modules.khlc.khfagl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;
import org.cmms.modules.khlc.khfagl.mapper.PmaASchemeMenuMapper;
import org.cmms.modules.khlc.khfagl.model.SchemeMenulTreeModel;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemeMenuService;
import org.cmms.modules.util.FindsSchenmeMenuChildrenUtil;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 考核方案目录
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Service

public class PmaASchemeMenuServiceImpl extends ServiceImpl<PmaASchemeMenuMapper, PmaASchemeMenu> implements IPmaASchemeMenuService {



    @Override
    public List<SchemeMenulTreeModel> queryTreeList() {
        LambdaQueryWrapper<PmaASchemeMenu> query = new LambdaQueryWrapper<PmaASchemeMenu>();
        query.orderByAsc(PmaASchemeMenu::getCreateTime);
        List<PmaASchemeMenu> list = this.list(query);
        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<SchemeMenulTreeModel> listResult = FindsSchenmeMenuChildrenUtil.wrapTreeDataToTreeList(list);
        return listResult;
    }
}
