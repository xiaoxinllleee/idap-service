package org.cmms.modules.khdj.khdjcsgl.service;

import org.cmms.modules.khdj.khdjcsgl.entity.KhdjCsgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 客户等级评定参数管理
 * @Author: cmms
 * @Date:   2019-10-28
 * @Version: V1.0
 */
public interface IKhdjCsglService extends IService<KhdjCsgl> {

    KhdjCsgl queryByPid(Map<String, String> sql);

}
