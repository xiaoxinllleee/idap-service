package org.cmms.modules.tjfx.khnlfctj.service;

import org.cmms.modules.tjfx.khnlfctj.entity.Tjfxkhnlfctj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户年龄分层统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface ITjfxkhnlfctjService extends IService<Tjfxkhnlfctj> {

    public void extract(String tjyf);

}
