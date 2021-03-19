package com.github.pulsebeat02.utility;

import java.io.File;

public class FileUtilities {

    public static File getFile(final String file) {
        return new File(String.valueOf(FileUtilities.class.getClassLoader().getResource(file)));
    }

}
