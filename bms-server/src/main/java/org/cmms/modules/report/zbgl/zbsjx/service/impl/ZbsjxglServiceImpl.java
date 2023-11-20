package org.cmms.modules.report.zbgl.zbsjx.service.impl;

import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import org.cmms.modules.report.zbgl.zbsjx.mapper.ZbsjxglMapper;
import org.cmms.modules.report.zbgl.zbsjx.service.IZbsjxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 指标数据项管理
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Service
public class ZbsjxglServiceImpl extends ServiceImpl<ZbsjxglMapper, Zbsjxgl> implements IZbsjxglService {

    @Override
    public List<Zbsjxgl> getListByQydm(String qydm,String zblx, String zbwd, String zbid) {
        return baseMapper.getListByQydm(qydm,zblx, zbwd,zbid);
    }
}
