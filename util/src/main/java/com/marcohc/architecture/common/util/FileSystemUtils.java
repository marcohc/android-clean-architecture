/*
 * Copyright (C) 2016 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcohc.architecture.common.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * File system methods
 */
public class FileSystemUtils {

    public static List<File> getFilesFromLocalFolder(File folder) {
        List<File> filesList = new ArrayList<>();
        if (!folder.isDirectory()) {
            return filesList;
        }
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File[] filesArray = folder.listFiles();
        filesList.addAll(Arrays.asList(filesArray));
        return filesList;
    }

    public static boolean copyFileTo(FileInputStream originFile, File destinationFile) {

        boolean exportDone = true;

        try {
            File sd = Environment.getExternalStorageDirectory();

            if (sd.canWrite()) {

                // Create parent directories
                destinationFile.getParentFile().mkdirs();

                FileChannel src = originFile.getChannel();
                FileChannel dst = new FileOutputStream(destinationFile).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            exportDone = false;
        }
        return exportDone;
    }

    public static boolean copyFileTo(File originFile, File destinationFile) {
        try {
            return copyFileTo(new FileInputStream(originFile), destinationFile);
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static void deleteFolderContent(File directoryToBeDeleted, int daysToKeepFiles, boolean isRecursive) {

        Calendar limitCalendar = Calendar.getInstance();
        limitCalendar.add(Calendar.DAY_OF_YEAR, -1 * daysToKeepFiles);

        if (directoryToBeDeleted.isDirectory()) {
            String[] children = directoryToBeDeleted.list();
            for (int i = 0; i < children.length; i++) {

                File file = new File(directoryToBeDeleted, children[i]);

                if (isRecursive && file.isDirectory()) {
                    deleteFolderContent(directoryToBeDeleted, daysToKeepFiles, isRecursive);
                } else {

                    Calendar fileCalendar = Calendar.getInstance();
                    fileCalendar.setTimeInMillis(file.lastModified());

                    if (daysToKeepFiles != -1) {
                        if (limitCalendar.after(fileCalendar)) {
                            file.delete();
                        }
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }

    public static String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1, path.length());
    }

    public static String getFolderNameFromFilePath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf(getFileNameFromPath(filePath)));
    }
}
