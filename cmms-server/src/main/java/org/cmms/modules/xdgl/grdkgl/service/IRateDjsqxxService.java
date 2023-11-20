package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xdgl.grdkgl.entity.RateDjsqxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 等级申请信息
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateDjsqxxService extends IService<RateDjsqxx> {

    public RateDjsqxx querydjsqxx(String zjhm , Date djnf);

}
