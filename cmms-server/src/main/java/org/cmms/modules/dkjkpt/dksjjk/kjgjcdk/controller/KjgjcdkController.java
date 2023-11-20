package org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.entity.Kjgjcdk;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.service.IKjgjcdkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSON;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 跨机构交叉贷款
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="跨机构交叉贷款")
@RestController
@RequestMapping("/dkjkpt/dksjjk/kjgjcdk")
public class KjgjcdkController extends JeecgController<Kjgjcdk, IKjgjcdkService> {
	@Autowired
	private IKjgjcdkService kjgjcdkService;
	
	/**
	 * 分页列表查询
	 *
	 * @param kjgjcdk
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-分页列表查询")
	@ApiOperation(value="跨机构交叉贷款-分页列表查询", notes="跨机构交叉贷款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Kjgjcdk kjgjcdk,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Kjgjcdk> queryWrapper = QueryGenerator.initQueryWrapper(kjgjcdk, req.getParameterMap());
		Page<Kjgjcdk> page = new Page<Kjgjcdk>(pageNo, pageSize);
		IPage<Kjgjcdk> pageList = kjgjcdkService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param kjgjcdk
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-添加")
	@ApiOperation(value="跨机构交叉贷款-添加", notes="跨机构交叉贷款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Kjgjcdk kjgjcdk) {
		kjgjcdkService.save(kjgjcdk);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param kjgjcdk
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-编辑")
	@ApiOperation(value="跨机构交叉贷款-编辑", notes="跨机构交叉贷款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Kjgjcdk kjgjcdk) {
		kjgjcdkService.updateById(kjgjcdk);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-通过id删除")
	@ApiOperation(value="跨机构交叉贷款-通过id删除", notes="跨机构交叉贷款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		kjgjcdkService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-批量删除")
	@ApiOperation(value="跨机构交叉贷款-批量删除", notes="跨机构交叉贷款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.kjgjcdkService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "跨机构交叉贷款-通过id查询")
	@ApiOperation(value="跨机构交叉贷款-通过id查询", notes="跨机构交叉贷款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Kjgjcdk kjgjcdk = kjgjcdkService.getById(id);
		return Result.ok(kjgjcdk);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param kjgjcdk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Kjgjcdk kjgjcdk) {
      return super.exportXls(request, kjgjcdk, Kjgjcdk.class, "跨机构交叉贷款");
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
      return super.importExcel(request, response, Kjgjcdk.class);
    }

	 /**
	  * 跨机构交叉贷款-提取
	  * @return
	  */
    @RequestMapping(value = "/init", method = RequestMethod.PUT)
	public Result<?> InitData() {
    	try {
    		kjgjcdkService.InitData();
		} catch (Exception e) {
			log.error("提取失败！",e.getMessage());
			return Result.error(e.getMessage());
		}
    	return Result.ok("提取成功！");
	}

}
