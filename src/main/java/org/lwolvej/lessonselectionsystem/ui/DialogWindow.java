package org.lwolvej.lessonselectionsystem.ui;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRippler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogWindow {
    public void display(String message, JFXButton button) {
        Label label = new Label("提示信息");
        label.setTextFill(Color.BLACK);
        label.setStyle("-fx-font-size: 20px;");
        JFXRippler rippler = new JFXRippler(label);
        rippler.setAlignment(Pos.TOP_LEFT);

        JFXAlert alert = new JFXAlert((Stage) button.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(rippler);
        layout.setBody(new Label(message));

        JFXButton closeButton = new JFXButton("我知道了");
        closeButton.setStyle("-fx-background-color: rgba(19,98,255,0.94); -fx-text-fill: WHITE;");
        closeButton.getStyleClass().add("dialog-accept");
        closeButton.setOnAction(event -> alert.close());

        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }
}
