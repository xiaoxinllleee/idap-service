package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service.impl;

import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdsjxgl.entity.Pdsjxgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.mapper.ZbsjtzMapper;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service.IZbsjtzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 指标数据调整
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
@Service
public class ZbsjtzServiceImpl extends ServiceImpl<ZbsjtzMapper, Zbsjtz> implements IZbsjtzService {

    @Override
    public List<Khjljcsjsz> getListClaim(String pdzq, String pdrq, String zzbz, String yggh, String khjlbz, String ygxm) {
        return baseMapper.getListClaim(pdzq, pdrq, zzbz, yggh, khjlbz, ygxm);
    }

    @Override
    public List<Pdsjxgl> getListZbid(String sjxid, String sjxmc, String sjxwd) {
        return baseMapper.getListZbid(sjxid, sjxmc, sjxwd);
    }
}
