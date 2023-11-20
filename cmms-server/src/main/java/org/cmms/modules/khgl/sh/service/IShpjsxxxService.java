package org.cmms.modules.khgl.sh.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.sh.entity.Shpjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 商户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
public interface IShpjsxxxService extends IService<Shpjsxxx> {

    public Map<String,Object> queryZcxx(String shid);
}
