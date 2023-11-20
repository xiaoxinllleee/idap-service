package org.cmms.modules.tjfx.jcsjgl.gsxxdr.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.entity.TjfxGsxxdr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-12
 * @Version: V1.0
 */
public interface ITjfxGsxxdrService extends IService<TjfxGsxxdr> {
    public int delete2(String zjhm,String sszh,Date gsrq);
    public TjfxGsxxdr queryByMsg(String sszh,String zjhm,Date gsrq);
    public Long querygscs( String sszh,String zjhm );


}
