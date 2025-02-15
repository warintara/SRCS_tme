package srcs.persistance;

import java.io.OutputStream;
import java.io.IOException;

public interface Sauvegardable {
	void save(OutputStream out) throws IOException;
}
