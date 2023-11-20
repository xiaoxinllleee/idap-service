package org.cmms.modules.tjfx.birthdayreminder.service;



import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.birthdayreminder.dto.srtxDto;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;

import javax.servlet.http.HttpServletRequest;


public interface srtxmyBirthdayCustomerService extends IService<myBirthdayCustomerEntity> {

    IPage<myBirthdayCustomerEntity> selectmyBirthdayCustomerEntityByListPage(srtxDto srtxDt, Integer pageNo, Integer pageSiz, HttpServletRequest request);

    historySrtxVo getUploadPictureImfomationDetailById(String id, HttpServletRequest req);
}
