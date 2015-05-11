
public class Module {

    Parser moduleParser;
    SearchAPI moduleSearchAPI;

    public Module() {
        moduleParser = new Parser();
        moduleSearchAPI = new SearchAPI();
    }
}
