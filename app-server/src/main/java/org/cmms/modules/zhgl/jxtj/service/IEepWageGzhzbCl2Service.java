package org.cmms.modules.zhgl.jxtj.service;

import org.cmms.modules.zhgl.jxtj.entity.EepWageGzhzbCl2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 慈利工资表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
public interface IEepWageGzhzbCl2Service extends IService<EepWageGzhzbCl2> {

    public Date getMaxImpday();
}
