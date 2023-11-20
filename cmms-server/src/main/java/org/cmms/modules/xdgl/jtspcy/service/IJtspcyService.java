package org.cmms.modules.xdgl.jtspcy.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 集体审批成员
 * @Author: jeecg-boot
 * @Date:   2020-09-14
 * @Version: V1.0
 */
public interface IJtspcyService extends IService<Jtspcy> {
    public void deleteJtspcy(String id ,String yggh);
    public Integer updatespjl(Jtspcy jtspcy);
    public Jtspcy queryById(String id,String zrrid);
    public void deleteJtspcyByZrrids(String id, List<String> zrrids);

}
