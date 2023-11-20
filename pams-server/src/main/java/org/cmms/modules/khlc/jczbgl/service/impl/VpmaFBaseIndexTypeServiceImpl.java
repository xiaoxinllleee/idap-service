package org.cmms.modules.khlc.jczbgl.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.template.utility.StringUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;
import org.cmms.modules.khlc.jczbgl.mapper.VpmaFBaseIndexTypeMapper;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.jczbgl.service.IVpmaFBaseIndexTypeService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.khlc.zbljgl.service.IErpBasSjxAreaService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

/**
 * @Description: 基础指标树包含指标
 * @Author: jeecg-boot
 * @Date:   2021-01-26
 * @Version: V1.0
 */
@Service

public class VpmaFBaseIndexTypeServiceImpl extends ServiceImpl<VpmaFBaseIndexTypeMapper, VpmaFBaseIndexType> implements IVpmaFBaseIndexTypeService {
    @Autowired
    private IErpBasZbkService erpBasZbkService;
    @Autowired
    private IErpBasSjxAreaService erpBasSjxAreaService;
    @Autowired
    private ISysDictService sysDictService;
    @Override
    public List<VpmaFBaseIndexType> listTree() {
        List<VpmaFBaseIndexType> PmaFBaseIndexTypes =  baseMapper.selectList(null);

        List<VpmaFBaseIndexType> tree = new ArrayList<>();

        for (VpmaFBaseIndexType user : PmaFBaseIndexTypes) {
            if (!user.getDirType().equals("3")){
                user.setIsLeaf(false);
            }else {
                user.setIsLeaf(true);
            }
            //找到根节点
            if (user.getParentId() == null || user.getParentId().equals("0")|| user.getParentId().equals("")) {
                tree.add(user);
            }
            List<VpmaFBaseIndexType> children = new ArrayList<>();
            //再次遍历list，找到user的子节点
            for (VpmaFBaseIndexType node : PmaFBaseIndexTypes) {
                if (node.getParentId().equals(user.getId())) {
                    children.add(node);
                }
            }
            user.setChild(children);
        }
        return tree;
    }

    @Override
    public JSONArray queryTreeList(Integer khfs,Integer zblx) {
       /* LambdaQueryWrapper<VpmaFBaseIndexType> query = new LambdaQueryWrapper<VpmaFBaseIndexType>();
        //query.eq(HrBasOrganization::getQybz, 1d);
        List<VpmaFBaseIndexType> list = this.list(query);

        // 调用wrapTreeDataToTreeList方法生成树状数据
        List<JczbsTreeModel> listResult = FindsJczbChildrenUtil.wrapTreeDataToTreeList(list);*/

        QueryWrapper<ErpBasSjxArea> queryWrapper=new QueryWrapper();
        queryWrapper.eq("khfs",khfs);
        queryWrapper.eq("zblx",zblx);
        queryWrapper.eq("sfqy","1");
        queryWrapper.orderByAsc("zbid");
        JSONArray ja = new JSONArray();
        JSONObject jo1 = new JSONObject();
        jo1.put("title","指标库");
        jo1.put("key", 0);
        jo1.put("disabled", true);
        JSONArray ja1 = new JSONArray();
        List<ErpBasSjxArea> list1 = erpBasSjxAreaService.list(queryWrapper);
        for(ErpBasSjxArea zbk:list1){
            JSONObject jo = new JSONObject();
            jo.put("key", zbk.getZbid());
            QueryWrapper queryWrapper1=new QueryWrapper();
            queryWrapper1.eq("zbid",zbk.getZbid());
            ErpBasZbk one = erpBasZbkService.getOne(queryWrapper1);
            String zbmc=one.getZbmc();
            String zbwd = sysDictService.queryDictTextByKey("zbwd", zbk.getZbwd());
            jo.put("title", zbk.getZbid()+"("+zbmc+")"+"_"+zbwd);
            jo.put("children",new JSONArray());
            ja1.add(jo);
        }
        jo1.put("children",ja1);
        ja.add(jo1);



        return ja;
    }
}
