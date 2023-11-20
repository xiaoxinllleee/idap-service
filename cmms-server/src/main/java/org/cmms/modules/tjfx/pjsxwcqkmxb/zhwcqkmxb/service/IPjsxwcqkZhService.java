package org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.service;

import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.entity.PjsxwcqkZh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface IPjsxwcqkZhService extends IService<PjsxwcqkZh> {

    void InitDataToZh(Map<String, String> sql);

}
