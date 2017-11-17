package exception;

public class CamNotFoundException extends RuntimeException  {
	public CamNotFoundException(Long id) {
		super("Cam mit der Id " + id + " konnte nicht gefunden werden!");
	}
}
