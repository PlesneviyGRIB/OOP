package Task_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ForTest_NonBlockingClient {
    private static BufferedReader console = null;
    private final SocketChannel sc;

    public ForTest_NonBlockingClient(int port, String nodeName) throws Exception {

        Selector selector = Selector.open();

        sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(InetAddress.getByName(nodeName), port));
        sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
                if (selector.select() > 0) {
                    if (processReadySet(selector.selectedKeys())) break;
                }
            TimeUnit.MILLISECONDS.sleep(100);
        }
        sc.close();
    }
    private int cnt =0;

    private Boolean processReadySet(Set<SelectionKey> readySet) throws Exception {
        SelectionKey key = null;

        Iterator<SelectionKey> iterator = readySet.iterator();

        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();
        }

        if (key.isConnectable())
            if (!processConnect(key)) return true;

        if (key.isReadable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            sc.read(bb);

            String message = new String(bb.array()).trim();
            System.out.println("Message received from Server: " + message);
        }


        if (key.isWritable()) {
            System.out.print("Type a message:");
            String msg = console.readLine();

            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer bb = ByteBuffer.wrap(msg.getBytes());
            sc.write(bb);
        }

        return false;
    }
    private Boolean processConnect(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            while (sc.isConnectionPending())
                sc.finishConnect();
        } catch (IOException e) {
            key.cancel();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        String host = "localhost";
        int port = 1238;

        new ForTest_NonBlockingClient(port, host);
    }
}