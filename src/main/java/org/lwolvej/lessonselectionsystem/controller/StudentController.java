package org.lwolvej.lessonselectionsystem.controller;

import com.google.common.collect.Lists;
import com.jfoenix.controls.*;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.dto.TimeTableDTO;
import org.lwolvej.lessonselectionsystem.entity.Score;
import org.lwolvej.lessonselectionsystem.entity.Student;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.LessonService;
import org.lwolvej.lessonselectionsystem.service.ScoreService;
import org.lwolvej.lessonselectionsystem.ui.DialogWindow;
import org.lwolvej.lessonselectionsystem.ui.MainWindow;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    /****引入界面*****/

    private MainWindow mainWindow;

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /***********引入界面**************/


    /********引入服务**********/

    private ScoreService scoreService = Factory.getInstance().create(ScoreService.class);

    private LessonService lessonService = Factory.getInstance().create(LessonService.class);


    /********引入服务**********/

    /********引入控件**********/

    //学生信息标签
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;

    //按钮
    @FXML
    private JFXButton buttonFor;
    @FXML
    private JFXButton buttonAdd;

    //表格
    @FXML
    private JFXTreeTableView treeView1;
    @FXML
    private JFXTreeTableView treeView2;
    @FXML
    private JFXTreeTableView treeView3;

    //输入框
    //课程表搜索
    @FXML
    private JFXTextField textField1;
    //分数搜索
    @FXML
    private JFXTextField textField2;
    //课程搜索
    @FXML
    private JFXTextField textField3;
    //课程号输入（成绩详情）
    @FXML
    private JFXTextField textField4;
    //课程号输入（选课）
    @FXML
    private JFXTextField textField5;

    @FXML
    private JFXTreeTableColumn<TimeTableView, String> treeColumn11;
    @FXML
    private JFXTreeTableColumn<TimeTableView, String> treeColumn12;
    @FXML
    private JFXTreeTableColumn<TimeTableView, String> treeColumn13;
    @FXML
    private JFXTreeTableColumn<TimeTableView, String> treeColumn14;
    @FXML
    private JFXTreeTableColumn<StudentScoreView, String> treeColumn21;
    @FXML
    private JFXTreeTableColumn<StudentScoreView, String> treeColumn22;
    @FXML
    private JFXTreeTableColumn<LessonAddView, String> treeColumn31;
    @FXML
    private JFXTreeTableColumn<LessonAddView, String> treeColumn32;
    @FXML
    private JFXTreeTableColumn<LessonAddView, String> treeColumn33;
    @FXML
    private JFXTreeTableColumn<LessonAddView, String> treeColumn34;
    @FXML
    private JFXTreeTableColumn<LessonAddView, String> treeColumn35;

    /********引入控件**********/


    /********引入实体**********/

    private Student student;

    private List<Score> scoreList;

    private List<TimeTableDTO> timeTableDTOList;

    private List<ManageDTO> lessons;

    public List<ManageDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<ManageDTO> lessons) {
        this.lessons = lessons;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public List<TimeTableDTO> getTimeTableDTOList() {
        return timeTableDTOList;
    }

    public void setTimeTableDTOList(List<TimeTableDTO> timeTableDTOList) {
        this.timeTableDTOList = timeTableDTOList;
    }

    /********引入实体**********/

    /********初始化工作**********/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //设置课程表
        treeColumn11.setCellValueFactory((TreeTableColumn.CellDataFeatures<TimeTableView, String> param) -> param.getValue().getValue().lessonName);
        treeColumn12.setCellValueFactory((TreeTableColumn.CellDataFeatures<TimeTableView, String> param) -> param.getValue().getValue().roomName);
        treeColumn13.setCellValueFactory((TreeTableColumn.CellDataFeatures<TimeTableView, String> param) -> param.getValue().getValue().timeName);
        treeColumn14.setCellValueFactory((TreeTableColumn.CellDataFeatures<TimeTableView, String> param) -> param.getValue().getValue().teacherName);

        //设置成绩
        treeColumn21.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentScoreView, String> param) -> param.getValue().getValue().scoreName);
        treeColumn22.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentScoreView, String> param) -> param.getValue().getValue().lessonName);

        //设置选课
        treeColumn31.setCellValueFactory((TreeTableColumn.CellDataFeatures<LessonAddView, String> param) -> param.getValue().getValue().lessonId);
        treeColumn32.setCellValueFactory((TreeTableColumn.CellDataFeatures<LessonAddView, String> param) -> param.getValue().getValue().lessonName);
        treeColumn33.setCellValueFactory((TreeTableColumn.CellDataFeatures<LessonAddView, String> param) -> param.getValue().getValue().teacherName);
        treeColumn34.setCellValueFactory((TreeTableColumn.CellDataFeatures<LessonAddView, String> param) -> param.getValue().getValue().timeName);
        treeColumn35.setCellValueFactory((TreeTableColumn.CellDataFeatures<LessonAddView, String> param) -> param.getValue().getValue().roomName);
    }


    //初始化学生信息
    public void initStudent() {
        label1.setText(student.getStudentId().toString());
        label2.setText(student.getStudentName());
        label3.setText(student.getStudentGender() ? "男" : "女");
        label4.setText(student.getStudentNumber());
        label5.setText(student.getDepartment().getDepartmentName());
        label6.setText(student.getProfession().getProfessionName());
        label7.setText(student.getClasses().getClassesName());
        label8.setText(student.getGrade().getGradeName().toString());
    }

    //初始化课程表
    public void initTimeTable() {
        ObservableList<TimeTableView> data = FXCollections.observableArrayList();
        timeTableDTOList = lessonService.queryTimeTable(student.getStudentId().toString());
        System.out.println("执行了一次课程表的初始化");
        if (timeTableDTOList != null) {
            for (TimeTableDTO dto : timeTableDTOList) {
                data.add(new TimeTableView(
                        dto.getLessonName(), dto.getRoomName(), dto.getClockName(), dto.getTeacherName())
                );
            }
            treeView1.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView1.setShowRoot(false);
            treeView1.setEditable(true);
        }
        textField1.textProperty().addListener(setupSearchField1(treeView1));
    }

    //初始化成绩表
    public void initScoreView() {
        ObservableList<StudentScoreView> data = FXCollections.observableArrayList();
        scoreList = scoreService.queryScoreByStudent(student.getStudentId().toString());
        System.out.println("执行了一次学生成绩初始化");
        if (scoreList != null) {
            for (Score score : scoreList) {
                data.add(new StudentScoreView(
                        score.getScoreNumber().toString(), score.getLesson().getLessonName())
                );
            }
            treeView2.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView2.setShowRoot(false);
            treeView2.setEditable(true);
        }
        textField2.textProperty().addListener(setupSearchField2(treeView2));
    }

    //初始化选课表单
    public void initSelectLesson() {
        ObservableList<LessonAddView> data = FXCollections.observableArrayList();
        lessons = lessonService.queryLessonCanSelect(student.getStudentId().toString());
        if (lessons != null) {
            lessons.forEach(obj ->
                    data.add(new LessonAddView(obj.getLessonId().toString(), obj.getLessonName(),
                            obj.getTeacherName(), obj.getRoomName(), obj.getClockName()))
            );
            treeView3.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView3.setShowRoot(false);
            treeView3.setEditable(true);
        }
        textField4.textProperty().addListener(setupSearchField3(treeView3));
    }
    /********初始化工作**********/


    /********查询框的监听**********/

    //课程表查询框的监听
    private ChangeListener<String> setupSearchField1(
            final JFXTreeTableView<StudentController.TimeTableView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(timeTableProp -> {
                    final TimeTableView view = timeTableProp.getValue();
                    return view.lessonName.get().contains(newVar)
                            || view.roomName.get().contains(newVar)
                            || view.lessonName.get().contains(newVar)
                            || view.teacherName.get().contains(newVar);
                });
    }

    //分数查询框的监听
    private ChangeListener<String> setupSearchField2(
            final JFXTreeTableView<StudentController.StudentScoreView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(scoreProp -> {
                    final StudentScoreView view = scoreProp.getValue();
                    return view.lessonName.get().contains(newVar)
                            || view.scoreName.get().contains(newVar);
                });
    }

    //选课查询框的监听
    public ChangeListener<String> setupSearchField3(
            final JFXTreeTableView<StudentController.LessonAddView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(lessonProp -> {
                    final LessonAddView view = lessonProp.getValue();
                    return view.lessonName.get().contains(newVar)
                            || view.lessonId.get().contains(newVar)
                            || view.roomName.get().contains(newVar)
                            || view.timeName.get().contains(newVar)
                            || view.teacherName.get().contains(newVar);
                });
    }

    /********查询框的监听**********/

    /****按钮功能设置****/
    @FXML
    protected void find() {
        String id = textField3.getText();
        if (id != null) {
            List<AnalyseDTO> dtos = scoreService.queryScoreAnalysis(id);
            if(dtos != null && dtos.size() != 0) {
                String arr = "最高分:" + dtos.get(0).getData() + ";最低分:" + dtos.get(1).getData() + ";平均分:" + dtos.get(2).getData();
                new DialogWindow().display(arr, buttonFor);
            }else{
                new DialogWindow().display("输入信息不正确", buttonFor);
                textField3.setText("");
            }
        } else {
            new DialogWindow().display("信息没有输入", buttonFor);
        }
    }

    @FXML
    protected void addLesson() {
        String id = textField5.getText();
        if (id != null) {
            List<String> data = Lists.newArrayList();
            data.add(id);
            Boolean l = lessonService.studentSelectLesson(student.getStudentId().toString(), data);
            if (l != null) {
                if (l) {
                    new DialogWindow().display("成功插入", buttonAdd);
                } else {
                    new DialogWindow().display("信息冲突", buttonAdd);
                }
            } else {
                new DialogWindow().display("信息无效", buttonAdd);
            }
        } else {
            new DialogWindow().display("信息没有输入", buttonFor);
        }
    }

    //课程表的刷新
    @FXML
    protected void refresh1() {
        initTimeTable();
    }

    //分数的刷新
    @FXML
    protected void refresh2() {
        initScoreView();
    }

    //课程列表的刷新
    @FXML
    protected void refresh3() {
        initSelectLesson();
    }

    @FXML
    protected void quit(){mainWindow.start(stage);}


    /****按钮功能设置*****/


    //课程表数据类型
    static final class TimeTableView extends RecursiveTreeObject<TimeTableView> {
        final StringProperty lessonName;

        final StringProperty roomName;

        final StringProperty timeName;

        final StringProperty teacherName;

        TimeTableView(String lessonName, String roomName, String timeName, String teacherName) {
            this.lessonName = new SimpleStringProperty(lessonName);
            this.roomName = new SimpleStringProperty(roomName);
            this.timeName = new SimpleStringProperty(timeName);
            this.teacherName = new SimpleStringProperty(teacherName);
        }
    }

    //学生成绩数据类型
    static final class StudentScoreView extends RecursiveTreeObject<StudentScoreView> {
        final StringProperty lessonName;

        final StringProperty scoreName;

        StudentScoreView(String lessonName, String scoreName) {
            this.lessonName = new SimpleStringProperty(lessonName);
            this.scoreName = new SimpleStringProperty(scoreName);
        }
    }

    //选课数据类型
    static final class LessonAddView extends RecursiveTreeObject<LessonAddView> {
        final StringProperty lessonId;

        final StringProperty lessonName;

        final StringProperty teacherName;

        final StringProperty roomName;

        final StringProperty timeName;

        LessonAddView(String lessonId, String lessonName, String teacherName, String roomName, String timeName) {
            this.lessonId = new SimpleStringProperty(lessonId);
            this.lessonName = new SimpleStringProperty(lessonName);
            this.teacherName = new SimpleStringProperty(teacherName);
            this.roomName = new SimpleStringProperty(roomName);
            this.timeName = new SimpleStringProperty(timeName);
        }
    }
}
