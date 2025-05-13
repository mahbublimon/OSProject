package com.example.multithreadingdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.Duration;

public class MainController {
    @FXML
    private Label counterLabel;

    @FXML
    private Slider pollIntervalSlider;

    @FXML
    private Label intervalLabel;

    private CounterThread counterThread;
    private Timeline poller;

    @FXML
    public void initialize() {
        // Initialize counter thread
        counterThread = new CounterThread();
        counterThread.setDaemon(true);
        counterThread.start();

        // Configure slider
        pollIntervalSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updatePollInterval(newVal.doubleValue());
        });

        // Initial setup
        updatePollInterval(pollIntervalSlider.getValue());
    }

    private void updatePollInterval(double intervalMillis) {
        intervalLabel.setText(String.format("Poll Interval: %.0f ms", intervalMillis));

        if (poller != null) {
            poller.stop();
        }

        poller = new Timeline(
                new KeyFrame(Duration.millis(intervalMillis),
                        event -> updateCounterDisplay())
        );
        poller.setCycleCount(Timeline.INDEFINITE);
        poller.play();
    }

    private void updateCounterDisplay() {
        int currentValue = counterThread.getCounter();
        counterLabel.setText(String.format("Counter: %,d", currentValue));
    }

    public void shutdown() {
        if (counterThread != null) {
            counterThread.stopCounting();
        }
        if (poller != null) {
            poller.stop();
        }
    }
}