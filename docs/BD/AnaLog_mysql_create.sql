CREATE TABLE `Rolo` (
	`IDrolo` INT NOT NULL AUTO_INCREMENT,
	`Titulo` varchar(20) NOT NULL,
	`ISO` FLOAT,
	`Formato` INT,
	`NExposicoes` INT NOT NULL,
	`Descricao` TEXT,
	`Revelado` BOOLEAN NOT NULL,
	`Data` DATE NOT NULL,
	`IDcam` DATE NOT NULL,
	PRIMARY KEY (`IDrolo`)
);

CREATE TABLE `Camera` (
	`IDcam` INT NOT NULL AUTO_INCREMENT,
	`Marca` varchar(20) NOT NULL,
	`Modelo` varchar(20) NOT NULL,
	PRIMARY KEY (`IDcam`)
);

CREATE TABLE `Exposicao` (
	`IDexp` INT NOT NULL AUTO_INCREMENT,
	`VelDisparo` INT,
	`Abertura` INT,
	`DistFocal` INT,
	`Descricao` TEXT,
	`Data` DATE NOT NULL,
	`IDRolo` DATE NOT NULL,
	`IDobj` DATE NOT NULL,
	PRIMARY KEY (`IDexp`)
);

CREATE TABLE `Objetiva` (
	`Idobj` INT NOT NULL AUTO_INCREMENT,
	`Marca` varchar(20) NOT NULL,
	`Modelo` varchar(20) NOT NULL,
	PRIMARY KEY (`Idobj`)
);

ALTER TABLE `Rolo` ADD CONSTRAINT `Rolo_fk0` FOREIGN KEY (`IDcam`) REFERENCES `Camera`(`IDcam`);

ALTER TABLE `Exposicao` ADD CONSTRAINT `Exposicao_fk0` FOREIGN KEY (`IDRolo`) REFERENCES `Rolo`(`IDrolo`);

ALTER TABLE `Exposicao` ADD CONSTRAINT `Exposicao_fk1` FOREIGN KEY (`IDobj`) REFERENCES `Objetiva`(`Idobj`);

