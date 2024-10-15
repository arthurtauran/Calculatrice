import model.CalculatriceModel;
import view.CalculatriceView;
import controller.CalculatriceController;

public class CalculatorApp {
    public static void main(String[] args) {
        CalculatriceModel model = new CalculatriceModel();
        CalculatriceView view = new CalculatriceView();
        CalculatriceController controller = new CalculatriceController(model, view);
    }
}
