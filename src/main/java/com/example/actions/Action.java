package com.example.actions;

public interface Action {
    void execute();
    void undo();
    void redo();
}
