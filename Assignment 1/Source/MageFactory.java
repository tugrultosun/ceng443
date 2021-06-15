public class MageFactory extends KnightFactory
{
    /**create new mage
     * @param team new mage will be added to this team
     * @return produced knight will be returned.
     */
    //TODO
    @Override
    public Knight produce(Team team) {
        Knight knight=new Noob(new Mage());
        team.addKnightToTeam(knight);
        return knight;
    }
}
