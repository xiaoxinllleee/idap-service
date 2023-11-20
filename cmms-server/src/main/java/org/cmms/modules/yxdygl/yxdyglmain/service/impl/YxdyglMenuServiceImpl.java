package org.cmms.modules.yxdygl.yxdyglmain.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.constant.CacheConstant;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;
import org.cmms.modules.yxdygl.yxdyglmain.mapper.YxdyglMenuMapper;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 营销单元菜单
 * @Author: jeecg-boot
 * @Date:   2021-11-16
 * @Version: V1.0
 */
@Service
public class YxdyglMenuServiceImpl extends ServiceImpl<YxdyglMenuMapper, YxdyglMenu> implements IYxdyglMenuService {

    @Override
    public List<YxdyglMenu> listTree(String khjl) {
        List<YxdyglMenu> yxdyglMenus = null;

        if (StringUtils.isNotBlank(khjl)){
            yxdyglMenus = baseMapper.getKhjlList(khjl);
        }else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.orderByAsc("menu_level");
            yxdyglMenus = baseMapper.selectList(queryWrapper);
        }
        List<YxdyglMenu> tree = new ArrayList<>();

        for (int i = 0; i < yxdyglMenus.size(); i++) {

            /*if (yxdyglMenu.getMenuType().equals("0")){
                yxdyglMenu.setIsLeaf(false);
            }else {
                yxdyglMenu.setIsLeaf(true);
            }*/
            //找到根节点 查询排序后 直接取第一个为根
            YxdyglMenu yxdyglMenu = yxdyglMenus.get(i);
            if (i == 0) {
                tree.add(yxdyglMenu);
            }
            List<YxdyglMenu> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (YxdyglMenu node : yxdyglMenus) {
                if (node.getParentId().equals(yxdyglMenu.getId())) {
                    children.add(node);
                }
            }
            yxdyglMenu.setChildren(children);
        }

        return tree;
    }
}
