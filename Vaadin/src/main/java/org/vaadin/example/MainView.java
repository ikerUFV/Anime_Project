package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired AnimeService service) throws Exception {

        Tab animeTab = new Tab("Animes");
        Tab searchTab = new Tab("Buscar");

        Tabs tabs = new Tabs(animeTab, searchTab);
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);

        VerticalLayout contenido = new VerticalLayout();
        contenido.add(AnimeTab(service));

        tabs.addSelectedChangeListener(event -> {
            try {
                setContent(service, event.getSelectedTab(), animeTab, contenido);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        VerticalLayout layoutPrincipal = new VerticalLayout();
        layoutPrincipal.add(tabs, contenido);

        add(layoutPrincipal);
    }

    private void setContent(@Autowired AnimeService service, Tab selectedTab, Tab animeTab, VerticalLayout contenido) throws Exception {
        if(selectedTab.equals(animeTab)){
            contenido.removeAll();
            contenido.add(AnimeTab(service));
        }else{
            contenido.removeAll();
            contenido.add(SearchTab(service));
        }
    }

    private VerticalLayout AnimeTab(@Autowired AnimeService service) throws Exception {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        Grid<Anime> grid = new Grid<>();
        List<Anime> lista = new ArrayList<>();

        lista = service.leeAnime();

        grid.addColumn(Anime::getStudio).setHeader("Estudio");
        grid.addColumn(Anime::getGenres).setHeader("Géneros");
        grid.addColumn(Anime::getHype).setHeader("Hype");
        grid.addColumn(Anime::getDescription).setHeader("Descripción");
        grid.addColumn(Anime::getTitle).setHeader("Título");
        grid.addColumn(Anime::getStart_date).setHeader("Fecha de estreno");

        grid.setItems(lista);
        results.add(grid);

        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.getElement().setAttribute("aria-label", "Añadir nuevo anime");
        VerticalLayout dialogLayout = createDialogLayout(service, dialog);
        dialog.add(dialogLayout);

        Button aniadir = new Button("Añadir", e -> dialog.open());

        VerticalLayout layoutFinal = new VerticalLayout(results, aniadir, dialog);
        return layoutFinal;
    }

    private VerticalLayout SearchTab(@Autowired AnimeService service) throws Exception {
        VerticalLayout v = new VerticalLayout();
        Grid<Anime> grid = new Grid<>();    // Creamos la tabla con sus equivalencias

        grid.addColumn(Anime::getStudio).setHeader("Estudio");
        grid.addColumn(Anime::getGenres).setHeader("Géneros");
        grid.addColumn(Anime::getHype).setHeader("Hype");
        grid.addColumn(Anime::getDescription).setHeader("Descripción");
        grid.addColumn(Anime::getTitle).setHeader("Título");
        grid.addColumn(Anime::getStart_date).setHeader("Fecha de estreno");

        TextField search = new TextField();
        search.setLabel("Buscar");
        search.setPlaceholder("Índice en tabla");
        // ESte boton se encarga de mandar una petición a la API con el ID que recoge del TextField "Search",
        // y lo que devuelva lo mete en el grid
        Button boton = new Button("Buscar", e -> {
            try {
                grid.setItems(service.leeUnAnime(Integer.parseInt(search.getValue())));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        v.add(search, boton, grid); // Añadimos el TextField, el botón y el grid
        return v;   // Retornamos el layout para luego igualarlo con el contenido global de la página
    }

    private VerticalLayout createDialogLayout(@Autowired AnimeService service, Dialog dialog) throws ParseException {
        // CREACIÓN DE TODOS LOS ELEMENTOS PARA INTRODUCIR TEXTO
        TextField estudio = new TextField();
        estudio.setLabel("Estudio");

        IntegerField hype = new IntegerField();
        hype.setLabel("hype");

        TextField descripcion = new TextField();
        descripcion.setLabel("Descripción");

        TextField titulo = new TextField();
        titulo.setLabel("Título");

        TextField generos = new TextField();
        generos.setLabel("Géneros");

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Fecha de estreno");

        // Creamos el horizontal layout para meter los botones después
        HorizontalLayout h = new HorizontalLayout();
        Button boton = new Button("Cerrar", e -> dialog.close());   // Botón para cerrar el diálogo

        Button botonAniadir = new Button("Añadir", e -> {
            try {
                Anime anime = new Anime();  // Objeto anime que se va a añadir
                anime.setDescription(descripcion.getValue());   // Introducción de variables
                anime.setHype(hype.getValue());
                String[] generosS = generos.getValue().replaceAll(" ", "").split(",");  // Separamos el string por comas para qie se vea más bonito
                List<String> generosAlAnime = new ArrayList<>(Arrays.asList(generosS));
                anime.setGenres(generosAlAnime);
                anime.setStudio(estudio.getValue());
                System.out.println(datePicker.getValue());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datePicker.getValue().toString()); // Formateamos la fecha a cómo la traduce el datepicker
                anime.setStart_date(date);
                Titulo tituloC = new Titulo();
                tituloC.setText(titulo.getValue());
                tituloC.setLink("");
                anime.setTitle(tituloC);

                System.out.println(service.addAnime(anime));    // llamamos al servicio, y de paso imprimimos el código para asegurarnos de que funciona

                dialog.close(); // Cerramos el diálogo
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        h.add(boton, botonAniadir);
        VerticalLayout v = new VerticalLayout();
        v.add(estudio, hype, descripcion, titulo, generos, datePicker, h);

        return v;
    }


}
