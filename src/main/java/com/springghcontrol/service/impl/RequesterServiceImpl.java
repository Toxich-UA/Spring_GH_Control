package com.springghcontrol.service.impl;

import com.springghcontrol.service.NodeService;
import com.springghcontrol.service.RequesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class RequesterServiceImpl implements RequesterService {

    NodeService nodeService;

    public RequesterServiceImpl(NodeService nodeService) {
        this.nodeService = nodeService;
    }


    @Scheduled(fixedDelay = 5000)
    private void getUpdateFromNodes() {
        nodeService.update();
    }

}
