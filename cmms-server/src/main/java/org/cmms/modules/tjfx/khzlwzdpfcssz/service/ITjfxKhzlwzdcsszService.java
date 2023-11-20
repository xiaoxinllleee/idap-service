package org.cmms.modules.tjfx.khzlwzdpfcssz.service;

import org.cmms.modules.tjfx.khzlwzdpfcssz.entity.TjfxKhzlwzdcssz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户建档完整度评分参数设置
 * @Author: cmms
 * @Date:   2019-12-05
 * @Version: V1.0
 */
public interface ITjfxKhzlwzdcsszService extends IService<TjfxKhzlwzdcssz> {

    public List<TjfxKhzlwzdcssz> selectByMainId(String csbm);

    List<Map<String,String>> selectTable(String tablename);

}
