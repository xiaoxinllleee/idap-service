package org.cmms.modules.khgl.khxx.service;

import org.cmms.modules.khgl.khxx.entity.vKhglKhjbxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
public interface IvKhglKhjbxxService extends IService<vKhglKhjbxx> {
    public Map<String,Object> selectByHhbm(String hhbm);

}
