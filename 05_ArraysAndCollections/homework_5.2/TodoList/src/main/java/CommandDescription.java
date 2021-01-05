public class CommandDescription {

    private CommandType commandType;
    private String commandText;
    private int commandParam;

    public CommandDescription(String input) {
        int spaceIndex = 0;
        spaceIndex = input.indexOf(' ');
        String tmp = new String();


        if (spaceIndex > 0) {
            commandType = CommandType.valueOf(input.substring(0, spaceIndex));
            tmp = input.substring(spaceIndex + 1);
        } else {
            commandType = CommandType.valueOf(input);
        }

        if (commandType == CommandType.ADD) {
            if (tmp.matches("[1-9][0-9]*\\s.*")) {
                spaceIndex = tmp.indexOf(' ');
                commandParam = Integer.parseInt(tmp.substring(0, spaceIndex));
                commandText = tmp.substring(spaceIndex + 1);
                commandType = CommandType.ADD_WITH_INDEX;

            } else {
                commandText = tmp;
            }
        } else if (commandType == CommandType.EDIT) {
            spaceIndex = tmp.indexOf(' ');
            commandParam = Integer.parseInt(tmp.substring(0, spaceIndex));
            commandText = tmp.substring(spaceIndex + 1);

        } else if (commandType == CommandType.DELETE) {
            commandParam = Integer.parseInt(tmp);
        }

    }

    public CommandType getCommandType() {
        return commandType;
    }

    public int getCommandParam() {
        return commandParam;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandParam(int commandParam) {
        this.commandParam = commandParam;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }
}
