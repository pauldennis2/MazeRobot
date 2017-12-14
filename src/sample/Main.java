package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;
    Maze maze;

    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        maze = new Maze();
        primaryStage.setTitle("MazeRunner");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setOnMouseClicked(e -> advanceTime());
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(5);

        gc.setStroke(Color.color(0, 0, 0));

        Group rootGroup = new Group();
        rootGroup.getChildren().add(canvas);

        paintMaze(gc);

        Scene scene = new Scene(rootGroup, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    /*
        while (true) {
            Thread.sleep(1000);

            advanceTime();
            paintMaze(graphicsContext);
        }*/
    }

    public void advanceTime () {
        maze.robot.makeMove();
        MazeCell current = maze.cells[maze.robot.getxLoc()][maze.robot.getyLoc()];
        maze.robot.pickUp();
        paintMaze(gc);
    }


    public static final int PADDING = 10;
    public static final int CELL_SIZE = 70;
    public static final int CENTER = PADDING + CELL_SIZE / 2;
    public static final int SCALE = PADDING + CELL_SIZE;
    public void paintMaze (GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.color(0,0,0));
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gc.strokeRect(PADDING + SCALE * x, PADDING + SCALE * y, CELL_SIZE, CELL_SIZE);
                if (maze.cells[x][y].gem != null) {
                    switch (maze.cells[x][y].gem) {
                        case RED:
                            gc.setStroke(Color.color(1, 0, 0));
                            break;
                        case GREEN:
                            gc.setStroke(Color.color(0, 1, 0));
                            break;
                        case YELLOW:
                            gc.setStroke(Color.color(1, 1, 0));
                            break;
                    }
                    gc.strokeOval(CENTER + SCALE * x, CENTER + SCALE * y, 20, 20);
                    gc.setStroke(Color.color(0,0,0));
                }
            }
        }
        gc.setStroke(Color.color(0, 0, 1));
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                MazeCell current = maze.cells[x][y];
                int centerX = CENTER + SCALE * x;
                int centerY = CENTER + SCALE * y;
                //I believe every line gets drawn twice. Oh well.
                if (current.nConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX, centerY - SCALE);
                }
                if (current.eConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX + SCALE, centerY);
                }
                if (current.sConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX, centerY + SCALE);
                }
                if (current.wConnection != null) {
                    gc.strokeLine(centerX, centerY, centerX - SCALE, centerY);
                }
            }
        }
        gc.fillText("S", CENTER + SCALE * maze.start.xLoc, CENTER + SCALE * maze.start.yLoc);
        gc.fillText("E", CENTER + SCALE * maze.exit.xLoc, CENTER + SCALE * maze.exit.yLoc);
        Image octoCat = new Image("megacat4.png");
        Image arm = new Image("arm.png");
        gc.drawImage(octoCat, CENTER / 2+ SCALE * maze.robot.getxLoc(), CENTER / 2 + SCALE * maze.robot.getyLoc(), 40, 40);
        if (maze.armLoc.hasArm) {
            gc.drawImage(arm, CENTER / 2 + SCALE * maze.armLoc.xLoc, CENTER / 2 + SCALE * maze.armLoc.yLoc, 40, 40);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
