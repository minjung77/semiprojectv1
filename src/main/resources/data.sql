-- member 테이블
insert into members(userid,passwd,name,email,regdate) values
('냐옹냐옹', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', '1234','1234@1234',default),
('abc123', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', '1234','1234@1234',default),
('987xyz', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', '1234','1234@1234',default);


insert into replys (comments, userid, ref, pno) values ('안녕하세요', 'abc123', 1, 3000);
insert into replys (comments, userid, ref, pno) values ( '방가방가!', '987xyz', 2, 3000);
insert into replys (comments, userid, ref, pno) values ( '오늘춥네여', '냐옹냐옹', 3, 3000);
insert into replys (comments, userid, ref, pno) values ( '날씨는요?', 'abc123', 2, 3000);