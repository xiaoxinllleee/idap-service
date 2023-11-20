package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.service.impl;

import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.mapper.CkjkptKhfxYgglkhckqsfxMapper;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.service.ICkjkptKhfxYgglkhckqsfxService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 客户近10日存款余额
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@Service
public class CkjkptKhfxYgglkhckqsfxServiceImpl extends ServiceImpl<CkjkptKhfxYgglkhckqsfxMapper, CkjkptKhfxYgglkhckqsfx> implements ICkjkptKhfxYgglkhckqsfxService {
    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqs(String zzbz,  String gwbz, String yggh, String zjhm){
            return  baseMapper.queryjsryeqs(zzbz,gwbz,yggh,zjhm);
    }

    @Override
    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqsDsj(String zzbz, String gwbz, String yggh, String zjhm) {
        return baseMapper.queryjsryeqsDsj(zzbz,gwbz,yggh,zjhm);
    }

    ;
}
