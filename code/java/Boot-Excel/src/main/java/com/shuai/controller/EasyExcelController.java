
package com.shuai.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import com.shuai.listener.EasyExcelUserDataListener;
import com.shuai.model.EasyExcelUser;
import com.shuai.model.EasyPoiUser;
import com.shuai.utils.EasyResponseUtils;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/easyExcel")
@Tag(name = "EasyExcelController", description = "EasyExcel相关操作")
public class EasyExcelController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EasyExcelController.class);

    /**
     * 构建模拟数据
     */
    private List<EasyExcelUser> buildMockData() {
        return CollUtil.newArrayList(
                new EasyExcelUser("张三", 18),
                new EasyExcelUser("李四", 20),
                new EasyExcelUser("王五", 22),
                new EasyExcelUser("赵六", 24),
                new EasyExcelUser("孙七", 26),
                new EasyExcelUser("周八", 28),
                new EasyExcelUser("吴九", 30),
                new EasyExcelUser("郑十", 32),
                new EasyExcelUser("王十一", 34),
                new EasyExcelUser("冯十二", 36),
                new EasyExcelUser("陈十三", 38),
                new EasyExcelUser("褚十四", 40),
                new EasyExcelUser("卫十五", 42),
                new EasyExcelUser("蒋十六", 44),
                new EasyExcelUser("沈十七", 46),
                new EasyExcelUser("韩十八", 48),
                new EasyExcelUser("杨十九", 50),
                new EasyExcelUser("朱二十", 52),
                new EasyExcelUser("秦二十一", 54),
                new EasyExcelUser("尤二十二", 56),
                new EasyExcelUser("许二十三", 58),
                new EasyExcelUser("何二十四", 60)
        );
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<EasyExcelUser> data = buildMockData();
        EasyResponseUtils.buildResponseHeader(response, "用户数据.xls");
        EasyExcel.write(response.getOutputStream(), EasyExcelUser.class)
                        .sheet("用户数据")
                        .doWrite(data);
//        response.flushBuffer();
    }

    /**
     * 导出Excel 模版
     */
    @Operation(summary = "导出Excel模版")
    @GetMapping("/exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        EasyExcelUser data = new EasyExcelUser("张三", 18);
        EasyResponseUtils.buildResponseHeader(response, "用户数据模版.xlsx");
        EasyExcel.write(response.getOutputStream(), EasyExcelUser.class)
                .sheet("用户数据模版")
                .doWrite(CollUtil.newArrayList(data));
        response.flushBuffer();
    }

    /**
     * 导入数据（单文件上传）
     * @param file 上传的Excel文件
     * @return 导入结果
     */
    @Operation(summary = "导入数据")
    @PostMapping("/import")
    public List<EasyPoiUser> importData(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcelUserDataListener listener = new EasyExcelUserDataListener();
        return EasyExcel.read(
                file.getInputStream(),
                EasyExcelUser.class,
                listener
        ).sheet("用户数据").doReadSync();
    }

}