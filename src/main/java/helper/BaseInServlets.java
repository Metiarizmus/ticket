package helper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class BaseInServlets<E> {

    public JSONObject getRequest(BufferedReader s){

        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = s;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());

            return jsonObject;
        }catch (IOException e){
            System.err.println("error json ");
        }

        return null;
    }

    public String jsonResponse(E e) {


        return null;
    }
}
