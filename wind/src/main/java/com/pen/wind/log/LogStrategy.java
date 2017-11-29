package com.pen.wind.log;

public interface LogStrategy {

  void log(int priority, String tag, String message);
}
