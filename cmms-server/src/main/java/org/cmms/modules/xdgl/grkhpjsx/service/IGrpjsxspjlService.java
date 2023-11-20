package org.cmms.modules.xdgl.grkhpjsx.service;

import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 个人客户评级授信记录
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
public interface IGrpjsxspjlService extends IService<Grpjsxspjl> {
    public void deleteByspid(String spid);

    public void deleteGrdkByspid(String spid);

    /**
     * 获取电子贷款证数据
     * */
    public List<Grpjsxspjl> getDzdkzData();

}
