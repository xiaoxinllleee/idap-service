package org.cmms.modules.jtxx.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.base.entity.Dict;
import org.cmms.modules.jtxx.entity.Jtxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jtxx.entity.JtxxList;

import java.util.List;

/**
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
public interface IJtxxService extends IService<Jtxx> {
    public List<Jtxx>  queryJtxx(String hhbm,String id);

    public Integer insertJtxxList(List<Jtxx> jtxxList,String hhbm);

    public Dict queryDict(String dictCode);

    public Integer deleteJtcyxx(String hhbm,String id);

    public Integer updateJtxx(JtxxList jtxxList);

    public Integer updateHhbm(String id,String sign);

    public Jtxx selectByZjhm(String zjhm);

    public Jtxx getName(String zjhm);

    public Integer deleteByzjhm(String zjhm);

    public Integer deleteById(String id);
}
