package org.cmms.modules.yxdygl.pqzrrgl.service;

import org.cmms.modules.yxdygl.pqzrrgl.entity.YxdyglPqzrrgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 片区责任人管理
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */
public interface IYxdyglPqzrrglService extends IService<YxdyglPqzrrgl> {

    public YxdyglPqzrrgl queryqydm(Map<String,String> sql);

    public int queryCountBykhjl(String khjl);

}
