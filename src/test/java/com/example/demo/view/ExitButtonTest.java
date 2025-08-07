package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ExitButtonTest {

    @Test
    public void testExitButtonIsAddedToGroup() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            // JavaFX initialized
        });

        Platform.runLater(() -> {
            try {
                Group root = new Group();
                Stage stage = new Stage();

                ExitButton.addTo(root, stage, 800);

                boolean buttonExists = root.getChildren().stream()
                        .anyMatch(node -> node instanceof Button && "X".equals(((Button) node).getText()));

                assertTrue(buttonExists, "Exit button with text 'X' should be added to the root group");
            } finally {
                latch.countDown();
            }
        });

        latch.await(); // Wait for test to finish on JavaFX thread
    }
}