create table transactions (
        id IDENTITY NOT NULL PRIMARY KEY,
        amount float(53),
        customer_id VARCHAR(50) NOT NULL,
        date date
    );