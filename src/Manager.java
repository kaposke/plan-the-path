import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

public class Manager {
    private Map map;
    private String[] availableCommands;
    private Queue<String> commandQueue = new LinkedList<>();

    public Manager(Map map) {
        setMap(map);

        availableCommands = new String[] {
                "left", "l",
                "right", "r",
                "up", "u",
                "down", "d",
                "go",
                "restart"
        };
    }

    private void showCommands() {
        System.out.println("Comandos disponíveis: ");
        System.out.println("- left / l");
        System.out.println("- right / r");
        System.out.println("- up / u");
        System.out.println("- down / d");
        System.out.println("- go");
        System.out.println("- restart");
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void progammingLoop() {
        clearQueue();

        String command = "";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("======================================================================================");
            System.out.println(map);
            System.out.println("Programe seus passos para chegar até o 'E'");
            System.out.println("Comandos enfileirados: " + commandQueue.size());
            showCommands();

            command = scanner.next().toLowerCase();

            if (command.equals("go")) break;
            else if (command.equals("restart")) clearQueue();
            else if (!queueCommand(command)) System.out.println("Comando inválido.");

        } while (!command.equals("go"));
    }

    public void execute() {
        int step = 1;
        while (commandQueue.size() > 0) {
            String command = commandQueue.remove();

            System.out.println("======================================================================================");
            System.out.println("Comando " + step + ": " + command);
            System.out.println(map);

            executeCommand(command);
            step++;
        }
        if (map.isPlayerOnTarget()) System.out.println("Você conseguiu!");
        else System.out.println("Melhor tentar novamente!");
        System.out.println(map);
    }

    private boolean queueCommand(String command) {
        if (!isValidCommand(command)) return false;
        commandQueue.add(command);
        return true;
    }

    private void executeCommand(String command) {
        boolean success = false;
        switch (command) {
            case "left":
            case "l":
                success = map.tryMoveLeft();
                break;
            case "right":
            case "r":
                success = map.tryMoveRight();
                break;
            case "up":
            case "u":
                success = map.tryMoveUp();
                break;
            case "down":
            case "d":
                success = map.tryMoveDown();
                break;
        }
        if (!success) System.out.println("Movimento inválido!");
    }

    private boolean isValidCommand(String command) {
        for (String availableCommand : availableCommands) {
            if (command.equals(availableCommand)) return true;
        }
        return false;
    }

    private void clearQueue() {
        commandQueue.clear();
    }
}
