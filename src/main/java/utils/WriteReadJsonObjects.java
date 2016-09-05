package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created with Intellij IDEA.
 * Project name: fsToParser.
 * Date: 21.08.2016.
 * Time: 11:52.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class WriteReadJsonObjects {

    private final String FILPATH = "D:\\Illia\\Java\\Projects\\angularServletTest\\src\\main\\resources\\data.json";

    /*todo: якщо в файлі фільми з датами оновлення більше ніж місяць то їх потрібно всі оновити*/
    public ArrayList<Film> getFilmsFromFile() {
        ArrayList<Film> films = new ArrayList<>();

        JSONObject jsonObject = readObjectFromFile();
        JSONArray jsonArray = jsonObject.getJSONArray("films");

        for (Object film : jsonArray) {
            films.add(new Film((JSONObject) film));
        }

        return films;
    }

    public JSONObject getFilmsFromFileJson() {
        return readObjectFromFile();
    }

    public void writeObjectsToFile(ArrayList<Film> films) {
        /*Метод не перезаписує все а тільки дописує нове*/
        ArrayList<Film> sortedFilms = new ArrayList<>();
        sortedFilms.addAll(films);
        sortedFilms.addAll(getFilmsFromFile());
        sortedFilms.sort(Comparator.comparing(Film::getRates).reversed());

        JSONObject jsonObject = readObjectFromFile();

        jsonObject = jsonObject.put("films", new JSONArray());

        for (Film film : sortedFilms) {
            jsonObject.getJSONArray("films").put(new JSONObject(film));
        }
        int filmsCount = jsonObject.getJSONArray("films").length();

        System.out.println("filmsCount: " + filmsCount);

        jsonObject = jsonObject.put("filmsCount", filmsCount);

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(FILPATH), "UTF-8"));
            out.write(jsonObject.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject readObjectFromFile() {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FILPATH)), "UTF8"));

            line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(sb.toString());
    }

    public ArrayList<Film> compareFilms(ArrayList<Film> filmsFromFile, ArrayList<Film> newFilms) {
        ArrayList<Film> result = new ArrayList<>();
        ArrayList<Film> onlyNewFilms = new ArrayList<>();

        result.addAll(filmsFromFile);
        boolean notEqual = true;
        if (filmsFromFile.size() > 1) {

            for (Film newFilm : newFilms) {

                if (!result.contains(newFilm)) {

                    for (Film film : filmsFromFile) {

                        if (film.getName().equals(newFilm.getName()) && film.getYear().equals(newFilm.getName())) {
                            result.remove(film);
                            result.add(newFilm);
                            notEqual = false;
                            break;
                        }
                    }
                    if (notEqual) {
                        result.add(newFilm);
                        onlyNewFilms.add(newFilm);
                    }
                    notEqual = true;
                }
            }
        } else {
            result.addAll(newFilms);
            onlyNewFilms.addAll(newFilms);
        }

        if (onlyNewFilms.size() >= 1) {
            onlyNewFilms.sort(Comparator.comparing(Film::getRates).reversed());
            writeObjectsToFile(onlyNewFilms);
        }
        return result;
    }

    public int getFilmsCount() {

        int filmsCount = readObjectFromFile().getInt("filmsCount");

        return filmsCount;
    }

}


