package org.cmms.modules.tjfx.zhPjsxtjbb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.zhPjsxtjbb.Vo.ZhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.entity.ZhPjsxtjbb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 支行评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
public interface IZhPjsxtjbbService extends IService<ZhPjsxtjbb> {
    List<ZhPjsxxxMx> zhPjsxtjbbMx(String sszh,String sjrq);
}
