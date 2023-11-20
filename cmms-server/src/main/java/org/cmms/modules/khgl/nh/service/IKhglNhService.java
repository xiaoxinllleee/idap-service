package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.KhglNhhzzllb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface IKhglNhService extends IService<KhglNhhzzllb> {

    public List<KhglNhhzzllb> selectByMainId(String hhbm);

}
