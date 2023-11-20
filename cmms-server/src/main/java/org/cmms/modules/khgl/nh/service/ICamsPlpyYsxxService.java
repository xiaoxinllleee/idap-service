package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.CamsPlpyYsxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 批量评议验收信息
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
public interface ICamsPlpyYsxxService extends IService<CamsPlpyYsxx> {

    Map<String,String> getWgbhYsqkAndPyls(Integer pyls);
}
