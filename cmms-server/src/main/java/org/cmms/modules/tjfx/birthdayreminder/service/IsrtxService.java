package org.cmms.modules.tjfx.birthdayreminder.service;



import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.birthdayreminder.dto.myBirthdayCustomersDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.srtxDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.statusDto;
import org.cmms.modules.tjfx.birthdayreminder.entity.srtx;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
public interface IsrtxService extends IService<srtx> {

    Boolean changeOperateStatus(statusDto statusDto, HttpServletRequest request);

    IPage<srtx> queryByPageList(HttpServletRequest req, srtxDto srtxDto, Integer pageNo, Integer pageSize);



    public void initDataBySheduler();




    JSONObject selectImportCostumerNumber();



    boolean insertIntoDataAndUploadImage(myBirthdayCustomersDto birthdayCustomersDto, HttpServletRequest req);

    IPage<historySrtxVo> getUploadPictureImfomation(srtxDto srt, Integer pageNo, Integer pageSize, HttpServletRequest req);
}
