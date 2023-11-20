package org.cmms.modules.appbase.tbtjfxcssz.service;

import org.cmms.modules.appbase.tbtjfxcssz.entity.TbTjfxCssz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2021-05-20
 * @Version: V1.0
 */
public interface ITbTjfxCsszService extends IService<TbTjfxCssz> {

    public String getValue(String key);

    public Date getTheMaxDate();

}
