package org.cmms.modules.tjfx.birthdayreminder.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;
import org.cmms.modules.tjfx.birthdayreminder.service.srtxmyBirthdayCustomerService;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Slf4j
@Api(tags = "sd")
@RestController
@RequestMapping("/zfcjyxkhstj/srtx")
public class srtxMyBirthdayHistoryController extends JeecgController<myBirthdayCustomerEntity, srtxmyBirthdayCustomerService> {

    @Autowired
    private srtxmyBirthdayCustomerService birthdayCustomerService;

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/historyExportExl")
    public ModelAndView exportXls(HttpServletRequest request, myBirthdayCustomerEntity myBirthdayCustomerEntity) {


        QueryWrapper<myBirthdayCustomerEntity> queryWrapper = null;
        myBirthdayCustomerEntity srtx = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                srtx = JSON.parseObject(deString, myBirthdayCustomerEntity.class);
                queryWrapper = QueryGenerator.initQueryWrapper(myBirthdayCustomerEntity, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<myBirthdayCustomerEntity> pageList = birthdayCustomerService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "历史重要生日客户列表");
        mv.addObject(NormalExcelConstants.CLASS, myBirthdayCustomerEntity.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("历史重要生日客户列表", "导出人:Jeecg", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);

        return super.exportXls(request, srtx, myBirthdayCustomerEntity.class, "已庆生客户列表");

    }


    /**
     * 已庆生客户
     *
     * @param
     * @return
     */
    @AutoLog(value = "sd-通过")
    @ApiOperation(value = "sd-获取已上传用户以及附件信息详情通过id", notes = "sd-已庆生客户")
    @GetMapping(value = "/getUploadPictureImfomationById")
    public Result<?> getUploadPictureImfomationDetailById(@RequestParam(name = "id", required = true)String id, HttpServletRequest req) {

        Result result = new Result<IPage<List<historySrtxVo>>>();
        historySrtxVo  e=birthdayCustomerService.getUploadPictureImfomationDetailById(id, req);
        result.setSuccess(true);
        result.setResult(e);
        return Result.ok(result);
    }





}
