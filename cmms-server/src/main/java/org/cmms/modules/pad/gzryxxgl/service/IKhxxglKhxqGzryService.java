package org.cmms.modules.pad.gzryxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzry;

/**
 * @Description: 公职人员信息管理
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface IKhxxglKhxqGzryService extends IService<KhxxglKhxqGzry> {

    public void init(String gzryid, String yggh, String lrr);
}
