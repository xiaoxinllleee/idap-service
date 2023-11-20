package org.cmms.modules.khjg.gzjg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.khjg.pszbjg.entity.GzQueryVo;
import org.cmms.modules.khjg.pszbjg.entity.ParamFEvInRe;
import org.cmms.modules.khjg.pszbjg.service.IParamFEvInReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/khjg/gzjg")
public class GzjgController {
    @Autowired
    private IParamFEvInReService paramFEvInReService;

    @AutoLog(value = "派生指标结果-分页列表查询")
    @ApiOperation(value="派生指标结果-分页列表查询", notes="派生指标结果-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(GzQueryVo gzQueryVo,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        Page page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        IPage<ParamFEvInRe> gzjg = paramFEvInReService.getGzjg(page, gzQueryVo);
        return Result.ok(gzjg);
    }

    @GetMapping(value = "/listIndexId")
    public Result<?> listIndexId(GzQueryVo gzQueryVo,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        Page page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        IPage<ParamFEvInRe> gzjg = paramFEvInReService.getgzbyfa(page, gzQueryVo);
        return Result.ok(gzjg);
    }

    @GetMapping(value = "/listJg")
    public Result<?> listJg(GzQueryVo gzQueryVo,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        Page page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        IPage<ParamFEvInRe> gzjg = paramFEvInReService.getgzbyjg(page,gzQueryVo);
        return Result.ok(gzjg);
    }
}
