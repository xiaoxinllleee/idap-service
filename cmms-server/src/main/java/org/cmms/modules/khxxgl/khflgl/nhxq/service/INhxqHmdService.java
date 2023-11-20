package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqHmd;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
public interface INhxqHmdService extends IService<NhxqHmd> {
    public void init();
    List<NhxqHmd> getHByZjhm(String zjhm);

    List<NhxqHmd> getByHnkd(KhxxglHnkd khxxglHnkd);

    //boolean nhIsQxfkData(String id);
}
