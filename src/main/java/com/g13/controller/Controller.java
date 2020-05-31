package com.g13.controller;

import java.io.IOException;

public interface Controller {
    void start() throws IOException;
    void render() throws IOException;
    boolean isFinished();
}
