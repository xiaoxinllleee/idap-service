package org.cmms.modules.khxxgl.khflgl.qyxx.service;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQyfcxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShfcxx;

import java.util.List;

/**
 * @Description: 企业房产信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface ICamsZcsxQyfcxxService extends IService<CamsZcsxQyfcxx> {
    public List<CamsZcsxQyfcxx> getByQyid(String qyid);
}
