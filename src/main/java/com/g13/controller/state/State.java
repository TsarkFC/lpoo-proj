package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.model.Model;
import com.g13.view.View;

import java.io.IOException;

public interface State {
    Controller getController();
}
