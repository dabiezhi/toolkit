create table chain
(
    id               bigint auto_increment
        primary key,
    application_name varchar(32) null,
    chain_name       varchar(32) null,
    chain_desc       varchar(64) null,
    el_data          text        null,
    create_time      datetime    null
);


create table script
(
    id               bigint auto_increment
        primary key,
    application_name varchar(32) null,
    script_id        varchar(32) null,
    script_name      varchar(64) null,
    script_data      text        null,
    script_type      varchar(16) null
);

INSERT INTO poseidon.chain (id, application_name, chain_name, chain_desc, el_data, create_time) VALUES (1, 'demo', 'chain1', '测试流程', 'THEN(a, b, c, s1);', '2022-09-19 19:31:00');

INSERT INTO poseidon.script (id, application_name, script_id, script_name, script_data, script_type) VALUES (1, 'demo', 's1', '脚本s1', 'import cn.hutool.core.date.DateUtil

def date = DateUtil.parse("2022-10-17 13:31:43")
println(date)
defaultContext.setData("demoDate", date)

class Student {
   int studentID
   String studentName
}

Student student = new Student()
student.studentID = 100301
student.studentName = "张三"
defaultContext.setData("student",student)

def a=3
def b=2
defaultContext.setData("s1",a*b)', 'script');
INSERT INTO poseidon.script (id, application_name, script_id, script_name, script_data, script_type) VALUES (2, 'demo', 's2', '脚本s2', 'defaultContext.setData("s2","hello")', 'script');


INSERT INTO `chain` VALUES (1, 'demo', 'chain1', '测试流程1', ' IF(s3, THEN(a.tag(\"tag1\"), b, c, s4), e);', '2022-09-19 19:31:00');
INSERT INTO `chain` VALUES (2, 'demo', 'chain2', '测试流程2', 'THEN(a, b, c);', '2023-03-29 17:04:19');

INSERT INTO `script` VALUES (1, 'demo', 's1', '脚本s1', 'import cn.hutool.core.date.DateUtil\n\ndef date = DateUtil.parse(\"2022-10-17 13:31:43\")\nprintln(date)\ndefaultContext.setData(\"demoDate\", date)\n\nclass Student {\n   int studentID\n   String studentName\n}\n\nStudent student = new Student()\nstudent.studentID = 100301\nstudent.studentName = \"张三\"\ndefaultContext.setData(\"student\",student)\n\ndef a=3\ndef b=2\ndefaultContext.setData(\"s1\",a*b)', 'script', 'groovy');
INSERT INTO `script` VALUES (2, 'demo', 's2', '脚本s2', 'defaultContext.setData(\"s2\",\"hello\")', 'script', 'groovy');
INSERT INTO `script` VALUES (3, 'demo', 's3', '条件脚本', 'return false || demo.getFlag()', 'if_script', 'groovy');
INSERT INTO `script` VALUES (4, 'demo', 's4', '脚本s4', 'defaultContext.setData(\"s1\",demo.getStr(_meta.requestData))', 'script', 'groovy');

