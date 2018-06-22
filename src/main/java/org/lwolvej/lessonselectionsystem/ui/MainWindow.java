package org.lwolvej.lessonselectionsystem.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lwolvej.lessonselectionsystem.controller.MainWindowController;
import org.lwolvej.lessonselectionsystem.controller.OfficerController;
import org.lwolvej.lessonselectionsystem.controller.StudentController;
import org.lwolvej.lessonselectionsystem.controller.TeacherController;
import org.lwolvej.lessonselectionsystem.entity.Student;
import org.lwolvej.lessonselectionsystem.entity.Teacher;

import java.io.IOException;

public class MainWindow extends Application {

    public static void main(String... args) {
        launch(args);
    }

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            mainStage = primaryStage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_window.fxml"));
            Parent root = fxmlLoader.load();
            MainWindowController controller = fxmlLoader.getController();
            controller.setMainWindow(this);
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentWindow(Student student) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/student_window.fxml"));
            Parent root = fxmlLoader.load();
            StudentController controller = fxmlLoader.getController();
            controller.setMainWindow(this);
            controller.setStudent(student);
            controller.setStage(mainStage);

            controller.initStudent();
            controller.initTimeTable();
            controller.initScoreView();
            controller.initSelectLesson();

            Scene scene = new Scene(root, 900, 600);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOfficerWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/officer_window.fxml"));
            Parent root = fxmlLoader.load();
            OfficerController controller = fxmlLoader.getController();
            controller.setMainWindow(this);
            controller.setStage(mainStage);

            controller.initApproveLesson();
            controller.initStudent();
            controller.initDepartment();
            controller.initTeacherComBox();
            controller.initStudentDepartmentComBox();
            controller.initStudentGrade();

            Scene scene = new Scene(root, 900, 600);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTeacherWindow(Teacher teacher) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/teacher_window.fxml"));
            Parent root = fxmlLoader.load();
            TeacherController controller = fxmlLoader.getController();
            controller.setMainWindow(this);
            controller.setStage(mainStage);

            controller.setTeacher(teacher);
            controller.initTeacher();
            controller.initLessonDetail();
            controller.initGradeList();
            controller.initRoomList();
            controller.initTimeList();
            controller.initLessonIdList();

            Scene scene = new Scene(root, 900, 600);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
