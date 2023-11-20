package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.xdgl.grdkgl.entity.YwhyewlxxJtsjhz;
import org.cmms.modules.xdgl.grdkgl.mapper.YwhyewlxxJtsjhzMapper;
import org.cmms.modules.xdgl.grdkgl.service.IYwhyewlxxJtsjhzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 与我行业务往来信息-家庭数据汇总
 * @Author: Penghr
 * @Date:   2020-10-22
 * @Version: V1.0
 */
@Service
public class YwhyewlxxJtsjhzServiceImpl extends ServiceImpl<YwhyewlxxJtsjhzMapper, YwhyewlxxJtsjhz> implements IYwhyewlxxJtsjhzService {

    @Autowired
    private YwhyewlxxJtsjhzMapper mapper;

    @Override
    public List<YwhyewlxxJtsjhz> GetYwhywwlxxJtsjhzByHhbm(String hhbm) {
        return mapper.GetYwhywwlxxJtsjhzByHhbm(hhbm);
    }

    @Override
    public List<Ywhywwlxx> queryJtcyYwhywwlDataByHhbm(String hhbm) {
        return mapper.queryJtcyYwhywwlDataByHhbm(hhbm);
    }
}
