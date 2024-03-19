package com.springghcontrol.controller;

import com.springghcontrol.repository.NodesRepository;
import com.springghcontrol.service.NodeService;
import com.springghcontrol.util.NodeJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/node")
public class NodeController {

    private final NodesRepository nodesRepository;

    private final NodeService nodeService;

    public NodeController(NodesRepository nodesRepository, NodeService nodeService) {
        this.nodesRepository = nodesRepository;
        this.nodeService = nodeService;
    }

    @GetMapping
    public List<NodeJson> getSensors() {
        return nodeService.getAllNodes();
    }

    @GetMapping("/get/{ip}")
    public ResponseEntity<NodeJson> getTemperature(@PathVariable("ip") String nodeIp) {
        Optional<NodeJson> nodeByIp = nodeService.getNodeByIp(nodeIp);
        return nodeByIp.map(nodeJson -> new ResponseEntity<>(nodeJson, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
