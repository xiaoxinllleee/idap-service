package org.cmms.modules.khgl.csjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.csjl.entity.AppEtcCsjlVO;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjl;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjlVO;
import org.cmms.modules.khgl.csjl.mapper.AppEtckhCsjlMapper;
import org.cmms.modules.khgl.csjl.service.IAppEtckhCsjlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: etc客户催收记录
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@Service
public class AppEtckhCsjlServiceImpl extends ServiceImpl<AppEtckhCsjlMapper, AppEtckhCsjl> implements IAppEtckhCsjlService {

    @Override
    public List<AppEtckhCsjlVO> getCsxxList(int start, int end, String namecn) {
        return baseMapper.getCsxxList(start, end, namecn);
    }

    @Override
    public IPage<AppEtcCsjlVO> getCsjlList(Page page, String zjhm) {
        return baseMapper.getCsjlList(page, zjhm);
    }
}
