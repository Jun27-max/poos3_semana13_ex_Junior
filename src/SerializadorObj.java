import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializadorObj {
	
	private File file;

	public SerializadorObj(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}
	
	public void serializar(Object obj) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
		os.writeObject(obj);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Object desseralizar() {
		try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))){
			return is.readObject();
		}
		catch(IOException | ClassNotFoundException  e) {
			throw new RuntimeException(e);
		}
	}



}
