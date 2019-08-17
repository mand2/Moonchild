ALTER SCHEMA `moonchild`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_bin ;


-- 회원
CREATE TABLE moonchild.member (
	`idx_m`   INT(7)       NOT NULL, -- 회원번호
	`id`      VARCHAR(12)  NOT NULL, -- 아이디
	`pw`      VARCHAR(16)  NOT NULL, -- 비밀번호
	`name`    VARCHAR(20)  NOT NULL, -- 이름
	`photo`   VARCHAR(255) NULL,     -- 사진
	`regdate` DATETIME     NULL,     -- 가입날짜
	`phone`   INT(15)      NULL      -- 전화번호
);

-- 회원
ALTER TABLE moonchild.member
	ADD CONSTRAINT `PK_member` -- 회원 기본키
		PRIMARY KEY (
			`idx_m` -- 회원번호
		);
ALTER TABLE `moonchild`.`member` 
CHANGE COLUMN `idx_m` `idx_m` INT(7) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `moonchild`.`member` 
CHANGE COLUMN `regdate` `regdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ;

select * from moonchild.member;
desc moonchild.member;

-- ----------------------------------------------------------------------
-- 게시판
CREATE TABLE moonchild.board (
	`idx_b` INT(7)      NOT NULL, -- 게시글번호
	`idx_m` INT(7)      NOT NULL, -- 회원번호
	`title` VARCHAR(50) NOT NULL, -- 제목
	`cont`  TEXT        NOT NULL, -- 내용
	`time`  TIMESTAMP   NULL,     -- 작성시간
	`hit`   INT(7)      NULL      -- 조회수
);

-- 게시판
ALTER TABLE moonchild.board
	ADD CONSTRAINT `PK_board` -- 게시판 기본키
		PRIMARY KEY (
			`idx_b` -- 게시글번호
		);

ALTER TABLE `moonchild`.`board`
	MODIFY COLUMN `idx_b` INT(7) NOT NULL AUTO_INCREMENT;

-- 게시판
ALTER TABLE `moonchild`.`board`
	ADD CONSTRAINT `FK_member_TO_board` -- 회원 -> 게시판
		FOREIGN KEY (
			`idx_m` -- 회원번호
		)
		REFERENCES `member` ( -- 회원
			`idx_m` -- 회원번호
		);
        
ALTER TABLE `moonchild`.`board` 
CHANGE COLUMN `time` `time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
CHANGE COLUMN `hit` `hit` INT(7) NULL DEFAULT 0 ;

select * from moonchild.board;
desc moonchild.board;        
-- --------------------------------------------------------------------
-- 좋아요
CREATE TABLE `moonchild`.`like` (
	`idx_b`    INT(7) NOT NULL, -- 게시글번호
	`idx_m`    INT(7) NOT NULL, -- 회원번호
	`like_num` INT(7) NOT NULL  -- 좋아요수
);

-- 좋아요
ALTER TABLE `moonchild`.`like`
	ADD CONSTRAINT `PK_like` -- 좋아요 기본키
		PRIMARY KEY (
			`idx_b` -- 게시글번호
		);

-- 좋아요
ALTER TABLE `moonchild`.`like`
	ADD CONSTRAINT `FK_member_TO_like` -- 회원 -> 좋아요
		FOREIGN KEY (
			`idx_m` -- 회원번호
		)
		REFERENCES `member` ( -- 회원
			`idx_m` -- 회원번호
		);

-- 좋아요
ALTER TABLE `moonchild`.`like`
	ADD CONSTRAINT `FK_board_TO_like` -- 게시판 -> 좋아요
		FOREIGN KEY (
			`idx_b` -- 게시글번호
		)
		REFERENCES `board` ( -- 게시판
			`idx_b` -- 게시글번호
		);
        
-- --------------------------------------------------------------------        
-- 댓글
CREATE TABLE `moonchild`.`comment` (
	`c_num`  INT(7)    NOT NULL, -- 댓글번호
	`idx_m`  INT(7)    NOT NULL COMMENT '댓글쓴사람의회원번호', -- 회원번호
	`idx_b`  INT(7)    NOT NULL, -- 게시글번호
	`cont`   TEXT      NOT NULL, -- 내용
	`time`   TIMESTAMP NULL,     -- 작성시간
	`depth`  INT(7)    NULL,     -- 새 컬럼
	`parent` INT(7)    NULL      -- 부모댓글
);

-- 댓글
ALTER TABLE `moonchild`.`comment`
	ADD CONSTRAINT `PK_comment` -- 댓글 기본키
		PRIMARY KEY (
			`c_num` -- 댓글번호
		);

-- 댓글
ALTER TABLE `moonchild`.`comment`
	ADD CONSTRAINT `FK_member_TO_comment` -- 회원 -> 댓글
		FOREIGN KEY (
			`idx_m` -- 회원번호
		)
		REFERENCES `member` ( -- 회원
			`idx_m` -- 회원번호
		);

-- 댓글
ALTER TABLE `moonchild`.`comment`
	ADD CONSTRAINT `FK_board_TO_comment` -- 게시판 -> 댓글
		FOREIGN KEY (
			`idx_b` -- 게시글번호
		)
		REFERENCES `board` ( -- 게시판
			`idx_b` -- 게시글번호
		);
ALTER TABLE `moonchild`.`comment` 
CHANGE COLUMN `c_num` `c_num` INT(7) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `time` `time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
CHANGE COLUMN `depth` `depth` INT(7) NULL DEFAULT 0 ;        

-- ---------------------------------------------------------------
-- 회원_탈퇴
CREATE TABLE `moonchild`.`member_out` (
	`idx_mem` INT(7)       NOT NULL, -- 회원번호
	`id`      VARCHAR(12)  NOT NULL, -- 아이디
	`pw`      VARCHAR(16)  NOT NULL, -- 비밀번호
	`name`    VARCHAR(20)  NOT NULL, -- 이름
	`photo`   VARCHAR(255) NULL,     -- 사진
	`regdate` DATETIME     NULL,     -- 가입날짜
	`phone`   INT(15)      NULL      -- 전화번호
);

-- 회원_탈퇴
ALTER TABLE `moonchild`.`member_out`
	ADD CONSTRAINT `PK_member_out` -- 회원_탈퇴 기본키
		PRIMARY KEY (
			`idx_mem` -- 회원번호
		);