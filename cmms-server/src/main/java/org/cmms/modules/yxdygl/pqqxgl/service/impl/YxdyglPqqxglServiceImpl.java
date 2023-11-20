package org.cmms.modules.yxdygl.pqqxgl.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.base.service.DictService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxglTree;
import org.cmms.modules.yxdygl.pqqxgl.mapper.YxdyglPqqxglMapper;
import org.cmms.modules.yxdygl.pqqxgl.service.IYxdyglPqqxglService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.mapper.YxdyglMainMapper;
import org.cmms.modules.yxdygl.yxdyglmain.service.impl.YxdyglMainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date:   2021-11-17
 * @Version: V1.0
 */
@Service
@Slf4j
public class YxdyglPqqxglServiceImpl extends ServiceImpl<YxdyglPqqxglMapper, YxdyglPqqxgl> implements IYxdyglPqqxglService {

    @Autowired
    ISysDictService sysDictService;

    @Autowired
    YxdyglMainMapper yxdyglMainMapper;

    @Override
    public IPage<YxdyglPqqxglTree> getTreeList(IPage page, YxdyglPqqxgl yxdyglPqqxgl) {
        int getcount = baseMapper.getcount();
        long size = page.getSize();
        long current = page.getCurrent();
        long start =  (current - 1) * size;
        long end =  current * size;
        if (StringUtils.isNotBlank(yxdyglPqqxgl.getMenuId())){
            /*LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.like(YxdyglMain::getWgmc,yxdyglPqqxgl.getMenuId());
            List<YxdyglMain> yxdyglMains = yxdyglMainMapper.selectList(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(yxdyglMains)){
                yxdyglPqqxgl.setMenuId(yxdyglMains.get(0).getId());
            }*/
            yxdyglPqqxgl.setMenuId("%"+yxdyglPqqxgl.getMenuId()+"%");
        }


        List<YxdyglPqqxglTree> byPage = baseMapper.getByPage(start, end,yxdyglPqqxgl);
        List<YxdyglPqqxglTree> result = new ArrayList<>();
        YxdyglPqqxglTree root = null;
        List<YxdyglPqqxglTree> childs = new ArrayList<>();
        for (int i = 0; i < byPage.size(); i++) {
             YxdyglPqqxglTree yxdyglPqqxglTree = byPage.get(i);
             if (StringUtils.isNotBlank(yxdyglPqqxglTree.getKhjl())){
                 String s = sysDictService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",yxdyglPqqxglTree.getKhjl());
                 yxdyglPqqxglTree.setKhjlVal(s);
             }
             if (StringUtils.isNotBlank(yxdyglPqqxglTree.getSfzkhjl())){
                  String sfzkhjl = sysDictService.queryDictTextByKey("sfzkhjl", yxdyglPqqxglTree.getSfzkhjl());
                  yxdyglPqqxglTree.setSfzkhjlVal(sfzkhjl);
             }
             /*if (yxdyglPqqxglTree.getSfzkhjl() == null || "1".equals(yxdyglPqqxglTree.getSfzkhjl())){
                 yxdyglPqqxglTree.setSfzkhjlVal("是");
             }else {
                 yxdyglPqqxglTree.setSfzkhjlVal("否");
             }*/
             if (StringUtils.isNotBlank(yxdyglPqqxglTree.getMenuId())){
                 String s = sysDictService.queryTableDictTextByKey("YXDYGL_MAIN","wgmc","id",yxdyglPqqxglTree.getMenuId());
                 yxdyglPqqxglTree.setMenuIdVal(s);
             }

            //先找每一个节点的根
            if (i==0){
                root = yxdyglPqqxglTree;
            }else {
                YxdyglPqqxglTree child = yxdyglPqqxglTree;
                if (root.getMenuId().equals(child.getMenuId())) {
                    childs.add(child);
                } else {
                    root.setChildren(childs);
                    result.add(root);
                    root = child;
                    childs = new ArrayList<>();
                }
                if (i == byPage.size() - 1){
                    root.setChildren(childs);
                    result.add(root);
                }
            }
        }

        IPage<YxdyglPqqxglTree> iPage = new Page<>();
        iPage.setRecords(result);
        iPage.setPages(page.getPages());
        iPage.setSize(page.getSize());
        iPage.setCurrent(page.getCurrent());
        iPage.setTotal(getcount);

        return iPage;
    }

    @Override
    public List<String> getMenuIdsByZkhjl() {
        return baseMapper.getMenuIdsByZkhjl();
    }


    @Override
    public List<String> getMenuIdsByKhjlgh(String yggh) {
        return baseMapper.getMenuIdsByKhjlgh(yggh);
    }
}
