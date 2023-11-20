package org.cmms.modules.pad.nhzhsd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.nhzhsd.entity.Nhzhsd;

import java.util.List;

/**
 * @Description: 农户支行审定
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
public interface INhzhsdService extends IService<Nhzhsd> {
    public List<String> getWwcCzfpWg(String  khjl);
}
