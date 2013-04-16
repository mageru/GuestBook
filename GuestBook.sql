DROP TABLE MESSAGES;

CREATE TABLE MESSAGES
(
	MessageID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	DATE DATE NOT NULL,
	NAME VARCHAR (30) NOT NULL,
	EMAIL VARCHAR (30) NOT NULL,
	MESSAGE VARCHAR (150) NOT NULL
);

/**INSERT INTO Addresses (FirstName,LastName,Street,City,State,Zip) VALUES 
   ('Bob','Green','5 Bay St.','San Francisco','CA','94133'),
   ('Liz','White','100 5th Ave.','New York','NY','10011'),
   ('Mike','Brown','3600 Delmar Blvd.','St. Louis','MO','63108'),
   ('Mary','Green','300 Massachusetts Ave.','Boston','MA','02115'),
   ('John','Gray','500 South St.','Philadelphia','PA','19147'),
   ('Meg','Gold','1200 Stout St.','Denver','CO','80204'),
   ('James','Blue','1000 Harbor Ave.','Seattle','WA','98116'),
   ('Sue','Black','1000 Michigan Ave.','Chicago','IL','60605'); **/