package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.HrBasStaffPostVO;

import java.util.List;

/**
 * @Description: 员工岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface IHrBasStaffPostVOService extends IService<HrBasStaffPostVO> {

}
