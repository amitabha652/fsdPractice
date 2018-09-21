--<ScriptOptions statementTerminator=";"/>

CREATE TABLE PUBLIC.SUBJECT_TABLE (
	SUBJECTID DECIMAL(65535 , 32767) NOT NULL,
	SUBTITLE VARCHAR(255) NOT NULL,
	DURATIONINHOURS DECIMAL(65535 , 32767) NOT NULL
);

CREATE TABLE PUBLIC.BOOK_TABLE (
	BOOKID DECIMAL(65535 , 32767) NOT NULL,
	TITLE VARCHAR(255) NOT NULL,
	PRICE DECIMAL(65535 , 32767) NOT NULL,
	VOLUME DECIMAL(65535 , 32767),
	PUBLISHDATE DATE,
	SUBJECTID DECIMAL(65535 , 32767)
);

