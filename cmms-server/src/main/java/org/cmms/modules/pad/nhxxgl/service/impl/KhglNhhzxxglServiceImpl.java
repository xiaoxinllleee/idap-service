package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.pad.nhxxgl.entity.DkxxVo;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.mapper.KhglNhhzxxglMapper;
import org.cmms.modules.pad.nhxxgl.mapper.KhglNhhzzllbMapper;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-15
 * @Version: V1.0
 */
@Service
public class KhglNhhzxxglServiceImpl extends ServiceImpl<KhglNhhzxxglMapper, KhglNhhzxxgl> implements IKhglNhhzxxglService {
    @Autowired
    private KhglNhhzxxglMapper khglNhhzxxglMapper;
    @Autowired
    private KhglNhhzzllbMapper khglNhhzzllbMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;



    @Override
    public void updateMain(KhglNhhzxxgl khglNhhzxxgl, List<KhglNhhzzllb> khglNhhzzllbs) {
        for(KhglNhhzzllb entity : khglNhhzzllbs) {
            if (StringUtils.isEmpty(entity.getZlbh())) {
                //新增的图片
                //外键设置
                entity.setHhbm(khglNhhzxxgl.getHhbm());
                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                entity.setScr(loginUser.getUsername());
                String fwlj = entity.getFwlj();
                String fjlj = uploadpath + File.separator + fwlj;
                File file = new File(fjlj);
                entity.setZlmc(file.getName());
                entity.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                entity.setFwlj(fwlj);
                entity.setZllj(fjlj);
                entity.setScsj(DateUtils.getDate());
                khglNhhzzllbMapper.insert(entity);
            } else {
                //删除
//                if (entity.getLrbz().equalsIgnoreCase("3")) {
//                    khhffjxxMapper.deleteById(entity.getId());
//                }
            }
        }
    }
    @Override
    public List<Nhbkbpyfsxx> selectpyxx(String hhbm){
        return  khglNhhzxxglMapper.selectpyxx(hhbm);
    }

    @Override
    public int syncYesNhhzxx() {
        return khglNhhzxxglMapper.syncYesNhhzxx();
    }
    @Override
    @Transactional
    public void updateKhlx(String hhbm,String newhhbm) {
        khglNhhzxxglMapper.updateKhlx(hhbm,newhhbm);
    }

    @Override
    public List<DkxxVo> getJtcyDkxxByZjhm(String zjhm) {
        return khglNhhzxxglMapper.getJtcyDkxxByZjhm(zjhm);
    }
}
