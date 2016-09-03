package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created with Intellij IDEA.
 * Project name: fsToParser.
 * Date: 21.08.2016.
 * Time: 11:52.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class WriteReadJsonObjects {

    public static void main(String[] args) {
        WriteReadJsonObjects writeReadJsonObjects = new WriteReadJsonObjects();


    }
/*todo: якщо в файлі фільми з датами оновлення більше ніж місяць то їх потрібно всі оновити*/

    public void writeObjectsToFile() {
        /*Метод не перезаписує все а тільки дописує нове*/

        JSONObject jsonObject = readObjectFromFile();


        try {
            jsonObject = jsonObject.put("filmsCount", "12");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("D:\\Illia\\Java\\Projects\\angularSevletTest\\src\\main\\resources\\data.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readObjectFromFile() {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File("D:\\Illia\\Java\\Projects\\angularSevletTest\\src\\main\\resources\\data.json")), "UTF8"));
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


}


