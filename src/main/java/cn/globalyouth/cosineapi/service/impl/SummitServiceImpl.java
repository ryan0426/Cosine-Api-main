package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.summit.VisaInfo;
import cn.globalyouth.cosineapi.dao.summit.VisaInfoDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.ISummitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * @author liuyufeng, Rujun Yan 峰会接口服务
 */
@Service
@Slf4j
public class SummitServiceImpl implements ISummitService {

    @Resource
    private VisaInfoDao visaInfoDao;

    @Value("${sendFilePath}")
    private String filePath;
    @Value("${baseurl}")
    private String baseUrl;

    @Override
    public ApiResponse<Object> addInfo(VisaInfo data) {
        log.info("addInfo({})", data);
        try {
            data.setAddTime(new Timestamp(System.currentTimeMillis()));
            if (1 == visaInfoDao.addInfo(data)) {
                log.info("预约成功");
                return ApiResponse.success(null);
            }
            log.info("预约失败");
            return ApiResponse.error();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<String> exportInfo(String dateStr) {
        log.info("exportInfo({})", dateStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 计算相差的天数
            Date date = format.parse(dateStr);
            Date now = new Date();
            now.setHours(0);
            now.setMinutes(0);
            now.setSeconds(0);
            int dayBefore = (int) ((date.getTime() - now.getTime()) / (24 * 60 * 60 * 1000));
            List<VisaInfo> data = visaInfoDao.listByDayBefore(dayBefore);
            log.info("查询到注册数据{}条", data.size());
            String filePath = exportToXlsx(data, dateStr);
            log.info("xlsx输出成功");
            return ApiResponse.success(baseUrl + "/" + filePath);
        } catch (ParseException e) {
            log.error("日期格式不正确");
            e.printStackTrace();
            return ApiResponse.error(400, "日期格式不正确，应为'2021-04-01'格式。");
        } catch (IOException e) {
            log.error("文件导出失败");
            e.printStackTrace();
            return ApiResponse.error(402, "文件导出失败。");
        }
    }

    private String exportToXlsx(List<VisaInfo> data, String dateStr) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        createFirstRow(row0);
        for (int i = 0; i < data.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            createSendRow(row, data.get(i));
        }
        String file = filePath + File.separator + "summit-" + dateStr + ".xlsx";
        workbook.write(new FileOutputStream(file));
        return "summit-" + dateStr + ".xlsx";

    }

    private void createFirstRow(XSSFRow row0) {
        XSSFCell cell00 = row0.createCell(0, XSSFCell.CELL_TYPE_STRING);
        cell00.setCellValue("数据id");
        XSSFCell cell01 = row0.createCell(1, XSSFCell.CELL_TYPE_STRING);
        cell01.setCellValue("姓名");
        XSSFCell cell02 = row0.createCell(2, XSSFCell.CELL_TYPE_STRING);
        cell02.setCellValue("手机号");
        XSSFCell cell03 = row0.createCell(3, XSSFCell.CELL_TYPE_STRING);
        cell03.setCellValue("所在地区");
    }

    private void createSendRow(XSSFRow row, VisaInfo data) {
        XSSFCell cell00 = row.createCell(0, XSSFCell.CELL_TYPE_NUMERIC);
        cell00.setCellValue(data.getId());
        XSSFCell cell01 = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
        cell01.setCellValue(data.getName());
        XSSFCell cell02 = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
        cell02.setCellValue(data.getPhone());
        XSSFCell cell03 = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
        cell03.setCellValue(data.getLocation());
    }

}
