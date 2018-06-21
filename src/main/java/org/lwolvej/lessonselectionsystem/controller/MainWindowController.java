package org.lwolvej.lessonselectionsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.lwolvej.lessonselectionsystem.entity.Student;
import org.lwolvej.lessonselectionsystem.entity.Teacher;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.StudentService;
import org.lwolvej.lessonselectionsystem.service.TeacherService;
import org.lwolvej.lessonselectionsystem.ui.DialogWindow;
import org.lwolvej.lessonselectionsystem.ui.MainWindow;

import java.net.URL;
import java.util.ResourceBundle;

@ViewController(value = "/fxml/main_window.fxml")
public class MainWindowController implements Initializable {

    private MainWindow mainWindow;

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXRadioButton radioStudent;

    @FXML
    private JFXRadioButton radioTeacher;

    @FXML
    private JFXRadioButton radioOfficer;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private ImageView roleImage;

    //单选按钮组
    private final ToggleGroup group = new ToggleGroup();

    //角色信息
    private String role;

    //密码
    private String password;

    //账号
    private String id;

    //教师服务层
    private TeacherService teacherService = Factory.getInstance().create(TeacherService.class);

    //学生服务层
    private StudentService studentService = Factory.getInstance().create(StudentService.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //单选按钮加入组
        radioOfficer.setToggleGroup(group);
        radioStudent.setToggleGroup(group);
        radioTeacher.setToggleGroup(group);

        //设置提示消息
        textField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                textField.validate();
            }
        });

        //设置提示消息
        passwordField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                passwordField.validate();
            }
        });

        //设置单选动作，并设置图片
        group.selectedToggleProperty().addListener((o, oldVar, newVar) -> {
            if (group.getSelectedToggle() != null) {
                role = group.getSelectedToggle().getUserData().toString();
                final Image image = new Image(getClass()
                        .getResourceAsStream("/image/" + role + ".gif"));
                roleImage.setImage(image);
            }
        });

    }

    @FXML
    protected void login() {
        //获取账号
        if (textField.getText() != null && !textField.getText().isEmpty()) {
            //获取密码
            if (passwordField.getText() != null && !passwordField.getText().isEmpty()) {
                id = textField.getText();
                password = passwordField.getText();
            } else {
                new DialogWindow().display("密码为空", loginButton);
            }
        } else {
            new DialogWindow().display("账号为空", loginButton);
        }

        //如果角色信息此时不为空，则开始执行登录操作
        if (role != null) {
            switch (role) {
                //学生
                case "1": {
                    Student student = studentService.studentLogin(id, password);
                    if (student != null) {
                        mainWindow.showStudentWindow(student);
                    } else {
                        new DialogWindow().display("输入信息错误", loginButton);
                        passwordField.setText("");
                    }
                }
                break;
                //教师
                case "2": {
                    Teacher teacher = teacherService.teacherLogin(id, password);
                    if (teacher != null) {
                        mainWindow.showTeacherWindow(teacher);
                    } else {
                        new DialogWindow().display("输入信息错误", loginButton);
                        passwordField.setText("");
                    }
                }
                break;
                //教务处
                case "3": {
                    if (id.equals("admin") && password.equals("admin")) {
                        mainWindow.showOfficerWindow();
                    } else {
                        new DialogWindow().display("输入信息错误", loginButton);
                        passwordField.setText("");
                    }
                }
                break;
            }
        } else {
            new DialogWindow().display("角色信息为空", loginButton);
        }

    }
}
