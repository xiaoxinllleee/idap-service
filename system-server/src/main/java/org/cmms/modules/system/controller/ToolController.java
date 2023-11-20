package org.cmms.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.system.service.ToolService;
import org.cmms.modules.system.vo.TableComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tool")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping(value = "/getTableCols")
    public Result<?> getTableCols(@RequestParam(name = "tableName") String tableName) {
        List<TableComments> tableCols = toolService.getTableAll(tableName);
        return Result.ok(tableCols);
    }
}
