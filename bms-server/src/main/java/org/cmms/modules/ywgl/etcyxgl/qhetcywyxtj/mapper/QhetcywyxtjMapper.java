package org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.entity.Qhetcywyxtj;

/**
 * @Description: 全行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
public interface QhetcywyxtjMapper extends BaseMapper<Qhetcywyxtj> {
    void pQhetcywyxtj(@Param("tjyf")String tjyf);
}
