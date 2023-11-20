package org.cmms.modules.khgl.xjjl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import org.cmms.modules.khgl.xjjl.mapper.AppJhshXjjlMapper;
import org.cmms.modules.khgl.xjjl.service.IAppJhshXjjlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 聚合商户巡检记录
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class AppJhshXjjlServiceImpl extends ServiceImpl<AppJhshXjjlMapper, AppJhshXjjl> implements IAppJhshXjjlService {


    @Override
    public List<TgacsTpsMchntInfo> getAllList(int start, int end, String namecn) {
        return baseMapper.getAllList(start, end, namecn);
    }

    @Override
    public List<AppJhshXjjl> getXjsj(String mchntId) {
        return baseMapper.getXjsj(mchntId);
    }

    @Override
    public List<TgacsTpsMchntInfo> showXJ(int start, int end, String namecn) {
        return baseMapper.showXJ(start, end, namecn);
    }


}
