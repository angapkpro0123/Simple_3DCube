package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Cube3DApp extends Application {
    protected Pane pane; // Pane chứa Box
    private TabPane objectControllers; // Pane add các slider điều chỉnh
    protected SubScene subScene; // Scene dùng để add light

    @Override
    public void start(Stage s) throws Exception {
        // Khởi tạo giao diện cho Window
        pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        SplitPane layout = new SplitPane();

        // Pane chứa các Sliders và Reset Button
        objectControllers = new TabPane();
        objectControllers.setStyle("-fx-open-tab-animation: NONE; -fx-close-tab-animation: NONE;");

        // Thêm pane chứa slider và pane chứa box vào layout
        layout.setOrientation(Orientation.VERTICAL);
        layout.getItems().addAll(subScene = new SubScene(pane, 800, 500), objectControllers);

        //add 3d objects
        add3dObjects();

        Scene sc = new Scene(layout);
        s.setScene(sc);
        s.show();
    }

    protected void addControllerTab(String name, Node... children) {
        Tab t = new Tab(name);
        t.setContent(new HBox(children));

        // Xét nếu truyền vào pane là reset button thì add listener cho button để reset app
        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof Button) {
                children[i].setTranslateY(20);
                children[i].setTranslateX(50);
                ((Button) children[i]).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        pane.getChildren().clear();
                        add3dObjects();
                    }
                });
            }
        }

        objectControllers.getTabs().clear();
        objectControllers.getTabs().add(t);
    }

    protected abstract void add3dObjects();
}
