package com.shuai.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import com.shuai.model.EasyPoiUser;
import com.shuai.utils.EasyResponseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/easyPoi")
@Tag(name = "EasyPoiController", description = "EasyPoi相关操作")
public class EasyPoiController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EasyPoiController.class);

    /**
     * 构建模拟数据
     */
    private List<EasyPoiUser> buildMockData() {
        return CollUtil.newArrayList(
                new EasyPoiUser("张三", 18),
                new EasyPoiUser("李四", 20),
                new EasyPoiUser("王五", 22),
                new EasyPoiUser("赵六", 24),
                new EasyPoiUser("孙七", 26),
                new EasyPoiUser("周八", 28),
                new EasyPoiUser("吴九", 30),
                new EasyPoiUser("郑十", 32),
                new EasyPoiUser("王十一", 34),
                new EasyPoiUser("冯十二", 36),
                new EasyPoiUser("陈十三", 38),
                new EasyPoiUser("褚十四", 40),
                new EasyPoiUser("卫十五", 42),
                new EasyPoiUser("蒋十六", 44),
                new EasyPoiUser("沈十七", 46),
                new EasyPoiUser("韩十八", 48),
                new EasyPoiUser("杨十九", 50),
                new EasyPoiUser("朱二十", 52),
                new EasyPoiUser("秦二十一", 54),
                new EasyPoiUser("尤二十二", 56),
                new EasyPoiUser("许二十三", 58),
                new EasyPoiUser("何二十四", 60)
        );
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据")
    @PostMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<EasyPoiUser> data = buildMockData();
        ExportParams exportParams = new ExportParams("用户数据", "用户列表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(
                exportParams,
                EasyPoiUser.class,
                data
        );
        EasyResponseUtils.buildResponseHeader(response, "用户数据.xls");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 导出Excel 模版
     */
    @Operation(summary = "导出Excel 模版")
    @PostMapping("/exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        EasyPoiUser easyPoiUser = new EasyPoiUser("张三", 18);
        List<EasyPoiUser> data = CollUtil.newArrayList(easyPoiUser);
        ExportParams exportParams = new ExportParams("用户数据模板", "用户列表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(
                exportParams,
                EasyPoiUser.class,
                data
        );
        EasyResponseUtils.buildResponseHeader(response, "用户数据模板.xls");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 导入数据（单文件上传）
     * @param file 上传的Excel文件
     * @return 导入结果
     */
    @Operation(summary = "导入数据（单文件上传）")
    @PostMapping("/import")
    public List<EasyPoiUser> importData(@RequestParam("file") MultipartFile file) {
        try {
            // 设置导入参数
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(1);  // 标题行数（从第1行开始读取数据，跳过标题行）
            importParams.setHeadRows(1);   // 表头行数
            importParams.setStartRows(0);   // 起始行（0表示从第一行开始）

            // 执行导入
            List<EasyPoiUser> userList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    EasyPoiUser.class,
                    importParams
            );

            // 这里可以添加业务逻辑，比如保存到数据库
            System.out.println("导入成功，共 " + userList.size() + " 条数据");

            // 返回导入的数据
            return userList;

        } catch (Exception e) {
            logger.error("导入Excel失败：e" + e);
            throw new RuntimeException("导入Excel失败：" + e.getMessage());
        }
    }

    /**
     * 导入数据（多文件上传）
     * @param files 上传的多个Excel文件
     * @return 导入结果
     */
    @Operation(summary = "导入数据（多文件上传）")
    @PostMapping("/importBatch")
    public List<List<EasyPoiUser>> importBatchData(@RequestParam("files") MultipartFile[] files) {
        List<List<EasyPoiUser>> allResults = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                ImportParams importParams = new ImportParams();
                importParams.setTitleRows(1);
                importParams.setHeadRows(1);

                List<EasyPoiUser> userList = ExcelImportUtil.importExcel(
                        file.getInputStream(),
                        EasyPoiUser.class,
                        importParams
                );
                allResults.add(userList);
                System.out.println("文件 " + file.getOriginalFilename() + " 导入成功，共 " + userList.size() + " 条数据");

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("导入Excel失败：" + e.getMessage());
            }
        }

        return allResults;
    }

    /**
     * 导入数据并验证（带返回结果对象）
     * @param file 上传的Excel文件
     * @return 导入结果（包含成功数据、失败数据、错误信息等）
     */
    @Operation(summary = "导入数据并验证（带返回结果对象）")
    @PostMapping("/importWithValidate")
    public ImportResult importDataWithValidate(@RequestParam("file") MultipartFile file) {
        ImportResult result = new ImportResult();
        List<EasyPoiUser> successList = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        try {
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(1);
            importParams.setHeadRows(1);
            importParams.setNeedVerify(true);  // 开启验证

            List<EasyPoiUser> userList = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    EasyPoiUser.class,
                    importParams
            );

            // 业务验证
            for (int i = 0; i < userList.size(); i++) {
                EasyPoiUser user = userList.get(i);
                try {
                    // 示例：年龄验证
                    if (user.getAge() < 0 || user.getAge() > 150) {
                        errorMessages.add("第" + (i + 2) + "行：年龄必须在0-150之间");
                        continue;
                    }

                    // 示例：姓名验证
                    if (user.getName() == null || user.getName().trim().isEmpty()) {
                        errorMessages.add("第" + (i + 2) + "行：姓名不能为空");
                        continue;
                    }

                    // 保存到成功列表
                    successList.add(user);

                } catch (Exception e) {
                    errorMessages.add("第" + (i + 2) + "行：数据处理失败 - " + e.getMessage());
                }
            }

            // 这里可以批量保存成功的数据到数据库
            // userService.batchSave(successList);

            result.setSuccess(true);
            result.setTotalCount(userList.size());
            result.setSuccessCount(successList.size());
            result.setFailCount(errorMessages.size());
            result.setSuccessData(successList);
            result.setErrorMessages(errorMessages);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage("导入失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 导入结果内部类
     */
    public static class ImportResult {
        private boolean success;
        private int totalCount;
        private int successCount;
        private int failCount;
        private List<EasyPoiUser> successData;
        private List<String> errorMessages;
        private String errorMessage;

        // Getters and Setters
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }

        public int getTotalCount() { return totalCount; }
        public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

        public int getSuccessCount() { return successCount; }
        public void setSuccessCount(int successCount) { this.successCount = successCount; }

        public int getFailCount() { return failCount; }
        public void setFailCount(int failCount) { this.failCount = failCount; }

        public List<EasyPoiUser> getSuccessData() { return successData; }
        public void setSuccessData(List<EasyPoiUser> successData) { this.successData = successData; }

        public List<String> getErrorMessages() { return errorMessages; }
        public void setErrorMessages(List<String> errorMessages) { this.errorMessages = errorMessages; }

        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }
}