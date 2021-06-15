public class RogueFactory extends KnightFactory
{
    /**
     * @param team new knight will be added to this team
     * @return produded knight
     */
    //TODO
    @Override
    public Knight produce(Team team) {
        Knight knight=new Noob(new Rogue());
        team.addKnightToTeam(knight);
        return knight;
    }
}
