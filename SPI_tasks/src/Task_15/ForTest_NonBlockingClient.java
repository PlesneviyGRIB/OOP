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
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ForTest_NonBlockingClient {
    private final SocketChannel sc;
    private final LinkedBlockingQueue<String> input = new LinkedBlockingQueue<>();

    public ForTest_NonBlockingClient(int port, String nodeName) throws Exception {

        Selector selector = Selector.open();

        sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(InetAddress.getByName(nodeName), port));
        sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        var executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            var console = new BufferedReader(new InputStreamReader(System.in));

            while (!Thread.currentThread().isInterrupted()){
                try {
                    input.put(console.readLine());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executorService.shutdown();

        while (true) {
                if (selector.select() > 0) {
                    if (processReadySet(selector.selectedKeys())) break;
                }
        }
        sc.close();
    }

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

            String message = Utils.unwrapMessage(new String(bb.array()).trim());
            System.out.println("SERVER: " + message);
        }

        if (key.isWritable()) {
            String message = input.poll();
            if(message != null) {
                SocketChannel sc = (SocketChannel) key.channel();
                message = Utils.wrapMessage(message);
                ByteBuffer bb = ByteBuffer.wrap(message.getBytes());
                sc.write(bb);
            }
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