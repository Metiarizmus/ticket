package connect;

import enums.StateProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyInf {

    public Properties getProperties(StateProperties state) {
        InputStream fis = null;
        Properties properties = new Properties();

        try {
            if (StateProperties.CONNECT.equals(state)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("config.properties");
            } else if (StateProperties.EMAIL.equals(state)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("email.properties");
            } else if (StateProperties.SQL.equals(state)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("sql.properties");

            }

            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


}