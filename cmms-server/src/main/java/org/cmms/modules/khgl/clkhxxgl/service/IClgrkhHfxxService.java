package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface IClgrkhHfxxService extends IService<ClgrkhHfxx> {

    List<ClgrkhHfxx> queryHfxxByZjhm(String zjhm);

    List<String> queryTodayHfxx(String yggh);
}
