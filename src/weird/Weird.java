package weird;

public class Weird {
    public static void main(String[] args){
        int i = 0;
        while(true){
            try{
                System.out.println(i++);
                break;
            } finally{
                if (i < 5) continue;
            }
        }
    }
}
