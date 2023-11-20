package org.cmms.modules.khgl.dkkh.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.KhgxglDkkhghlsb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 贷款客户管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface IKhgxglDkkhghlsbService extends IService<KhgxglDkkhghlsb> {

    List<String> getGhBsByHth(String hth,Integer ghlx);

    String getBsrByHth(String hth);

    String getGhlxByHth(@Param("hth") String hth, @Param("ghlx") int ghlx);
}
