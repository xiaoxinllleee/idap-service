package org.cmms.modules.tjfx.qhywjd.qhckqk.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.qhywjd.qhckqk.entity.TjfxQhckqk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.qhywjd.qhckqk.service.ITjfxQhckqkService;
import org.cmms.modules.util.DateUtil;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 全行存款情况
 * @Author: jeecg-boot
 * @Date: 2023-08-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "全行存款情况")
@RestController
@RequestMapping("/qhckqk/tjfxqhckqk")
public class TjfxQhckqkController extends JeecgController<TjfxQhckqk, ITjfxQhckqkService> {
    @Autowired
    private ITjfxQhckqkService tjfxQhckqkService;

    /**
     * 分页列表查询
     *
     * @param tjfxQhckqk
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "全行存款情况-分页列表查询")
    @ApiOperation(value = "全行存款情况-分页列表查询", notes = "全行存款情况-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxQhckqk tjfxQhckqk,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxQhckqk> queryWrapper = QueryGenerator.initQueryWrapper(tjfxQhckqk, req.getParameterMap());
        queryWrapper.orderByAsc("to_number(sszh)");
        Page<TjfxQhckqk> page = new Page<TjfxQhckqk>(pageNo, pageSize);
        IPage<TjfxQhckqk> pageList = tjfxQhckqkService.page(page, queryWrapper);
        if(pageList.getPages()==pageNo){
            String sjrq=tjfxQhckqk.getSjrq()==null?null: DateUtil.dateToString(tjfxQhckqk.getSjrq(),"yyyy-MM-dd").replace("-","");
            TjfxQhckqk newTjfxQhckqk=tjfxQhckqkService.getHjDate(sjrq,tjfxQhckqk.getSszh());
            List<TjfxQhckqk> list=pageList.getRecords();
            list.add(newTjfxQhckqk);
            pageList.setRecords(list);
        }
        return Result.ok(pageList);
    }

    /**
     * 获取表内最大日期
     */
    @GetMapping("getMaxDate")
    public Result<?> getMaxDate(){
        return Result.ok(tjfxQhckqkService.getMaxDate());
    }

    /**
     * 添加
     *
     * @param tjfxQhckqk
     * @return
     */
    @AutoLog(value = "全行存款情况-添加")
    @ApiOperation(value = "全行存款情况-添加", notes = "全行存款情况-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxQhckqk tjfxQhckqk) {
        tjfxQhckqkService.save(tjfxQhckqk);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxQhckqk
     * @return
     */
    @AutoLog(value = "全行存款情况-编辑")
    @ApiOperation(value = "全行存款情况-编辑", notes = "全行存款情况-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxQhckqk tjfxQhckqk) {
        tjfxQhckqkService.updateById(tjfxQhckqk);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行存款情况-通过id删除")
    @ApiOperation(value = "全行存款情况-通过id删除", notes = "全行存款情况-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxQhckqkService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "全行存款情况-批量删除")
    @ApiOperation(value = "全行存款情况-批量删除", notes = "全行存款情况-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxQhckqkService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行存款情况-通过id查询")
    @ApiOperation(value = "全行存款情况-通过id查询", notes = "全行存款情况-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxQhckqk tjfxQhckqk = tjfxQhckqkService.getById(id);
        return Result.ok(tjfxQhckqk);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxQhckqk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxQhckqk tjfxQhckqk) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<TjfxQhckqk> queryWrapper = QueryGenerator.initQueryWrapper(tjfxQhckqk, request.getParameterMap());
        queryWrapper.orderByAsc("to_number(sszh)");
        List<TjfxQhckqk> exportList = service.list(queryWrapper);

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "全行存款情况");
        mv.addObject(NormalExcelConstants.CLASS, TjfxQhckqk.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("全行存款情况" + "报表", "导出人:" + sysUser.getRealname(), "全行存款情况"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
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
        return super.importExcel(request, response, TjfxQhckqk.class);
    }

	/**
	 * 提取数据
	 */
	@RequestMapping(value = "/initData", method = RequestMethod.PUT)
	public Result<?> initData(@RequestBody JSONObject object) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String sjrq=object.getString("sjrq");
        if (StringUtils.isBlank(sjrq)){
            return Result.error("数据提取日期不能为空！");
        }
		try {
			service.initData(sjrq.replace("-",""),loginUser.getWorkNo());
		} catch (Exception e) {
			log.error("提取失败！",e.getMessage());
			return Result.error(e.getMessage());
		}
		return Result.ok("提取成功！");
	}
}
