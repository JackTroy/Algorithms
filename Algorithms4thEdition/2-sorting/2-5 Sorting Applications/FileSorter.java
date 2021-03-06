import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class FileSorter {

	public static void main(String[] args) {
		File directory = new File(args[0]);
        if (!directory.exists()) {
            StdOut.println(args[0] + " does not exist");
            return;
        }
        if (!directory.isDirectory()) {
            StdOut.println(args[0] + " is not a directory");
            return;
        }
        
        File[] files=directory.listFiles();
        if (files == null) {
            StdOut.println("could not read files");
            return;
        }
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++) {
			StdOut.println(files[i].getName());
		}
	}

}
