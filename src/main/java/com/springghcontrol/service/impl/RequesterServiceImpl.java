package com.springghcontrol.service.impl;

import com.springghcontrol.service.NodeService;
import com.springghcontrol.service.RequesterService;
import com.springghcontrol.util.NodeJson;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class RequesterServiceImpl implements RequesterService {

  NodeService nodeService;

  public RequesterServiceImpl(NodeService nodeService) {
    this.nodeService = nodeService;
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleAtFixedRate(this::getUpdateFromNode, 0, 10, TimeUnit.SECONDS);
  }


  private void getUpdateFromNode() {

    URL url = null;
    try {
      url = new URL("http://192.168.1.90/sensors");
      System.out.println("DEBUG: Requested");

      nodeService.update(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

}
