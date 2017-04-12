import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatClient {

	public static void main(String[] args) {
		try {
			
			/**
			 * After username is written, enter must be pressed more than once.
			 */
			
			Socket socket = new Socket("codebank.xyz", 38001);
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			PrintStream out = new PrintStream(socket.getOutputStream(), true,
					"UTF-8");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			out.print(input.readLine());
			
			Runnable r = new Runnable() {

				public void run() {
					try {
						String text;
						while ((text = br.readLine()) != null) {
							System.out.print(text);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};

			new Thread(r).start();
			
			String message;
			while (!(message = input.readLine()).equalsIgnoreCase("exit")) {
				out.println(message);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
