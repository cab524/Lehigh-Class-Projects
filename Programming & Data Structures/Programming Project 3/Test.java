import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test{
    public static void main(String[]args){
        HashMapSC<String,String> hashSC = new HashMapSC<>(100000);
        HashMapLP<String,String> hashLP = new HashMapLP<>(100000);
        HashMapSC<String,String> hashSCML = new HashMapSC<>(100000);
        HashMapLP<String,String> hashLPML = new HashMapLP<>(100000);
        TreeMap<String,String> tree = new TreeMap<>(new StringComparator());
        ArrayList<HashMapEntry<String,String>> emails = new ArrayList<>();
        ArrayList<HashMapEntry<String,String>> mailingList = new ArrayList<>();
        readFile(emails, "emails.txt");
        readFile(mailingList, "mailingList.txt");
        for(HashMapEntry<String,String> em: emails){
            hashSC.put(em.getKey(), em.getValue()); //store words and its definition
            hashLP.put(em.getKey(), em.getValue());
            tree.add(em.getKey(), em.getValue());

            hashSCML.put(em.getKey(), em.getValue()); //store words and its definition
            hashLPML.put(em.getKey(), em.getValue());

        }

        //TESTING GET
        // System.out.println(hashSC.size());
        // System.out.println(hashLP.size());
        // System.out.println(tree.size());
        System.out.println("Testing get()");
        int totalHashSC = 0, totalHashLP = 0, totalTree = 0;
        //System.out.printf("%-20s\t%-15s\t%-15s\n", "Username", "HashMapSC", "HashMapLP");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Username", "HashMapSC", "HashMapLP", "TreeMap");
        for(int i = 0; i < 100; i++){
            String ml = mailingList.get(i).getKey();

            hashSC.get(ml);
            hashLP.get(ml);
            tree.get(ml);
            totalHashSC += HashMapSC.iterations;
            totalHashLP += HashMapLP.iterations;
            totalTree += tree.iterations;
            if(i % 5 == 0){
                //System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", ml, hashSC.iterations, hashMapLP.iterations, tree.iterations);
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", ml, HashMapSC.iterations, HashMapLP.iterations, tree.iterations);
            }
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalHashSC/100, totalHashLP/100, totalTree/100);

        
        //TESTING SORT
        System.out.println("\n\nTesting sort() - number of iterations");
        hashSC.sortedKeys(new StringComparator());
        System.out.printf("%-20s\t%-15d\n", "HashMapSC", HashMapSC.iterations);
        hashLP.sortedKeys(new StringComparator());
        System.out.printf("%-20s\t%-15d\n", "HashMapLP", HashMapLP.iterations);
        tree.sortedKeys();
        System.out.printf("%-20s\t%-15d\n\n", "TreeMap", tree.iterations);
        
        


        //TESTING PUT  
        System.out.println("\nTesting put() - number of collisions");
        System.out.printf("%-20s\t%-15s\t%-15s\n", "Size", "HashMapSC", "HashMapLP");

        for(int size = 50000; size < 550000; size+= 50000){
            HashMapSC<String,String> hashSCK = new HashMapSC<>(size);
            HashMapLP<String,String> hashLPK = new HashMapLP<>(size);
            for(int i = 0; i < emails.size(); i++){
                HashMapEntry<String,String> em  = emails.get(i);
                hashSCK.put(em.getKey(), em.getValue()); //store words and its definition
                hashLPK.put(em.getKey(), em.getValue());
                
            }
            System.out.printf("%-20d\t%-15d\t%-15d\n", size, hashSCK.collisions, hashLPK.collisions);
            
        }

    }

    public static void readFile(ArrayList<HashMapEntry<String, String>> al, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split(" ");
                String email = items[0];
                String password = items[1];
                HashMapEntry<String,String> pair = new HashMapEntry<>(email, password);
                al.add(pair);

            }
            read.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}