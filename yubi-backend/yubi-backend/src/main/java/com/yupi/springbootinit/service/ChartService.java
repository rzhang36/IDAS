package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.BiResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public interface ChartService extends IService<Chart> {

    BiResponse genChartUsingAi(String chartName, String chartType, MultipartFile multipartFile, String name, String goal, User user);

    boolean genChartUsingAiAsyncMq(long id);
}
