package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.type.Position;
import cn.globalyouth.cosineapi.dao.type.PositionDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IPositionService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.io.StringWriter;
import java.io.PrintWriter;



/**
 * @author liuyufeng 职位服务
 */
@Service
@Slf4j
public class PositionServiceImpl implements IPositionService {

    @Resource
    private PositionDao dao;

    @Override
    public ApiResponse<Integer> batchAddPosition() {
        String url = "https://resume.shixiseng.com/xiaozhao";
        int count = 0;
        try {
            List<Map<String, List<String>>> data = snatchPosition(url);
            if (data.size() > 0) {
                dao.deleteAll();
            }
            int code = 100;
            for (Map<String, List<String>> typeData : data) {
                // 先添加分类
                String typeKey = (String) typeData.keySet().toArray()[0];
                Position type = new Position();
                type.setName(typeKey);
                type.setCode(Integer.toString(code));
                if (1 == dao.addPosition(type)) {
                    count++;
                }
                // 添加各职位
                List<String> positions = typeData.get(typeKey);
                for (int i = 0; i < positions.size(); i++) {
                    Position position = new Position();
                    position.setName(positions.get(i));
                    position.setCode(Integer.toString(code * 10 + i + 1));
                    if (1 == dao.addPosition(position)) {
                        count++;
                    }
                }
                // 分类码+10
                code += 10;
            }
            return ApiResponse.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Position>> listPositionTypes() {
        try {
            return ApiResponse.success(dao.listTypes());
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Position>> listByType(String typeCode) {
        try {
            typeCode = typeCode.substring(0, 2) + "__";
            return ApiResponse.success(dao.listByType(typeCode));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<String> selectById(int id) {
        try {
            return ApiResponse.success(dao.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    private List<Map<String, List<String>>> snatchPosition(String url) throws IOException {
        List<Map<String, List<String>>> data = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        Elements elements = document.select(".type-list>div");
        for (Element div : elements) {
            Map<String, List<String>> typeData = new HashMap<>(1);
            Elements hrefs = div.getAllElements();
            String typeKey = "";
            List<String> positions = new ArrayList<>();
            for (int i = 1; i < hrefs.size(); i++) {
                if (i == 1) {
                    typeKey = hrefs.get(i).text();
                    continue;
                }
                positions.add(hrefs.get(i).text());
            }
            typeData.put(typeKey, positions);
            data.add(typeData);
        }
        return data;
    }

}
