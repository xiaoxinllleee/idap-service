package org.cmms.modules.report.sgtzgl.wjplsc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.configurationTable;

/**
 * @Description: dwd
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
public interface IconfigurationTableService extends IService<configurationTable> {

    configurationTable selectTableCodeByTableName(String name);
}
