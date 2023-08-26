import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCmdCommand {
    public static void main(String[] args) {
        String cmdCommand = "dir"; // Replace with the CMD command you want to run

        runCmdCommand(cmdCommand);
    }

    public static void runCmdCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Command executed, exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
