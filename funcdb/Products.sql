-- Create a table called Products with a name, description, and price
CREATE TABLE Products
(
    Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY ,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Price DECIMAL(10,2) NOT NULL
);

GO

