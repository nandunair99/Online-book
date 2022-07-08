package com.narola.bookmyseat.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.narola.bookmyseat.exception.ApplicationException;

public class ImageUtility {
	public static String inputstreamToImage(InputStream inputstream) throws ApplicationException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			final byte[] bytes = new byte[1024];
			int read = 0;
			while ((read = inputstream.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			return Base64.getEncoder().encodeToString(os.toByteArray());
		} catch (IOException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}
}
