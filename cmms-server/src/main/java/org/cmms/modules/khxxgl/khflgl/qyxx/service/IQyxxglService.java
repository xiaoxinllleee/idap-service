package org.cmms.modules.khxxgl.khflgl.qyxx.service;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.Qyxxgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;

import java.util.List;

/**
 * @Description: 企业信息
 * @Author: jeecg-boot
 * @Date:   2022-11-02
 * @Version: V1.0
 */
public interface IQyxxglService extends IService<Qyxxgl> {
    public void init();

    public List<Qyxxgl> selectByQyxx (String hhbm);

}
