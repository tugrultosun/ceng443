public class PriestFactory extends KnightFactory
{
    //TODO
    @Override
    public Knight produce(Team team) {
        Knight knight=new Noob(new Priest());
        team.addKnightToTeam(knight);
        return knight;
    }
}
