package org.lwolvej.lessonselectionsystem.controller;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.dto.StudentDTO;
import org.lwolvej.lessonselectionsystem.dto.TeacherDTO;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.*;
import org.lwolvej.lessonselectionsystem.ui.DialogWindow;
import org.lwolvej.lessonselectionsystem.ui.MainWindow;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OfficerController implements Initializable {

    /*     界面设置       */
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

    /*     界面设置       */


    /*     数据服务       */
    private DepartmentService departmentService = Factory.getInstance().create(DepartmentService.class);

    private ProfessionService professionService = Factory.getInstance().create(ProfessionService.class);

    private ClassesService classesService = Factory.getInstance().create(ClassesService.class);

    private GradeService gradeService = Factory.getInstance().create(GradeService.class);

    private TeacherService teacherService = Factory.getInstance().create(TeacherService.class);

    private StudentService studentService = Factory.getInstance().create(StudentService.class);

    private LessonService lessonService = Factory.getInstance().create(LessonService.class);

    private ScoreService scoreService = Factory.getInstance().create(ScoreService.class);
    /*     数据服务       */


    /*     服务数据类型      */
    private List<ManageDTO> lessonsNotApprove;

    private List<Score> studentScore;

    private List<AnalyseDTO> analyseDTOS;

    private List<Student> students;

    private List<Department> departments;

    private String teacherDepartment;

    private String studentDepartmentId;

    private String studentProfessionId;

    private String studentClassId;

    private String studentGradeId;

    private List<Profession> professions;

    private List<Grade> grades;

    private List<Classes> classesList;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    /*     服务数据类型      */


    /*     界面控件      */
    //第一个tab
    @FXML
    private JFXTreeTableView<ApproveLessonView> treeView1;
    @FXML
    private JFXTreeTableColumn<ApproveLessonView, String> treeColumn11;
    @FXML
    private JFXTreeTableColumn<ApproveLessonView, String> treeColumn12;
    @FXML
    private JFXTreeTableColumn<ApproveLessonView, String> treeColumn13;
    @FXML
    private JFXTreeTableColumn<ApproveLessonView, String> treeColumn14;
    @FXML
    private JFXTreeTableColumn<ApproveLessonView, String> treeColumn15;
    @FXML
    private JFXTextField searchField1;
    @FXML
    private JFXButton buttonRefresh1;
    @FXML
    private JFXTextField lessonIdField;
    @FXML
    private JFXButton buttonAccept;
    @FXML
    private JFXButton buttonNotAccept;

    //第二个Tab
    @FXML
    private JFXTreeTableView<StudentScoreView> treeView2;
    @FXML
    private JFXTreeTableColumn<StudentScoreView, String> treeColumn21;
    @FXML
    private JFXTreeTableColumn<StudentScoreView, String> treeColumn22;
    @FXML
    private JFXTextField lessonIdField2;
    @FXML
    private JFXButton buttonSearch1;
    @FXML
    private Label maxScore;
    @FXML
    private Label minScore;
    @FXML
    private Label averageScore;
    @FXML
    private Label notAcNumber;

    //第三个tab
    @FXML
    private JFXTreeTableView<StudentView> treeView3;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn31;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn32;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn33;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn34;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn35;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn36;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn37;
    @FXML
    private JFXTreeTableColumn<StudentView, String> treeColumn38;
    @FXML
    private JFXTextField searchField2;


    //第四个tab
    @FXML
    private JFXTextField teacherNameField;
    @FXML
    private JFXTextField teacherPasswordField;
    @FXML
    private JFXTextField teacherSiteField;
    @FXML
    private JFXTextField teacherNumberField;
    @FXML
    private JFXComboBox<String> teacherDepartBox;
    @FXML
    private JFXRadioButton teacherGender1;
    @FXML
    private JFXRadioButton teacherGender2;
    @FXML
    private JFXButton teacherButton;
    private final ToggleGroup group = new ToggleGroup();

    //第五个tab
    @FXML
    private JFXTextField studentNameField;
    @FXML
    private JFXTextField studentPasswordField;
    @FXML
    private JFXRadioButton studentGender1;
    @FXML
    private JFXRadioButton studentGender2;
    @FXML
    private JFXTextField studentNumberField;
    @FXML
    private JFXComboBox<String> studentDepartmentBox;
    @FXML
    private JFXComboBox<String> studentProfessionBox;
    @FXML
    private JFXComboBox<String> studentGradeBox;
    @FXML
    private JFXComboBox<String> studentClassBox;
    @FXML
    private JFXButton studentButton;
    private final ToggleGroup group1 = new ToggleGroup();




    /*     界面控件      */


    /*     初始化操作     */
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        //课程申请表格数据组装
        treeColumn11.setCellValueFactory((TreeTableColumn.CellDataFeatures<ApproveLessonView, String> param) -> param.getValue().getValue().lessonId);
        treeColumn12.setCellValueFactory((TreeTableColumn.CellDataFeatures<ApproveLessonView, String> param) -> param.getValue().getValue().lessonName);
        treeColumn13.setCellValueFactory((TreeTableColumn.CellDataFeatures<ApproveLessonView, String> param) -> param.getValue().getValue().roomName);
        treeColumn14.setCellValueFactory((TreeTableColumn.CellDataFeatures<ApproveLessonView, String> param) -> param.getValue().getValue().timeName);
        treeColumn15.setCellValueFactory((TreeTableColumn.CellDataFeatures<ApproveLessonView, String> param) -> param.getValue().getValue().gradeName);

        //成绩申请表数据组装
        treeColumn21.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentScoreView, String> param) -> param.getValue().getValue().studentName);
        treeColumn22.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentScoreView, String> param) -> param.getValue().getValue().scoreNumber);

        //学生表数据组装
        treeColumn31.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().studentId);
        treeColumn32.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().studentName);
        treeColumn33.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().studentGender);
        treeColumn34.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().studentNumber);
        treeColumn35.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().departmentName);
        treeColumn36.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().professionName);
        treeColumn37.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().gradeName);
        treeColumn38.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentView, String> param) -> param.getValue().getValue().className);

        //添加教师
        //将单选按钮加入组
        teacherGender1.setToggleGroup(group);
        teacherGender2.setToggleGroup(group);
        //将每个输入框设置提示
        teacherNameField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                teacherNameField.validate();
            }
        });
        teacherPasswordField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                teacherPasswordField.validate();
            }
        });
        teacherSiteField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                teacherSiteField.validate();
            }
        });
        teacherNumberField.focusedProperty().addListener((o, oldVar, newVar) -> {
            if (!newVar) {
                teacherNumberField.validate();
            }
        });
        teacherDepartBox.setVisibleRowCount(8);
        teacherDepartBox.setOnAction(e -> {
            teacherDepartment = teacherDepartBox.getSelectionModel().getSelectedItem().split("=")[0];
        });

        //添加学生
        studentGender1.setToggleGroup(group1);
        studentGender2.setToggleGroup(group1);
        studentDepartmentBox.setVisibleRowCount(6);
        studentProfessionBox.setVisibleRowCount(6);
        studentGradeBox.setVisibleRowCount(6);
        studentClassBox.setVisibleRowCount(6);
        studentDepartmentBox.setOnAction(e -> {
            studentDepartmentId = studentDepartmentBox.getSelectionModel().getSelectedItem().split("=")[0];
            initProfession();
        });
        studentProfessionBox.setOnAction(e ->
                studentProfessionId = studentProfessionBox.getSelectionModel().getSelectedItem().split("=")[0]
        );
        studentGradeBox.setOnAction(e -> {
            if (studentProfessionId != null && studentDepartmentId != null) {
                studentGradeId = studentGradeBox.getSelectionModel().getSelectedItem().split("=")[0];
                initStudentClass();
            }
        });
        studentClassBox.setOnAction(e ->
                studentClassId = studentClassBox.getSelectionModel().getSelectedItem().split("=")[0]
        );
    }

    //初始化课程申请的数据
    public void initApproveLesson() {
        ObservableList<ApproveLessonView> data = FXCollections.observableArrayList();
        lessonsNotApprove = lessonService.queryLessonNotApprove();
        if (lessonsNotApprove != null && lessonsNotApprove.size() != 0) {
            for (ManageDTO now : lessonsNotApprove) {
                data.add(new ApproveLessonView(
                        now.getLessonId().toString(), now.getLessonName(),
                        now.getRoomName(), now.getClockName(),
                        now.getGradeName().toString())
                );
            }
            treeView1.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView1.setShowRoot(false);
            treeView1.setEditable(true);
        }
        searchField1.textProperty().addListener(setSearchField1(treeView1));
    }

    //课程表查询框的监听
    private ChangeListener<String> setSearchField1(
            final JFXTreeTableView<OfficerController.ApproveLessonView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(approveProp -> {
                    final ApproveLessonView view = approveProp.getValue();
                    return view.gradeName.get().contains(newVar)
                            || view.lessonId.get().contains(newVar)
                            || view.lessonName.get().contains(newVar)
                            || view.roomName.get().contains(newVar)
                            || view.timeName.get().contains(newVar);
                });
    }

    //初始化学生成绩表
    private Boolean initStudentScore(String lessonId) {
        ObservableList<StudentScoreView> data = FXCollections.observableArrayList();
        studentScore = scoreService.queryScoreByTeacher(lessonId);
        if (studentScore != null && studentScore.size() != 0) {
            Integer num = 0;
            for (Score score : studentScore) {
                if (score.getScoreNumber() < 60) num++;
                data.add(new StudentScoreView(
                        score.getStudent().getStudentName(),
                        score.getScoreNumber().toString()));
            }
            treeView2.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView2.setShowRoot(false);
            treeView2.setEditable(true);
            notAcNumber.setText(num.toString());
            return true;
        }
        return false;
    }

    //初始化学生表
    public void initStudent() {
        ObservableList<StudentView> data = FXCollections.observableArrayList();
        students = studentService.queryAllStudent();
        if (students != null && students.size() != 0) {
            students.forEach(obj -> {
                data.add(new StudentView(
                        obj.getStudentId().toString(), obj.getStudentName(),
                        obj.getStudentGender() ? "男" : "女", obj.getStudentNumber(),
                        obj.getDepartment().getDepartmentName(), obj.getProfession().getProfessionName(),
                        obj.getGrade().getGradeName().toString(), obj.getClasses().getClassesName()
                ));
            });
            treeView3.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
            treeView3.setShowRoot(false);
            treeView3.setEditable(true);
        }
        searchField2.textProperty().addListener(setSearchField2(treeView3));
    }

    //监听学生表的检索
    private ChangeListener<String> setSearchField2(
            final JFXTreeTableView<OfficerController.StudentView> tableView) {
        return (o, oldVar, newVar) ->
                tableView.setPredicate(studentProp -> {
                    final StudentView view = studentProp.getValue();
                    return view.studentId.get().contains(newVar)
                            || view.studentName.get().contains(newVar)
                            || view.departmentName.get().contains(newVar)
                            || view.professionName.get().contains(newVar)
                            || view.className.get().contains(newVar)
                            || view.gradeName.get().contains(newVar)
                            || view.studentGender.get().contains(newVar);
                });
    }

    //初始化所有学院
    public void initDepartment() {
        departments = departmentService.queryAllDepartment();
    }

    //初始化教师的选择框
    public void initTeacherComBox() {
        departments.forEach(obj -> {
            teacherDepartBox.getItems().add(obj.getDepartmentId() + "=" + obj.getDepartmentName());
        });
    }

    //初始化学生的学院
    public void initStudentDepartmentComBox() {
        departments.forEach(obj ->
                studentDepartmentBox.getItems().add(obj.getDepartmentId() + "=" + obj.getDepartmentName())
        );
    }

    //初始化学生的专业
    private void initProfession() {
        if (studentDepartmentId != null) {
            professions = professionService.queryProfessionByDepartmentId(studentDepartmentId);
            professions.forEach(obj ->
                    studentProfessionBox.getItems().add(obj.getProfessionId() + "=" + obj.getProfessionName())
            );
        }
    }

    //初始化年级
    public void initStudentGrade() {
        grades = gradeService.queryAllGrade();
        grades.forEach(obj ->
                studentGradeBox.getItems().add(obj.getGradeId() + "=" + obj.getGradeName())
        );
    }

    private void initStudentClass() {
        classesList = classesService.queryClassById(studentDepartmentId, studentProfessionId, studentGradeId);
        classesList.forEach(obj ->
                studentClassBox.getItems().add(obj.getClassesId() + "=" + obj.getClassesName())
        );
    }

    /*     初始化操作     */


    /*     按钮动作     */
    //课程申请通过
    @FXML
    protected void lessonAccept() {
        String lessonId = lessonIdField.getText();
        Boolean l = lessonService.changeLessonStatus(lessonId);
        if (l) {
            new DialogWindow().display("成功", buttonAccept);
        } else {
            new DialogWindow().display("失败", buttonAccept);
        }
    }

    //课程申请不通过
    @FXML
    protected void lessonNotAccept() {
        String lessonId = lessonIdField.getText();
        Boolean l = lessonService.removeLesson(lessonId);
        if (l) {
            new DialogWindow().display("成功", buttonNotAccept);
        } else {
            new DialogWindow().display("失败", buttonNotAccept);
        }
    }

    //刷新
    @FXML
    protected void refresh1() {
        initApproveLesson();
    }

    //查询某门课程详情
    @FXML
    protected void searchScore() {
        String lessonId = lessonIdField2.getText();
        if (initStudentScore(lessonId)) {
            analyseDTOS = scoreService.queryScoreAnalysis(lessonId);
            Double num1 = analyseDTOS.get(0).getData();
            Double num2 = analyseDTOS.get(1).getData();
            Double num3 = analyseDTOS.get(2).getData();
            maxScore.setText(num1.toString());
            minScore.setText(num2.toString());
            averageScore.setText(num3.toString());
        } else {
            new DialogWindow().display("输入课程号错误或当前课程还没有成绩", buttonSearch1);
        }
    }

    //刷新学生表
    @FXML
    protected void refresh2() {
        initStudent();
    }

    //添加教师
    @FXML
    protected void addTeacher() {
        String name = teacherNameField.getText();
        String password = teacherPasswordField.getText();
        String site = teacherSiteField.getText();
        String gender = group.getSelectedToggle().getUserData().toString();
        String number = teacherNumberField.getText();
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherName(name);
        dto.setTeacherPwd(password);
        dto.setTeacherGender(gender.equals("男"));
        dto.setTeacherSite(site);
        dto.setTeacherNumber(number);
        dto.setDepartmentId(Long.parseLong(teacherDepartment));
        Long teacherId = teacherService.addNewTeacher(dto);
        if (teacherId == null) {
            new DialogWindow().display("插入失败", teacherButton);
        } else {
            new DialogWindow().display("新加入的教师编号为:" + teacherId, teacherButton);
        }
    }

    //添加学生
    @FXML
    protected void addStudent() {
        String name = studentNameField.getText();
        String password = studentPasswordField.getText();
        String gender = group1.getSelectedToggle().getProperties().toString();
        String number = studentNumberField.getText();
        StudentDTO dto = new StudentDTO();
        dto.setStudentName(name);
        dto.setStudentPwd(password);
        dto.setStudentNumber(number);
        dto.setStudentGender(gender.equals("1"));
        dto.setDepartmentId(Long.parseLong(studentDepartmentId));
        dto.setProfessionId(Long.parseLong(studentProfessionId));
        dto.setClassesId(Long.parseLong(studentClassId));
        dto.setGradeId(Long.parseLong(studentGradeId));
        Long l = studentService.addNewStudent(dto);
        if (l != null) {
            new DialogWindow().display("新加入学生学号为:" + l, studentButton);
        } else {
            new DialogWindow().display("填写信息有误", studentButton);
        }
    }

    //退出登录
    @FXML
    protected void quit() {
        mainWindow.start(getStage());
    }

    /*     按钮动作     */


    /*     表格数据类型       */
//课程申请数据类型
    static final class ApproveLessonView extends RecursiveTreeObject<ApproveLessonView> {
        final StringProperty lessonId;

        final StringProperty lessonName;

        final StringProperty roomName;

        final StringProperty timeName;

        final StringProperty gradeName;

        ApproveLessonView(String lessonId, String lessonName, String roomName, String timeName, String gradeName) {
            this.lessonId = new SimpleStringProperty(lessonId);
            this.lessonName = new SimpleStringProperty(lessonName);
            this.roomName = new SimpleStringProperty(roomName);
            this.timeName = new SimpleStringProperty(timeName);
            this.gradeName = new SimpleStringProperty(gradeName);
        }
    }

    //学生数据类型
    static final class StudentView extends RecursiveTreeObject<StudentView> {
        final StringProperty studentId;

        final StringProperty studentName;

        final StringProperty studentGender;

        final StringProperty studentNumber;

        final StringProperty departmentName;

        final StringProperty professionName;

        final StringProperty gradeName;

        final StringProperty className;

        StudentView(String studentId, String studentName, String studentGender,
                    String studentNumber, String department, String professionName,
                    String gradeName, String className) {
            this.studentId = new SimpleStringProperty(studentId);
            this.studentName = new SimpleStringProperty(studentName);
            this.studentGender = new SimpleStringProperty(studentGender);
            this.studentNumber = new SimpleStringProperty(studentNumber);
            this.departmentName = new SimpleStringProperty(department);
            this.professionName = new SimpleStringProperty(professionName);
            this.className = new SimpleStringProperty(className);
            this.gradeName = new SimpleStringProperty(gradeName);
        }
    }

    //学生成绩数据类型
    static final class StudentScoreView extends RecursiveTreeObject<StudentScoreView> {
        final StringProperty studentName;

        final StringProperty scoreNumber;

        StudentScoreView(String studentName, String scoreNumber) {
            this.studentName = new SimpleStringProperty(studentName);
            this.scoreNumber = new SimpleStringProperty(scoreNumber);
        }
    }
    /*     表格数据类型       */
}
