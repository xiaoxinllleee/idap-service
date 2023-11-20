package org.cmms.modules.khjg.zhfyfp.service;

import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpHz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
public interface IZhfyfpHzService extends IService<ZhfyfpHz> {
    public void extract(String sql,String fpyf,String fylx);
}
