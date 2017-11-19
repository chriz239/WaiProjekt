package exception;

public class CamImageNotFoundException extends RuntimeException {

	public CamImageNotFoundException() {
		super("CamImage konnte nicht gefunden werden");
	}
}
