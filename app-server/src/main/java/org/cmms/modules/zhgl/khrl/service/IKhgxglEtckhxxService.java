package org.cmms.modules.zhgl.khrl.service;

import org.cmms.modules.zhgl.khrl.entity.KhgxglEtckhxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: ETC客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
public interface IKhgxglEtckhxxService extends IService<KhgxglEtckhxx> {

    List<KhgxglEtckhxx> getEtcListByKhmc(String khmc, String jgdm);
}
