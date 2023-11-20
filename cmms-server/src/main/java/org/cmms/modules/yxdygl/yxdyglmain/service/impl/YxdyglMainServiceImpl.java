package org.cmms.modules.yxdygl.yxdyglmain.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;
import org.cmms.modules.yxdygl.yxdyglmain.mapper.YxdyglMainMapper;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 营销单元管理
 * @Author: jeecg-boot
 * @Date:   2021-11-15
 * @Version: V1.0
 */
@Service
public class YxdyglMainServiceImpl extends ServiceImpl<YxdyglMainMapper, YxdyglMain> implements IYxdyglMainService {
    @Autowired
    private YxdyglMainMapper yxdyglMainMapper;

    @Override
    public List<YxdyglMain> getWgxxTreeDateBySskhjl(String sskhjl, int maxLevel, String wgxz, String wgxzType,
                                                    String disableSelect,String minLevel,String zzbz) {
        List<YxdyglMain> yxdyglMains = yxdyglMainMapper.getWgxxTreeDateBySskhjl(sskhjl, wgxz, wgxzType,zzbz);

        List<YxdyglMain> yxdyglMainResults = new ArrayList<>();
        List<String> wgbhList = new ArrayList<>();
        //获取客户经理权限网格与其所有的上级网格
        for (int i = 0; i < yxdyglMains.size(); i++) {
            YxdyglMain yxdyglMain = yxdyglMains.get(i);
            if (StringUtils.isNotEmpty(yxdyglMain.getQxwgbh())) {
                String wgbhShow = yxdyglMain.getWgbhShow();
                String[] wgbhStrs = wgbhShow.split("-");
                for (int j = 0; j < wgbhStrs.length; j++) {
                    String wgbh = wgbhStrs[j];
                    if (!wgbhList.contains(wgbh)) {
                        wgbhList.add(wgbh);
                    }
                }
            }
        }

        yxdyglMainResults = yxdyglMains.stream().filter(item -> wgbhList.contains(item.getWgbh())).collect(Collectors.toList());
        //过滤之前的最下级level
        String completeLowestLevel =  null;
        if (CollUtil.isNotEmpty(yxdyglMainResults)){
            completeLowestLevel = yxdyglMainResults.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        }
        yxdyglMainResults = yxdyglMainResults.stream().filter(item -> Integer.parseInt(item.getLevel()) <= maxLevel).collect(Collectors.toList());

        //最下级的level值，当前level等于最下级的level时才能选择
        String  lowestLevel = null;
        if (CollUtil.isNotEmpty(yxdyglMainResults)){
              lowestLevel = yxdyglMainResults.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        }
        List<YxdyglMain> tree = new ArrayList<>();

        for (int i = 0; i < yxdyglMainResults.size(); i++) {
            //找到根节点 查询排序后 直接取第一个为根
            YxdyglMain yxdyglMain = yxdyglMainResults.get(i);
            if("1".equals(disableSelect)) {
                if (StringUtils.isNotBlank(minLevel)){
                    yxdyglMain.setDisabled(minLevel.compareTo(yxdyglMain.getLevel()) >= 0 );
                }else {
                    yxdyglMain.setDisabled(!completeLowestLevel.equals(yxdyglMain.getLevel()));
                }

            }
            int level = Integer.parseInt(yxdyglMain.getLevel());
            if ("1".equals(yxdyglMain.getLevel())) {
                tree.add(yxdyglMain);
            }
            List<YxdyglMain> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (YxdyglMain node : yxdyglMainResults) {
                if (node.getParentId().equals(yxdyglMain.getId())) {
                    if(node.getLevel().equals(completeLowestLevel)) {
                        node.setIsLeaf(true);
                    } else {
                        node.setIsLeaf(false);
                    }
                    children.add(node);
                }
            }
            yxdyglMain.setChildren(children);
        }
        return tree;
    }


    @Override
    public List<YxdyglMain> getWgxxTreeDateAll(int maxLevel, String wgxz, String wgxzType, String disableSelect) {
        List<YxdyglMain> yxdyglMains = yxdyglMainMapper.getWgxxTreeDateAll(wgxz, wgxzType);

        List<YxdyglMain> yxdyglMainResults = new ArrayList<>();
        List<String> wgbhList = new ArrayList<>();
        //获取客户经理权限网格与其所有的上级网格
        for (int i = 0; i < yxdyglMains.size(); i++) {
            YxdyglMain yxdyglMain = yxdyglMains.get(i);
            if (StringUtils.isNotEmpty(yxdyglMain.getQxwgbh())) {
                String wgbhShow = yxdyglMain.getWgbhShow();
                String[] wgbhStrs = wgbhShow.split("-");
                for (int j = 0; j < wgbhStrs.length; j++) {
                    String wgbh = wgbhStrs[j];
                    if (!wgbhList.contains(wgbh)) {
                        wgbhList.add(wgbh);
                    }
                }
            }
        }

        yxdyglMainResults = yxdyglMains.stream().filter(item -> wgbhList.contains(item.getWgbh())).collect(Collectors.toList());


        yxdyglMainResults = yxdyglMainResults.stream().filter(item -> Integer.parseInt(item.getLevel()) <= maxLevel).collect(Collectors.toList());

        //最下级的level值，当前level等于最下级的level时才能选择
        String  lowestLevel = null;
        if (CollUtil.isNotEmpty(yxdyglMainResults)){
            lowestLevel = yxdyglMainResults.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        }
        List<YxdyglMain> tree = new ArrayList<>();

        for (int i = 0; i < yxdyglMainResults.size(); i++) {
            //找到根节点 查询排序后 直接取第一个为根
            YxdyglMain yxdyglMain = yxdyglMainResults.get(i);
            if("1".equals(disableSelect)) {
                yxdyglMain.setDisabled(!lowestLevel.equals(yxdyglMain.getLevel()));
            }
            int level = Integer.parseInt(yxdyglMain.getLevel());
            if ("1".equals(yxdyglMain.getLevel())) {
                tree.add(yxdyglMain);
            }
            List<YxdyglMain> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (YxdyglMain node : yxdyglMainResults) {
                if (node.getParentId().equals(yxdyglMain.getId())) {
                    children.add(node);
                }
            }
            yxdyglMain.setChildren(children);
        }
        return tree;
    }

    @Override
    public List<YxdyglMain> listTree(String khjl, String previousLevel, String pId, String queryOrganize) {
        List<YxdyglMain> yxdyglMains = null;

        if(StringUtils.isEmpty(pId)) {
            pId = "0";
        }

        if (StringUtils.isNotBlank(khjl)){
            //yxdyglMenus = baseMapper.getKhjlList(khjl);
        }else {
            yxdyglMains = yxdyglMainMapper.getPreviousLevel(previousLevel, pId, queryOrganize);
        }
        List<YxdyglMain> tree = new ArrayList<>();
        String maxLevel = yxdyglMains.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        for (int i = 0; i < yxdyglMains.size(); i++) {

            /*if (yxdyglMenu.getMenuType().equals("0")){
                yxdyglMenu.setIsLeaf(false);
            }else {
                yxdyglMenu.setIsLeaf(true);
            }*/
            //找到根节点 查询排序后 直接取第一个为根
            YxdyglMain yxdyglMain = yxdyglMains.get(i);
            if ("1".equals(yxdyglMain.getLevel())) {
                tree.add(yxdyglMain);
            }
            List<YxdyglMain> children = new ArrayList<>();
            if (!maxLevel.equals(yxdyglMain.getLevel())) {
                //再次遍历list，找到user的子节点
                for (YxdyglMain node : yxdyglMains) {
                    if (node.getParentId().equals(yxdyglMain.getId())) {
                        children.add(node);
                    }
                }
            }
            yxdyglMain.setChildren(children);
        }
        return tree;
    }

    @Override
    public List<YxdyglMain> listTreeByKhjl(String khjl, String pId) {
//        List<YxdyglMain> yxdyglMains = null;

        if (StringUtils.isBlank(khjl)){
            return null;
        }

        List<YxdyglMain> yxdyglMainList = yxdyglMainMapper.getKhjlList(khjl);
        List<YxdyglMain> yxdyglMainResults = new ArrayList<>();
        List<String> wgbhList = new ArrayList<>();
        //获取客户经理权限网格与其所有的上级网格
        for (int i = 0; i < yxdyglMainList.size(); i++) {
            YxdyglMain yxdyglMain = yxdyglMainList.get(i);
            if (StringUtils.isNotEmpty(yxdyglMain.getQxwgbh())) {
                String wgbhShow = yxdyglMain.getWgbhShow();
                String[] wgbhStrs = wgbhShow.split("-");
                for (int j = 0; j < wgbhStrs.length; j++) {
                    String wgbh = wgbhStrs[j];
                    if (!wgbhList.contains(wgbh)) {
                        wgbhList.add(wgbh);
                    }
                }
            }
        }
        //必须是客户经理有权限的网格或者上级网格数据
        yxdyglMainResults = yxdyglMainList.stream().filter(item -> wgbhList.contains(item.getWgbh()) && item.getParentId().equals(pId)).collect(Collectors.toList());
        return yxdyglMainResults;


    }

    @Override
    public List<YxdyglMain> listTreeAll(String pId) {
        List<YxdyglMain> yxdyglMains = null;

        return yxdyglMainMapper.getAllList(pId);
    }

    @Override
    public String getZhenByNoRoot(String wgbh,String level) {
        if ("2".equals(level))
            return wgbh;
        //由于网格性质不能确定到镇  我们就选择是村上面一级的
        while (true){
            List<YxdyglMain> wgbhParentWgbh = baseMapper.getWgbhParentWgbh(wgbh);
            if (CollUtil.isNotEmpty(wgbhParentWgbh)){
                 YxdyglMain yxdyglMain = wgbhParentWgbh.get(0);
                 if ("7".equals(yxdyglMain.getWgxz())){
                     return null;
                 }
                 if ("1".equals(yxdyglMain.getWgxz())){
                     return yxdyglMain.getWgbh();
                 }else {
                     wgbh = yxdyglMain.getWgbh();
                 }
            }
        }
    }

    @Override
    public List<YxdyglMain> getWgbhParentWgbh(String wgbh) {
        return baseMapper.getWgbhParentWgbh(wgbh);
    }

    @Override
    public List<YxdyglMain> getByQydm(String qydm) {
        try {
            return baseMapper.getByQydm("yxdygl_main"+qydm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<YxdyglMain> getByQydm(String qydm, YxdyglMain yxdyglMain) {
        try {
            return baseMapper.getByQydmAndDao("yxdygl_main"+qydm,yxdyglMain);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<YxdyglMain> getWgxxTreeDate(Integer maxLevel,String disableSelect,String minLevel) {

        List<YxdyglMain> yxdyglMains = yxdyglMainMapper.getAll();

        List<YxdyglMain> yxdyglMainResults = new ArrayList<>();
        List<String> wgbhList = new ArrayList<>();
        //获取客户经理权限网格与其所有的上级网格
        for (int i = 0; i < yxdyglMains.size(); i++) {
            YxdyglMain yxdyglMain = yxdyglMains.get(i);
            if (StringUtils.isNotEmpty(yxdyglMain.getQxwgbh())) {
                String wgbhShow = yxdyglMain.getWgbhShow();
                String[] wgbhStrs = wgbhShow.split("-");
                for (int j = 0; j < wgbhStrs.length; j++) {
                    String wgbh = wgbhStrs[j];
                    if (!wgbhList.contains(wgbh)) {
                        wgbhList.add(wgbh);
                    }
                }
            }
        }

        yxdyglMainResults = yxdyglMains.stream().filter(item -> wgbhList.contains(item.getWgbh())).collect(Collectors.toList());
        //过滤之前的最下级level
        String completeLowestLevel =  null;
        if (CollUtil.isNotEmpty(yxdyglMainResults)){
            completeLowestLevel = yxdyglMainResults.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        }
        yxdyglMainResults = yxdyglMainResults.stream().filter(item -> Integer.parseInt(item.getLevel()) <= maxLevel).collect(Collectors.toList());

        //最下级的level值，当前level等于最下级的level时才能选择
        String  lowestLevel = null;
        if (CollUtil.isNotEmpty(yxdyglMainResults)){
            lowestLevel = yxdyglMainResults.stream().max(Comparator.comparing(YxdyglMain::getLevel)).get().getLevel();
        }
        List<YxdyglMain> tree = new ArrayList<>();

        for (int i = 0; i < yxdyglMainResults.size(); i++) {
            //找到根节点 查询排序后 直接取第一个为根
            YxdyglMain yxdyglMain = yxdyglMainResults.get(i);
            if("1".equals(disableSelect)) {
                if (StringUtils.isNotBlank(minLevel)){
                    yxdyglMain.setDisabled(minLevel.compareTo(yxdyglMain.getLevel()) >= 0 );
                }else {
                    yxdyglMain.setDisabled(!completeLowestLevel.equals(yxdyglMain.getLevel()));
                }

            }
            int level = Integer.parseInt(yxdyglMain.getLevel());
            if ("1".equals(yxdyglMain.getLevel())) {
                tree.add(yxdyglMain);
            }
            List<YxdyglMain> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (YxdyglMain node : yxdyglMainResults) {
                if (node.getParentId().equals(yxdyglMain.getId())) {
                    if(node.getLevel().equals(completeLowestLevel)) {
                        node.setIsLeaf(true);
                    } else {
                        node.setIsLeaf(false);
                    }
                    children.add(node);
                }
            }
            yxdyglMain.setChildren(children);
        }
        return tree;
    }

}
