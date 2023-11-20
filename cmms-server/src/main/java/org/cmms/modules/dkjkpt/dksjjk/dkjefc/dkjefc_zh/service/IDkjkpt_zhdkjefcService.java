package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity.Dkjkpt_zhdkjefc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 贷款金额分成_支行
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkpt_zhdkjefcService extends IService<Dkjkpt_zhdkjefc> {
    void InitDataToQh(Map<String, String> sql);

}
