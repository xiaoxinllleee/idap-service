package org.cmms.modules.xdgl.grdkgl.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.mapper.*;
import org.cmms.modules.xdgl.grdkgl.service.IGrdksplcService;
import org.cmms.modules.xdgl.grdkgl.vo.GrdkglPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人贷款审批流程
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Service
public class GrdksplcServiceImpl extends ServiceImpl<GrdksplcMapper, Grdksplc> implements IGrdksplcService {
    @Autowired
    private GrdkBzxxMapper bzxxMapper;
    @Autowired
    private GrdkDbxxMapper dbxxMapper;

    public void updateMain(GrdkglPage grdkglPage, List<GrdkBzxx> bzxxList, List<GrdkDbxx> dbxxList) {
        //2.子表数据重新插入

        bzxxMapper.deleteByMainId(grdkglPage.getZjhm());
        dbxxMapper.deleteByMainId(grdkglPage.getZjhm());
        for(GrdkBzxx entity:bzxxList) {
            //外键设置
            entity.setZjhm(grdkglPage.getZjhm());
            entity.setKhmc(grdkglPage.getKhmc());
            entity.setSskhjl(grdkglPage.getSskhjl());
            bzxxMapper.insert(entity);
        }
        for(GrdkDbxx entity:dbxxList) {
            //外键设置
            entity.setZjhm(grdkglPage.getZjhm());
            entity.setKhmc(grdkglPage.getKhmc());
            entity.setSskhjl(grdkglPage.getSskhjl());
            dbxxMapper.insert(entity);
        }
    }


}
