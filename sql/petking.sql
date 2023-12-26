-- petking 계정 생성 및 권한 부여
alter session set "_oracle_script" = true;

create user petking
identified by petking
default tablespace users;

grant connect, resource, create view to petking;

alter user petking quota unlimited on users;

-- DDL 테이블 생성 및 수정
----------------------------------------------------------------- user 영역
CREATE TABLE users (
                       id varchar2(30) NOT NULL,
                       grade_id varchar2(20) default 'g01',
                       club_id number NULL,
                       nickname varchar2(100) NOT NULL,
                       name varchar2(20) NOT NULL,
                       password varchar2(1000) NOT NULL,
                       origin_profile_name varchar2(300),
                       renamed_profile_name VARCHAR(300),
                       email varchar2(200),
                       phone char(11) NOT NULL,
                       result_point number DEFAULT 0,
                       role char(1) default 'U',
                       reg_date Date DEFAULT sysdate,
                       constraints pk_users_id primary key(id),
                       constraints fk_users_grade_id foreign key(grade_id) references user_grade(id) on delete set null,
                       constraints uq_users_email unique(email),
                       constraints ck_users_role check(role in ('U', 'A'))
);

-- drop table users;
-- drop sequence seq_users_nickname;
create sequence seq_users_nickname;

----------------------------------------------------------------- del_user 영역
create table del_users(
      id number not null,
      user_id varchar2(30) not null,
      name varchar2(20) not null,
      role char(1) not null,
      del_date date default sysdate,
      del_reason varchar2(1000) not null,
      reg_date date default sysdate,
      constraints pk_del_users_id primary key(id)
);
create sequence seq_del_users_id;
select * from del_users;

----------------------------------------------------------------- pet 영역
create table pet(
      user_id varchar2(20) not null,
      pet_name varchar2(100),
      pet_age number,
      pet_gender char(1) not null,
      neutered char(1),
      reg_date date default sysdate,
      constraints pk_fk_pet_user_id foreign key(user_id) references users(id) on delete cascade,
      constraints uq_fk_pet_user_id unique(user_id),
      constraints ck_pet_pet_gender check(pet_gender in ('M', 'F')),
      constraints ck_pet_neutered check(neutered in ('Y', 'N'))
);
-- drop table pet;
----------------------------------------------------------------- user_grade 영역
CREATE TABLE user_grade (
	id varchar2(30) NOT NULL,
	name varchar2(300) NOT NULL,
	min_point number,
	max_point number,
    constraints pk_user_grade_id primary key(id)
);
select * from pet;
-- drop table user_grade;

----------------------------------------------------------------- point 영역
create table point (
    id number not null,
    user_id varchar2(30) not null,
    point number not null,
    point_log varchar2(2000) not null,
    reg_date timestamp default systimestamp,
    constraints pk_point_id primary key(id),
    constraints fk_point_user_id foreign key(user_id) references users(id) on delete cascade
);
select * from point;

----------------------------------------------------------------- wish 영역
create table wish(
    id number not null,
    user_id varchar2(30) not null,
    camp_id number not null,
    reg_date date default sysdate,
    constraints pk_wish_id primary key(id),
    constraints fk_wish_user_id foreign key(user_id) references users(id) on delete cascade,
    constraints fk_wish_camp_id foreign key(camp_id) references camp(id) on delete cascade
);
select * from wish;
create sequence seq_wish_id;

----------------------------------------------------------------- board 영역
create table board (
       id number NOT NULL,
       user_id varchar2(30) NOT NULL,
       board_type varchar2(21) NOT NULL, -- 질문, 자유, 동아리
       board_title varchar2(1000) NOT NULL,
       board_content varchar2(4000) not null,
       reg_date timestamp default systimestamp,
       board_attr number default 1,
       view_count number default 0,
       constraints pk_board_id primary key(id),
       constraints fk_board_user_id foreign key(user_id) references users(id) on delete cascade,
       constraints ck_board_board_type check(board_type in(-1, 0, 1))
);
create sequence seq_board_id;
-- board_type의 -1은 질문, 0은 자유, 1은 동아리를 의미합니다.
-- 이거는 그냥 질문, 자유, 동아리 문자열 자체로 입력하면 db에서 볼때도 작업할 때도 더 직관적일 것 같습니다.

----------------------------------------------------------------- board_comment 영역
create table board_comment (
       id number not null,
       board_id number not null,
       user_id varchar2(30) not null,
       content varchar2(2000) not null,
       reg_date timestamp default systimestamp not null,
       constraints pk_board_comment_id primary key(id),
       constraints fk_board_comment_board_id foreign key(board_id) references board(id) on delete cascade,
       constraints fk_board_comment_user_id foreign key(user_id) references users(id) on delete cascade
);
create sequence seq_board_comment_id;

----------------------------------------------------------------- review 영역
create table review (
       id number NOT NULL,
       user_id varchar2(30) NOT NULL,
       camp_id number not null,
       board_attr number default 2,
       review_tag varchar2(1000),
       review_title varchar2(1000) not null,
       review_content varchar2(4000) not null,
       view_count number default 0,
       like_count number default 0,
        reg_date timestamp default systimestamp,
       constraints pk_review_id primary key(id),
       constraints fk_review_user_id foreign key(user_id) references users(id) on delete cascade,
       constraints fk_review_camp_id foreign key(camp_id) references camp(id) on delete cascade
);
create sequence seq_review_id;

----------------------------------------------------------------- review_comment 영역
create table review_comment (
    id number not null,
    user_id varchar2(30) not null,
    review_id number not null,
    content varchar2(2000) not null,
    reg_date timestamp default systimestamp not null,
    constraints pk_review_comment_id primary key(id),
    constraints fk_review_comment_review_id foreign key(review_id) references review(id) on delete cascade
);
create sequence seq_review_comment_id;

----------------------------------------------------------------- attachement 영역
create table attachment (
    id number not null,
    original_name varchar2(255) not null,
    renamed_name varchar2(255) not null, -- uuid
    constraints pk_attachment_id primary key(id)
);
create sequence seq_attachment_id;

----------------------------------------------------------------- board_attach 영역
create table board_attach(
    id number not null, -- 의미없는 pk
    attach_id number not null, -- 첨부파일 id
    board_id number not null,  -- 게시글 id
    board_attr number not null, -- 게시판 종류(1:통합, 2:리뷰)
    constraints pk_board_attach_id primary key(id)
);
-- 두 게시판을 대상으로 foreign key를 설정할 수 없음
-- attach insert 시 board_attach에도 insert하도록 service 코드 작성
create sequence seq_board_attach_id;

----------------------------------------------------------------- club 영역
create table club(
     id number not null,
     club_name varchar2(100) not null,
     club_intro_title varchar2(1000) not null,
     club_intro_content varchar2(4000) not null,
     max_user number not null,
     reg_date date default sysdate,
     constraints pk_club_id primary key(id),
     constraints ck_club_max_user check(max_user <=100)
);
create sequence club_id;
select * from club;

----------------------------------------------------------------- club_user 영역
create table club_users(
       club_id number not null,
       user_id varchar2(30) not null,
       join_state number default 0 not null,
       reg_date date default sysdate,
       constraints fk_club_users_club_id foreign key(club_id) references club(id) on delete set null,
       constraints fk_club_users_user_id foreign key(user_id) references users(id) on delete set null,
       constraints ck_club_users_join_state check(join_state in (-1, 0, 1))
);
select * from club_users;

----------------------------------------------------------------- camp 영역
CREATE TABLE camp (
                      id number NOT NULL,
                      business_id varchar2(100) NOT NULL,
                      business_password varchar2(1000) not null,
                      business_number varchar2(100) NOT NULL,
                      business_name varchar2(20) NOT NULL,
                      camp_name varchar2(200)	NOT NULL,
                      camp_intro varchar2(1000) NULL,
                      camp_phone number NOT NULL,
                      camp_addr varchar2(200)	NOT NULL,
                      camp_lc_la number NOT NULL,
                      camp_lc_lo number NOT NULL,
                      camp_img varchar2(200),
                      camp_state number default 0 not null,
                      reg_date date DEFAULT sysdate not null,
                      constraints pk_camp_id primary key(id),
                      constraints uq_camp_business_id unique(business_id),
                      constraints uq_camp_business_number unique(business_number),
                      constraints ck_camp_camp_state check(camp_state in (-1, 0, 1))
);
-- camp_state의 -1은 승인 거절, 0은 대기, 1은 승인 완료를 의미합니다.
-- lc_la는 위도 / lc_lo는 경도이며 지도 api에 필요한 정보입니다.
-- https://apis.map.kakao.com/web/guide/
-- drop table camp;
create sequence seq_camp_id;
-- drop sequence seq_camp_id;
----------------------------------------------------------------- camp_approve_msg 영역
create table camp_approve_msg (
    id number not null,
    camp_id number not null,
    camp_msg varchar2(1000) not null,
    constraints pk_camp_approve_msg_id primary key(id),
    constraints fk_camp_approve_camp_id foreign key(camp_id) references camp(id) on delete cascade
);
create sequence seq_camp_approve_msg_id;

----------------------------------------------------------------- promotion 영역
create table promotion(
      id number not null,
      start_date date not null,
      end_date date null,
      constraints pk_promotion_id primary key(id)
);
create sequence promotion_id;
select * from promotion;

----------------------------------------------------------------- camp_promotion 영역
create table camp_promotion(
       promo_id number not null,
       camp_id number not null,
       img_original_name varchar2(300) not null,
       img_renamed_name varchar2(300) not null,
       promo_state number default 0 not null,
       reg_date date default sysdate,
       constraints fk_camp_promotion_promo_id foreign key(promo_id) references promotion(id) on delete set null,
       constraints fk_camp_promotion_camp_id foreign key(camp_id) references camp(id) on delete set null,
       constraints ck_camp_promotion_promo_state check(promo_state in (-1, 0, 1))
);
create sequence camp_promotion_promo_id;

select * from camp_promotion;
--drop table camp_promotion;

----------------------------------------------------------------- camp_type 영역 혜진
CREATE TABLE camp_type(
    id number not null,
    name char(1) not null,
    constraints pk_camp_type_id primary key(id),
    constraints ck_camp_type_name check(name in('O','G','C','R'))
);
create sequence seq_camp_type_id;
----------------------------------------------------------------- camp_with_type 영역
create table camp_with_type(
      id number not null,
      type_id number not null,
      camp_id number not null,
      constraints pk_camp_with_type_id primary key(id),
      constraints fk_camp_with_type_type_id foreign key(type_id) references camp_type(id) on delete set null,
      constraints fk_camp_with_type_camp_id foreign key(camp_id) references camp(id) on delete set null
);
select * from camp_with_type;
create sequence seq_camp_with_type;
----------------------------------------------------------------- camp_with_type 영역
create table camp_with_type (
        type_id number not null,
        camp_id number not null,
        constraints fk_camp_with_type_type_id foreign key (type_id) references camp_type(id) on delete set null,
        constraints fk_camp_with_type_camp_id foreign key (camp_id) references camp(id) on delete set null
);
-- drop table camp_with_type;
----------------------------------------------------------------- camp_tag 영역
create table camp_tag(
     id number not null,
     name varchar2(100) not null,
     constraints pk_camp_tag_id primary key(id)
);
select * from camp_tag;
----------------------------------------------------------------- camp_with_tag 영역
create table camp_with_tag(
      id number not null,
      tag_id number not null,
      camp_id number not null,
      constraints pk_camp_with_tag_id primary key(id),
      constraints fk_camp_with_tag_tag_id foreign key(tag_id) references camp_tag(id) on delete set null,
      constraints fk_camp_with_tag_camp_id foreign key(camp_id) references camp(id) on delete set null
);
select * from camp_with_tag;
create sequence seq_camp_with_tag;
----------------------------------------------------------------- camp_room 영역
CREATE TABLE room(
    id number not null,
    camp_id number not null,
    room_name varchar2(100) not null,
    room_type number not null,
    room_intro varchar2(2000),
    room_default_person number not null,
    room_maximum_person number not null,
    constraints pk_room_id primary key(id),
    constraints fk_room_camp_id foreign key(camp_id) references camp(id) on delete set null,
    constraints fk_room_room_type foreign key(room_type) references camp_type(id) on delete set null--캠핑타입의 아이디를 외래키로 삼는다.
);
select * from room;
create sequence seq_room_id;
----------------------------------------------------------------- room_attach 영역
create table room_attach (
     id number not null,
     room_id number not null,
     room_attach_original_name varchar2(255) not null,
     room_attach_renamed_name varchar2(255) not null, -- uuid
     constraints pk_room_attach_id primary key(id),
     constraints fk_room_attach_room_id foreign key(room_id) references room(id) on delete cascade
);
create sequence seq_room_attach_id;
----------------------------------------------------------------- camp_attach 영역
create table camp_attach (
     id number not null,
     camp_id number not null,
     camp_attach_original_name varchar2(255) not null,
     camp_attach_renamed_name varchar2(255) not null, -- uuid
     constraints pk_camp_attach_id primary key(id),
     constraints fk_camp_attach_camp_id foreign key(camp_id) references camp(id) on delete cascade
);
create sequence seq_camp_attach_id;

----------------------------------------------------------------- reservation 영역

CREATE TABLE reservation(
    id number not null,
    camp_id number not null,--fk
    room_id number not null,--fk
    user_id varchar2(30) not null,--fk
    start_date date not null,
    end_date date not null,
    nop number,
    status char(1) not null, -- boolean X -> 0(boolean), 1(true)
    constraints pk_reservation_id primary key(id),
    constraints fk_reservation_camp_id foreign key(camp_id) references camp(id) on delete set null,--fk 캠핑장아이디
    constraints fk_reservation_room_id foreign key(room_id) references room(id) on delete set null,--fk 객실아이디
    constraints fk_reservation_user_id foreign key(user_id) references users(id) on delete set null,--fk 유저아이디 
    constraints ck_reservation_status check(status in ('0', '1'))
);
create sequence seq_reservation_id;

----------------------------------------------------------------- reservation_pay 영역

CREATE TABLE reservation_pay(
    id number not null, --pk
    reserv_ex number,
    reserv_usepoint number,
    reserv_date date default sysdate,
    reserv_id number not null, --fk
    constraints pk_reservation_pay_id primary key(id), --pk
    constraints fk_reservation_reserv_id foreign key(reserv_id) references reservation(id) on delete set null --fk reservation테이블의 id
);
create sequence seq_reservation_pay_id;

-----------------------------------------------------------------------------
-- 데이터 삽입 insert ~
-- camp data 총 61행

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'rfg10q', '3493kdkfs*@', '635-87-02125', '이광석', '(유)금강 두승산 글램핑', '대형 수영장과 어린이 풀이 있는 글램핑장', 635364441, '전라북도 정읍시 고부면 영원로 222-15', 35.59371447, 126.7957776, NULL, 0, to_date('10/01/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'eqi54d', 'dfjk2@^323d', '635-87-02225', '이지훈', '(주)두산개발 별고을테마파크', '반려견과 함께히는 전국최대규모 애견동반펜션', 549312489, '경상북도 성주군 선남면 도성3길 22-27', 35.88380416, 128.3709748, NULL, 0, to_date('10/02/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '68ladq', 'dfe9r0237*', '635-87-02325', '이현우', '(주)쉐르빌리안티티', '반려견 동반 가능 글램핑장', 334351199, '강원도 홍천군 서면 밤벌길19번길 111', 37.70151912, 127.5967171, NULL, 0, to_date('10/03/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'y0z8oa', 'jdfs9@1455d', '635-87-02425', '이준서', '가고파캠핑장', '반려동물 동반 가능', 334353199, '경상남도 산청군 시천면 지리산대로1478번길 31-10', 35.23999917, 127.8177287, NULL, 0, to_date('10/04/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'd4bas4', 'djfk394#@3*', '635-87-02525', '이우진', '가리산자연휴양림 야영장', '오지캠핑을 즐길 수 있음', 334356034, '강원도 홍천군 두촌면 가리산길 426', 37.8656358, 127.9811498, NULL, 0, to_date('10/05/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'cnz1ab', 'dfjslk342*dsf', '635-87-02625', '전건우', '가평 사과나무 캠핑장', '전망좋음. 사이트별 단독사이트 설치가능', 314356034, '강원도 홍천군 두촌면 가리산길 427', 37.78949631, 127.358451, NULL, 1, to_date('10/06/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'wow9to', 'yA%5QZeU6D(!-r', '635-87-02725', '전예준', '강화고인돌 캠핑장', '수영장, 수로낚시터', 31435604, '강원도 홍천군 두촌면 가리산길 428', 37.77651594, 126.4353811, NULL, 1, to_date('10/07/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'fc4mnm', 'Kj?I?2Fgge18', '635-87-02825', '이현준', '강화도산들애농원캠핑수영장', '대형 수영장, 얼음 썰매장', 74895793, '인천광역시 강화군 강화읍 고비고개로 171', 37.7409657, 126.4601757, NULL, 1, to_date('10/08/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'z5daxs', '1ab3?0FSGdKz', '635-87-02925', '이도현', '개랭이 오토 캠핑장', '제석산과 오봉산 사이 골짜기에 위치', 617454040, '전라남도 순천시 별량면 흑가길 6-1', 34.88641651, 127.3845028, NULL, 1, to_date('06/11/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'utosa5', 'o4R1s$7XDUFw', '635-87-03025', '이준혁', '거북바위 야영장', '서울 인근 야영장', 637282939, '경기도 양평군 서종면 거북바위1길 185', 37.56735167, 127.4288808, NULL, 1, to_date('06/12/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'nffzoa', 'wf0RNdMbe7#5', '635-87-03125', '이동현', '골드펫리조트', '반려견 동반 가능', 312869111, '경기도 용인시 기흥구 기흥단지로 406', 37.22101838, 127.1387381, NULL, 1, to_date('06/13/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'rpng5x', '1v6rH7V?sIF#', '635-87-03225', '이민재', '그라티아 글램핑', '대금산 인근 위치, 야외수영장', 330995855, '경기도 가평군 가평읍 태봉두밀로 421-24', 37.81527416, 127.4547661, NULL, 1, to_date('06/14/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'wi9gd3', '?ZdUIR@w4CUG', '636-97-02725', '이서준', '그레이스 글램핑', '리버뷰, 개별샤워장, 개별바비큐', 103994855, '강원도 춘천시 남산면 북한강변길 888', 37.82795206, 127.6097338, NULL, 1, to_date('06/15/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'voy551', 'T6x58?Vkjjk', '136-97-02725', '이승현', '그루터기캠핑장', '계곡 물놀이, 운동시설', 336738767, '강원도 양양군 서면 구룡령로 1694', 37.95566652, 128.5091045, NULL, 1, to_date('06/16/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '7s9ekd', '8qcJ@2PDHlpU', '236-97-02725', '이승민', '그리심 글램핑장', '트렘폴린, 계곡 물놀이, 산책로, 운동장', 300400404, '충청남도 공주시 정안면 어물길 395', 36.58914984, 127.1477909, NULL, 1, to_date('09/20/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'ny66jz', 'dp4JgcZS#1$q', '136-97-02726', '김민성', '그린스톤 오토캠핑장', '계곡 물놀이', 404040595, '경상북도 문경시 가은읍 완장길 103-63', 36.68296608, 127.9776183, NULL, 1, to_date('09/21/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '1q4h3c', 'too5K#9om$$i', '236-97-02726', '김시우', '그린앤블루', '농어촌체험시설, 물놀이장', 553512200, '경상남도 밀양시 단장면 고례로 805-25', 35.48945998, 128.9234713, NULL, 1, to_date('09/22/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '6d2sq4', '$rvN5AA0LAy8', '136-97-02727', '김준영', '그린오토캠핑장', '바다 인접', 505895050, '경상북도 포항시 남구 호미곶면 호미로 1178', 36.06133414, 129.5739415, NULL, 1, to_date('09/23/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'q0b960', 'pG0MSW@6GzaB', '236-97-02727', '김서진', '그린콩 캠핑장', '계곡과 숲이 어우러진 조용한 캠핑장', 83830493, '경상북도 포항시 남구 호미곶면 호미로 1179', 37.89736754, 127.5542843, NULL, 1, to_date('09/24/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'p59016', 'NyQ8cOCq!e9#', '136-97-02748', '김정우', '글램퍼스양평', '별 전멍대, 유아 수영장', 317727179, '경기도 양평군 단월면 대부록길 38-3', 37.57934081, 127.6819564, NULL, 1, to_date('09/25/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'u8jtld', 'GEQwg@OiOoQ4', '236-97-01728', '김지환', '글램핑스테이션', '야외 수영장, 노래시설, 캠프파이어', 438348877, '충청북도 괴산군 연풍면 연풍로 823-9', 36.78148144, 127.9512501, NULL, 1, to_date('09/26/2021', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'k5lxhk', '#n#cYh7huebN', '346-97-02729', '김지호', '글램핑스토리', '남산 제일봉(매화산)에 위치함', 394020394, '경상남도 합천군 가야면 구미가천로 345-1', 35.74923129, 128.0878149, NULL, 1, to_date('10/18/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '84hj8m', '?OERz5Pqc?t2', '236-47-02729', '김준우', '글램핑앤카라반', '트렘폴린, 물놀이장', 394930303, '경기도 양평군 단월면 단월로 600', 37.58602404, 127.6776924, NULL, 1, to_date('10/19/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'a5fu09', 'A7cwQ4$DO4ag', '556-37-02730', '김성현', '내리금봉캠핑장', '내리계곡에 위치한 조용한 캠핑장', 393838700, '강원도 영월군 김삿갓면 내리계곡로 1146', 37.10866383, 128.6718989, NULL, 1, to_date('10/20/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '48t70w', '!6DBRzFn@!Go', '541-37-02731', '김민규', '내린천솔밭힐링캠핑장', '계곡뷰', 334621591, '강원도 인제군 상남면 가산동길 25', 37.91030254, 128.2909589, NULL, 1, to_date('10/21/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'cd39ha', '?H2232k!atJ', '356-97-06532', '박지후', '노마드글램핑', '반려동물 동반 가능', 332611185, '경기도 가평군 상면 원흥길 46-12', 37.81877934, 127.3260751, NULL, 1, to_date('10/22/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'ta1pzm', 'OmV3GLiQ#OfI', '324-37-04733', '박진우', '노을빛 바다애글램핑', '갯벌체험 가능', 303099009, '경기도 가평군 상면 원흥길 46-13', 37.62136582, 126.37651, NULL, 1, to_date('05/01/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '21k2yx', '$0oQRxm2D!f6', '556-37-02334', '박성민', '노을캠핑장', '바다뷰, 낚시와 해루질 가능', 398820232, '인천광역시 강화군 양도면 해안서로 695', 37.68932612, 126.3892758, NULL, 1, to_date('05/02/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '90x4qh', '$?lKgnzdyk', '522-37-33735', '박지원', '달곡캠프', '달곡저수지에서 낚시체험 가능', 522227825, '인천광역시 강화군 양도면 해안서로 696', 35.61629641, 129.4036892, NULL, 1, to_date('05/03/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'q9thoe', 'kP8NZx2w?3Aw', '256-14-03736', '박은찬', '달과별 캠핑장', '계곡과 폭포 일품, 가재잡이 할수 있는 계곡', 597060990, '인천광역시 강화군 양도면 해안서로 697', 37.1021717, 128.8069017, NULL, 1, to_date('05/04/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'sscjga', 'S6lT687!brR$', '533-22-02437', '박한결', '달다래 캠핑장', '넓은 잔디밭, 바닷가 산책로', 399805680, '경상남도 고성군 동해면 조선특구로 1526', 35.02800292, 128.4801519, NULL, 1, to_date('05/05/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '5jawn8', '!tpTjW#Sn3l#', '056-37-02733', '박지민', '달콤이네 캠핑장', '폐교 리모델링', 394095894, '강원도 원주시 문막읍 취병로 106', 37.31123471, 127.8009256, NULL, 1, to_date('05/06/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'z8stkr', 'ducjhkhj#', '516-37-01272', '전재원', '답게', '반려동물 동반 가능', 210093899, '강원도 화천군 사내면 화악산로 621-6', 38.0378363, 127.5135151, NULL, 1, to_date('05/07/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '5tgx07', 'PG7tj$pX3$xC', '886-37-02340', '전현서', '대부도캠핑성', '곳곳에 심은 야자수로 이국적인 분위기 연출, 다양한 부대시설', 209398094, '강원도 화천군 사내면 화악산로 621-7', 37.22982914, 126.5779691, NULL, 1, to_date('05/08/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '4w2522', 'm2$aZpJ?d1X7', '886-37-02341', '오승우', '더 프라우드', '수영장', 436517000, '강원도 화천군 사내면 화악산로 621-8', 37.22460086, 128.0025014, NULL, 1, to_date('07/09/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'mcpr7c', '$u9bcIzzn5UD', '336-37-02342', '오재민', '두리캠핑장', '카라반,캠핑카 진입불가', 495069450, '강원도 화천군 사내면 화악산로 621-9', 37.57040236, 126.7635059, NULL, 1, to_date('07/10/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '8kqpb9', 'vK9b2Jw!yEa!', '336-37-02343', '오민혁', '라라캠핑장', '계곡 물놀이, 산책로, 해수욕', 595060404, '경상북도 경주시 양남면 서왕길 381-14', 35.68772365, 129.3963357, NULL, 1, to_date('07/11/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '7wrlbj', '0v!3jnR#mFF4', '336-37-02344', '오재현', '렛츠고 강화캠핑', '산책로, 운동장, 물놀이장이 있는 캠핑장', 495045604, '경상북도 경주시 양남면 서왕길 381-15', 37.62047931, 126.3846197, NULL, 1, to_date('07/12/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'mqbd5z', '9#UDhRl#2hjk', '336-37-02345', '오민석', '로뎀하우스', '개인 카라반과 트레일러 동반 입장 가능, 수영장', 393405969, '전라북도 완주군 운주면 금고당로 1070-14', 36.0254856, 127.3506616, NULL, 1, to_date('07/13/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'u89wbd', 'Qej85ORcBhjk', '336-37-02346', '오나영', '마음이 머무는 곳', '계곡 물놀이, 산책로, 운동시설', 334416066, '강원도 화천군 사내면 포화로 653-76', 38.08505165, 127.4869658, NULL, 1, to_date('07/14/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '1tsi6f', 'dsjkfldi*33', '886-37-02347', '조은지', '만석꾼 캠핑장', '운동시설, 물놀이장, 놀이터', 494059699, '부산광역시 기장군 장안읍 월내4길 56-17', 35.33106239, 129.2712631, NULL, 1, to_date('07/15/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '1mbd31', 'eQD7KK0P09hjjh', '886-37-02348', '조시연', '맑은물소리캠핑장', '벽계천에 위치', 495050494, '경기도 가평군 설악면 가마소길 17-270', 37.63489035, 127.4729383, NULL, 1, to_date('07/16/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'ciqqan', '@8n3BT8MK61@', '886-37-02349', '조연우', '메이플트리글램핑', '소형 반려동물 동반 가능', 595829394, '경기도 가평군 상면 수목원로 181-28', 37.76225881, 127.3645766, NULL, 1, to_date('07/17/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'hnfsmo', '@XRHpUoSoT29', '886-37-02350', '조민경', '무주 글램핑', '덕유산국립공원 등산로 입구에 위치한 글램핑장', 489304949, '경기도 가평군 상면 수목원로 181-29', 35.87774693, 127.7751193, NULL, 1, to_date('07/18/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'dc6hjc', 'h@YQvb$z#jjk', '886-37-02351', '조나경', '문장대오토캠핑장', '산책로, 놀이터', 545331165, '전라북도 임실군 강진면 이윤길 76', 36.54036608, 127.9100696, NULL, 1, to_date('07/19/2022', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'iudcsj', 'x5?iPlVl?4li', '776-77-12744', '조나현', '미루밸리 캠핑장', '포천계곡에 위치', 394038585, '경기도 가평군 상면 축령로 183-53', 35.85515376, 128.1306969, NULL, 1, to_date('03/04/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'txr2s4', 'A3QtA$gHcR9@', '776-77-12745', '조수진', '바다풍경카라반', '다양한 체험 프로그램이 있는 카라반 캠핑장', 450394958, '충청남도 태안군 원북면 학암포길 21-75', 36.89852566, 126.2074465, NULL, 1, to_date('03/05/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'hc7zhw', '54uKx@$Ha4W&', '776-77-12746', '황지연', '바람이좋은저녁', '사이트 간격이 넓은 야영장', 493059493, '경상북도 군위군 삼국유사면 화산산성길 65', 36.10515299, 128.7802934, NULL, 1, to_date('03/06/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'ayv3bn', '@uXzi5$tRGiM', '776-77-12747', '황지유', '봉화열목어마을', '폐교 리모델링', 546740204, '경상북도 경주시 산내면 문복로 1637-32', 37.05718282, 128.979106, NULL, 1, to_date('03/07/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'v0yaoz', '#Bn3@LWCuOn7', '776-77-12748', '정시현', '불태산야영장', '불태산 자락에 위치', 498069059, '전라남도 장성군 진원면 고산로 61-164', 35.2868281, 126.8366342, NULL, 1, to_date('03/08/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'ifb6ea', 'A1eDx3?xiXYs', '776-77-12749', '정다현', '사나래글램핑장', '물놀이장, 놀이터, 운동시설', 318331107, '경기도 연천군 왕징면 군왕로 181', 38.09061308, 127.0005394, NULL, 1, to_date('04/20/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'arzlcz', '3vjghhhhhhhhhhhhhhhhhhhhhhghh', '776-77-12750', '정민정', '산내들 야영장', '산들래자연체험장 안에 조성된 캠핑장', 313560768, '경기도 화성시 비봉면 자청로207번길 21-60', 37.20947546, 126.8944336, NULL, 1, to_date('04/21/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '5pn3i9', 'tTbufS5V!XF?', '776-77-12751', '정가연', '설매재 자연휴양림 야영장', '산책로, 수영장, 놀이터, 운동장, 운동시설', 317746959, '경기도 양평군 옥천면 용천로 510', 37.55686153, 127.5093575, NULL, 1, to_date('04/22/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'p6mtf2', 'p@muCAb7JMoZ', '136-97-02728', '정유나', '어은돌야영장', '오토캠핑, 파쇄석, 반려동물, 체험활동, 장기숙박, 바닷가, 공용시설, 자연학습, 해수욕장', 416759340, '충청남도 태안군 소원면 연들길 67-3', 36.75153398, 126.1336219, NULL, 1, to_date('04/23/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'li7guj', '$Ha$w4Jq21iV', '136-97-02729', '최가현', '오도산 자연휴양림', '주차, 예약', 559303733, '경상남도 합천군 봉산면 오도산휴양로 433', 35.6723341, 128.0552989, NULL, 1, to_date('11/08/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'lsqmov', '?xiJPpSL67eE', '236-97-02729', '최가영', '와이글램핑', '주차, 예약, 유아시설 (놀이방), 남/녀 화장실 구분', 317725772, '경기도 양평군 용문면 강이대길38번길 26-37', 37.55253295, 127.6163965, NULL, 1, to_date('11/09/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'pnl6km', 'Tl2thh#VuJ86', '136-97-02730', '최하윤', '우니메이카 강화점', '오토캠핑, 파쇄석, 단체, 와이파이, 바닷가', 50714701189, '인천광역시 강화군 하점면 창후로228번길 26-16', 37.76876414, 126.3638855, NULL, 1, to_date('11/10/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '9eh1ku', 's0#3deNiesh?', '236-97-02730', '최채린', '원탑캠핑장', '오토캠핑, 데크, 파쇄석, 바비큐장, 공용시설, 계곡', 507401189, '충청북도 괴산군 청천면 화양로 834-12', 36.68046749, 127.8123871, NULL, 1, to_date('11/11/2020', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, 'eg3dkr', '2AFqqp@zIGnz', '436-97-02729', '한규리', '잣향기푸른숲캠핑장', '알수없음', 0, '경기도 가평군 상면 축령로 183-53', 37.77444338, 127.3552628, NULL, 1, to_date('07/08/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '7gbtp2', '@FCgo4WolH28', '436-97-02730', '한아영', '잭스캠프', '글램핑, 카라반, 데크, 개별바비큐, 오토캠핑, 파쇄석, 단체', 50714100928, '경기도 포천시 신북면 청신로1196번길 359-18', 37.93298516, 127.1662124, NULL, 1, to_date('07/09/2023', 'MM/DD/RRRR'));

INSERT INTO CAMP (ID, BUSINESS_ID, BUSINESS_PASSWORD, BUSINESS_NUMBER, BUSINESS_NAME, CAMP_NAME, CAMP_INTRO, CAMP_PHONE, CAMP_ADDR, CAMP_LC_LA, CAMP_LC_LO, CAMP_IMG, CAMP_STATE, REG_DATE)
VALUES (seq_camp_id.nextval, '6w8pbf', 'e4wFOVsr$Sy4', '436-97-02733', '황소현', '제주베스트힐', '글램핑, 바비큐장, 개별바비큐, 신축, 온돌방, 독채, 단체, 한옥, 가족실, 침대방, 수영장, 체험활동, 세미나실, 와이파이, 2인실, 잔디, 자연학습', 7044003300, '제주특별자치도 제주시 조천읍 남조로 2109-36', 33.45383584, 126.6577122, NULL, 1, to_date('08/10/2023', 'MM/DD/RRRR'));
commit;

select * from camp;

-- user_grade
INSERT INTO USER_GRADE (ID, NAME, MIN_POINT, MAX_POINT)
VALUES ('g01', '치와와', 0, 1000);

INSERT INTO USER_GRADE (ID, NAME, MIN_POINT, MAX_POINT)
VALUES ('g02', '비숑', 1000, 5000);

INSERT INTO USER_GRADE (ID, NAME, MIN_POINT, MAX_POINT)
VALUES ('g03', '웰시코기', 5000, 20000);

INSERT INTO USER_GRADE (ID, NAME, MIN_POINT, MAX_POINT)
VALUES ('g04', '달마시안', 20000, 40000);

INSERT INTO USER_GRADE (ID, NAME, MIN_POINT, MAX_POINT)
VALUES ('g05', '아프간 하운드', 40000, 0);

select * from user_grade;
commit;

-- users table
INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('goyoung12', 'g01', 1, '공지영', 'B4uukJ@XR5qq', NULL, NULL, 'goyoung12@naver.com', '01023541234', 500, '얕은 풍산개' || seq_users_nickname.nextval, default, to_date('10/08/2023', 'MM/DD/RRRR'));

INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('jun345', 'g02', 2, '공현준', 'GxQ98sDdW7!$', NULL, NULL, 'jun345@gmail.com', '01025484587', 1100, '엄청난 사슴' || seq_users_nickname.nextval, default, to_date('06/13/2022', 'MM/DD/RRRR'));

INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('parkpark00', 'g03', 3, '박혜경', 'w@CaMdMdsad', NULL, NULL, 'parkpark00@naver.com', '01055483875', 5024, '적은 늑대' || seq_users_nickname.nextval, default, to_date('09/21/2021', 'MM/DD/RRRR'));

INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('heeee12', 'g04', 2, '곽혜리', 'dfsd?IJoN', NULL, NULL, 'heeee12@gmail.com', '01058439987', 25125, '빨간 칠면조' || seq_users_nickname.nextval, default, to_date('09/25/2021', 'MM/DD/RRRR'));

INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('ronn11', 'g05', 1, '권새롬', '?0tym0dm?KcB', NULL, NULL, 'ronn11@naver.com', '01098565896', 40351, '구석진 붉은메기' || seq_users_nickname.nextval, default, to_date('10/21/2022', 'MM/DD/RRRR'));

INSERT INTO USERS (ID, GRADE_ID, CLUB_ID, NAME, PASSWORD, ORIGIN_PROFILE_NAME, RENAMED_PROFILE_NAME, EMAIL, PHONE, RESULT_POINT, NICKNAME, role, REG_DATE)
VALUES ('youusang', 'g01', 3, '권유상', '57CPW02dsaf', NULL, NULL, 'youusang@gmail.com', '01068562698', 0, '나쁜 고라니' || seq_users_nickname.nextval, 'A', to_date('05/02/2022', 'MM/DD/RRRR'));

select * from users;
commit;