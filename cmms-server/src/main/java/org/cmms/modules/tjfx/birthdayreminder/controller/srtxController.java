package org.cmms.modules.tjfx.birthdayreminder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.birthdayreminder.dto.myBirthdayCustomersDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.srtxDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.statusDto;
import org.cmms.modules.tjfx.birthdayreminder.entity.srtx;
import org.cmms.modules.tjfx.birthdayreminder.service.IsrtxService;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date: 2022-07-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "sd")
@RestController
@RequestMapping("/zfcjyxkhstj/srtx")
public class srtxController extends JeecgController<srtx, IsrtxService> {
    @Autowired
    private IsrtxService srtxService;


    /**
     * 分页列表查询
     *
     * @param srtx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "sd-分页列表查询")
    @ApiOperation(value = "sd-分页列表查询", notes = "sd-分页列表查询")
    @GetMapping(value = "/ipadlist")
    public Result<?> queryPageList(srtx srtx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<srtx> queryWrapper = QueryGenerator.initQueryWrapper(srtx, req.getParameterMap());
        Page<srtx> page = new Page<srtx>(pageNo, pageSize);
        IPage<srtx> pageList = srtxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param srtx
     * @return
     */
    @AutoLog(value = "sd-添加")
    @ApiOperation(value = "sd-添加", notes = "sd-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody srtx srtx) {
        srtxService.save(srtx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param srtx
     * @return
     */
    @AutoLog(value = "sd-编辑")
    @ApiOperation(value = "sd-编辑", notes = "sd-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody srtx srtx) {
        srtxService.updateById(srtx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "sd-通过id删除")
    @ApiOperation(value = "sd-通过id删除", notes = "sd-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        srtxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "sd-批量删除")
    @ApiOperation(value = "sd-批量删除", notes = "sd-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.srtxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "sd-通过id查询")
    @ApiOperation(value = "sd-通过id查询", notes = "sd-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        srtx srtx = srtxService.getById(id);
        return Result.ok(srtx);
    }



    /**
     * 导出excel
     *
     * @param request
     * @param srtx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, srtx srtx) {


        QueryWrapper<srtx> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                srtx rtx = JSON.parseObject(deString, srtx.class);
                queryWrapper = QueryGenerator.initQueryWrapper(rtx, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<srtx> pageList = srtxService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "重要生日客户列表");
        mv.addObject(NormalExcelConstants.CLASS, srtx.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("重要生日客户列表数据", "导出人:Jeecg", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);

        return super.exportXls(request, srtx, srtx.class, "生日提醒客户");
    }




    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, srtx.class);
    }


    /**
     * 分页列表查询
     *
     * @param
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "sd-通过时间等条件查询")
    @ApiOperation(value = "sd-通过时间等条件查询", notes = "sd-通过时间等条件查询")
    @GetMapping(value = "/ipadListByTime")
    public Result<?> queryPageListByTime(srtxDto srtxDto,
                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         HttpServletRequest req) {


        IPage<srtx> pageList = srtxService.queryByPageList(req,srtxDto, pageNo, pageSize);
        return Result.ok(pageList);
    }

    /**
     * 获取用户名
     */

    @AutoLog(value = "sd-获取用户名")
    @ApiOperation(value = "sd-获取用户名", notes = "sd-获取用户名")
    @GetMapping(value = "/getUserName")
    public Result<?> getUserName(HttpServletRequest request) {

        String token = request.getHeader("X-Access-Token");
        String UserName = JwtUtil.getUsername(token);
        return Result.ok(UserName);


    }

    @GetMapping(value = "/getSrtxInfoByZjhm")
    public Result<?> getSrtxInfoByZjhm(String zjhm) {
        QueryWrapper<srtx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("idnumber", zjhm);
        List<srtx> list = service.list(queryWrapper);
        if (!list.isEmpty()) {
            return Result.ok(list.get(0));
        }
        return Result.ok("未找到数据");
    }


    /**
     * 改变操作状态
     *
     * @param
     * @return
     */
    @AutoLog(value = "sd-改变操作的状态")
    @ApiOperation(value = "sd-改变操作的状态", notes = "sd-改变操作的状态")
    @PostMapping(value = "/changeOperateStatus")
    public Result<?> changeOperateStatus(@RequestBody statusDto statusDto, HttpServletRequest request) {


        Boolean flagStatus = srtxService.changeOperateStatus(statusDto,request);
        if (flagStatus) {
            return Result.ok("状态改变成功");
        }

        return  Result.ok("lock","他人已锁定无法操作");

    }


    /**
     * 改变操作状态
     *
     * @param
     * @return
     */
    @AutoLog(value = "sd-图片上传")
    @ApiOperation(value = "sd-图片上传", notes = "sd-图片上传")
    @PutMapping(value = "/uploadPicture")
    public Result<?> uploadPicture(@RequestBody myBirthdayCustomersDto birthdayCustomersDto, HttpServletRequest req) {


        boolean flag = srtxService.insertIntoDataAndUploadImage(birthdayCustomersDto, req);

        if (flag) {
            return Result.ok("状态改变成功");
        }



        return Result.error("上传失败");
    }


    /**
     * 改变操作状态
     *
     * @param
     * @return
     */
    @AutoLog(value = "sd-重要客户个数")
    @ApiOperation(value = "sd-重要客户个数", notes = "sd-重要客户个数")
    @GetMapping(value = "/importCoustmerNumber")
    public Result<?> importCoustmerCount() {
        JSONObject Number = srtxService.selectImportCostumerNumber();
        return Result.ok(Number);
    }


    /**
     * 已庆生客户
     *
     * @param
     * @return
     */
    @AutoLog(value = "sd-通过")
    @ApiOperation(value = "sd-获取已上传用户以及附件信息", notes = "sd-已庆生客户")
    @GetMapping(value = "/getUploadPictureImfomation")
    public Result<?> getUploadPictureImfomation(srtxDto srtxDto,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req) {

        Result result = new Result<IPage<List<historySrtxVo>>>();
        IPage<historySrtxVo> page=srtxService.getUploadPictureImfomation(srtxDto,pageNo, pageSize, req);
        result.setSuccess(true);
        result.setResult(page);
        return Result.ok(result);
    }



//    @PostConstruct
//    public void initData() {
//        log.info("----------------开始执行生日提醒数据初始化存储过程开始-------------");
//    /*    srtxService.initDataBySheduler();*/
//        log.info("------------------开始执行生日提醒数据初始化存储过程完成----------");
//
//    }









}
