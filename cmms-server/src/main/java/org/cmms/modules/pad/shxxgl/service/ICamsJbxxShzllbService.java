package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 商户资料列表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface ICamsJbxxShzllbService extends IService<CamsJbxxShzllb> {
    public List<CamsJbxxShzllb> getByShid(String shid);
}
