package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.KhglShhmcxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * @Description: 商户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface IKhglShhmcxxService extends IService<KhglShhmcxx> {
    public HashMap<String, Object> getYwxxByZjhm(String zjhm);

    public void init(String shid, String yggh, String lrr);

}
