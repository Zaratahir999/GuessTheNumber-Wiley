create database guessnumber;

use guessnumber;

create table game(
gameId INT PRIMARY KEY,
answer CHAR(4) NOT NULL,
finished BOOLEAN NOT NULL DEFAULT 0
);

create table round(
roundId INT PRIMARY KEY,
gameId INT NOT NULL,
guess CHAR(4) NOT NULL,
result VARCHAR(10) NOT NULL,
guessTime DATETIME NOT NULL,
FOREIGN KEY (gameId) REFERENCES game(gameId));

select * from round;