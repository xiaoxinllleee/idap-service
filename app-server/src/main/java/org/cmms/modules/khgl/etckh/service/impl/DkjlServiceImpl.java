package org.cmms.modules.khgl.etckh.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlVO;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlbVO;
import org.cmms.modules.khgl.etckh.entity.Dkjl;
import org.cmms.modules.khgl.etckh.mapper.DkjlMapper;
import org.cmms.modules.khgl.etckh.service.IDkjlService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: ETC贷记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Service

public class DkjlServiceImpl extends ServiceImpl<DkjlMapper, Dkjl> implements IDkjlService {

    @Override
    public List<DjkdkjlbVO> getDkjlList(int start, int end, String namecn) {
        return baseMapper.getDkjlList(start,end,namecn);
    }

//    @Override
//    public List<DjkdkjlbVO> getYdk(String dkje) {
//        return baseMapper.getYdk(dkje);
//    }

    @Override
    public List<DjkdkjlbVO> getSfcs(int start, int end, String namecn) {
        return baseMapper.getSfcs(start, end, namecn);
    }

    @Override
    public List<DjkdkjlbVO> getAll(int start, int end, String namecn) {
        return baseMapper.getAll(start, end, namecn);
    }

    @Override
    public IPage<DjkdkjlVO> getDjkDkjlList(Page page, String zh) {
        return baseMapper.getDjkDkjlList(page, zh);
    }


}
