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
    private WriteReadJsonObjects writeReadJsonObjects;
    private FilmPageParser filmPageParser = new FilmPageParser();

    public Films() {
    }

    public Films(String dataFilePath) {
        writeReadJsonObjects = new WriteReadJsonObjects(dataFilePath);
    }

    public static void main(String[] args) {
        Films films = new Films();

        System.out.println(films.getPagesCounts());
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

    private ArrayList<Film> getFilms(int filmPage) {

        ArrayList<Film> films = new ArrayList<>();

        ArrayList<Film> filmsFromFile = writeReadJsonObjects.getFilmsFromFile();
        films.addAll(filmsFromFile);

        int[] pageScope = getPageScope(filmPage);

        int filmCount = pageScope[1];

        int filmsPage = filmPageParser.getFilmsPageCount(filmCount);

        while (films.size() < filmCount) {
            ArrayList<Film> filmsFromNet = filmPageParser.getFilms((filmCount - films.size()), filmsPage);

            films = writeReadJsonObjects.compareFilms(films, filmsFromNet);
            filmsPage++;

        }

        ArrayList<Film> result = new ArrayList<>();
        films.sort(Comparator.comparing(Film::getRates).reversed());

        for (int i = pageScope[0]; i < pageScope[1]; i++) {
            result.add(films.get(i));
        }


        result.sort(Comparator.comparing(Film::getRates).reversed());
        return result;
    }

    public JSONObject getFilmsJsonByPage(int page) {
        JSONObject result = new JSONObject();
        ArrayList<Film> films = getFilms(page);
        films.sort(Comparator.comparing(Film::getRates).reversed());
        JSONArray array = new JSONArray();

        for (int i = 1; i < films.size() + 1; i++) {
            array.put(new JSONObject(films.get(i - 1)));
            if (i % 5 == 0) {

                String arrayName = "array1";

                if (i == 10) {
                    arrayName = "array2";
                } else if (i == 15) {
                    arrayName = "array3";
                }

                result.put(arrayName, array);
                array = new JSONArray();
            }
        }
        return result;
    }

    private int[] getPageScope(int page) {
        int[] result = new int[2];

        int end = 15 * page;
        int start = end - 15;


        result[0] = start;
        result[1] = end;
        return result;
    }

    public int getPagesCounts() {
        int result = writeReadJsonObjects.getFilmsCount() / 15;
        return result + 1;
    }
}
