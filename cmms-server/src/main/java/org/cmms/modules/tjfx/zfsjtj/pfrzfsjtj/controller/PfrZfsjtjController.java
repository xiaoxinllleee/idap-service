package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.TjfxZcsxjdbXzdk;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfMxVo;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfsjtj;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfsjtjVo;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.service.IPfrZfsjtjService;
import org.cmms.modules.util.DateUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 陪访人走访数据统计
 * @Author: jeecg-boot
 * @Date: 2022-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "陪访人走访数据统计")
@RestController
@RequestMapping("/zfsjtj/pfrZfsjtj")
public class PfrZfsjtjController extends JeecgController<PfrZfsjtj, IPfrZfsjtjService> {
    @Autowired
    private IPfrZfsjtjService pfrZfsjtjService;

    /**
     * 分页列表查询
     *
     * @param pfrZfsjtj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-分页列表查询")
    @ApiOperation(value = "陪访人走访数据统计-分页列表查询", notes = "陪访人走访数据统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(PfrZfsjtj pfrZfsjtj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<PfrZfsjtj> queryWrapper = QueryGenerator.initQueryWrapper(pfrZfsjtj, req.getParameterMap());
        Page<PfrZfsjtj> page = new Page<PfrZfsjtj>(pageNo, pageSize);
        IPage<PfrZfsjtj> pageList = pfrZfsjtjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param pfrZfsjtj
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-添加")
    @ApiOperation(value = "陪访人走访数据统计-添加", notes = "陪访人走访数据统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody PfrZfsjtj pfrZfsjtj) {
        pfrZfsjtjService.save(pfrZfsjtj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pfrZfsjtj
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-编辑")
    @ApiOperation(value = "陪访人走访数据统计-编辑", notes = "陪访人走访数据统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody PfrZfsjtj pfrZfsjtj) {
        pfrZfsjtjService.updateById(pfrZfsjtj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-通过id删除")
    @ApiOperation(value = "陪访人走访数据统计-通过id删除", notes = "陪访人走访数据统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pfrZfsjtjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-批量删除")
    @ApiOperation(value = "陪访人走访数据统计-批量删除", notes = "陪访人走访数据统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pfrZfsjtjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "陪访人走访数据统计-通过id查询")
    @ApiOperation(value = "陪访人走访数据统计-通过id查询", notes = "陪访人走访数据统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        PfrZfsjtj pfrZfsjtj = pfrZfsjtjService.getById(id);
        return Result.ok(pfrZfsjtj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param pfrZfsjtj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PfrZfsjtj pfrZfsjtj) {
        return super.exportXls(request, pfrZfsjtj, PfrZfsjtj.class, "陪访人走访数据统计");
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
        return super.importExcel(request, response, PfrZfsjtj.class);
    }

	/**
	 * 新田-总行陪访人走访统计明细
	 * @param lx 明细类型
	 * @param tjrq 统计日期
	 * @param gwbz 岗位标志
	 * @param yggh 员工工号
	 * @param khlx 客户类型
	 * @return
	 */
    @GetMapping("/getPfrZfMx")
	public Result<?> getPfrZfMx(@RequestParam("lx")String lx,
								@RequestParam("tjrq")String tjrq,
								@RequestParam("gwbz")String gwbz,
								@RequestParam("yggh")String yggh,
								@RequestParam("khlx")String khlx,
								@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								HttpServletRequest req){
		if (StringUtils.isNotBlank(lx) && StringUtils.isNotBlank(khlx)){
			Page<PfrZfMxVo> page = new Page<>(pageNo, pageSize);
			IPage<PfrZfMxVo> pfrZfMxVoIPage = null;
			String weekFristDay=DateUtil.getWeekFristDayString(DateUtil.string2Date(tjrq,null),"yyyy-MM-dd").replace("-","");
			//农户
			if ("1".equals(khlx)){
				pfrZfMxVoIPage=service.getPfrZfMxNhPage(page,lx,tjrq.replace("-",""),gwbz,yggh, weekFristDay);
				return Result.ok(pfrZfMxVoIPage);
			//商户
			}else {
				pfrZfMxVoIPage=service.getPfrZfMxShPage(page,lx,tjrq.replace("-",""),gwbz,yggh,weekFristDay);
				return Result.ok(pfrZfMxVoIPage);
			}
		}
		return Result.ok();
	}

    /**
     * 导出excel-新田
     *
     * @param request
     * @param pfrZfsjtj
     */
    @RequestMapping(value = "/exportXlsXt")
    public ModelAndView exportXlsXt(HttpServletRequest request, PfrZfsjtj pfrZfsjtj) {
        // Step.1 组装查询条件
        QueryWrapper<PfrZfsjtj> queryWrapper = QueryGenerator.initQueryWrapper(pfrZfsjtj, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        //20211201 过滤选中数据
        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if(oConvertUtils.isNotEmpty(rowKey)){
                queryWrapper.in(rowKey,selectionList);
            }else{
                queryWrapper.in("ID",selectionList);
            }
        }

        // Step.2 获取导出数据
        List<PfrZfsjtj> list = service.list(queryWrapper);
        List<PfrZfsjtjVo> exportList=new ArrayList<>();
        list.forEach(e->{
            PfrZfsjtjVo pfrZfsjtjVo=new PfrZfsjtjVo();
            BeanUtils.copyProperties(e,pfrZfsjtjVo);
            exportList.add(pfrZfsjtjVo);
        });


        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "总行陪访人走访统计"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, PfrZfsjtjVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("总行陪访人走访统计" + "报表", "导出人:" + sysUser.getRealname(), "总行陪访人走访统计"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }
}
