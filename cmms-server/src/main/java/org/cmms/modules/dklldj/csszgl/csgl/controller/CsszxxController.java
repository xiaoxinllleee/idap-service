package org.cmms.modules.dklldj.csszgl.csgl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import org.cmms.modules.dklldj.csszgl.csgl.service.ICsszxxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 参数管理
 * @Author: Penghr
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数管理")
@RestController
@RequestMapping("/csszgl/csgl")
public class CsszxxController extends JeecgController<Csszxx, ICsszxxService> {
	@Autowired
	private ICsszxxService csszxxService;

	/**
	 * 分页列表查询
	 * @param csszxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数管理-分页列表查询")
	@ApiOperation(value="参数管理-分页列表查询", notes="参数管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Csszxx csszxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<Csszxx>> result = new Result<IPage<Csszxx>>();
		QueryWrapper<Csszxx> queryWrapper = QueryGenerator.initQueryWrapper(csszxx, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(ICsszxxService.class,csszxxService, pageNo, pageSize, queryWrapper, "csid");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param csszxx
	 * @return
	 */
	@AutoLog(value = "参数管理-添加")
	@ApiOperation(value="参数管理-添加", notes="参数管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Csszxx csszxx) {
		csszxxService.save(csszxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 * @param csszxx
	 * @return
	 */
	@AutoLog(value = "参数管理-编辑")
	@ApiOperation(value="参数管理-编辑", notes="参数管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Csszxx csszxx) {
		try {
			csszxxService.updateById(csszxx);
			return Result.ok("参数信息编辑成功!");
		} catch (Throwable throwable) {
			//throwable.printStackTrace();
			log.error("系统错误，请联系管理员处理！"+throwable.getMessage());
			return Result.error("参数信息编辑失败!"+throwable.getMessage());
		}
	}

	/**
	 * 通过csid删除
	 * @param csid
	 * @return
	 */
	@AutoLog(value = "参数管理-通过csid删除")
	@ApiOperation(value="参数管理-通过csid删除", notes="参数管理-通过csid删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="csid",required=true) String csid) {
        try {
            csszxxService.deleteByCsid(csid);
        } catch (Exception e) {
            log.error("删除失败"+e.getMessage());
            return Result.error("删除失败!");
        }
		return Result.ok("删除成功!");
	}

    /**
     * 导出excel
     * @param request
     * @param csszxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Csszxx csszxx) {
      return super.exportXls(request, csszxx, Csszxx.class, "参数管理");
    }

}
