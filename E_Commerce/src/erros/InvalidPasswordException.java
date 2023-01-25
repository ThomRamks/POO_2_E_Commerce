package erros;

public class InvalidPasswordException extends RuntimeException {
    /**
     * Deixei a IDE resolver isso apenas para tirar o alerta.
     */
    private static final long serialVersionUID = 1L;

    public String getMessage() {
        return "\n >> Senha inválida! << \n";
    }
}
