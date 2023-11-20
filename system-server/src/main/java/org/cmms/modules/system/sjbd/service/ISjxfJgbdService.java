package org.cmms.modules.system.sjbd.service;

import org.cmms.modules.system.sjbd.entity.SjxfJgbd;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 数据下发结果比对
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
public interface ISjxfJgbdService extends IService<SjxfJgbd> {
    long getCount(String tablename,String ds);

    double getSum(String tablename,String fzzd,String hzzd,String ds);

    double getAvg(String tablename,String fzzd,String hzzd,String ds);

}
