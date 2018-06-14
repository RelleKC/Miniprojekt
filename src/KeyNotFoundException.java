/**
 * An exception that is raised when a key is not found.
 * 
 * @author Marcus
 */
public class KeyNotFoundException extends Exception {
	
	/**
	 * The serialization version.
	 */
	private static final long serialVersionUID = 3795850112973739657L;

	/**
	 * Creates a new key not found exception.
	 * 
	 * @param message The message describing the error.
	 */

	public KeyNotFoundException(String message) {
		super(message);
	}
}
