//public class Model {
//    private Galaxy galaxy;
//    private Player player;
//    private boolean active = false;
//
//    /**
//     * Start a new game.
//     */
//    public void startNewGame(Controller controller) { // TODO: Clean up magic screens
//        this.galaxy = GalaxyFactory.get().make();
//        this.player = new Player(new ObjectIntMap<>(), galaxy.getStations().get("Homeworld"), galaxy.getStations().get("Homeworld"));
//        this.player.getQuests().add(new QuestFactory().make("INTRO_QUEST", controller, this));
//        this.active = true;
//    }
//
//    public void dispose() { }
//
//    // === Getters / Setters === //
//    public Player getPlayer() {
//        return player;
//    }
//
//    public Galaxy getGalaxy() { return galaxy; }
//
//    public boolean isActive() {
//        return active;
//    }
//}