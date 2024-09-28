package com.idas.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel 工具类
 */
@Slf4j
public class ExcelUtils {

    /**
     * Excel 转换为 csv
     * @param multipartFile
     * @return
     */
    public static String excelToCsv(MultipartFile multipartFile) {
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:test01.xlsx");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("读取 Excel 文件失败", e);
        }
        if(CollUtil.isEmpty(list)) {
            return "empty";
        }
        //转换为 csv
        StringBuilder sb = new StringBuilder();

        //读取表头
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap<Integer, String>) list.get(0);
        //过滤掉空的表头
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//        System.out.println(StringUtils.join(headerList, ","));
        sb.append(StringUtils.join(headerList, ",")).append("\n");
        //读取数据
        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> row = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> rowList = row.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//            System.out.println(StringUtils.join(rowList, ","));
            sb.append(StringUtils.join(rowList, ",")).append("\n");

        }

//        System.out.println(list);
        return sb.toString();
    }

    public static void main(String[] args) {
        excelToCsv(null);
    }
}
