DROP DATABASE gymManagement;
CREATE DATABASE gymManagement;
USE gymManagement;

CREATE TABLE Clients(
       clientNo VARCHAR(4),
       fName VARCHAR(30) NOT NULL,
       lName VARCHAR(30) NOT NULL,
       DOB DATE NOT NULL,
       contactNumber INTEGER  NOT NULL,
       email VARCHAR(30) NOT NULL,
       gender ENUM('M','F') NOT NULL,
       PRIMARY KEY (clientNo)
);

CREATE TABLE Focus(
       focusNo VARCHAR(4),
       focusName VARCHAR(30) NOT NULL,
       PRIMARY KEY (focusNo)
);
       
CREATE TABLE personalTrainers(
       personalTrainerNo VARCHAR(4),
       fName VARCHAR(30) NOT NULL,
       lName VARCHAR(30) NOT NULL,
       email VARCHAR(30) NOT NULL,
       focusNo VARCHAR(4),
       PRIMARY KEY (personalTrainerNo),
       FOREIGN KEY (focusNo) REFERENCES Focus(focusNo)
);

CREATE TABLE Bookings(
       bookingId VARCHAR(4),
       clientNo VARCHAR(4),
       personalTrainerNo VARCHAR(4),
       focusNo VARCHAR (4),
       sessionDate DATE NOT NULL,
       sessionStart TIME NOT NULL,
       sessionEnd TIME  NOT NULL,
       PRIMARY KEY (bookingId),
       FOREIGN KEY (clientNo) REFERENCES Clients(clientNo),
       FOREIGN KEY (personalTrainerNo) REFERENCES personalTrainers(personalTrainerNo),
       FOREIGN KEY (focusNo) REFERENCES Focus(focusNo),
       UNIQUE KEY `sessionDate` (`sessionDate`,`sessionStart`) 
);

INSERT INTO Clients
VALUES
('CL01', 'John', 'Smith', '1991-03-27', 02085545969, 'Jsmith1@gmail.com', 'M'),
('CL02', 'Paul', 'Moss', '1970-11-8', 02016329907, 'PM33@outlook.co.uk', 'M'),
('CL03', 'Caroline', 'Alderton', '1989-01-31', 02017982465, 'Carolinealderton@hotmail.com', 'F'),
('CL04', 'Keith', 'Earnshaw', '2002-07-26', 02077895290, 'keithearnshaw_2002@hotmail.com', 'M'),
('CL05', 'Heather', 'Winterdale', '1998-04-06', 02055467231, 'Hwinterdale@yahoo.com', 'F'),
('CL06', 'Lisa', 'Bailey', '2001-09-30', 02033679804, 'Lisab@gmail.com', 'F');

INSERT INTO Focus
VALUES
('FS01', 'Flexibility'),
('FS02', 'Muscle gain'),
('FS03', 'Weight loss'),
('FS04', 'Aerobic  exercizes'),
('FS05', 'Anaerobic exercises'),
('FS06', 'Bicycle riding');

INSERT INTO personalTrainers
VALUES
('PT01', 'Robert', 'Tyler', 'Roberttyler@gmail.com', 'FS02'),
('PT02', 'Brian', 'Okley', 'Brianokley@gmail.com', 'FS01'),
('PT03', 'Shelly', 'Kirkby', 'Shellykirby@gmail.com', 'FS06'),
('PT04', 'Racheal', 'Porter', 'Rachealporter@gmail.com', 'FS04'),
('PT05', 'Clair', 'Camponi', 'Claircamponi@gmail.com', 'FS01'),
('PT06', 'Oliver', 'Jobson', 'Oliverjobson@gmail.com', 'FS03');

INSERT INTO Bookings
VALUES
('BK01', 'CL04', 'PT05', 'FS01', '2019-11-11', '12:00:00', '02:00:00'),
('BK02', 'CL05', 'PT03', 'FS06', '2019-12-05', '04:30:00', '06:30:00'),
('BK03', 'CL02', 'PT01', 'FS02', '2019-11-30', '03:00:00', '05:00:00'),
('BK04', 'CL01', 'PT06', 'FS03', '2020-01-07', '09:45:00', '11:45:00'),
('BK05', 'CL06', 'PT02', 'FS04', '2020-01-09', '05:00:00', '07:00:00'),
('BK06', 'CL03', 'PT04', 'FS05', '2020-02-27', '01:00:00', '03:00:00');






       
