package africa.jopen.utils;

import africa.jopen.Application;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XUtils {

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {

        try {
            URL resource = Application.class.getClassLoader().getResource(filename);
            byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
            return new String(bytes);
        } catch (IOException | URISyntaxException|java.nio.file.FileSystemNotFoundException e) {
            e.printStackTrace();
            System.err.println("Access Error Occurred,its alright access from native image module perspective !");
            String moduleName = "java.base";
            String resourcePath = "/" + filename;
            Module resource1 = ModuleLayer.boot().findModule(moduleName).get();
            InputStream ins = resource1.getResourceAsStream(resourcePath);
            if (ins == null) {
                System.out.println(" now trying to load from Class");
                ins = Application.class.getResourceAsStream(resourcePath);
            }
            if (ins != null) {
                StringBuilder sb = new StringBuilder();
                for (int ch; (ch = ins.read()) != -1; ) {
                    sb.append((char) ch);
                }
                System.out.println(" sb"+sb);
                return sb.toString();
            }
        }
        return null;
    }

    private static String readFileFromResourcesLargeFiles(String fileName) {
        URL resource = Application.class.getClassLoader().getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException("file is not found!");

        StringBuilder fileContent = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(resource.getFile()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }


    /**
     * Execute a bash command. We can handle complex bash commands including
     * multiple executions (; | && ||), quotes, expansions ($), escapes (\), e.g.:
     * "cd /abc/def; mv ghi 'older ghi '$(whoami)"
     *
     * @param command
     * @return true if bash got started, but your command may have failed.
     */
    public static boolean executeBashCommand(String command) {
        boolean success = false;
        System.out.println("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        // Use bash -c so we can handle things like multi commands separated by ; and
        // things like quotes, $, |, and \. My tests show that command comes as
        // one argument to bash, so we do not need to quote it to make it one thing.
        // Also, exec may object if it does not have an executable file as the first thing,
        // so having bash here makes it happy provided bash is installed and in path.
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }

            b.close();
            success = true;
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }
}
