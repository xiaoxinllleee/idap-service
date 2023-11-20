package org.cmms.modules.khgl.cqjm.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 城区居民资产信息
 * @Author: jeecg-boot
 * @Date:   2020-02-22
 * @Version: V1.0
 */
public interface ICqjmZcxxService extends IService<CqjmZcxx> {

    void deleteZcxxByZjhm(@Param("zjhm") String zjhm);

}
