package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created with Intellij IDEA.
 * Project name: fsToParser.
 * Date: 23.08.2016.
 * Time: 0:17.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Films {
    private WriteReadJsonObjects writeReadJsonObjects = new WriteReadJsonObjects();
    private FilmPageParser filmPageParser = new FilmPageParser();

    public static void main(String[] args) {
        Films films = new Films();
        System.out.println(films.getFilmsJson(5));
    }

    public JSONObject getFilmsJson(int filmCount) {
        JSONObject result = new JSONObject();

        JSONArray array = new JSONArray();

        ArrayList<Film> films = getFilms(filmCount);

        for (int i = 0; i < 5; i++) {
            array.put(new JSONObject(films.get(i)));
        }

        result.put("films1", array);
        array = new JSONArray();

        for (int i = 5; i < 10; i++) {

            array.put(new JSONObject(films.get(i)));
        }

        result.put("films2", array);
        array = new JSONArray();

        for (int i = 10; i < 15; i++) {

            array.put(new JSONObject(films.get(i)));
        }

        result.put("films3", array);

        return result;
    }

    private ArrayList<Film> getFilms(int filmCount) {
        ArrayList<Film> films = new ArrayList<>();

        ArrayList<Film> filmsFromFile = writeReadJsonObjects.getFilmsFromFile();
        films.addAll(filmsFromFile);

        int filmsPage = filmPageParser.getFilmsPageCount(filmCount);

        while (films.size() < filmCount) {
            ArrayList<Film> filmsFromNet = filmPageParser.getFilms((filmCount - films.size()), filmsPage);

            films = writeReadJsonObjects.compareFilms(films, filmsFromNet);
            filmsPage++;

        }

        while (films.size() != filmCount) {
            films.remove(films.size() - 1);
        }

        films.sort(Comparator.comparing(Film::getRates).reversed());
        return films;
    }
}
