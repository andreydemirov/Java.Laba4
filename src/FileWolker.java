import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class FileWorker {


    public Database serialize(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws IOException {

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(squares);
            oos.writeObject(prisms);
            oos.close();
            fos.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return new Database(squares, prisms);
    }

    public Database deserialize(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws IOException {

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            squares = (ArrayList<Square>) ois.readObject();
            prisms = (ArrayList<Prism>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return new Database(squares, prisms);
    }


    public void serializeFastjson(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws IOException {
        FileWriter os = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(os);
        bw.write(JSON.toJSONString(squares));
        bw.write("\n");
        bw.write(JSON.toJSONString(prisms));
        bw.close();
        os.close();
    }

    public Database deserializeFastjson(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws IOException {

        FileReader fr = new FileReader(filename);
        Scanner scanner = new Scanner(fr);
        Square c = null;

        ArrayList<JSONObject> JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            squares.add(new Square(st.getDouble("length")));
        }

        JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            prisms.add(new Prism( st.getDouble("height"), st.getDouble("length")));
        }

        scanner.close();
        fr.close();

        return new Database(squares, prisms);
    }


    public void save(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Square square : squares) {
            try {
                bw.write("Square " + String.valueOf(square.getLength()));
                bw.write(System.lineSeparator());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Prism prism : prisms) {
            try {
                bw.write("Prism " + String.valueOf(prism.getLength()) + " " + String.valueOf(prism.getHeight()));
                bw.write(System.lineSeparator());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.close();
        fw.close();
    }

    public void read(String filename, ArrayList<Square> squares, ArrayList<Prism> prisms) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(filename));
        while (scanner.hasNextLine())
        {
            String[] line = scanner.nextLine().split(" ");
            if(line[0].matches("Square"))
            {
                squares.add(new Square(Double.valueOf(line[1])));
            }
            else if(line[0] .matches("Prism") )
            {
                prisms.add(new Prism(Double.valueOf(line[1]),Double.valueOf(line[2])));
            }
            else
                System.out.println("ERROR");

        }
    }
}
