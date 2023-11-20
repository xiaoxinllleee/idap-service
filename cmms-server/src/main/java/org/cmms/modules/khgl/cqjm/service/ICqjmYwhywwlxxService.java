package org.cmms.modules.khgl.cqjm.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.CqjmYwhywwlxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 城区居民与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface ICqjmYwhywwlxxService extends IService<CqjmYwhywwlxx> {

    void deleteYwwlxxByZjhm(@Param("zjhm") String zjhm);

}
