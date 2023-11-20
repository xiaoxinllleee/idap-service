package org.cmms.modules.yxdygl.sjyxdygl.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.yxdygl.sjyxdygl.entity.EjyxdyglReuse;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.VSjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
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
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
@Service
public class SjyxdyglServiceImpl extends ServiceImpl<SjyxdyglMapper, Sjyxdygl> implements ISjyxdyglService {

    @Autowired
    private SjyxdyglMapper sjyxdyglMapper;
    @Autowired
    private YxdyfjxxMapper yxdyfjxxMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Override
    public List<EjyxdyglReuse> QuerySszhByYjyxdybh(String ejyxdybh) {
        return sjyxdyglMapper.QuerySszhByYjyxdybh(ejyxdybh);
    }

    @Override
    public Sjyxdygl queryDataByDybh(Map<String, String> sql) {
        return sjyxdyglMapper.queryDataByDybh(sql);
    }

    @Override
    public String queryDybhBySsyxdy(String ssyxdy) {
        return sjyxdyglMapper.queryDybhBySsyxdy(ssyxdy);
    }

    @Override
    public List<Sjyxdygl> queryDataByUser(String username) {
        return sjyxdyglMapper.queryDataByUser(username);
    }

    @Override
    @Transactional
    public void saveMain(Sjyxdygl ejyxdygl, List<Yxdyfjxx> yxdyfjxxList) {
        sjyxdyglMapper.insert(ejyxdygl);
        for(Yxdyfjxx entity:yxdyfjxxList) {
            //外键设置
            entity.setDybh(ejyxdygl.getDybh());
            yxdyfjxxMapper.insert(entity);
        }
    }

    @Override
    @Transactional
    public void updateMain(Sjyxdygl sjyxdygl, List<Yxdyfjxx> yxdyfjxxList) {
        sjyxdyglMapper.updateById(sjyxdygl);

        for(Yxdyfjxx entity : yxdyfjxxList) {
            if (StringUtils.isEmpty(entity.getId())) {
                //新增的图片
                //外键设置
                entity.setDybh(sjyxdygl.getDybh());
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
        sjyxdyglMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id:idList) {
            yxdyfjxxMapper.deleteByMainId(id.toString());
            sjyxdyglMapper.deleteById(id);
        }
    }
}
