package com.codeborne.selenide.impl;

import com.codeborne.selenide.impl.SelenideLogger.EventStatus;
import com.codeborne.selenide.logevents.LogEvent;

import static com.codeborne.selenide.impl.SelenideLogger.EventStatus.IN_PROGRESS;

public class SelenideLog implements LogEvent {

  private final long startNs;
  private long endNs;
  private final String subject;
  private final String element;
  private EventStatus status = IN_PROGRESS;

  public SelenideLog(String element, String subject) {
    this.element = element;
    this.subject = subject;
    startNs = System.nanoTime();
  }

  @Override
  public String getSubject() {
    return this.subject;
  }

  @Override
  public String getStatus() {
    return this.status.name();
  }
  
  protected void setStatus(EventStatus status) {
    this.status = status;
    endNs = System.nanoTime();
  }

  @Override
  public String getElement() {
    return this.element;
  }
  
  @Override
  public long getDuration() {
    return (endNs-startNs) / 1000000;
  }

  @Override
  public String toString() {
    return "$(" + element + ") " + subject;
  }
}
