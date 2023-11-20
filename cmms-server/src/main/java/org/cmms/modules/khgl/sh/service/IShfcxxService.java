package org.cmms.modules.khgl.sh.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.sh.entity.Shfcxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 商户房产信息
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
public interface IShfcxxService extends IService<Shfcxx> {

    public Map<String,Object> queryFcxx( String shid);
}
