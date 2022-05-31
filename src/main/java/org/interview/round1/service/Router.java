package org.interview.round1.service;

public interface Router {
    void addRoute(String path, String methodName);
    String route(String path);
}
