package org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.service;

import org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.entity.PjsxwcqkZmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 组完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface IPjsxwcqkZmxService extends IService<PjsxwcqkZmx> {

    void InitDataToXzz(Map<String, String> sql);

}
