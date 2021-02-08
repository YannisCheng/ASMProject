package com.cwj.asm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutPutFile {

    public static void outBytes(byte[] bytes, String fileName) throws IOException {
        File file = new File("/Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/AsmProject/src/main/java/com/cwj/asm/out_file/" + fileName + ".class");
        if (file.exists()) {
            if (file.delete()) {
                boolean newFile = file.createNewFile();
            }
        } else {
            boolean newFile = file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

}
