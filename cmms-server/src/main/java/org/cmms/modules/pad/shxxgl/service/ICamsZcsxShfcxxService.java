package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShfcxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 商户房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface ICamsZcsxShfcxxService extends IService<CamsZcsxShfcxx> {
    public List<CamsZcsxShfcxx> getByShid(String shid);
}
