package org.cmms.modules.performance.depositcustomer.ckkhghyj.service;

import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 存款客户管户移交
 * @Author: Penghr
 * @Date:   2021-03-25
 * @Version: V1.0
 */
public interface ICkkhghyjxxService extends IService<Ckkhghyjxx> {

    String getProcessIdByProcessKey(String processKey);

    List<Ckkhghyjxx> getListByIds(List<String> ids);

}
