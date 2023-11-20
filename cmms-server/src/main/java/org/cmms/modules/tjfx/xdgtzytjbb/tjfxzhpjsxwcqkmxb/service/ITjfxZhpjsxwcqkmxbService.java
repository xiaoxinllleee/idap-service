package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service;

import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxZhpjsxwcqkmxb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
public interface ITjfxZhpjsxwcqkmxbService extends IService<TjfxZhpjsxwcqkmxb> {

    public void extract(Map<String,String>sql);

}
