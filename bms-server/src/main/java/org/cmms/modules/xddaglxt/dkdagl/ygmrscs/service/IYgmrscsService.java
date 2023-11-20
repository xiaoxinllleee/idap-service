package org.cmms.modules.xddaglxt.dkdagl.ygmrscs.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.entity.Ygmrscs;

import java.util.Date;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@DS("eweb")
public interface IYgmrscsService extends IService<Ygmrscs> {
    void pYgmrscs(String tjrqBegin, String tjrqEnd, String username);
}
