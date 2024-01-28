package com.base.admin.context;

import java.lang.ThreadLocal;
import org.openapitools.model.Auth;

public class UserContext {
  public static final ThreadLocal<Auth> currentUser = new ThreadLocal<>();
}
