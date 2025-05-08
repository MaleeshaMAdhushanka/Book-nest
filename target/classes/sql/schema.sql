
CREATE DATABASE bookB;

CREATE TABLE admin(
                      username VARCHAR(30) PRIMARY KEY,
                      password VARCHAR(255),
                      imgUrl VARCHAR(255)

);

CREATE TABLE branch(
                       branch_id VARCHAR(30) PRIMARY KEY,
                       branch_name VARCHAR(100),
                       branch_address VARCHAR(255),
                       username VARCHAR(30) NULL,
                       CONSTRAINT fk_branch_admin FOREIGN KEY (username) REFERENCES admin(username) ON DELETE SET NULL
);

CREATE TABLE user (
                      email VARCHAR(30) PRIMARY KEY,
                      name  VARCHAR(100),
                      address VARCHAR(255),
                      password VARCHAR(255),
                      imgUrl VARCHAR(255)
);


CREATE TABLE book (
                      book_id VARCHAR(30) PRIMARY KEY,
                      title VARCHAR(255),
                      author VARCHAR(255),
                      genre VARCHAR(100),
                      available BOOLEAN,
                      branch_id VARCHAR(30),
                      CONSTRAINT fk_book_branch FOREIGN KEY (branch_id) REFERENCES branch(branch_id) ON DELETE CASCADE
);

CREATE TABLE book_transaction(
                                 transaction_Id INT AUTO_INCREMENT PRIMARY KEY,
                                 borrowDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 returnDate DATE,
                                 isReturned BOOLEAN,
                                 book_id VARCHAR(30),
                                 email VARCHAR(30),
                                 CONSTRAINT fk_transaction_book FOREIGN KEY(book_id) REFERENCES book(book_id) ON DELETE CASCADE,
                                 CONSTRAINT fk_transaction_user FOREIGN KEY (email) REFERENCES user(email) ON DELETE CASCADE

);