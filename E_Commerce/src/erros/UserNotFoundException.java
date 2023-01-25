package erros;

public class UserNotFoundException extends RuntimeException{
    /**
     * Deixei a IDE resolver isso apenas para tirar o alerta.
     */
    private static final long serialVersionUID = 1L;

    public String getMessage() {
        return "\n >> Usuário não encontrado! << \n";
    }
}