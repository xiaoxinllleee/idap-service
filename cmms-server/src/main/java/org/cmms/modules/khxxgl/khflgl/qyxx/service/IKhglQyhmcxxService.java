package org.cmms.modules.khxxgl.khflgl.qyxx.service;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.KhglQyhmcxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 企业信息管理
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
public interface IKhglQyhmcxxService extends IService<KhglQyhmcxx> {

    public void init(String shid, String yggh, String lrr);

}
