package org.cmms.modules.khdj.khdjpdsjx.service;

import org.cmms.modules.khdj.khdjpdsjx.entity.KhdjpdSjx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 客户等级评定数据项
 * @Author: cmms
 * @Date:   2019-10-16
 * @Version: V1.0
 */
public interface IKhdjpdSjxService extends IService<KhdjpdSjx> {

    KhdjpdSjx queryBySjxid(Map<String, String> sql);

}
