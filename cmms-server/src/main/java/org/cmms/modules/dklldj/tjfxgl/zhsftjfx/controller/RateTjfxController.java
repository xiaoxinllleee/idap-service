package org.cmms.modules.dklldj.tjfxgl.zhsftjfx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.dklldj.tjfxgl.zhsftjfx.entity.RateTjfx;
import org.cmms.modules.dklldj.tjfxgl.zhsftjfx.service.IRateTjfxService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 支行上浮统计(浏阳 - Version)
 * @Author: Penghr
 * @Date: 2022-01-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "支行上浮统计(浏阳-Version)")
@RestController
@RequestMapping("/tjfxgl/zhsftjfx")
public class RateTjfxController extends JeecgController<RateTjfx, IRateTjfxService> {
    @Autowired
    private IRateTjfxService iRateTjfxService;

    /**
     * 分页列表查询
     *
     * @param rateTjfx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "支行上浮统计(浏阳-Version)-分页列表查询")
    @ApiOperation(value = "支行上浮统计(浏阳-Version)-分页列表查询", notes = "支行上浮统计(浏阳-Version)-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateTjfx rateTjfx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateTjfx>> result = new Result<>();
        QueryWrapper<RateTjfx> queryWrapper = QueryGenerator.initQueryWrapper(rateTjfx, req.getParameterMap());
        queryWrapper.eq("zzlb",2);
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateTjfxService.class, iRateTjfxService, pageNo, pageSize, queryWrapper, "tjyf","zzbz");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rateTjfx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateTjfx rateTjfx) {
        return super.exportXls(request, rateTjfx, RateTjfx.class, "支行上浮统计(浏阳-Version)");
    }

}
