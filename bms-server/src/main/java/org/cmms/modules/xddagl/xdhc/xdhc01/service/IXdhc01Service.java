package org.cmms.modules.xddagl.xdhc.xdhc01.service;

import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
public interface IXdhc01Service extends IService<Xdhc01> {
    public void pXdhc01();
}
