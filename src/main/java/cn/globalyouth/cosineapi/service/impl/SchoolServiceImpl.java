package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.type.Major;
import cn.globalyouth.cosineapi.model.bean.type.School;
import cn.globalyouth.cosineapi.dao.type.MajorDao;
import cn.globalyouth.cosineapi.dao.type.SchoolDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.ISchoolService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuyufeng, Rujun Yan
 * 学校服务
 */
@Service
public class SchoolServiceImpl implements ISchoolService {

    @Resource
    private SchoolDao schoolDao;

    @Resource
    private MajorDao majorDao;

    /**
     * 通过xlsx批量添加学校
     *
     * @param filePath 文件路径
     * @return ApiResponse<Integer>
     */
    @Override
    public ApiResponse<Integer> batchAddSchool(String filePath) {
        String url = "http://news.sina.com.cn/zt_d/qswur2020";
        try {
            AtomicInteger count = new AtomicInteger();
            List<Map<String, String>> data = read(filePath);
            if (data.size() > 0) {
                schoolDao.deleteAll();
            }
            data.forEach(map -> {
                School school = new School();
                school.setId(Integer.parseInt(map.get("序号")));
                school.setName(map.get("学校名称"));
                school.setCode(map.get("学校标识码"));
                school.setMajor(map.get("主管部门"));
                school.setLocation(map.get("所在地"));
                school.setLayer(map.get("办学层次"));
                school.setComment(map.get("备注"));
                if (1 == schoolDao.addSchool(school)) {
                    count.getAndIncrement();
                }
            });
            data = snatchSchool(url);
            data.forEach(map -> {
                count.getAndIncrement();
                School school = new School();
                school.setId(count.get());
                school.setName(map.get("name"));
                school.setComment(map.get("comment"));
                school.setLocation(map.get("location"));
                schoolDao.addSchool(school);
            });
            return ApiResponse.success(count.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 通过网页批量添加专业
     *
     * @return ApiResponse<Integer>
     */
    @Override
    public ApiResponse<Integer> batchAddMajor() {
        String url = "http://www.gaosan.com/gaokao/247945.html";
        try {
            List<Map<String, String>> data = snatchMajor(url);
            if (data.size() > 0) {
                majorDao.deleteAll();
            }
            int count = 0;
            for (int i = 0; i < data.size(); i++) {
                Major major = new Major();
                major.setId(i + 1);
                String name = data.get(i).get("name");
                if (name.contains("(")) {
                    name = name.substring(0, name.indexOf("("));
                } else if (name.contains("（")) {
                    name = name.substring(0, name.indexOf("（"));
                }
                major.setName(name);
                major.setCode(data.get(i).get("code"));
                if (1 == majorDao.addMajor(major)) {
                    count++;
                }
            }
            return ApiResponse.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 模糊查询学校
     *
     * @param name 模糊查询匹配值
     * @return ApiResponse<List < String>>
     */
    @Override
    public ApiResponse<List<String>> listSchool(String name) {
        try {
            return ApiResponse.success(schoolDao.listSchool(name));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<String>> listMajor(String name) {
        try {
            return ApiResponse.success(majorDao.listMajor("%" + name + "%"));
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * @param file 文件路径
     * @return 每一条数据为一个Map，由List包装。Map中，每个字段的key为文档头的列标识。
     * @throws IOException 文件读取异常
     */
    private List<Map<String, String>> read(String file) throws IOException {
        java.util.List<Map<String, String>> data = new LinkedList<>();
        //读取文档
        XSSFWorkbook wb = new XSSFWorkbook(file);
        //获取sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        //获取所有行
        Iterator<Row> rows = sheet.rowIterator();
        //跳过前两行
        for (int i = 0; i < 2; i++) {
            rows.next();
        }
        //第三行为列标识
        XSSFRow row = (XSSFRow) rows.next();
        //存放列标识
        List<String> titles = new ArrayList<>();
        Iterator<Cell> titleCells = row.cellIterator();
        //读取每个列标识属性
        while (titleCells.hasNext()){
            XSSFCell titleCell = (XSSFCell) titleCells.next();
            titles.add(titleCell.getStringCellValue());
        }
        //读取行数据
        while (rows.hasNext()){
            row = (XSSFRow) rows.next();
            Map<String, String> map = new LinkedHashMap<>();
            Iterator<Cell> cells = row.cellIterator();
            //记录是第几格
            int count = 0;
            //读取单元格
            while (cells.hasNext()){
                XSSFCell cell = (XSSFCell) cells.next();
                //第一个为字符串，本行跳过
                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && count == 0){
                    break;
                }
                //数据存储进map
                switch (cell.getCellType()){
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        map.put(titles.get(count), String.valueOf(Math.round(cell.getNumericCellValue())));
                        count++;
                        break;
                    case XSSFCell.CELL_TYPE_STRING:
                        map.put(titles.get(count), cell.getStringCellValue());
                        count++;
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        map.put(titles.get(count), null);
                        count++;
                        break;
                    default:
                        count++;
                        break;
                }
            }
            //空map跳过
            if (map.size() > 0){
                data.add(map);
            }
        }
        return data;
    }

    /**
     * 爬取专业
     * @param url 网页url
     * @return List<Map<String, String>>
     * @throws IOException 文档转化异常
     */
    private List<Map<String, String>> snatchMajor(String url) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements elements = document.select("tbody>tr");
        for (Element element : elements) {
            Elements tds = element.getAllElements();
            Map<String, String> map = new HashMap<>(2);
            Element td1 = tds.get(1);
            if (null == td1.text() || "".equals(td1.text()) || "编号".equals(td1.text())) {
                continue;
            }
            Element td2 = tds.get(2);
            map.put("code", td1.text());
            map.put("name", td2.text());
            if (!map.isEmpty()) {
                data.add(map);
            }
        }
        return data;
    }

    /**
     * 爬取学校
     * @param url 网页url
     * @return List<Map<String, String>>
     * @throws IOException 文档转化异常
     */
    private List<Map<String, String>> snatchSchool(String url) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements elements = document.select(".tb01 tr");
        for (Element element : elements) {
            Elements tds = element.getAllElements();
            Map<String, String> map = new HashMap<>(3);
            Element td1 = tds.get(2);
            if (null == td1.text() || "".equals(td1.text()) || "中文名称".equals(td1.text())) {
                continue;
            }
            Element td2 = tds.get(3);
            Element td3 = tds.get(4);
            map.put("name", td1.text());
            map.put("comment", td2.text());
            map.put("location", td3.text());
            if (!map.isEmpty()) {
                data.add(map);
            }
        }
        return data;
    }
}
