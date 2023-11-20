package org.cmms.modules.performance.loancustomer.dkkhyj.service;

import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.loancustomer.dkkhyj.entity.Dkkhyjxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 贷款客户移交
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface IDkkhyjxxService extends IService<Dkkhyjxx> {
    String getProcessIdByProcessKey(String processKey);
    List<Dkkhyjxx> getListByIds(List<String> ids);
}
