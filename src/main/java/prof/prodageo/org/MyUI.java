package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import java.util.Date;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.shared.ui.label.ContentMode;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("prof.prodageo.org.MyAppWidgetset")
public class MyUI extends UI {

        private static final Logger log = LoggerFactory.getLogger(MyUIServlet.class);
        private Recherche controleur = new Recherche();
        private List<String> annonces = null;

    /* explicit declaration as attributes of graphical components for GenMyModel */
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout layoutTitre = new HorizontalLayout();
        final HorizontalLayout layoutRecherche = new HorizontalLayout();
        VerticalLayout layoutAnnonces = new VerticalLayout();
        final Label titre = new Label("<h1>COSYROOM</h1>", ContentMode.HTML);
        final TextField lieu = new TextField("Lieu");
        final PopupDateField dateDepart = new PopupDateField("Date arrivée");
        final PopupDateField dateFin = new PopupDateField("Date départ");
        final Slider prixMin = new Slider("Prix minimum");
        final Slider prixMax = new Slider("Prix maximum");
        final Button button = new Button("Rechercher") ;


    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event)
        {
           controleur.setMessageErreur();
           controleur.fixerLieu(lieu.getValue());
           if (dateDepart.getValue() != null && dateFin.getValue() != null) {
            controleur.fixerDateArrivee(dateDepart.getValue().getYear() + 1900, dateDepart.getValue().getMonth() + 1, dateDepart.getValue().getDate());
            controleur.fixerDateDepart(dateFin.getValue().getYear() + 1900, dateFin.getValue().getMonth() + 1, dateFin.getValue().getDate());
           }
           else {
             controleur.fixerDateArrivee(0,0,0);
             controleur.fixerDateDepart(0,0,0);
           }

           controleur.fourchettePrix(prixMin.getValue().intValue(), prixMax.getValue().intValue());

           annonces = controleur.annoncesCorrespondantes();
           actualiserAnnonces();
           layout.addComponents(layoutAnnonces);
        }
    }

   private VerticalLayout actualiserAnnonces() {
      layoutAnnonces.removeAllComponents();
      if (annonces != null) {
         if (annonces.size() != 0) {
            for (String s : annonces) {
               layoutAnnonces.addComponents(representationAnnonce(s));
            }
         }
         else {
            layoutAnnonces.addComponents(new Label(controleur.getMessageErreur()));
         }
      }
      return layoutAnnonces;
   }


    @Override
    protected void init(VaadinRequest vaadinRequest) {


        // final VerticalLayout layout = new VerticalLayout();

        // final TextField name = new TextField();
        //name.setCaption("Type your name here:");

        /*
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        });
        */

        prixMin.setImmediate(true);
        prixMin.setMin(0.0);
        prixMin.setMax(1000.0);
        prixMin.setValue(0.0);

        prixMax.setImmediate(true);
        prixMax.setMin(0.0);
        prixMax.setMax(1000.0);
        prixMax.setValue(500.0);

        ClickMeClass callback = new ClickMeClass() ;
        button.addClickListener( callback ) ;
        layoutTitre.addComponents(titre);

        layoutRecherche.addComponents(lieu, dateDepart, dateFin, prixMin, prixMax, button);
        layoutRecherche.setMargin(true);
        layoutRecherche.setSpacing(true);
        layoutAnnonces.setSpacing(true);
        layoutAnnonces.setImmediate(true);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponents(layoutTitre, layoutRecherche, layoutAnnonces);
        layout.setComponentAlignment(layoutTitre, Alignment.TOP_CENTER);

        //layoutAnnonces.addComponents(representationAnnonce("Paris Square/Paris/20/Hôtel-budget moderne avec Wi-Fi gratuit/3/PS.jpg"));

        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    private HorizontalLayout representationAnnonce(String annonce) {
      String[] info = annonce.split("/");
      HorizontalLayout l = new HorizontalLayout();
      VerticalLayout milieu = new VerticalLayout();
      Label titreAnnonce = new Label(info[0]);
      Label location = new Label(info[1]);
      Label description = new Label(info[2]);
      milieu.setSizeFull();
      milieu.addComponents(titreAnnonce, location, description);
      VerticalLayout droite = new VerticalLayout();
      Label prix = new Label(info[3]+"€/nuit");
      Label note = new Label();
      note.setContentMode(ContentMode.HTML);
      String textNote = "";
      for (int i = 0; i < 5; i++) {
         if (i < Integer.parseInt(info[4])) {
            textNote += FontAwesome.STAR.getHtml() + " ";
         }
         else {
            textNote += FontAwesome.STAR_O.getHtml() + " ";
         }
      }
      note.setValue(textNote);
      droite.setSizeFull();
      droite.addComponents(prix, note);
      l.addComponents(new Image("",new ThemeResource("img/" + info[5])), milieu, droite);
      l.setMargin(true);
      l.setSpacing(true);
      return l;
   }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
