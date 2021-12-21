package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * �������˴���ServerSocket��ѭ������accept()�ȴ��ͻ������ӡ�
 * �ͻ��˴���һ��socket������ͷ����������ӡ�
 * �������˽��ܿͻ������󣬴���socket��ÿͻ�����ר�����ӡ�
 * �������ӵ�����socket��һ���������߳��϶Ի���
 * �������˼����ȴ��µ����ӡ�
 * */
public class Server {
	static int PORT = 3044;
	
	@SuppressWarnings({ "resource", "static-access" })
	public static void main(String[] args) throws Exception {
		// create socket, bind port
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		// keep listening
		System.out.println("waiting ...");
		
		while (true) {
			Socket socket = serverSocket.accept();
			// display info
			System.out.println("Client: "
			+ socket.getInetAddress().getLocalHost()
			+ " connected to server");
			
			new Thread(()->{
				// get input
				try {
					HttpRequest httpRequest = new HttpRequest();
					httpRequest.ParseHttp(socket.getOutputStream(), socket.getInputStream());
					socket.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
		
		
	}
}
