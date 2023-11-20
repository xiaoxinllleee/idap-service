package org.cmms.modules.pad.pyxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;

import java.util.List;

/**
 * @Description: 农户批量评议
 * @Author: jeecg-boot
 * @Date:   2022-03-07
 * @Version: V1.0
 */
public interface INhplpyService extends IService<Nhplpy> {
    List<Nhplpy> getByPyls(Integer pyls);
}
