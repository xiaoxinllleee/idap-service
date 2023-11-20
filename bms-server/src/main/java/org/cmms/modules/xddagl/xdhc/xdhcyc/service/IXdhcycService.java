package org.cmms.modules.xddagl.xdhc.xdhcyc.service;

import org.cmms.modules.xddagl.xdhc.xdhcyc.entity.Xdhcyc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 信贷T+1核查先隐藏
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
public interface IXdhcycService extends IService<Xdhcyc> {
    public void pXdhcyc();
}
