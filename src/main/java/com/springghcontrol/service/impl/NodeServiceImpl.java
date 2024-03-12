package com.springghcontrol.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springghcontrol.service.NodeService;
import com.springghcontrol.util.NodeJson;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class NodeServiceImpl implements NodeService {

  private NodeJson nodeJson;
  ObjectMapper om;


  public NodeServiceImpl(NodeJson nodeJson) {
    this.nodeJson = nodeJson;
    om = new ObjectMapper();
  }

  @Override
  public void update(URL url) {

    try {
      nodeJson = om.readValue(url, NodeJson.class);
    } catch (IOException e) {
      log.log(Level.ALL, "Unable to parse update for Node");
    }
  }
}
