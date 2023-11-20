package org.cmms.modules.pad.qyxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.qyxxgl.entity.VKhglQyxxgl;

/**
 * @Description: 企业信息管理_pad
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
public interface IKhglQyxxglService extends IService<VKhglQyxxgl> {

    public void init(String qyid, String yggh, String lrr);
}
