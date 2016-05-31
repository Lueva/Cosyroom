CREATE TABLE annonce (
  id SERIAL PRIMARY KEY,
  nomChambreHote VARCHAR(20) NOT NULL,
  lieu VARCHAR(25) NOT NULL,
  prix DOUBLE PRECISION NOT NULL,
  description VARCHAR(100) NOT NULL,
  note INTEGER NOT NULL CHECK (note >= 0 AND note <= 5),
  image VARCHAR(10) NOT NULL,
);

CREATE TABLE dateIndispo(
  idAnnonce INTEGER NOT NULL REFERENCES annonce(id),
  annee INTEGER NOT NULL,
  mois INTEGER NOT NULL,
  jour INTEGER NOT NULL,

);


INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES ('Paris Square', 'Paris', 20, 'Hôtel-budget moderne avec Wi-Fi gratuit', 3, 'PS.jpg');
INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES ('My Open Paris', 'Paris', 35, 'Situé en plein cœur de Paris, cet établissement affiche une excellente situation géographique ', 4, 'MOP.jpg');
INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES ('The Malte House', 'Londres', 55, 'Bed and breakfast 3 étoiles, avec petit-déjeuner gratuit et piscine extérieure', 4, 'TMH.jpg');
INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image)(nomChambreHote,lieu,prix,description,note,image) VALUES ('Camden B&B', 'Londres', 49, 'B&B près du centre de Londres', 3, 'camden.jpg');
INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES ('My Little Poney - Enora''s Palace', 'Londres', 30, 'Petit lieu de Paradis au centre de la capitale', 5, 'MLP_EP.jpg');


INSERT INTO dateIndispo VALUES(1,2016,06,10);
INSERT INTO dateIndispo VALUES(1,2016,06,11);
INSERT INTO dateIndispo VALUES(2,2016,06,12);
INSERT INTO dateIndispo VALUES(2,2016,06,13);
INSERT INTO dateIndispo VALUES(3,2016,06,14);
INSERT INTO dateIndispo VALUES(3,2016,06,15);
INSERT INTO dateIndispo VALUES(4,2016,06,16);
INSERT INTO dateIndispo VALUES(4,2016,06,17);
INSERT INTO dateIndispo VALUES(5,2016,06,18);
INSERT INTO dateIndispo VALUES(5,2016,06,19);
