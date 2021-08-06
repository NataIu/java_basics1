import lombok.Data;

import java.util.List;

@Data
public class Command {

    private CommandType commandType;
    private String shop;
    private String product;
    private int price;

    public void init(String commandLine) throws ShopException {

        List commandFieldsList = List.of(commandLine.split(" "));

        fillCommandType(commandFieldsList);
        fillShop(commandFieldsList);
        fillProduct(commandFieldsList);
        fillPrice(commandFieldsList);
    }

    private void fillCommandType(List commandFieldsList) throws ShopException {
        commandType = chooseCommandType(getListField(commandFieldsList,0));
    }

    private CommandType chooseCommandType(String line) throws ShopException {
        CommandType result;
        if (line.equalsIgnoreCase("ДОБАВИТЬ_МАГАЗИН")) {
            result = CommandType.ADD_SHOP;
        }
        else  if (line.equalsIgnoreCase("ДОБАВИТЬ_ТОВАР")) {
            result =  CommandType.ADD_PRODUCT;
        }
        else  if (line.equalsIgnoreCase("ВЫСТАВИТЬ_ТОВАР")) {
            result =  CommandType.ADD_PRODUCT_TO_SHOP;
        }
        else  if (line.equalsIgnoreCase("СТАТИСТИКА_ТОВАРОВ")) {
            result =  CommandType.GET_STATISTICS;
        }
        else {
            throw new ShopException("Unknown command: "+line);
        }
        return result;

    }

    private void fillShop(List commandFieldsList) throws ShopException {
        if (commandType.equals(CommandType.ADD_SHOP)) {
            shop = getListField(commandFieldsList,1);
        }
        else if (commandType.equals(CommandType.ADD_PRODUCT_TO_SHOP)) {
            shop = getListField(commandFieldsList,2);

        }
    }

    private void fillProduct(List commandFieldsList) throws ShopException {
        if (commandType.equals(CommandType.ADD_PRODUCT)) {
            product = getListField(commandFieldsList,1);
        }
        else if (commandType.equals(CommandType.ADD_PRODUCT_TO_SHOP)) {
            product = getListField(commandFieldsList,1);

        }
    }

    private void fillPrice(List commandFieldsList) throws ShopException {
        if (commandType.equals(CommandType.ADD_PRODUCT)) {
            price =  Integer.parseInt(getListField(commandFieldsList,2));
        }
    }

    private String getListField(List commandFieldsList, int num) {
        if (commandFieldsList.size() < num+1) {
            throw new IndexOutOfBoundsException("Wrong size of the command");
        }
        return (String) commandFieldsList.get(num);
    }

}
