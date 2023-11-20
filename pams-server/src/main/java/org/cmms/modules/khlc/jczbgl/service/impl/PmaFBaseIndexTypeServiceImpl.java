package org.cmms.modules.khlc.jczbgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khlc.jczbgl.entity.PmaFBaseIndexType;
import org.cmms.modules.khlc.jczbgl.mapper.PmaFBaseIndexTypeMapper;
import org.cmms.modules.khlc.jczbgl.service.IPmaFBaseIndexTypeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 基础指标类型表
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */
@Service

public class PmaFBaseIndexTypeServiceImpl extends ServiceImpl<PmaFBaseIndexTypeMapper, PmaFBaseIndexType> implements IPmaFBaseIndexTypeService {

    @Override
    public List<PmaFBaseIndexType> listTree(String dirType) {
        List<PmaFBaseIndexType> PmaFBaseIndexTypes = null;
        if (StringUtils.isNotBlank(dirType)){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("DIR_TYPE",dirType);
            PmaFBaseIndexTypes = baseMapper.selectList(queryWrapper);
        }else {
            PmaFBaseIndexTypes = baseMapper.selectList(null);
        }
        List<PmaFBaseIndexType> tree = new ArrayList<>();
        /*for (PmaFBaseIndexType PmaFBaseIndexTyp : PmaFBaseIndexTypes) {
            //找到根节点
            if (PmaFBaseIndexTyp.getParentId() == null || PmaFBaseIndexTyp.getParentId() == 0) {
                tree.add(findChildren(PmaFBaseIndexTyp, PmaFBaseIndexTypes));
            }
        }*/

        for (PmaFBaseIndexType user : PmaFBaseIndexTypes) {
            if (user.getDirType().equals("0")){
                user.setIsLeaf(false);
            }else {
                user.setIsLeaf(true);
            }
            //找到根节点
            if (user.getLevel0() == 0) {
                tree.add(user);
            }
            List<PmaFBaseIndexType> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (PmaFBaseIndexType node : PmaFBaseIndexTypes) {
                if (node.getParentId().equals(user.getId())) {
                    children.add(node);
                }
            }
            user.setChild(children);
        }
        return tree;
    }

    private PmaFBaseIndexType findChildren(PmaFBaseIndexType pmaFBaseIndexType, List<PmaFBaseIndexType> list) {
        List<PmaFBaseIndexType> children = new ArrayList<>();
        for (PmaFBaseIndexType node : list) {
            if (node.getParentId().equals(node.getId())) {
                //递归调用
                children.add(findChildren(node, list));
            }
        }
        pmaFBaseIndexType.setChild(children);
        return pmaFBaseIndexType;
    }

    @Override
    public int add(PmaFBaseIndexType pmaFBaseIndexType) {
        return 0;
    }

    @Override
    public List<Integer> listChildTree(int value) {
        return baseMapper.getAllChild(value);
    }


    }
