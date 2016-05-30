package prof.prodageo.org;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import org.h2.tools.*;

public class DbWrapper {

        private static final Logger log = LoggerFactory.getLogger(DbWrapper.class);
        String DBNAME = "testinmem" ;
        Server server ;
        Connection conn ;
        boolean not_started = true ;


        // throws ClassNotFoundException, SQLException, IOException
        public void init()
        {
            // open the in-memory database within a VM
            try {

                if ( not_started )
                {
                    Class.forName("org.h2.Driver"); // (1)
                    conn
                        = DriverManager.getConnection("jdbc:h2:mem:" + DBNAME, "sa", ""); // (2)
                    // username:password are very important and must be used for connecting via H2 Console

                    Statement stat = conn.createStatement(); // (3)
                    // Creation table ANNONCE
                    stat.executeUpdate("CREATE TABLE annonce (id SERIAL PRIMARY KEY,nomChambreHote VARCHAR(40) NOT NULL,lieu VARCHAR(25) NOT NULL,prix DOUBLE PRECISION NOT NULL,description VARCHAR(100) NOT NULL,note INTEGER NOT NULL CHECK (note >= 0 AND note <= 5),image VARCHAR(15) NOT NULL);");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('Paris Square', 'paris', 20, 'Hôtel-budget moderne avec Wi-Fi gratuit', 3, 'PS.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('My Open Paris', 'paris', 35, 'Situé en plein cœur de Paris, cet établissement affiche une excellente situation géographique ', 4, 'MOP.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('Paris Prestigieux', 'paris', 170, 'Magnifique demeure au jardin majestueux. Pauvres s''abstenir :(', 1, 'MOCHE.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('The Malte House', 'londres', 55, 'Bed and breakfast 3 étoiles, avec petit-déjeuner gratuit et piscine extérieure', 4, 'TMH.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('Camden B&B', 'londres', 49, 'B&B près du centre de Londres', 3, 'camden.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('My Little Poney - Enora''s Palace', 'londres', 30, 'Petit lieu de Paradis au centre de la capitale', 5, 'MLP_EP.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('La maison de l''UML', 'rouen', 42, 'Modeste demeure avec vue sur la Seine du haut du donjon', 5, 'CHATEAU.jpg');");
                    stat.executeUpdate("INSERT INTO annonce(nomChambreHote,lieu,prix,description,note,image) VALUES('INSA Rouen', 'rouen', 0, 'Logement de fortune dans une salle de TP', 3, 'INSA.jpg');");


                    // creation table DATE
                    stat.executeUpdate("CREATE TABLE dateIndispo(idAnnonce INTEGER NOT NULL REFERENCES annonce(id),annee INTEGER NOT NULL,mois INTEGER NOT NULL,jour INTEGER NOT NULL,);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(1,2016,06,10);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(1,2016,06,11);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(2,2016,06,12);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(2,2016,06,13);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(3,2016,06,14);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(3,2016,06,15);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(4,2016,06,16);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(4,2016,06,17);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(5,2016,06,18);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(5,2016,06,19);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(6,2016,06,20);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(6,2016,06,21);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(7,2016,06,22);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(7,2016,06,23);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(8,2016,06,24);");
                    stat.executeUpdate("INSERT INTO dateIndispo VALUES(8,2016,06,25);");
                    stat.close();
                }

                // Statement stat1 = conn.createStatement(); // (3)
                //  // Verify that sample data was really inserted
                // ResultSet rs = stat1.executeQuery("select * from annonce");
                // log.info("ResultSet output:");
                // while (rs.next()) {
                //     log.info(rs.getString("name"));
                // }
            // finally { log.info("DB mem initiated !");
            } catch (final ClassNotFoundException e) {
                log.info("ClassNotFoundException !");
                return;
            } catch (final SQLException e) {
                log.info("SQLException sur H2mem !");
                log.info(e.getMessage());
                return;
            }

                if ( not_started )
                {
                    // http://stackoverflow.com/questions/34238142/how-to-show-content-of-local-h2-databaseweb-console
                    not_started = false ;
                   try
                   {
                        server = Server.createWebServer("-web","-webAllowOthers","-webPort","8082");
                        server.start();
                        // Server.startWebServer(conn);
                        // server = Server.createTcpServer().start();
                       log.info(" URL " + server.getURL() ) ;
                   } catch (final SQLException e) {
                        log.info("SQLException sur Server !");
                        return;
                   }
                }
        }

        public Connection getConnection() {
            return conn;
        }

}
/*
   public class DbStuff1 {
        // public void DbStuff();

        public void init()
        {
            // SOURCE :
            // https://github.com/bmatthews68/inmemdb-maven-plugin/blob/master/src/it/webapp/src/main/java/com/btmatthews/testapp/ListUsersServlet.java
            log.info("DB initiated !");




        try {
            final Context ctx = new InitialContext();
            final DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myDb");
            final Connection con = ds.getConnection();
            try {


            } finally {
                con.close();
            }
        } catch (final SQLException e) {
            log.info("SQLException !");
            return;
        } catch (final NamingException e) {
            log.info("NamingException !");
            return;
        }

        }
    }
*/
