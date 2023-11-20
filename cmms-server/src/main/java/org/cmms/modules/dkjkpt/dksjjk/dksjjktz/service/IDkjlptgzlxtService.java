package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjlptgzlxt;

/**
 * @Description: 贷款监控平台关注类_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-01
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjlptgzlxtService extends IService<Dkjlptgzlxt> {
    void gzl(String tjnf,String zjhm);
}
