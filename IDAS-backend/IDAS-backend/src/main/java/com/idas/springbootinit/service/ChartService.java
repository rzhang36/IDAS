package com.idas.springbootinit.service;

import com.idas.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.idas.springbootinit.model.entity.User;
import com.idas.springbootinit.model.vo.BiResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public interface ChartService extends IService<Chart> {

    BiResponse genChartUsingAi(String chartName, String chartType, MultipartFile multipartFile, String name, String goal, User user);

    boolean genChartUsingAiAsyncMq(long id);
}
