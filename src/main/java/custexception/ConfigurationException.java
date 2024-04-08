package custexception;

public class ConfigurationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigurationException(String errorMessage) {
		super(errorMessage);
	}
	
	public ConfigurationException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}

}
