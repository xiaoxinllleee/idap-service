package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.xdgl.grdkgl.entity.Vgrdkspjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 个人贷款审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
public interface IVgrdkspjlService extends IService<Vgrdkspjl> {
    public List<Vgrdkspjl> queryDksp(String userId);
    public List<Vgrdkspjl> queryByZjhm(String userId, String zjhm,String khmc);
    public List<Vgrdkspjl> selectByZjhm(String userId,String zjhm);
    public List<Vgrdkspjl> selectByKhmc(String userId,String khmc);
}
