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
import org.lwolvej.lessonselectionsystem.dto.LessonDTO;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.dto.ScoreDTO;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.*;
import org.lwolvej.lessonselectionsystem.ui.DialogWindow;
import org.lwolvej.lessonselectionsystem.ui.MainWindow;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    private MainWindow mainWindow;

    private Stage stage;

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private ScoreService scoreService = Factory.getInstance().create(ScoreService.class);

    private ManageService manageService = Factory.getInstance().create(ManageService.class);

    private StudentService studentService = Factory.getInstance().create(StudentService.class);

    private LessonService lessonService = Factory.getInstance().create(LessonService.class);

    private GradeService gradeService = Factory.getInstance().create(GradeService.class);

    private RoomService roomService = Factory.getInstance().create(RoomService.class);

    private ClockService clockService = Factory.getInstance().create(ClockService.class);

    //信息界面
    @FXML
    private Label teacherIdLabel;
    @FXML
    private Label teacherNameLabel;
    @FXML
    private Label teacherSiteLabel;
    @FXML
    private Label teacherNumberLabel;
    @FXML
    private Label teacherGenderLabel;
    @FXML
    private Label teacherDepartmentLabel;

    //第一个tab
    @FXML
    private JFXTreeTableView<LessonDetailView> treeView1;
    @FXML
    private JFXTreeTableColumn<LessonDetailView, String> treeColumn11;
    @FXML
    private JFXTreeTableColumn<LessonDetailView, String> treeColumn12;
    @FXML
    private JFXTreeTableColumn<LessonDetailView, String> treeColumn13;
    @FXML
    private JFXTreeTableColumn<LessonDetailView, String> treeColumn14;
    @FXML
    private JFXTreeTableColumn<LessonDetailView, String> treeColumn15;
    @FXML
    private JFXTextField searchField1;

    //第二个tab
    @FXML
    private JFXTextField lessonField1;
    @FXML
    private JFXButton searchLessonButton;
    @FXML
    private JFXListView<Label> scoreListView;
    @FXML
    private Label lessonNameLabel;

    //第三个tab
    @FXML
    private JFXButton addLessonButton;
    @FXML
    private JFXComboBox<String> timeComBox;
    @FXML
    private JFXComboBox<String> roomComBox;
    @FXML
    private JFXComboBox<String> gradeComBox;
    @FXML
    private JFXTextField lessonNameField;

    //第四个Tab
    @FXML
    private JFXTextField lessonDeleteField;
    @FXML
    private JFXButton deleteLessonButton;

    //第六个tab
    @FXML
    private JFXComboBox<String> lessonIdComBox;
    @FXML
    private JFXComboBox<String> studentIdComBox;
    @FXML
    private JFXTextField studentLessonScore;
    @FXML
    private JFXButton addScoreButton;

    //第五个tab
    @FXML
    private JFXTextField studentIdNeedToChangeScore;
    @FXML
    private JFXButton searchStudentButton;
    @FXML
    private JFXListView<Label> studentListView;
    @FXML
    private JFXTextField lessonNeedToChange;
    @FXML
    private JFXTextField lessonNumberChange;
    @FXML
    private JFXButton changeLessonButton;


    private Teacher teacher;

    private List<Score> scoreList;

    private List<ManageDTO> manageList;

    private List<Student> students;

    private String timeId;

    private String roomId;

    private String gradeId;

    private String lessonSelectId;

    private String studentSelectId;

    private Student studentChangeScore;

    public Student getStudentChangeScore() {
        return studentChangeScore;
    }

    public void setStudentChangeScore(Student studentChangeScore) {
        this.studentChangeScore = studentChangeScore;
    }

    public String getStudentSelectId() {
        return studentSelectId;
    }

    public void setStudentSelectId(String studentSelectId) {
        this.studentSelectId = studentSelectId;
    }

    public String getLessonSelectId() {
        return lessonSelectId;
    }

    public void setLessonSelectId(String lessonSelectId) {
        this.lessonSelectId = lessonSelectId;
    }

    public ScoreService getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<ManageDTO> getManageList() {
        return manageList;
    }

    public void setManageList(List<ManageDTO> manageList) {
        this.manageList = manageList;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //课程类型表格组装
        treeColumn11.setCellValueFactory((TreeTableColumn.CellDataFeatures<TeacherController.LessonDetailView, String> param) -> param.getValue().getValue().lessonId);
        treeColumn12.setCellValueFactory((TreeTableColumn.CellDataFeatures<TeacherController.LessonDetailView, String> param) -> param.getValue().getValue().lessonName);
        treeColumn13.setCellValueFactory((TreeTableColumn.CellDataFeatures<TeacherController.LessonDetailView, String> param) -> param.getValue().getValue().roomName);
        treeColumn14.setCellValueFactory((TreeTableColumn.CellDataFeatures<TeacherController.LessonDetailView, String> param) -> param.getValue().getValue().timeName);
        treeColumn15.setCellValueFactory((TreeTableColumn.CellDataFeatures<TeacherController.LessonDetailView, String> param) -> param.getValue().getValue().gradeName);

        //设置复选框的监听
        timeComBox.setOnAction(e -> timeId = timeComBox.getSelectionModel().getSelectedItem().split(":")[0]);
        roomComBox.setOnAction(e -> roomId = roomComBox.getSelectionModel().getSelectedItem().split(":")[0]);
        gradeComBox.setOnAction(e -> gradeId = gradeComBox.getSelectionModel().getSelectedItem().split(":")[0]);

        lessonIdComBox.setOnAction(e -> {lessonSelectId = lessonIdComBox.getSelectionModel().getSelectedItem(); initStudentIdList();});
        studentIdComBox.setOnAction(e -> studentSelectId = studentIdComBox.getSelectionModel().getSelectedItem());
    }

    //初始化教师信息
    public void initTeacher() {
        teacherIdLabel.setText(teacher.getTeacherId().toString());
        teacherNameLabel.setText(teacher.getTeacherName());
        teacherSiteLabel.setText(teacher.getTeacherSite());
        teacherNumberLabel.setText(teacher.getTeacherNumber());
        teacherGenderLabel.setText(teacher.getTeacherGender() ? "男" : "女");
        teacherDepartmentLabel.setText(teacher.getDepartment().getDepartmentName());
    }

    //初始化课程详情
    public void initLessonDetail() {
        ObservableList<TeacherController.LessonDetailView> data = FXCollections.observableArrayList();
        manageList = manageService.queryTeacherLesson(teacher.getTeacherId().toString());
        if (manageList != null) {
            manageList.forEach(obj ->
                    data.add(new LessonDetailView(obj.getLessonId().toString(), obj.getLessonName(),
                            obj.getRoomName(), obj.getClockName(), obj.getGradeName().toString()))
            );
            treeView1.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView1.setShowRoot(false);
            treeView1.setEditable(true);
        }
        searchField1.textProperty().addListener(setupSearchField1(treeView1));
    }

    //课程表搜索监听
    private ChangeListener<String> setupSearchField1(
            final JFXTreeTableView<TeacherController.LessonDetailView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(tableViewProp -> {
                    final LessonDetailView view = tableViewProp.getValue();
                    return view.lessonName.get().contains(newVar)
                            || view.lessonId.get().contains(newVar)
                            || view.gradeName.get().contains(newVar)
                            || view.timeName.get().contains(newVar)
                            || view.roomName.get().contains(newVar);
                });
    }

    //初始化教室列表
    public void initRoomList() {
        roomService.queryAllRoom().forEach(obj -> roomComBox.getItems().add(obj.getRoomId() + ":" + obj.getRoomName()));
    }

    //初始化时间列表
    public void initTimeList() {
        clockService.queryAllClock().forEach(obj -> timeComBox.getItems().add(obj.getClockId() + ":" + obj.getClockName()));
    }

    //初始化年级列表
    public void initGradeList() {
        gradeService.queryAllGrade().forEach(obj -> gradeComBox.getItems().add(obj.getGradeId() + ":" + obj.getGradeName()));
    }

    public void initLessonIdList() {
        List<ManageDTO> manages = manageService.queryTeacherLesson(teacher.getTeacherId().toString());
        if(manages != null && manages.size() != 0) {
            manages.forEach(obj -> lessonIdComBox.getItems().add(obj.getLessonId().toString()));
        }
    }

    private void initStudentIdList() {
        scoreService.queryScoreByTeacher(lessonSelectId).forEach(obj -> studentIdComBox.getItems().add(obj.getStudent().getStudentId().toString()));
    }


    @FXML
    protected void quit() {
        mainWindow.start(stage);
    }

    @FXML
    protected void refresh1() {
        initLessonDetail();
    }

    @FXML
    protected void searchScore() {
        String lessonId = lessonField1.getText();
        students = studentService.queryStudentByLessonId(lessonId);
        if (students != null && students.size() != 0) {
            students.forEach(obj -> {
                Label label = new Label(obj.getStudentName());
                scoreListView.getItems().add(label);
            });
            Lesson lesson = lessonService.queryOneLesson(lessonId);
            lessonNameLabel.setText(lesson.getLessonName());
        } else {
            new DialogWindow().display("课程号错误", searchLessonButton);
        }
    }

    //新增课程
    @FXML
    protected void addNewLesson() {
        String lessonName = lessonNameField.getText().trim();
        if (roomId != null && gradeId != null && timeId != null && !("".equals(lessonName.trim()))) {
            LessonDTO dto = new LessonDTO();
            dto.setLessonName(lessonName);
            dto.setRoomId(Long.parseLong(roomId.trim()));
            dto.setGradeId(Long.parseLong(gradeId.trim()));
            dto.setClockId(Long.parseLong(timeId.trim()));
            dto.setTeacherId(teacher.getTeacherId());
            Boolean l = lessonService.addNewLesson(dto);
            if (l) {
                new DialogWindow().display("创建成功", addLessonButton);
            } else {
                new DialogWindow().display("创建失败", addLessonButton);
            }
        } else {
            new DialogWindow().display("输入信息残缺", addLessonButton);
        }
    }

    //删除课程
    @FXML
    protected void deleteLesson() {
        String lessonId = lessonDeleteField.getText();
        if (lessonId != null && !lessonId.equals("")) {
            Boolean l = lessonService.removeLesson(lessonId);
            if (l) {
                new DialogWindow().display("删除成功", deleteLessonButton);
            } else {
                new DialogWindow().display("删除失败", deleteLessonButton);
            }
        } else {
            new DialogWindow().display("没有输入信息", deleteLessonButton);
        }
    }
    //录入成绩
    @FXML
    protected void addScore() {
        String number = studentLessonScore.getText();
        if (lessonSelectId != null && studentSelectId != null && number != null) {
            List<ScoreDTO> data = Lists.newArrayList();
            ScoreDTO dto = new ScoreDTO();
            dto.setStudentId(studentSelectId);
            dto.setLessonId(lessonSelectId);
            dto.setScoreNumber(number);
            data.add(dto);
            Boolean l = scoreService.addScore(data);
            if (l) {
                new DialogWindow().display("插入成功", addScoreButton);
            } else {
                new DialogWindow().display("插入失败", addScoreButton);
            }
        } else {
            new DialogWindow().display("信息不完全", addScoreButton);
        }
    }
    //查找学生以及其所有学科成绩
    @FXML
    protected void searchStudent() {
        String id = studentIdNeedToChangeScore.getText();
        if(id != null && !id.equals("")) {
            List<Score> scores = scoreService.queryScoreByStudent(id);
            if(scores != null && scores.size() != 0) {
                studentChangeScore = scores.get(0).getStudent();
                scores.forEach(obj -> {
                    Label label = new Label(obj.getStudent().getStudentName() + " " + obj.getLesson().getLessonName() + " " + obj.getScoreNumber());
                    studentListView.getItems().add(label);
                });
            }else{
                new DialogWindow().display("", searchLessonButton);
            }
        }else{
            new DialogWindow().display("信息不正确", searchStudentButton);
        }
    }
    //修改成绩
    @FXML
    protected void changeLesson() {
        String id = lessonNeedToChange.getText();
        String number = lessonNumberChange.getText();
        if(!number.equals("") && number != null
                && id != null && !id.equals("")){
            ScoreDTO dto = new ScoreDTO();
            dto.setLessonId(id);
            dto.setStudentId(studentChangeScore.getStudentId().toString());
            dto.setScoreNumber(number);
            Boolean l = scoreService.changeScore(dto);
            if(l) {
                new DialogWindow().display("修改成功", changeLessonButton);
            }else{
                new DialogWindow().display("修改失败", changeLessonButton);
            }
        }else{
            new DialogWindow().display("信息不完整", changeLessonButton);
        }
    }


    //课程细节数据类型
    static final class LessonDetailView extends RecursiveTreeObject<LessonDetailView> {
        final StringProperty lessonId;

        final StringProperty lessonName;

        final StringProperty roomName;

        final StringProperty timeName;

        final StringProperty gradeName;

        LessonDetailView(String lessonId, String lessonName, String roomName, String timeName, String gradeName) {
            this.lessonId = new SimpleStringProperty(lessonId);
            this.lessonName = new SimpleStringProperty(lessonName);
            this.roomName = new SimpleStringProperty(roomName);
            this.timeName = new SimpleStringProperty(timeName);
            this.gradeName = new SimpleStringProperty(gradeName);
        }
    }
}
