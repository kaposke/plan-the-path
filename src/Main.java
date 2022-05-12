public class Main {
    public static void main(String[] args) {
        String[] layout = new String[] {
                "##########",
                "S # ######",
                "# # ## # E",
                "#        #",
                "##########",
        };

        Map map = new Map(layout);

        Manager manager = new Manager(map);
        manager.progammingLoop();
        manager.execute();
    }
}
