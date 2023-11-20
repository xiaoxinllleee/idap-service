package org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.service;

import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.entity.PjsxwcqkQh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface IPjsxwcqkQhService extends IService<PjsxwcqkQh> {

    void InitDataToQh(Map<String, String> sql);

}
