-- Création de la table pour les utilisateurs
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL
);

-- Insertion d'un utilisateur de test
INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$egcF2JHOCtrChA24ZSIn3eHm4BNTp4dd6ec6avck6BWhFkBuX2E4K');
