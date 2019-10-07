package prombot.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;


public class ProductParser  {
    private StringProperty name;
    private StringProperty price;
    private StringProperty availability;
    private StringProperty color;
    private StringProperty description;
    private List<StringProperty> products;

    public ProductParser(){
        this(null,null,null,null,null);
    }

    public ProductParser(String name, String price, String availability, String color, String description) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.availability = new SimpleStringProperty(availability);
        this.color = new SimpleStringProperty(color);
        this.description = new SimpleStringProperty(description);
    }

//    public ProductParser()  {
//      name = new SimpleStringProperty(name);
//        price = new SimpleStringProperty(getPrice());
//        availability = new SimpleStringProperty(getAvailability());
//        color = new SimpleStringProperty(getColor());
//      description = new SimpleStringProperty(getDescription());
//    }

//    public String getName()  {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//             String a = element.select("h1").text();
//            System.out.println(a);
//        }
//        return null;
//    }
//
//    public String getPrice() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String  price = element.getElementsByAttributeValue("class", "x-product-price").get(0).text();
//            System.out.println(price);
//        }
//        return null;
//    }
//
//    public String getAvailability() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String availability = element.getElementsByClass("x-product-presence").text();
//            System.out.println(availability);
//        }
//        return null;
//    }
//
//    public String  getColor() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String  color = element.getElementsByClass("x-attributes__value").get(6).text();
//            System.out.println(color);
//        }
//        return null;
//    }
//
//    public String  getDescription() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//            String description = element.getElementsByClass("x-user-content").text();
//            System.out.println(description);
//        }
//        return null;
//    }

    public String getName() {
        return name.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getAvailability() {
        return availability.get();
    }

    public String getColor() {
        return color.get();
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability.set(availability);
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public List<StringProperty> getProducts() {
        return products;
    }

    public void setProducts(List<StringProperty> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(price).append("\n");
        sb.append(availability).append("\n");
        sb.append(color).append("\n");
        sb.append(description).append("\n");
        return sb.toString();
    }
}
