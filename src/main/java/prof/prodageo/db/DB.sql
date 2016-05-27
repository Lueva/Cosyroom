CREATE TABLE annonce (
  id INTEGER PRIMARY KEY,
  nomChambreHote VARCHAR(20) NOT NULL,
  lieu VARCHAR(25) NOT NULL,
  prix DOUBLE NOT NULL,
  note INTEGER NOT NULL CHECK (note >= 0 AND note <= 5),
  image VARCHAR(10) NOT NULL,
  description VARCHAR(100) NOT NULL
);

CREATE TABLE dateIndispo(
  idAnnonce INTEGER NOT NULL REFERENCES annonce(id),
  annee INTEGER NOT NULL,
  mois INTEGER NOT NULL,
  jour INTEGER NOT NULL,

);

INSERT INTO annonce VALUES ();
*/
