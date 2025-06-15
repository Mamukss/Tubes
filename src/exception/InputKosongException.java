package exception;

public class InputKosongException extends Exception{
    public String message(){
        return "FIELD INPUT TIDAK BOLEH KOSONG !";
    }
}
