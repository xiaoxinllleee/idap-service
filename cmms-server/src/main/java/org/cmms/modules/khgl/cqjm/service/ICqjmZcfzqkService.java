package org.cmms.modules.khgl.cqjm.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 城区居民资产负债情况
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
public interface ICqjmZcfzqkService extends IService<CqjmZcfzqk> {

    List<CqjmZcfzqk> selectByZjhm(String zjhm);

    void deleteZcfzqkByZjhm(@Param("zjhm") String zjhm);

}
