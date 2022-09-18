package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.resume.Resume;
import cn.globalyouth.cosineapi.model.bean.send.Company;
import cn.globalyouth.cosineapi.model.bean.send.Job;
import cn.globalyouth.cosineapi.model.bean.send.Send;
import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.dao.send.JobDao;
import cn.globalyouth.cosineapi.dao.send.SendDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.ISendService;
import cn.globalyouth.cosineapi.model.vo.req.SendMultiReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SendReqVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * @author liuyufeng, Rujun Yan
 * 投递服务
 */
@Service
@Slf4j
public class SendServiceImpl implements ISendService {

    @Resource
    private SendDao sendDao;
    @Resource
    private JobDao jobDao;

    @Value("${sendFilePath}")
    private String filePath;
    @Value("${baseurl}")
    private String baseUrl;

    @Override
    public ApiResponse<Object> sendOne(SendReqVo reqVo) {
        log.info("sendOne({})", reqVo);
        try {
            if (1 == sendDao.addSend(handleSend(reqVo))) {
                jobDao.incSendCnt(reqVo.getJobId());
                log.info("投递成功");
                return ApiResponse.success(null);
            }
            log.info("投递失败");
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
    public ApiResponse<Integer> sendList(SendMultiReqVo reqVo) {
        log.info("sendList({})", reqVo);
        try {
            int count = 0;
            List<Send> list = handleSend(reqVo);
            for (Send send : list) {
                if (1 == sendDao.addSend(send)) {
                    jobDao.incSendCnt(send.getJob().getId());
                    count++;
                }
            }
            log.info("投递成功{}个", count);
            return ApiResponse.success(count);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<String> exportSend(String dateStr) {
        log.info("exportSend({})", dateStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 计算相差的天数
            Date date = format.parse(dateStr);
            Date now = new Date();
            now.setHours(0);
            now.setMinutes(0);
            now.setSeconds(0);
            int dayBefore = (int) ((date.getTime() - now.getTime()) / (24 * 60 * 60 * 1000));
            // 查询出投递数据
            List<Send> sendList = sendDao.listSendByDayBefore(dayBefore);
            log.info("查询到投递数据{}条", sendList.size());
            // 输出到xlsx中
            String filePath = exportToXlsx(sendList, dateStr);
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

    @Override
    public ApiResponse<Boolean> isSend(int userId, int jobId) {
        try {
            if (0 == sendDao.countSend(userId, jobId)) {
                return ApiResponse.success(false);
            }
            return ApiResponse.success(true);
        } catch (Exception e) {
            log.error("文件导出失败");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> sendWeb(int userId, int companyId, int jobId, long addTime) {
        try {
            Timestamp time = new Timestamp(addTime);
            Date day = new Date(time.getTime());
            Integer state = 8;
            if (1 == sendDao.addWeb(handleWeb(userId,companyId,jobId,time))) {
                jobDao.incSendCnt(jobId);
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    private Send handleWeb(int userId, int companyId, int jobId, Timestamp time) {
        Send res = new Send();
        User u = new User();
        u.setId(userId);
        res.setUser(u);
        Company c = new Company();
        c.setId(companyId);
        res.setCompany(c);
        Job j = new Job();
        j.setId(jobId);
        res.setJob(j);
        res.setState(8);
        res.setAddTime(time);
        Date day = new Date(time.getTime());
        res.setDate1(day);
        return res;
    }

    private Send handleSend(SendReqVo reqVo) {
        Send res = new Send();
        User u = new User();
        u.setId(reqVo.getUserId());
        res.setUser(u);
        Resume r = new Resume();
        r.setId(reqVo.getResumeId());
        res.setResume(r);
        res.setResumeTitle(reqVo.getResumeTitle());
        Company c = new Company();
        c.setId(reqVo.getCompanyId());
        res.setCompany(c);
        Job j = new Job();
        j.setId(reqVo.getJobId());
        res.setJob(j);
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        res.setState(1);
        Timestamp time = new Timestamp(reqVo.getAddTime());
        res.setAddTime(time);
        Date day = new Date(time.getTime());
        res.setDate1(day);
        return res;
    }

    private List<Send> handleSend(SendMultiReqVo reqVo) {
        List<Send> list = new ArrayList<>();
        for (int i = 0; i < reqVo.getJobs().size(); i++) {
            Send res = new Send();
            User u = new User();
            u.setId(reqVo.getUserId());
            res.setUser(u);
            Resume r = new Resume();
            r.setId(reqVo.getResumeId());
            res.setResume(r);
            res.setResumeTitle(reqVo.getResumeTitle());
            Company c = new Company();
            c.setId(reqVo.getJobs().get(i).getCompanyId());
            res.setCompany(c);
            Job j = new Job();
            j.setId(reqVo.getJobs().get(i).getJobId());
            res.setJob(j);
            res.setState(1);
            Timestamp time = new Timestamp(reqVo.getAddTime());
            res.setAddTime(time);
            Date day = new Date(time.getTime());
            res.setDate1(day);
            list.add(res);
        }
        return list;
    }

    private String exportToXlsx(List<Send> sendList, String dateStr) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        createFirstRow(row0);
        for (int i = 0; i < sendList.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            createSendRow(row, sendList.get(i));
        }
        String file = filePath + File.separator + dateStr + ".xlsx";
        workbook.write(new FileOutputStream(file));
        return dateStr + ".xlsx";
    }

    /**
     * 创建第一行
     */
    private void createFirstRow(XSSFRow row0) {
        XSSFCell cell00 = row0.createCell(0, XSSFCell.CELL_TYPE_STRING);
        cell00.setCellValue("公司id");
        XSSFCell cell01 = row0.createCell(1, XSSFCell.CELL_TYPE_STRING);
        cell01.setCellValue("公司名称");
        XSSFCell cell02 = row0.createCell(2, XSSFCell.CELL_TYPE_STRING);
        cell02.setCellValue("公司联系人");
        XSSFCell cell03 = row0.createCell(3, XSSFCell.CELL_TYPE_STRING);
        cell03.setCellValue("公司联系方式");
        XSSFCell cell04 = row0.createCell(4, XSSFCell.CELL_TYPE_STRING);
        cell04.setCellValue("职位id");
        XSSFCell cell05 = row0.createCell(5, XSSFCell.CELL_TYPE_STRING);
        cell05.setCellValue("职位名称");
        XSSFCell cell06 = row0.createCell(6, XSSFCell.CELL_TYPE_STRING);
        cell06.setCellValue("职位城市");
        XSSFCell cell07 = row0.createCell(7, XSSFCell.CELL_TYPE_STRING);
        cell07.setCellValue("简历id");
        XSSFCell cell08 = row0.createCell(8, XSSFCell.CELL_TYPE_STRING);
        cell08.setCellValue("简历下载地址");
        XSSFCell cell09 = row0.createCell(9, XSSFCell.CELL_TYPE_STRING);
        cell09.setCellValue("简历名称");
        XSSFCell cell010 = row0.createCell(10, XSSFCell.CELL_TYPE_STRING);
        cell010.setCellValue("用户id");
        XSSFCell cell011 = row0.createCell(11, XSSFCell.CELL_TYPE_STRING);
        cell011.setCellValue("用户名");
        XSSFCell cell012 = row0.createCell(12, XSSFCell.CELL_TYPE_STRING);
        cell012.setCellValue("用户手机号");
    }

    /**
     * 创建数据行
     */
    private void createSendRow(XSSFRow row, Send send) {
        XSSFCell cell00 = row.createCell(0, XSSFCell.CELL_TYPE_NUMERIC);
        cell00.setCellValue(send.getCompany().getId());
        XSSFCell cell01 = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
        cell01.setCellValue(send.getCompany().getName());
        XSSFCell cell02 = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
        cell02.setCellValue(send.getCompany().getContact());
        XSSFCell cell03 = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
        cell03.setCellValue(send.getCompany().getEmail());
        XSSFCell cell04 = row.createCell(4, XSSFCell.CELL_TYPE_NUMERIC);
        cell04.setCellValue(send.getJob().getId());
        XSSFCell cell05 = row.createCell(5, XSSFCell.CELL_TYPE_STRING);
        cell05.setCellValue(send.getJob().getTitle());
        XSSFCell cell06 = row.createCell(6, XSSFCell.CELL_TYPE_STRING);
        cell06.setCellValue(send.getJob().getLocation());
        XSSFCell cell07 = row.createCell(7, XSSFCell.CELL_TYPE_NUMERIC);
        cell07.setCellValue(send.getResume().getId());
        XSSFCell cell08 = row.createCell(8, XSSFCell.CELL_TYPE_STRING);
        cell08.setCellValue(send.getResume().getResumeKey());
        XSSFCell cell09 = row.createCell(9, XSSFCell.CELL_TYPE_STRING);
        cell09.setCellValue(send.getResumeTitle());
        XSSFCell cell010 = row.createCell(10, XSSFCell.CELL_TYPE_NUMERIC);
        cell010.setCellValue(send.getUser().getId());
        XSSFCell cell011 = row.createCell(11, XSSFCell.CELL_TYPE_STRING);
        cell011.setCellValue(send.getUser().getName());
        XSSFCell cell012 = row.createCell(12, XSSFCell.CELL_TYPE_STRING);
        cell012.setCellValue(send.getUser().getPhone());
    }
}
