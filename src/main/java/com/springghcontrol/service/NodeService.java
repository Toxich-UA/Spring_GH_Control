package com.springghcontrol.service;

import com.springghcontrol.util.NodeJson;

import java.util.List;
import java.util.Optional;

public interface NodeService {
    void update();

    void updateFromDb();

    List<NodeJson> getAllNodes();

    Optional<NodeJson> getNodeByIp(String ip);
}
