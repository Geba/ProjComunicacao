package erros;

@SuppressWarnings("serial")
public class UsuarioNaoEncontradoException extends Exception {
	
	private static long ID;
	
	public UsuarioNaoEncontradoException(){
		super("There is no user with id "+ID+".");
	}
	public UsuarioNaoEncontradoException(long ID){
		super("There is no user with id "+ID+".");
		UsuarioNaoEncontradoException.ID = ID;
	}
}
