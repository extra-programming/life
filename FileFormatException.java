public class FileFormatException extends RuntimeException{
    FileFormatException(Throwable t){
        super(t);
    }
    FileFormatException(String s){
        super(s);
    }
    FileFormatException(){
        super();
    }
}