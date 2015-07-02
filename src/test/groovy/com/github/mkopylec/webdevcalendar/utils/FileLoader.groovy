package com.github.mkopylec.webdevcalendar.utils

class FileLoader {

    static String loadJsonFile(String jsonFile) {
        return FileLoader.class.classLoader.getResource(jsonFile).text
    }

    private FileLoader() {
    }
}
