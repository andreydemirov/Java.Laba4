public class Database {

    ArrayList<Prism> prisms;
    ArrayList<Square> squares;

    public Database() {
        prisms = new ArrayList<>();
        squares = new ArrayList<>();
    }

    public Database(ArrayList<Square> squares, ArrayList<Prism> prisms) {
        this.squares = squares;
        this.prisms = prisms;
    }

    //,{"length":63.0,"radius":10.0,"square":314.0},{"length":38.0,"radius":6.0,"square":113.0},{"length":50.0,"radius":8.0,"square":201.0}][{"height":5.0,"length":31.0,"radius":5.0,"square":0.0,"volume":393.0},{"height":22.0,"length":63.0,"radius":10.0,"square":0.0,"volume":6912.0},{"height":12.0,"length":38.0,"radius":6.0,"square":0.0,"volume":1357.0},{"height":4.0,"length":50.0,"radius":8.0,"square":0.0,"volume":804.0}]

    public void clear() {
        this.prisms.clear();
        this.squares.clear();
    }

    public static String maxSquare(final ArrayList<Square> squares)
    {
        int index = 0;
        double sq = 0;
        for(int i = 0;i<squares.size();++i){
            if(sq<squares.get(i).getS()){
                sq = squares.get(i).getS();
                index = i;
            }
        }
        return "\nMax square of a squares: " + squares.get(index);
    }

    public static String maxDiag(ArrayList<Prism> prisms)
    {
        double diag = 0;
        int index = 0;
        for(int i = 0;i<prisms.size();++i){
            if(diag<prisms.get(i).getDiagonal()){
                diag = prisms.get(i).getDiagonal();
                index = i;
            }
        }
        return "\nAverage diagonal of a prisms = " + prisms.get(index);
    }
}
