package org.cmms.modules.pad.gzryxxgl.service;

import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzryfcxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 公职人员房产信息表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
public interface ICamsZcsxGzryfcxxService extends IService<CamsZcsxGzryfcxx> {

    public List<CamsZcsxGzryfcxx> getByGzryid(String gzryid);

}
