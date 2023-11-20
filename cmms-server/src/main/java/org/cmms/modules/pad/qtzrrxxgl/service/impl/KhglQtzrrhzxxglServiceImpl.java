package org.cmms.modules.pad.qtzrrxxgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhzxxgl;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhzzllb;
import org.cmms.modules.pad.qtzrrxxgl.mapper.KhglQtzrrhzxxglMapper;
import org.cmms.modules.pad.qtzrrxxgl.mapper.KhglQtzrrhzzllbMapper;
import org.cmms.modules.pad.qtzrrxxgl.service.IKhglQtzrrhzxxglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
public class KhglQtzrrhzxxglServiceImpl extends ServiceImpl<KhglQtzrrhzxxglMapper, KhglQtzrrhzxxgl> implements IKhglQtzrrhzxxglService {
    @Autowired
    private KhglQtzrrhzxxglMapper khglQtzrrhzxxglMapper;
    @Autowired
    private KhglQtzrrhzzllbMapper khglQtzrrhzzllbMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;



    @Override
    public void updateMain(KhglQtzrrhzxxgl khglQtzrrhzxxgl, List<KhglQtzrrhzzllb> khglQtzrrhzzllbs) {
        for(KhglQtzrrhzzllb entity : khglQtzrrhzzllbs) {
            if (StringUtils.isEmpty(entity.getZlbh())) {
                //新增的图片
                //外键设置
                entity.setHhbm(khglQtzrrhzxxgl.getHhbm());
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
                khglQtzrrhzzllbMapper.insert(entity);
            } else {
                //删除
//                if (entity.getLrbz().equalsIgnoreCase("3")) {
//                    khhffjxxMapper.deleteById(entity.getId());
//                }
            }
        }
    }
}
