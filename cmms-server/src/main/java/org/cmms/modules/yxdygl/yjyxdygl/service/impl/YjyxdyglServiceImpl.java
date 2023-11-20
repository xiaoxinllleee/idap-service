package org.cmms.modules.yxdygl.yjyxdygl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.cmms.modules.yxdygl.yxdyfjxx.mapper.YxdyfjxxMapper;
import org.cmms.modules.yxdygl.yjyxdygl.service.IYjyxdyglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 一级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class YjyxdyglServiceImpl extends ServiceImpl<YjyxdyglMapper, Yjyxdygl> implements IYjyxdyglService {

    @Autowired
    private YjyxdyglMapper yjyxdyglMapper;
    @Autowired
    private YxdyfjxxMapper yxdyfjxxMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Override
    public Yjyxdygl queryDataByDybh(Map<String, String> sql) {
        return yjyxdyglMapper.queryDataByDybh(sql);
    }

    @Override
    public List<Yjyxdygl> queryDataByUser(String username) {
        return yjyxdyglMapper.queryDataByUser(username);
    }

    @Override
    @Transactional
    public void saveMain(Yjyxdygl yjyxdygl, List<Yxdyfjxx> yxdyfjxxList) {
        yjyxdyglMapper.insert(yjyxdygl);
        for(Yxdyfjxx entity:yxdyfjxxList) {
            //外键设置
            entity.setDybh(yjyxdygl.getDybh());
            yxdyfjxxMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void updateMain(Yjyxdygl yjyxdygl,List<Yxdyfjxx> yxdyfjxxList) {
        yjyxdyglMapper.updateById(yjyxdygl);

        //1.先删除子表数据
        //yxdyfjxxMapper.deleteByMainId(yjyxdygl.getId());

        for(Yxdyfjxx entity : yxdyfjxxList) {
            if (StringUtils.isEmpty(entity.getId())) {
                //新增的图片
                //外键设置
                entity.setDybh(yjyxdygl.getDybh());
                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                entity.setScr(loginUser.getUsername());
                String fwlj = entity.getFwlj();
                String fjlj = uploadpath + File.separator + fwlj;
                File file = new File(fjlj);
                entity.setFjmc(file.getName());
                entity.setFjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                entity.setFjlj(fjlj);
                entity.setScsj(DateUtils.getDate());
                yxdyfjxxMapper.insert(entity);
            } else {
                //删除
//                if (entity.getLrbz().equalsIgnoreCase("3")) {
//                    khhffjxxMapper.deleteById(entity.getId());
//                }
            }
        }

        //2.子表数据重新插入
//        for(Yxdyfjxx entity:yxdyfjxxList) {
//            //外键设置
//            entity.setDybh(yjyxdygl.getDybh());
//            yxdyfjxxMapper.insert(entity);
//        }
    }

    @Override
    @Transactional
    public void delMain(String id) {
        yxdyfjxxMapper.deleteByMainId(id);
        yjyxdyglMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id:idList) {
            yxdyfjxxMapper.deleteByMainId(id.toString());
            yjyxdyglMapper.deleteById(id);
        }
    }
}
