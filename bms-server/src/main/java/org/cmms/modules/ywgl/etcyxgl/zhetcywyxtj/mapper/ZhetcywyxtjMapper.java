package org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.entity.Zhetcywyxtj;

/**
 * @Description: 支行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface ZhetcywyxtjMapper extends BaseMapper<Zhetcywyxtj> {
    void pZhetcywyxtj(@Param("tjyf")String tjyf);
}
