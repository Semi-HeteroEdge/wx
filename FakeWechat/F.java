import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class F {
	public static ArrayList<Socket> list = new ArrayList<Socket>();
	public static Executor ex = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		ServerSocket soo = null;
		Socket so = null;
		try {
			soo = new ServerSocket(61666);
			String chuangjian;
			chuangjian = "�����������ɹ�...";
			System.out.println(chuangjian);

			while (true) {
				so = soo.accept();
				list.add(so);
				ex.execute(new Jie(so));
			}
		} catch (IOException e) {
			System.out.println("����������ʧ��...");
			try {
				soo.close();
				so.close();
			} catch (IOException e1) {
			}

		}
	}
}

class Jie implements Runnable {// ���ڽ�����Ϣ
	private Socket so;

	Jie(Socket so) {
		this.so = so;
		String lianjie;
		lianjie = "������ �û�IP��" + so.getInetAddress().getHostAddress() + "��ǰ��������" + F.list.size();
		System.out.println(lianjie);
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(so.getInputStream(), "UTF-8"));
			String s;
			while ((s = in.readLine()) != null) {
				new Thread(new Fa(s, so)).start();
			}
			F.list.remove(so);
			String tuichu;
			tuichu = "���˳� �û�IP��" + so.getInetAddress().getHostAddress() + "��ǰ��������" + F.list.size();
			System.out.println(tuichu);
			in.close();
			so.close();
		} catch (IOException e) {
			F.list.remove(so);
			String tuichu;
			tuichu = "���˳� �û�IP��" + so.getInetAddress().getHostAddress() + "��ǰ��������" + F.list.size();
			System.out.println(tuichu);
			try {
				in.close();
				so.close();
			} catch (IOException e1) {
			}
		}
	}
}

class Fa implements Runnable {// ������Ϣ
	private String s;
	private Socket ss;

	Fa(String s, Socket so) {
		this.s = s;
		ss = so;
	}

	public void run() {
		Socket so;

		try {
			for (int i = 0; i < F.list.size(); i++) {
				so = F.list.get(i);
				if (so == ss)
					continue;
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(so.getOutputStream(), "UTF-8"));
				out.write(s + "\r\n");
				out.flush();
			}
		} catch (IOException e) {
			System.out.println("�쳣");

		}

	}

}