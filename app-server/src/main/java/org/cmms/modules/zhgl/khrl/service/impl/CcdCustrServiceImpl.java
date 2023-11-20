package org.cmms.modules.zhgl.khrl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.zhgl.khrl.entity.CcdCustr;
import org.cmms.modules.zhgl.khrl.entity.CcdCustrVO;
import org.cmms.modules.zhgl.khrl.mapper.CcdCustrMapper;
import org.cmms.modules.zhgl.khrl.service.ICcdCustrService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class CcdCustrServiceImpl extends ServiceImpl<CcdCustrMapper, CcdCustr> implements ICcdCustrService {

    @Override
    public List<CcdCustrVO> getXykListByKhmc(String khmc, String jgdm) {
        return baseMapper.getXykListByKhmc(khmc, jgdm);
    }
}
