package com.pen.wind.log;

public interface FormatStrategy {

  void log(int priority, String tag, String message);
}
