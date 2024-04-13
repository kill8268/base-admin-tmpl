package com.noobcat.admin.context;

import java.lang.ThreadLocal;
import org.openapitools.model.User;

public class UserContext {
  public static final ThreadLocal<User> currentUser = new ThreadLocal<>();
}
