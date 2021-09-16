import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import javax.crypto.*;

public class Main {


	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			throw new Exception("incorrect amount of arguments");
		}
		try {
			Integer.parseInt(args[1]);
		} catch (Exception e) {
			throw new Exception("second argument should be an integer");
		}

		var str = args[0];
		var hardness = Integer.valueOf(args[1]);

		if (hardness < 1 || hardness > 3) {
			throw new Exception("hardness should be 1, 2 or 3");
		}

		var cipher = Cipher.getInstance("AES");
		var kgen = KeyGenerator.getInstance("AES");
		kgen.init(128 + 64 * (hardness - 1));
		var key = kgen.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, key);

		var bytes = cipher.doFinal(str.getBytes());
		var file = new File("result.txt");
		var outStream = new FileOutputStream(file);

		var encStr = Arrays.toString(bytes);
		encStr = encStr.replace(",", "")
				.replace("[", "")
				.replace("]", "");
		outStream.write(encStr.getBytes());
	}
}
