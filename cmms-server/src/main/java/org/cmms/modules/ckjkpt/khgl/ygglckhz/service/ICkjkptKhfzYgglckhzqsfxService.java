package org.cmms.modules.ckjkpt.khgl.ygglckhz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhzqsfx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 员工揽储趋势分析
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptKhfzYgglckhzqsfxService extends IService<CkjkptKhfzYgglckhzqsfx> {
    public List<CkjkptKhfzYgglckhzqsfx> queryjsryeqs(String zzbz, String gwbz, String yggh);
}
