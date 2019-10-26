package prombot.model;

import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.Random;

public class CreateRandomValueToRegistrationPage {
    private WebDriver driver;

    public CreateRandomValueToRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCustomerName(int length) {

        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String ran = sb.toString();
        return ran;
    }

    public String getEmail() {
        return getCustomerName(7) + ".aerq" + (new Date().getTime()) + "@gmail.com";
    }


    public String getPassword(int min, int max) {
        Random random = new Random();
        return String.valueOf(random.ints(min, (max + 1)).findFirst().getAsInt()) + "aerQ";
    }

}
