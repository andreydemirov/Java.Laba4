import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        Database db = new Database();

        db.squares.add(new Square(5));
        db.squares.add(new Square(10));
        db.squares.add(new Square(6));
        db.squares.add(new Square(8));

        db.prisms.add(new Prism(db.squares.get(0).getLength(), 5));
        db.prisms.add(new Prism(db.squares.get(1).getLength(), 22));
        db.prisms.add(new Prism(db.squares.get(2).getLength(), 12));
        db.prisms.add(new Prism(db.squares.get(3).getLength(), 4));

        FileWorker fileWorker = new FileWorker();

        fileWorker.save("textfile.txt",db.squares, db.prisms);
        db.clear();
        fileWorker.read("textfile.txt",db.squares, db.prisms);


        fileWorker.serialize("ser.txt", db.squares, db.prisms);
        db.clear();
        db = fileWorker.deserialize("ser.txt", db.squares, db.prisms);


        fileWorker.serializeFastjson("ser.json", db.squares, db.prisms);
        db.clear();
        db = fileWorker.deserializeFastjson("ser.json", db.squares, db.prisms);

        System.out.println(db.squares.toString().replace("[", "").replace("]", ""));
        System.out.println(db.prisms.toString().replace("[", "").replace("]", ""));

        System.out.println(db.maxSquare(db.squares));
        System.out.println(db.maxDiag(db.prisms));
    }
}
