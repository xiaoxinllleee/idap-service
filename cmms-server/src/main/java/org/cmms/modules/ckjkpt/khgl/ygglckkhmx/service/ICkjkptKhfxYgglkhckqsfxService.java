package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.Date;
import java.util.List;

/**
 * @Description: 客户近10日存款余额
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptKhfxYgglkhckqsfxService extends IService<CkjkptKhfxYgglkhckqsfx> {
    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqs(String zzbz,  String gwbz, String yggh, String zjhm);
    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqsDsj(String zzbz,  String gwbz, String yggh, String zjhm);
}
