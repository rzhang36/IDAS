package com.idas.springbootinit.manager;


import com.idas.springbootinit.common.ErrorCode;
import com.idas.springbootinit.exception.BusinessException;
import com.idas.yucongming.dev.client.YuCongMingClient;
import com.idas.yucongming.dev.common.BaseResponse;
import com.idas.yucongming.dev.model.DevChatRequest;
import com.idas.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用于对接 AI 平台
 */
@Service
public class AiManager {

    @Resource
    private YuCongMingClient yuCongMingClient;

    /**
     * AI 对话
     *
     * @param modelId
     * @param message
     * @return
     */
    public String doChat(long modelId, String message) {
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
}
