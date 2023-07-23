
public class Main {
    public static void main(String[] args) {

        HashMap<String, String> testMap = new HashMap<>(4);

        String gimmeReturn = testMap.put("123", "eeny");

        testMap.showData();

        gimmeReturn = testMap.put("321", "meeny");
        gimmeReturn = testMap.put("231", "miny");
        gimmeReturn = testMap.put("213", "moe");
        //gimmeReturn = testMap.put("132","Catch a tiger by the toe");
        System.out.println();
        testMap.showData();

        gimmeReturn = testMap.get("123");

        gimmeReturn = testMap.remove("123");
        System.out.println();
        testMap.showData();


    }
}