package com.idas.springbootinit.constant;

/**
 * OpenAI 常量
 */
public interface OpenAiConstant {

    /**
     * OpenAI API Key
     */
    String TEST_TEST_API_KEY = "Replace with the correct API key";

    /**
     * OpenAI API URL
     */
    String API_URL = "https://api.openai.com/v1/chat/completions";

    String ASSISTANT_MESSAGE = "你现在是一名数据分析师和前端专家，我将按照以下固定格式给你提供内容给你如下数据格式：\n" +
            "分析需求：{数据分析的需求或者目标}" +
            "原始数据：{csv格式的原始数据，用,作为分隔符}\n" +
            "请你严格按照此以下数据格式方式回答我的问题,不要有任何多余的开头，结尾，注释等:" +
            "---{前端Echarts V5 的option配置对象（只包含option={}大括号内的内容，并且所有属性必须携带双引号），合理的将数据进行可视化展示，不要生成任何多余的注释等内容}---{明确的数据分析结论越详细越好，不要生成多余的注释}";

    String ASSISTANT_TEST_MESSAGE = "Please answer question with only yes or no";


}
