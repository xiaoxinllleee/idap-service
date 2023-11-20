package org.cmms.modules.khgl.jhsh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 聚合商户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@DS("eweb")
public interface ITgacsTpsMchntInfoService extends IService<TgacsTpsMchntInfo> {

    IPage<TgacsTpsMchntInfo> getxj(Page page,String ks,String js);
}
