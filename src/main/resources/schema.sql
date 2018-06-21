-- 学院表
CREATE TABLE `department` (
  `department_id`   BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '学院id',
  `department_name` VARCHAR(60) NOT NULL
  COMMENT '学院名称',
  PRIMARY KEY (`department_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT ='学院表';

-- 专业表
-- 创建关于学院的索引
-- 创建关于学院的外键约束
CREATE TABLE `profession` (
  `profession_id`   BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '专业id',
  `profession_name` VARCHAR(200) NOT NULL
  COMMENT '专业名称',
  `department_id`   BIGINT       NOT NULL
  COMMENT '学院id',
  PRIMARY KEY (`profession_id`),
  KEY `idx_department_id` (`department_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT ='专业表';

-- 年级表
CREATE TABLE `grade` (
  `grade_id`   BIGINT NOT NULL AUTO_INCREMENT
  COMMENT '年级id',
  `grade_name` INT    NOT NULL
  COMMENT '年级名称',
  PRIMARY KEY (`grade_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COMMENT ='年级表';

-- 班级表
-- 创建关于学院，专业，年级的索引
CREATE TABLE `classes` (
  `classes_id`    BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '班级id',
  `classes_name`  VARCHAR(90) NOT NULL
  COMMENT '班级名称',
  `department_id` BIGINT      NOT NULL
  COMMENT '学院id',
  `profession_id` BIGINT      NOT NULL
  COMMENT '专业id',
  `grade_id`      BIGINT      NOT NULL
  COMMENT '年级id',
  PRIMARY KEY (`classes_id`),
  KEY `idx_department_id` (`department_id`),
  KEY `idx_profession_id` (`profession_id`),
  KEY `idx_grade_id` (`grade_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT ='班级表';

-- 教室表
CREATE TABLE `room` (
  `room_id`   BIGINT      NOT NULL AUTO_INCREMENT,
  `room_name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`room_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COMMENT ='教室表';

-- 时间表
CREATE TABLE `clock` (
  `clock_id`   BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '时间id',
  `clock_name` VARCHAR(120) NOT NULL
  COMMENT '时间名称',
  PRIMARY KEY (`clock_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COMMENT ='时间表';

-- 学生表
-- 创建关于学院，专业，班级，年级的索引
CREATE TABLE `student` (
  `student_id`     BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '学生学号',
  `student_name`   VARCHAR(80) NOT NULL
  COMMENT '学生姓名',
  `student_gender` TINYINT(1)  NOT NULL
  COMMENT '学生性别 0:女生 1:男生',
  `student_pwd`    VARCHAR(80) NOT NULL
  COMMENT '学生密码',
  `student_number` VARCHAR(80) NOT NULL
  COMMENT '学生联系电话',
  `department_id`  BIGINT      NOT NULL
  COMMENT '学院id',
  `profession_id`  BIGINT      NOT NULL
  COMMENT '专业id',
  `classes_id`     BIGINT      NOT NULL
  COMMENT '班级id',
  `grade_id`       BIGINT      NOT NULL
  COMMENT '年级id',
  PRIMARY KEY (`student_id`),
  KEY `idx_department_id` (`department_id`),
  KEY `idx_profession_id` (`profession_id`),
  KEY `idx_classes_id` (`classes_id`),
  KEY `idx_grade_id` (`grade_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000000
  DEFAULT CHARSET = utf8
  COMMENT ='学生表';

-- 教师表
CREATE TABLE `teacher` (
  `teacher_id`     BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '教师id',
  `teacher_name`   VARCHAR(80)  NOT NULL
  COMMENT '教师姓名',
  `teacher_pwd`    VARCHAR(80)  NOT NULL
  COMMENT '教师密码',
  `teacher_gender` TINYINT(1)   NOT NULL
  COMMENT '教师性别',
  `teacher_number` VARCHAR(120) NOT NULL
  COMMENT '教师联系电话',
  `teacher_site`   VARCHAR(200) NOT NULL
  COMMENT '教师办公地点',
  `department_id`  BIGINT       NOT NULL
  COMMENT '教师所属学院id',
  PRIMARY KEY (`teacher_id`),
  KEY `idx_department_id` (`department_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100000
  DEFAULT CHARSET = utf8
  COMMENT ='教师表';

-- 课程表
CREATE TABLE lesson (
  `lesson_id`     BIGINT       NOT NULL AUTO_INCREMENT,
  `lesson_name`   VARCHAR(120) NOT NULL,
  `lesson_status` BIT          NOT NULL,
  `teacher_id`    BIGINT       NOT NULL,
  PRIMARY KEY (lesson_id),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `idx_lesson_status` (`lesson_status`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT ='课程表';

-- 课程安排表
CREATE TABLE manage (
  `manage_id`  BIGINT NOT NULL AUTO_INCREMENT,
  `room_id`    BIGINT NOT NULL,
  `clock_id`   BIGINT NOT NULL,
  `lesson_id`  BIGINT NOT NULL,
  `grade_id`   BIGINT NOT NULL,
  `teacher_id` BIGINT NOT NULL,
  PRIMARY KEY (`manage_id`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_clock_id` (`clock_id`),
  KEY `idx_lesson_id` (`lesson_id`),
  KEY `idx_grade_id` (`grade_id`),
  KEY `idx_teacher_id` (`teacher_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COMMENT ='课程安排表';

-- 成绩表
CREATE TABLE score (
  `score_id`     BIGINT NOT NULL AUTO_INCREMENT,
  `score_number` INT    NOT NULL,
  `student_id`   BIGINT NOT NULL,
  `lesson_id`    BIGINT NOT NULL,
  PRIMARY KEY (`score_id`),
  KEY `idx_student_id` (`student_id`),
  KEY `idx_lesson_id` (`lesson_id`),
  KEY `idx_score_number` (`score_number`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COMMENT ='成绩表';

-- 创建最高成绩视图
CREATE VIEW max_score
  AS
    SELECT
      score_id,
      MAX(score_number) AS max_score_number,
      lesson_id
    FROM score
    GROUP BY lesson_id;

-- 创建最低成绩视图
CREATE VIEW min_score
  AS
    SELECT
      score_id,
      MIN(score_number) AS min_score_number,
      lesson_id
    FROM score
    GROUP BY lesson_id;

-- 创建平均成绩视图
CREATE VIEW average_score
  AS
    SELECT
      score_id,
      AVG(score_number) AS average_score_number,
      lesson_id
    FROM score
    GROUP BY lesson_id;


INSERT INTO `department` (department_name)
VALUES ('计算机与软件学院'), ('大气科学学院'), ('应用气象学院'),
  ('大气物理学院'), ('地理科学学院'), ('遥感与测绘工程学院'),
  ('水文与水资源工程学院'), ('海洋科学学院'), ('环境科学与工程学院'),
  ('信息与控制学院'), ('电子与信息工程学院'), ('数学与统计学院'),
  ('物理与光电工程学院'), ('法政学院'), ('马克思主义学院'),
  ('管理工程学院'), ('商学院'), ('文学院');

INSERT INTO profession (profession_name, department_id)
VALUES ('计算机科学与技术', 1000), ('软件工程', 1000),
  ('网络工程', 1000), ('物联网工程', 1000),
  ('大气科学', 1001), ('应用气象学', 1002),
  ('农业资源与环境', 1002), ('生态学', 1002),
  ('大气科学', 1003), ('安全工程', 1003),
  ('自然地理与资源环境', 1004), ('人文地理与城乡规划', 1004),
  ('遥感科学与技术', 1005), ('测绘工程', 1005),
  ('水文与水资源', 1006), ('海洋科学', 1007),
  ('海洋技术', 1007), ('环境科学', 1008),
  ('大气科学', 1008), ('应用化学', 1008),
  ('环境工程', 1008), ('自动化', 1009),
  ('电气工程与自动化', 1009), ('测控技术与仪器', 1009),
  ('电子科学与技术', 1010), ('电子信息工程', 1010),
  ('通信工程', 1010), ('信息工程', 1010),
  ('应用统计学', 1011), ('信息与计算科学', 1011),
  ('数学与应用数学', 1011), ('光电信息科学与工程', 1012),
  ('物理学', 1012), ('应用物理学', 1012),
  ('材料物理', 1012), ('法学', 1013),
  ('行政管理', 1013), ('公共事业管理', 1013),
  ('马克思主义', 1014), ('信息管理与信息系统', 1015),
  ('物流管理', 1015), ('金融工程', 1015),
  ('财务与会计', 1016), ('国际经济与贸易', 1016),
  ('人力资源', 1016), ('市场营销', 1016),
  ('日语', 1017), ('翻译', 1017),
  ('英语', 1017), ('汉语言文学', 1017),
  ('汉语国际教育', 1017);

INSERT INTO `grade` (grade_name)
VALUES (2014),
  (2015),
  (2016),
  (2017),
  (2018);

INSERT INTO classes (classes_name, department_id, profession_id, grade_id)
VALUES ('计算机科学与技术一班', 1000, 1000, 3),
  ('计算机科学与技术二班', 1000, 1000, 3),
  ('计算机科学与技术三班', 1000, 1000, 3),
  ('软件工程一班', 1000, 1001, 3),
  ('软件工程二班', 1000, 1001, 3),
  ('软件工程嵌入式一班', 1000, 1001, 3);

INSERT INTO `room` (room_name)
VALUES ('明德s201'), ('明德s202'), ('明德s203'), ('明德s204'),
  ('明德n201'), ('明德n202'), ('明德n203'), ('明德n204'),
  ('明德s301'), ('明德s302'), ('明德s303'), ('明德s304'),
  ('明德n301'), ('明德n302'), ('明德n303'), ('明德n304'),
  ('明德s401'), ('明德s402'), ('明德s403'), ('明德s404'),
  ('明德n401'), ('明德n402'), ('明德n403'), ('明德n404'),
  ('明德s501'), ('明德s502'), ('明德s503'), ('明德s504'),
  ('明德n501'), ('明德n502'), ('明德n503'), ('明德n504');

INSERT INTO `clock` (clock_name)
VALUES ('周一上午第一节'), ('周一上午第二节'), ('周一下午第一节'), ('周一下午第二节'),
  ('周二上午第一节'), ('周二上午第二节'), ('周二下午第一节'), ('周二下午第二节'),
  ('周三上午第一节'), ('周三上午第二节'), ('周三下午第一节'), ('周三下午第二节'),
  ('周四上午第一节'), ('周四上午第二节'), ('周四下午第一节'), ('周四下午第二节'),
  ('周五上午第一节'), ('周五上午第二节'), ('周五下午第一节'), ('周五下午第二节');

INSERT INTO `student` (student_name, student_gender, student_pwd, student_number, department_id, profession_id, classes_id, grade_id)
VALUES ('马添翼', 1, 'mty1998', '188888801', 1000, 1001, 1005, 3),
  ('仇金东', 1, 'mty1998', '188888802', 1000, 1001, 1005, 3),
  ('邓盛', 1, 'mty1998', '188888803', 1000, 1001, 1005, 3),
  ('刘胜前', 1, 'mty1998', '188888804', 1000, 1001, 1005, 3),
  ('许均涛', 1, 'mty1998', '188888805', 1000, 1001, 1005, 3),
  ('朱燕冰', 1, 'mty1998', '188888806', 1000, 1001, 1005, 3),
  ('陈俊辉', 1, 'mty1998', '188888807', 1000, 1001, 1005, 3),
  ('陈宇豪', 1, 'mty1998', '188888808', 1000, 1001, 1005, 3),
  ('陈培新', 1, 'mty1998', '188888809', 1000, 1001, 1005, 3),
  ('陈还新', 1, 'mty1998', '188888810', 1000, 1001, 1005, 3),
  ('李杰', 1, 'mty1998', '188888811', 1000, 1001, 1005, 3),
  ('伍争', 1, 'mty1998', '188888812', 1000, 1001, 1005, 3),
  ('浦猇', 1, 'mty1998', '188888813', 1000, 1001, 1005, 3),
  ('苗翔宇', 1, 'mty1998', '188888814', 1000, 1001, 1005, 3),
  ('蔡昕睿', 1, 'mty1998', '188888815', 1000, 1001, 1005, 3),
  ('尚桐', 1, 'mty1998', '188888816', 1000, 1001, 1005, 3),
  ('徐鑫波', 1, 'mty1998', '188888817', 1000, 1001, 1005, 3),
  ('周涛', 1, 'mty1998', '188888818', 1000, 1001, 1005, 3),
  ('顾达伟', 1, 'mty1998', '188888819', 1000, 1001, 1005, 3),
  ('胡晋宁', 1, 'mty1998', '188888820', 1000, 1001, 1005, 3),
  ('张浩', 1, 'mty1998', '188888821', 1000, 1001, 1005, 3),
  ('盛天叶', 1, 'mty1998', '188888822', 1000, 1001, 1005, 3),
  ('苗奎', 1, 'mty1998', '188888823', 1000, 1001, 1005, 3),
  ('金旭', 1, 'mty1998', '188888824', 1000, 1001, 1005, 3),
  ('李汶昕', 1, 'mty1998', '188888825', 1000, 1001, 1005, 3),
  ('朱雨薇', 0, 'mty1998', '188888826', 1000, 1001, 1005, 3),
  ('陈曼钰', 0, 'mty1998', '188888827', 1000, 1001, 1005, 3),
  ('汤琳俪', 0, 'mty1998', '188888828', 1000, 1001, 1005, 3),
  ('吴惠敏', 0, 'mty1998', '188888829', 1000, 1001, 1005, 3),
  ('金鑫', 1, 'mty1998', '188888830', 1000, 1001, 1005, 3),
  ('陆衡玉', 1, 'mty1998', '188888831', 1000, 1001, 1005, 3);


INSERT INTO teacher (teacher_name, teacher_pwd, teacher_gender, teacher_number, teacher_site, department_id)
VALUES
  ('郑关胜', 123456, 1, '188888888', '计软院学办', 1000),
  ('潘锦基', 132654, 1, '188888889', '计软院学办', 1000);



























