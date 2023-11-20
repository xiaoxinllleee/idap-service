package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xdgl.grdkgl.entity.RateCssz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: Rate_cssz
 * @Author: jeecg-boot
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateCsszService extends IService<RateCssz> {

    public List<RateCssz> querycssz ();
}
