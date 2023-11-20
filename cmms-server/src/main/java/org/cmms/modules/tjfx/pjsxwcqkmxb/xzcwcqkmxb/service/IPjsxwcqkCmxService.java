package org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.service;

import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.entity.PjsxwcqkCmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 行政村完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface IPjsxwcqkCmxService extends IService<PjsxwcqkCmx> {

    void InitDataToXzc(Map<String, String> sql);

}
