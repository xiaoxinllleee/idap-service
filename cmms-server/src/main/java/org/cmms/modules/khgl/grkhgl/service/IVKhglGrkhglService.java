package org.cmms.modules.khgl.grkhgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.grkhgl.entity.VKhglGrkhgl;

/**
 * @Description: 视图
 * @Author: jeecg-boot
 * @Date:   2020-07-20
 * @Version: V1.0
 */
public interface IVKhglGrkhglService extends IService<VKhglGrkhgl> {

    int calculateModel(String hhbh, String zjhm);

     int addJtcyxx( VKhglGrkhgl v);

}
