package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    Maze maze;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        maze = new Maze();
        primaryStage.setTitle("MazeRunner");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        System.out.println("W pressed");
                        break;
                    case A:
                        System.out.println("A pressed");
                        break;
                    case S:
                        System.out.println("S pressed");
                        break;
                    case D:
                        System.out.println("D pressed");
                        break;
                    default:
                        System.out.println("Other key was pressed (" + event.getCode() + ")");
                        break;
                }
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mouse event!");
                System.out.println(event);
            }
        });
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(5);

        graphicsContext.setStroke(Color.color(0, 0, 0));

        Group rootGroup = new Group();
        rootGroup.getChildren().add(canvas);

        paintMaze(graphicsContext);

        Scene scene = new Scene(rootGroup, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void paintMaze (GraphicsContext gc) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gc.strokeRect(10 + 60 * x, 10 + 60 * y, 50, 50);

                //Center
                gc.setStroke(Color.color(1, 0, 0));
                //gc.strokeRect(34 + 60 * x, 34 + 60 * y, 1, 1);
                gc.setStroke(Color.color(0, 0, 0));
            }
        }
        gc.setStroke(Color.color(0, 0, 1));
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                MazeCell current = maze.cells[x][y];
                int centerX = 34 + 60 * x;
                int centerY = 34 + 60 * y;
                if (current.nConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX, centerY - 60);
                }
                if (current.eConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX + 60, centerY);
                }
                if (current.sConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX, centerY + 60);
                }
                if (current.wConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX - 60, centerY);
                }
            }
        }
        gc.setStroke(Color.color(0, 1, 0));
        gc.fillText("S", 34 + 60 * maze.start.xLoc, 34 + 60 * maze.start.yLoc);
        gc.fillText("E", 34 + 60 * maze.end.xLoc, 34 + 60 * maze.end.yLoc);
        gc.fillText("R", 34 + 60 * maze.robot.getxLoc(), 34 + 60 * maze.robot.getyLoc());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
