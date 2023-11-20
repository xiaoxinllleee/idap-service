package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxPjsxwcqkmxb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 评级授信完成情况明表
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
public interface ITjfxPjsxwcqkmxbService extends IService<TjfxPjsxwcqkmxb> {

    public List<TjfxPjsxwcqkmxb> querysjzfhs ( Date ksrq,  Date jsrq , String xzc, String qfbs,String code);

    public List<TjfxPjsxwcqkmxb> querysjpjhs ( Date ksrq, Date jsrq , String xzc, String qfbs);


}
