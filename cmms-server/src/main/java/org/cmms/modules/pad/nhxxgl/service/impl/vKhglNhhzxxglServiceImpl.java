package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.NhxqMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxglQuery;
import org.cmms.modules.pad.nhxxgl.mapper.KhglKhhmcxxPadMapper;
import org.cmms.modules.pad.nhxxgl.mapper.KhglNhhzxxglMapper;
import org.cmms.modules.pad.nhxxgl.mapper.vKhglNhhzxxglMapper;
import org.cmms.modules.pad.nhxxgl.service.IvKhglNhhzxxglService;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
@Service
public class vKhglNhhzxxglServiceImpl extends ServiceImpl<vKhglNhhzxxglMapper, vKhglNhhzxxgl> implements IvKhglNhhzxxglService {

    @Autowired
    private vKhglNhhzxxglMapper KhglNhhzxxgl1Mapper;

    @Autowired
    private NhxqMapper nhxqMapper;
    @Autowired
    private ITjfxcsszService tjfxcsszService;

    @Override
    public List<String> selectHzByCy(String lxfs,String yssxz,String yxzc,String zz,vKhglNhhzxxglQuery hzxx, HttpServletRequest req){
        QueryWrapper<Nhxq> nhxqQueryWrapper =new QueryWrapper<>();
        if (!StringUtils.isEmpty(hzxx.getHzxm())) {
            hzxx.setHzxm(hzxx.getHzxm().replaceAll("\\*", ""));
        }
        if (StringUtils.isEmpty(hzxx.getHzxm()) && StringUtils.isEmpty(hzxx.getHzzjhm())
                && StringUtils.isEmpty(lxfs)    &&StringUtils.isEmpty(yssxz)
                && StringUtils.isEmpty(yxzc) &&StringUtils.isEmpty(zz) ) {
            return null;
        }
        if (!StringUtils.isEmpty(hzxx.getHzxm())) {
            nhxqQueryWrapper.like("khmc", hzxx.getHzxm());
        }
        if (!StringUtils.isEmpty(lxfs)){
            nhxqQueryWrapper.like("sjhm", lxfs);
        }
        if(!StringUtils.isEmpty(hzxx.getHzzjhm())){
            nhxqQueryWrapper.eq("zjhm", hzxx.getHzzjhm());
        }
        if (!StringUtils.isEmpty(yssxz)){
            nhxqQueryWrapper.like("yssxz", yssxz);
        }
        if(!StringUtils.isEmpty(yxzc)){
            nhxqQueryWrapper.eq("yxzc", yxzc);
        }
        if (!StringUtils.isEmpty(zz)){
            nhxqQueryWrapper.like("zz", zz);
        }
        Long dateNum=nhxqMapper.selectCount(nhxqQueryWrapper);
        if (dateNum>1000){
            return null;
        }
        List<Nhxq> list = nhxqMapper.selectList(nhxqQueryWrapper);
        List<String> hhbmList = new ArrayList<>();
        for (Nhxq nhxq : list) {
            if (hhbmList.size() >= 1000) {
                break;
            }
            if (!StringUtils.isEmpty(nhxq.getHhbm())) {
                hhbmList.add(nhxq.getHhbm());
            }
        }
        return hhbmList;
    }

    @Override
    public void init(String hhbm, String zjhm, String yggh, String username, String zfrq){
        KhglNhhzxxgl1Mapper.init(hhbm, zjhm, yggh, username, zfrq);
    }
    @Override
    public void init1(String hhbm){
        KhglNhhzxxgl1Mapper.init1(hhbm);
    }

    @Override
    public void init2(String hhbm){
        KhglNhhzxxgl1Mapper.init2(hhbm);
    }

    @Override
    public Integer init3(String hhbm){
        return KhglNhhzxxgl1Mapper.init3(hhbm);
    }


    @Override
    public List<String> getLrryList() {
        return KhglNhhzxxgl1Mapper.getLrryList();
    }

    @Override
    public List<String> getPfrList() {
        return KhglNhhzxxgl1Mapper.getPfrList();
    }
}
