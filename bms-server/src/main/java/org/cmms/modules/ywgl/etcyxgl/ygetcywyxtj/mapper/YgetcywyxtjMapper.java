package org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.entity.Ygetcywyxtj;

/**
 * @Description: 员工etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface YgetcywyxtjMapper extends BaseMapper<Ygetcywyxtj> {
    void pYgetcywyxtj(@Param("tjyf")String tjyf);
}
