package com.atxzy.tensquare.client.impl;

import com.atxzy.tensquare.client.LabelClient;
import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR,"熔断器已启动");
    }
}
