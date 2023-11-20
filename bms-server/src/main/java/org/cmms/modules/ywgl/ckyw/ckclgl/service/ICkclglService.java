package org.cmms.modules.ywgl.ckyw.ckclgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;

import org.cmms.modules.ywgl.ckyw.ckclgl.entity.Ckclgl;

import java.util.List;

/**
 * @Description: 存款存量管理
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@DS("cdkyw")
public interface ICkclglService extends IService<Ckclgl> {
    void pCkclgl(String clnf);
}
