package org.vosao.service;

import org.jabsorb.JSONRPCBridge;
import org.vosao.service.back.LimitedUserService;

public interface LimitedService {

  public abstract void register(JSONRPCBridge bridge);

  public abstract void unregister(JSONRPCBridge bridge);

  public abstract LimitedUserService getUserService();

  public abstract void setUserService(LimitedUserService bean);

}