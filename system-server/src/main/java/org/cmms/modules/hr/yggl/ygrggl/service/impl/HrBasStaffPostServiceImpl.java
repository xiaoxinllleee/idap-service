package org.cmms.modules.hr.yggl.ygrggl.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;
import org.cmms.modules.hr.yggl.ygrggl.mapper.HrBasStaffPostMapper;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 员工入岗管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Service
public class HrBasStaffPostServiceImpl extends ServiceImpl<HrBasStaffPostMapper, HrBasStaffPost> implements IHrBasStaffPostService {

    @Override
    public boolean ifExistByYgghAndRgrqAndLgrq(HrBasStaffPost hrBasStaffPost) {
        return baseMapper.ifExistByYgghAndRgrqAndLgrq(hrBasStaffPost)>0?false:true;
    }

    @Override
    public Long getId() {
        return baseMapper.getId();
    }

    @Override
    public HrBasStaffPost getByNowDate(String yggh) {
        List<HrBasStaffPost> byNowDate = baseMapper.getByNowDate(yggh);
        if (CollUtil.isNotEmpty(byNowDate))
            return byNowDate.get(0);
        return null;
    }


    @Override
    public List<HrBasStaffPostZhzgry> getZhzgry(String fpyf, String zzbz) {
        return baseMapper.getZhzgry(fpyf,zzbz);
    }

    @Override
    public HrBasStaffPost getStaffPostInfoBySprq(String yggh, String sprq) {
        return baseMapper.getStaffPostInfoBySprq(yggh,sprq);
    }


}
