package org.cmms.modules.performance.depositcustomer.ckzhtzyj.service;

import org.cmms.modules.performance.depositcustomer.ckzhtzyj.entity.Ckzhtzyjxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 存款账号拓展移交信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
public interface ICkzhtzyjxxService extends IService<Ckzhtzyjxx> {
    String getProcessIdByProcessKey(String processKey);

    List<Ckzhtzyjxx> getListByIds(List<String> ids);
}
