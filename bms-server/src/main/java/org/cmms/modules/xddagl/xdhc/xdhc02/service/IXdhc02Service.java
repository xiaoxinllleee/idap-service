package org.cmms.modules.xddagl.xdhc.xdhc02.service;

import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
public interface IXdhc02Service extends IService<Xdhc02> {
    public void pXdhc02();
}
