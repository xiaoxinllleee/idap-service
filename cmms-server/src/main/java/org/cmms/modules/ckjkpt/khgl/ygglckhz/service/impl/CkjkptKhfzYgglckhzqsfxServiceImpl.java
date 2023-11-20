package org.cmms.modules.ckjkpt.khgl.ygglckhz.service.impl;

import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhzqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.mapper.CkjkptKhfzYgglckhzqsfxMapper;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.service.ICkjkptKhfzYgglckhzqsfxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 员工揽储趋势分析
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Service
public class CkjkptKhfzYgglckhzqsfxServiceImpl extends ServiceImpl<CkjkptKhfzYgglckhzqsfxMapper, CkjkptKhfzYgglckhzqsfx> implements ICkjkptKhfzYgglckhzqsfxService {
    public List<CkjkptKhfzYgglckhzqsfx> queryjsryeqs(String zzbz, String gwbz, String yggh){
        return  baseMapper.queryjsryeqs(zzbz,gwbz,yggh);
    };
}
