package net.ion.util;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils {

	public static boolean forceMkdirQuietly(File directory) {
		if (!(directory.exists())) {
			synchronized (Shell.class) {
				if (!(directory.exists())) {
					return directory.mkdirs();
				}
			}
		}
		return (!(directory.isFile()));
	}

	private static final class DirFilter implements FileFilter {
		public boolean accept(File file) {
			return file.isDirectory();
		}
	}

}