package org.cmms.modules.yxdygl.ejyxdygl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.mapper.EjyxdyglMapper;
import org.cmms.modules.yxdygl.ejyxdygl.service.IEjyxdyglService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyfjxx.mapper.YxdyfjxxMapper;
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
 * @Description: 二级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Service
public class EjyxdyglServiceImpl extends ServiceImpl<EjyxdyglMapper, Ejyxdygl> implements IEjyxdyglService {

    @Autowired
    private EjyxdyglMapper ejyxdyglMapper;
    @Autowired
    private YxdyfjxxMapper yxdyfjxxMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Override
    public Ejyxdygl queryDataByDybh(Map<String, String> sql) {
        return ejyxdyglMapper.queryDataByDybh(sql);
    }

    @Override
    public List<Ejyxdygl> queryDataByUser(String username) {
        return ejyxdyglMapper.queryDataByUser(username);
    }

    @Override
    public String queryDybhBySsyxdy(String ssyxdy) {
        return ejyxdyglMapper.queryDybhBySsyxdy(ssyxdy);
    }

    @Override
    @Transactional
    public void saveMain(Ejyxdygl ejyxdygl, List<Yxdyfjxx> yxdyfjxxList) {
        ejyxdyglMapper.insert(ejyxdygl);
        for(Yxdyfjxx entity:yxdyfjxxList) {
            //外键设置
            entity.setDybh(ejyxdygl.getDybh());
            yxdyfjxxMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void updateMain(Ejyxdygl ejyxdygl, List<Yxdyfjxx> yxdyfjxxList) {
        ejyxdyglMapper.updateById(ejyxdygl);

        for(Yxdyfjxx entity : yxdyfjxxList) {
            if (StringUtils.isEmpty(entity.getId())) {
                //新增的图片
                //外键设置
                entity.setDybh(ejyxdygl.getDybh());
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
    }

    @Override
    @Transactional
    public void delMain(String id) {
        yxdyfjxxMapper.deleteByMainId(id);
        ejyxdyglMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id:idList) {
            yxdyfjxxMapper.deleteByMainId(id.toString());
            ejyxdyglMapper.deleteById(id);
        }
    }

}
